package speevy.cardGames.klondike.storage;

import static org.junit.jupiter.api.Assertions.*;

import speevy.cardGames.AmericanCards;
import speevy.cardGames.klondike.Klondike;

/**
 * Tests that should be passed by any implementation of KlondikeRepository
 */
public abstract class KlondikeRepositoryTestBase {
	
	protected void crudTest(KlondikeRepository repo) {
		Klondike k1 = new Klondike(new AmericanCards());
		Klondike k2 = new Klondike(new AmericanCards());
		
		String id1 = repo.save(k1);
		String id2 = repo.save(k2);
		
		assertEquals(k1.getStatus(), repo.get(id1).get().getStatus());
		assertEquals(k2.getStatus(), repo.get(id2).get().getStatus());
		
		Klondike k3 = new Klondike(new AmericanCards());
		repo.update(id1, k3);

		assertEquals(k2.getStatus(), repo.get(id2).get().getStatus());
		assertEquals(k3.getStatus(), repo.get(id1).get().getStatus());
		
		assertTrue(repo.get("invalid id").isEmpty());

		assertEquals(k2.getStatus(), repo.delete(id2).get().getStatus());
		assertTrue(repo.get(id2).isEmpty());
		assertEquals(k3.getStatus(), repo.get(id1).get().getStatus());
	}
}
