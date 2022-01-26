package speevy.cardGames;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

public class AmericanCardsTest {
	@Test
	void getAll() {
		List<AmericanCardRank> ranks = List.of(AmericanCardRank.values());
		List<AmericanCardSuit> suits = List.of(AmericanCardSuit.values());
		
		Collection<Card> allCards = new AmericanCards().getAll();

		//Check total number of cards
		assertEquals(ranks.size() * suits.size(), allCards.size());
		
		//Check cards are valid
		allCards.forEach(card -> {
			assertTrue(ranks.contains(card.rank()));
			assertTrue(suits.contains(card.suit()));
		});
		
		//Check for duplicates
		assertEquals(ranks.size() * suits.size(), new HashSet<>(allCards).size());
	}

}
