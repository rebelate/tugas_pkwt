package dev.server.service;

import dev.server.dto.Response;
import dev.server.dto.UserDto;
import dev.server.entity.User;
import dev.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger("User Service");
    private static final String NOT_EXIST = "User does not exist";
    private static final String CREATE_FAILED = "Failed creating new user";
    private static final String CREATE_SUCCESS = "User created successfully";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response getUserList() {
        return Response.generate(userRepository.findAll());
    }

    @Override
    public Response getUserById(Long userId) {
        var optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        }
        return Response.generate(optionalUser.get());
    }

    @Override
    public Response createUser(UserDto userDto) {
        var errors = new ArrayList<String>();
        var user = new User();
        if (userDto.username() != null
        ) {
            user.setUsername(userDto.username());
        } else errors.add("username");
        if (userDto.name() != null
        ) {
            user.setName(userDto.name());
        } else errors.add("name");
        if (userDto.email() != null
        ) {
            user.setEmail(userDto.email());
        } else errors.add("email");
        if (userDto.password() != null
        ) {
            user.setPassword(userDto.password());
        } else errors.add("password");
        if (errors.isEmpty()) {
            var savedBook = userRepository.save(user);
            logger.info("CREATED NEW USER WITH ID " + savedBook.getId());
            return Response.generate(CREATE_SUCCESS, savedBook);
        } else {
            String parsedErrors
                    = "Need to specify " + errors.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                    .replace(",", ", ");
            return Response.generate(BAD_REQUEST, CREATE_FAILED + ": " + parsedErrors);
        }
    }

    @Override
    public Response updateUserById(Long userId, UserDto userDto) {
        var optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        }
        var user = optionalUser.get();
        if (userDto.username() != null
        ) {
            user.setUsername(userDto.username());
        }
        if (userDto.name() != null
        ) {
            user.setName(userDto.name());
        }
        if (userDto.email() != null
        ) {
            user.setEmail(userDto.email());
        }
        if (userDto.password() != null
        ) {
            user.setPassword(userDto.password());
        }
        logger.info("UPDATED BOOK WITH ID " + user.getId());
        return Response.generate(userRepository.save(user));
    }

    @Override
    public Response deleteUserById(Long userId) {
        var bookExist = userRepository.existsById(userId);
        if (bookExist)
            userRepository.deleteById(userId);
        else
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        logger.info("DELETED USER WITH ID " + userId);
        return Response.generate(String.format("id %s deleted", userId));
    }
}
