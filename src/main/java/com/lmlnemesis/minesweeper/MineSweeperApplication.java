package com.lmlnemesis.minesweeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories("com.lmlnemesis.minesweeper.repository.*")
@EntityScan("com.lmlnemesis.minesweeper.model.*")
@SpringBootApplication
public class MineSweeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MineSweeperApplication.class, args);
	}

}
