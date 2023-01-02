package speevy.cardGames.klondike;

import org.springframework.aot.hint.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.lang.Nullable;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import lombok.SneakyThrows;
import speevy.cardGames.*;

@SpringBootApplication
@ComponentScan(basePackages = {"speevy.cardGames"})
public class KlondikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KlondikeApplication.class, args);
	}

	@Bean
	Cards cards() {
		return new AmericanCards();
	}
	
	static class Registrar implements RuntimeHintsRegistrar {

		@Override
		@SneakyThrows
		public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
			hints.reflection().registerField(PropertyNamingStrategies.class.getField("SNAKE_CASE"));
		}
	}
}
