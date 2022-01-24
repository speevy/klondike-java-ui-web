package speevy.cardGames.klondike;

import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.*;

public class Random100Integers implements ArgumentsProvider {
	@Override
	public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
		final Random random = new Random();
		return Stream.concat(Stream.of(0, 1, 2, 3),	Stream.generate(random::nextInt))
				.map(Arguments::of).limit(100);
	}
}
