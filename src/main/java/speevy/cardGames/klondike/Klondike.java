package speevy.cardGames.klondike;

import java.util.*;

import lombok.extern.log4j.Log4j2;
import speevy.cardGames.*;
import speevy.cardGames.cardContainers.*;
import speevy.cardGames.klondike.Deck.DeckStatus;
import speevy.cardGames.klondike.Foundation.FoundationStatus;
import speevy.cardGames.klondike.Pile.PileStatus;

@Log4j2
public class Klondike {

	private final Deck deck;
	private final List<Pile> piles;
	private final List<Foundation> foundations;
	private final List<Action> actionLog = new ArrayList<>();
	
	private enum ActionType {
		MOVE_CARDS,
		TAKE
	}
	
	private record Action(ActionType type, CardOrigin origin, CardDestination destination, int cards) {
		static Action take() { 
			return new Action(ActionType.TAKE, null, null, 0); 
		}
	}
	
	public Klondike(final Cards cardDeck) {
		final List<Card> cards = new ArrayList<>(cardDeck.getAll());
		Collections.shuffle(cards);
		
		piles = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			piles.add(new Pile());
		}
		
		foundations = new ArrayList<>();
		int j = 0;
		for (int i = 0; i < 7; i++, j += i) {
			foundations.add(new Foundation(cards.subList(j, j + i + 1)));
		}
		
		deck = new Deck(cards.subList(j, cards.size()));
	}
	
	public KlondikeStatus getStatus() {
		return new KlondikeStatus(deck.getStatus(), 
				piles.stream().map(Pile::getStatus).toList(),
				foundations.stream().map(Foundation::getStatus).toList());
	}
	
	public record KlondikeStatus (
			DeckStatus deck, 
			List<PileStatus> piles, 
			List<FoundationStatus> foundations) {}
	
	public enum CardHolderType {
		DECK,
		PILE,
		FOUNDATION
	}
	
	public record CardHolder(CardHolderType type, int index) {
		public CardHolder {
			if (type == null || index < 0 || index >= switch(type) {
					case DECK -> 1;
					case FOUNDATION -> 7;
					case PILE -> 4;
					}) {
				throw new IllegalArgumentException();
			}
		}
		
		public CardHolder(CardHolderType type) { this(type, 0); }
	}

	public void moveCards(final CardHolder from, final CardHolder to, final int number) {
		if (from.equals(to)) {
			throw new IllegalArgumentException("Can't move cards from and to the same cardHolder");
		}
		
		CardOrigin origin = getOrigin(from);
		
		CardDestination destination = getDestination(to); 
		
		Collection<Card> cards = origin.peek(number);
		try {
			destination.poke(cards);
		} catch(RuntimeException e) {
			origin.undoPeek(cards);
			throw e;
		}
		actionLog.add(new Action(ActionType.MOVE_CARDS, origin, destination, number));
		log.debug(() -> "Action: move from " + from + " to " + to + " cards " + cards);
	}

	private CardDestination getDestination(final CardHolder to) {
		return switch(to.type()) {
		case FOUNDATION -> foundations.get(to.index());
		case PILE -> piles.get(to.index());
		default -> throw new IllegalArgumentException("Can't move cards to deck");
		};
	}

	private CardOrigin getOrigin(final CardHolder from) {
		return switch(from.type()) {
		case DECK -> deck;
		case FOUNDATION -> foundations.get(from.index());
		case PILE -> piles.get(from.index());
		};
	}

	/**
	 * Constructor used to prepare test cases, do not use for other purposes.
	 */
	@Deprecated
	Klondike(final Deck deck, final List<Pile> piles, final List<Foundation> foundations) {
		super();
		this.deck = deck;
		this.piles = piles;
		this.foundations = foundations;
	}

	public void toPile(final CardHolder from) {

		final CardOrigin origin = getOrigin(from);		
		final Collection<Card> cards = origin.peek(1);
		
		for (int i = 0; i < piles.size(); i++) {
			try {
				final Pile destination = piles.get(i);
				destination.poke(cards);
				actionLog.add(new Action(ActionType.MOVE_CARDS, origin, destination, 1));
				return;
			} catch (IllegalStateException e) {
				// Do nothing
			}
		}
		
		origin.undoPeek(cards);
		throw new IllegalStateException ("No pile accepted this card");
	}

	public void take() {
		deck.take();
		actionLog.add(Action.take());
		log.debug(() -> "Action: Take");
	}

	public void undo() {
		if (actionLog.isEmpty()) {
			log.warn(() -> "Undo called, but no logged actions found");
			return;
		}
		
		Action action  = actionLog.remove(actionLog.size() - 1);
		
		if (action.type().equals(ActionType.TAKE)) {
			deck.undoTake();
			log.debug(() -> "Undo take");
		} else {
			final Collection<Card> cards = action.destination().undoPoke(action.cards());
			action.origin().undoPeek(cards);
			log.debug(() -> "Undo move from " + getCardHolder(action.origin()) + 
					" to " + getCardHolder(action.destination()) + " cards " + cards);
		}
	}
	
	private CardHolder getCardHolder(Object object) {
		if (object instanceof Deck) {
			return new CardHolder(CardHolderType.DECK);
		}
		
		if (object instanceof Pile p) {
			return new CardHolder(CardHolderType.PILE, piles.indexOf(p));
		}
		
		if (object instanceof Foundation f) {
			return new CardHolder(CardHolderType.FOUNDATION, foundations.indexOf(f));
		}

		throw new IllegalArgumentException();
	}
}
