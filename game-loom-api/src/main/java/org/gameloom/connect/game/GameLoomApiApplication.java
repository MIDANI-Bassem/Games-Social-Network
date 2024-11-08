package org.gameloom.connect.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class GameLoomApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameLoomApiApplication.class, args);
	}

}
