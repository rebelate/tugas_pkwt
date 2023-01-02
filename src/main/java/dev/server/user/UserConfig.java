package dev.server.user;

import dev.server.book.Book;
import dev.server.book.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashSet;

@Configuration
@Profile("dev")
public class UserConfig {
    Logger logger = LoggerFactory.getLogger("User Config");

    @Bean
    CommandLineRunner UserConfigCLR(UserRepository userRepository, BookRepository bookRepository) {
        return _args -> {
            var borrowed = new HashSet<Book>();
            borrowed.add(bookRepository.findById(1L).get());
            borrowed.add(bookRepository.findById(2L).get());
            User user = new User("admin", "admin", "admin@example.com", "12345", borrowed);
            userRepository.save(user);
            logger.info("Config executed");
        };
    }
}
