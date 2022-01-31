package speevy.cardGames.klondike.storage;

import org.junit.jupiter.api.Test;

public class KlondikeHashMapRepositoryTest extends KlondikeRepositoryTestBase {

	@Test
	void crudTest() {
		crudTest(new KlondikeHashMapRepository());
	}
}
