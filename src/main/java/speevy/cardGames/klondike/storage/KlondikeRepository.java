package speevy.cardGames.klondike.storage;

import java.util.Optional;

import speevy.cardGames.klondike.Klondike;

/**
 *  Implementations of storage systems for Klondike games 
 *  should implement this trait.
 */
public interface KlondikeRepository {

    /** 
     * Saves the current state of a new game
     * @return the created id for it
     */
    String save(Klondike klondike);

    /**
     *  Saves the current state of an already saved game
     */
    void update(String id, Klondike klondike);

    /** 
     * Gets a saved game by it's id.
     */
    Optional<Klondike> get(String id);

    /**
     * Removes a saved game from the repository by it's id.
     * @return the removed element
     */
    Optional<Klondike> delete(String id);
}

	