package dev.server.service;

import dev.server.payload.Response;
import dev.server.dto.UserDto;

public interface IUserService {
    Response getUserList();

    Response getUserById(Long userId);

    Response createUser(UserDto userDto);

    Response updateUserById(Long userId, UserDto userDto);

    Response deleteUserById(Long userId);

    boolean alreadyExist(String name);
}
