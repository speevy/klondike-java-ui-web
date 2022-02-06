package speevy.cardGames.klondike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.nativex.hint.*;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@SpringBootApplication
@ComponentScan(basePackages = {"speevy.cardGames"})
@TypeHint(types = {PropertyNamingStrategies.class}, fields = {@FieldHint(name="SNAKE_CASE")})
public class KlondikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KlondikeApplication.class, args);
	}

}
