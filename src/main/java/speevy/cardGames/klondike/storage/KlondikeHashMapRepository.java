package speevy.cardGames.klondike.storage;

import java.util.*;

import org.springframework.stereotype.Component;

import speevy.cardGames.klondike.Klondike;

@Component
public class KlondikeHashMapRepository implements KlondikeRepository {
	
	final Map<String, Klondike> repository = new HashMap<>();
	
	@Override
	public synchronized String save(Klondike klondike) {
		String id = UUID.randomUUID().toString();
		repository.put(id, klondike);
		return id;
	}

	@Override
	public synchronized void update(String id, Klondike klondike) {
		repository.put(id, klondike);
	}

	@Override
	public synchronized Optional<Klondike> get(String id) {
		return Optional.ofNullable(repository.get(id));
	}

	@Override
	public synchronized Optional<Klondike> delete(String id) {
		return  Optional.ofNullable(repository.remove(id));
	}

}
