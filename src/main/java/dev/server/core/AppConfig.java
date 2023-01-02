package dev.server.core;

import dev.server.entity.Book;
import dev.server.repository.BookRepository;
import dev.server.entity.Category;
import dev.server.repository.CategoryRepository;
import dev.server.entity.User;
import dev.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashSet;
import java.util.List;

@Configuration
@Profile("dev")
public class AppConfig {
    Logger logger = LoggerFactory.getLogger("App Config");

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, CategoryRepository categoryRepository, BookRepository bookRepository) {
        return _args -> {
            var novel = new Category("novel");
            Category savedNovel = categoryRepository.save(novel);

            Book webGL = new Book("Khronos", "Khronos", "Advanced Shader WebGL", "", savedNovel);
            Book dilan = new Book("Dilan 1990", "Marijn Haverbeke", "Ipsum", "", savedNovel);
            Book rn = new Book("React Native", "Meta", "Meta", "", savedNovel);
            List<Book> savedBooks = bookRepository.saveAll(List.of(webGL, dilan, rn));

            var borrowed = new HashSet<Book>();
            borrowed.add(savedBooks.get(0));
            borrowed.add(savedBooks.get(1));
            User user = new User("admin", "admin", "admin@example.com", "12345", borrowed);
            userRepository.save(user);
            logger.info("Config executed successfully");
        };
    }
}
