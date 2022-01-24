package speevy.cardGames;

import lombok.*;

@AllArgsConstructor
@Getter
public enum AmericanCardSuit implements CardSuit {
    CLUBS ("CLUBS", "♣"),
    DIAMONDS ("DIAMONDS", "♦"),
    HEARTS ("HEARTS", "♥"),
    SPADES ("SPADES", "♤");
	private String name;
	private String symbol;
	
}
