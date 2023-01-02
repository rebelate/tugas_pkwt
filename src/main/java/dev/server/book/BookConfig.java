package dev.server.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
public class BookConfig {
    Logger logger = LoggerFactory.getLogger("Book Config");

    @Bean
    CommandLineRunner BookConfigCLR(BookRepository bookRepository) {
        return _args -> {
            Book webGL = new Book("Khronos", "Khronos", "Advanced Shader WebGL", "");
            Book dilan = new Book("Dilan 1990", "Marijn Haverbeke", "Ipsum", "");
            Book rn = new Book("React Native", "Meta", "Meta", "");
            bookRepository.saveAll(List.of(webGL, dilan, rn));
            logger.info("Config executed");
        };
    }
}
