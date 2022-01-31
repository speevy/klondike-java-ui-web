package speevy.cardGames.klondike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"speevy.cardGames"})
public class KlondikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KlondikeApplication.class, args);
	}

}
