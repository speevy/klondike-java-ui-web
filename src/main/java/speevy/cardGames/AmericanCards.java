package speevy.cardGames;

import java.util.*;

public class AmericanCards implements Cards {
	
	@Override
	public boolean isDescendingAndAlternatingColors(Card a, Card b) {
		
		if ( !(	(a.rank() instanceof AmericanCardRank rank1) && 
				(a.suit() instanceof AmericanCardSuit suit1) &&
				(b.rank() instanceof AmericanCardRank rank2) && 
				(b.suit() instanceof AmericanCardSuit suit2)
				)) {
			return false;
		}
		
		return rank1.getIndex() == rank2.getIndex() + 1  &&
				switch (suit1) {
				case DIAMONDS, HEARTS ->  switch (suit2) {
					case DIAMONDS, HEARTS -> false;
					default -> true;
					}; 
				default -> switch (suit2) {
					case DIAMONDS, HEARTS -> true;
					default -> false;
					}; 
				};

	}

	@Override
	public Collection<Card> getAll() {
		return Arrays.stream(AmericanCardSuit.values())
				.flatMap(suit -> Arrays.stream(AmericanCardRank.values())
						.map(rank-> new Card(suit, rank)))
				.toList();
	}


}
