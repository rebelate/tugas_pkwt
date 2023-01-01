package dev.server.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.tinylog.Logger;

import java.util.List;

@Configuration
@Profile("dev")
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return _args -> {
            Book webGL = new Book("Khronos", "Khronos", "Advanced Shader WebGL", "");
            Book dilan = new Book("Dilan 1990", "Marijn Haverbeke", "Ipsum", "");
            Book rn = new Book("React Native", "Meta", "Meta", "");
            bookRepository.saveAll(List.of(webGL, dilan, rn));
            Logger.info("Book Config executed");
        };
    }
}
