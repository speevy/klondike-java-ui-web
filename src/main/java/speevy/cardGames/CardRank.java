package speevy.cardGames;

public interface CardRank {
	String getName();
	boolean isImmediateNextOf(CardRank other);
	boolean isFirst();
	boolean isLast();
}
