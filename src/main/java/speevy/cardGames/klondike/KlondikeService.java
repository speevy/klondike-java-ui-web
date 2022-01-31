package speevy.cardGames.klondike;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import speevy.cardGames.Cards;
import speevy.cardGames.klondike.Klondike.*;
import speevy.cardGames.klondike.storage.KlondikeRepository;

@Service
@RequiredArgsConstructor
public class KlondikeService {
	private final KlondikeRepository repo;
	private final Cards cardDeck;
	
	public String createGame() {
		return repo.save(new Klondike(cardDeck));
	}
	
	public Optional<KlondikeStatus> getStatus(String id) {
		return repo.get(id).map(Klondike::getStatus);
	}
	
	private Optional<KlondikeStatus> execute(String id, Consumer<? super Klondike> action) {
		Optional<Klondike> game = repo.get(id);
		
		game.ifPresent(klondike -> {
			action.accept(klondike);
			repo.update(id, klondike);
		});
		
		return game.map(Klondike::getStatus);
	}
	
	public Optional<KlondikeStatus> move(String id, CardHolder from, CardHolder to, int number) {
		return execute (id, klondike -> klondike.moveCards(from, to, number));
	}
	
	public Optional<KlondikeStatus> take(String id) {
		return execute (id, Klondike::take);
	}
	
	public Optional<KlondikeStatus> toPile(String id, CardHolder from) {
		return execute (id, klondike -> klondike.toPile(from));
	}

	public Optional<KlondikeStatus> undo(String id) {
		return execute (id, Klondike::undo);
	}
	
	public Optional<KlondikeStatus> delete(String id) {
		return repo.delete(id).map(Klondike::getStatus);
	}
}
