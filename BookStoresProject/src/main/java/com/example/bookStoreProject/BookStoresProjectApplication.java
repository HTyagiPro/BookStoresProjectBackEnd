package com.example.bookStoreProject;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.bookStoreProject.entity.Book;
import com.example.bookStoreProject.repository.BookRepository;

@SpringBootApplication
public class BookStoresProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoresProjectApplication.class, args);
		
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
