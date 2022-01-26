package speevy.cardGames;

public interface CardRank {
	String getName();
	boolean isImmediatePreviousOf(CardRank other);
	boolean isImmediateNextOf(CardRank other);
	boolean isFirst();
	boolean isLast();
}
