package speevy.cardGames.cardContainers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import lombok.*;
import speevy.cardGames.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardContainersTest {
	
	public static void assertPeekOneReturns(CardOrigin origin, Card card) {
		Collection<Card> result = origin.peek(1);
		assertEquals(1, result.size());
		assertEquals(card, result.stream().findFirst().get());
	}
}
