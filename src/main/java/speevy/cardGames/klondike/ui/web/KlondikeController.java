package speevy.cardGames.klondike.ui.web;

import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import speevy.cardGames.klondike.Klondike.*;
import speevy.cardGames.klondike.KlondikeService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Log4j2
public class KlondikeController {

	private final KlondikeService service;
	
	private record Action(String action, String from, String to, Optional<Integer> number) {};
	
	@PostMapping("/game")
	public ResponseEntity<Void> createGame() throws URISyntaxException {
		String id = service.createGame();
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + id).build().toUri())
				.header("Access-Control-Expose-Headers", "Location")
				.build();
	}
	
	@GetMapping("/game/{id}")
	public ResponseEntity<KlondikeStatus> status(@PathVariable String id) {
		return optionalToResponseEntity(service.getStatus(id));
	}
	
	@PutMapping(value="/game/{id}")
	public ResponseEntity<KlondikeStatus> action (@PathVariable String id, @RequestBody Action action) {
		return optionalToResponseEntity(switch(action.action()) {
		case "take" -> service.take(id);
		case "undo" -> service.undo(id);
		case "move" -> service.move(id, parseCardHolder(action.from()), parseCardHolder(action.to()), action.number().orElse(1));
		default -> throw new IllegalArgumentException("Unexpected value: " + action.action());
		});
	}
	
	@DeleteMapping(value="/game/{id}") 
	public ResponseEntity<Void> deleteGame(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	public CardHolder parseCardHolder(String str) {
		if ("d".equals(str)) {
			return new CardHolder(CardHolderType.DECK);
		}
		int index = Integer.parseInt(str.substring(1));
		return new CardHolder(switch(str.charAt(0)) {
		case 'p' -> CardHolderType.PILE;
		case 'f' -> CardHolderType.FOUNDATION;
		default -> throw new IllegalArgumentException("Unexpected value: " + str.charAt(0));
		}, index - 1);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> illegalArgument(IllegalArgumentException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<String> illegalState(IllegalStateException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}

	<T> ResponseEntity<T> optionalToResponseEntity(Optional<T> optional) {
		return optional.map(o -> ResponseEntity.ok(o)).orElse(ResponseEntity.notFound().build());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> illegalArgument(Exception e) {
		log.error("Unexpected internal error", e);
		return ResponseEntity.internalServerError().body("Unexpected internal error");
	}
	
}
