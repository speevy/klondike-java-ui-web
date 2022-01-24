package speevy.cardGames;

import java.util.Collection;

public interface Cards {
	boolean isDescendingAndAlternatingColors(Card a, Card b);
	Collection<Card> getAll();
}
