package speevy.cardGames;

import static org.junit.jupiter.api.Assertions.*;
import static speevy.cardGames.AmericanCardRank.*;
import static speevy.cardGames.AmericanCardSuit.*;

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

	@Test
	void isDescendingAndAlternatingColors() {
		assertFalse(false);
		
		assertTrue(DescendingAndALternatingCase(DIAMONDS, FIVE, CLUBS, FOUR));
		assertFalse(DescendingAndALternatingCase(DIAMONDS, FIVE, HEARTS, FOUR));
		assertTrue(DescendingAndALternatingCase(DIAMONDS, FIVE, SPADES, FOUR));
		assertFalse(DescendingAndALternatingCase(DIAMONDS, FIVE, DIAMONDS, FOUR));
		assertFalse(DescendingAndALternatingCase(DIAMONDS, FIVE, CLUBS, THREE));
		assertFalse(DescendingAndALternatingCase(DIAMONDS, FIVE, HEARTS, THREE));
		assertFalse(DescendingAndALternatingCase(DIAMONDS, FIVE, SPADES, THREE));
		assertFalse(DescendingAndALternatingCase(DIAMONDS, FIVE, DIAMONDS, THREE));
	}

	private boolean DescendingAndALternatingCase(CardSuit suit1, CardRank rank1, CardSuit suit2, CardRank rank2) {
		return new AmericanCards().isDescendingAndAlternatingColors(new Card(suit1, rank1), new Card (suit2, rank2));
	}
}
