package speevy.cardGames;

import lombok.*;

@AllArgsConstructor
@Getter
public enum AmericanCardSuit implements CardSuit {
    CLUBS ("CLUBS", "♣", "BLACK"),
    DIAMONDS ("DIAMONDS", "♦", "RED"),
    HEARTS ("HEARTS", "♥", "RED"),
    SPADES ("SPADES", "♤", "BLACK");
	
	private String name;
	private String symbol;
	private String groupName;
	
}
