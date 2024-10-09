package com.example.learningSpringboot.journalApp;

import com.example.learningSpringboot.journalApp.repository.JournalEntryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

}
