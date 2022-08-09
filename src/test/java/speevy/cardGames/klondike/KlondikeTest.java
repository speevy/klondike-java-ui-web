package speevy.cardGames.klondike;

import static speevy.cardGames.AmericanCardRank.*;
import static speevy.cardGames.AmericanCardSuit.*;

import java.util.List;

import speevy.cardGames.Card;

@SuppressWarnings("deprecation")
public class KlondikeTest {

	public static Klondike createTestKlondike() {
		return new Klondike(
				new Deck(List.of(new Card(HEARTS, ACE), new Card(DIAMONDS, ACE))), 
				List.of(new Pile(), new Pile(), new Pile(), new Pile()),
				List.of (
					new Foundation(List.of(new Card(CLUBS, ACE)), List.of(new Card(CLUBS, TEN), new Card(HEARTS, NINE))),
					new Foundation(List.of(), List.of(new Card(DIAMONDS, JACK))),
					new Foundation(List.of(), List.of(new Card(DIAMONDS, KING))),
					new Foundation(List.of(), List.of(new Card(SPADES, ACE))),
					new Foundation(List.of(), List.of()),
					new Foundation(List.of(), List.of()),
					new Foundation(List.of(), List.of())
				));
	}
}
