package dev.server.core;

import dev.server.entity.Book;
import dev.server.entity.Category;
import dev.server.entity.User;
import dev.server.repository.BookRepository;
import dev.server.repository.CategoryRepository;
import dev.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("dev")
public class AppConfig {
    Logger logger = LoggerFactory.getLogger("App Config");

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, CategoryRepository categoryRepository, BookRepository bookRepository) {
        return _args -> {
            var novel = new Category("novel");
            var cs = new Category("computer science");
            Category savedNovel = categoryRepository.save(novel);
            Category savedCS = categoryRepository.save(cs);

            Book webGL = new Book("Khronos", "Khronos", "Advanced Shader WebGL", "", savedCS);
            Book dilan = new Book("Dilan 1990", "Marijn Haverbeke", "Ipsum", "", savedNovel);
            Book rn = new Book("React Native", "Meta", "Meta", "", savedCS);
            bookRepository.saveAll(List.of(webGL, dilan, rn));

            User user1 = new User("admin", "admin", "admin@example.com", "12345");
            User user2 = new User("foo", "foo", "foo@example.com", "12345");
            User user3 = new User("bar", "bar", "bar@example.com", "12345");
            userRepository.saveAll(List.of(user1, user2, user3));
            logger.info("Config executed successfully");
        };
    }
}
