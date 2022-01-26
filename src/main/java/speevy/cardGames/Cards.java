package speevy.cardGames;

import java.util.Collection;

/**
 * Abstraction of the distinct Card sets than can be used in card games.
 */
public interface Cards {
	Collection<Card> getAll();
}
