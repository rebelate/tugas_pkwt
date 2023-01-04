package dev.server.controller;

import dev.server.payload.Response;
import dev.server.dto.UserDto;
import dev.server.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    Response Get() {
        return userService.getUserList();
    }

    @PostMapping
    Response Post(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/{userId}")
    Response Get(@PathVariable("userId") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{userId}")
    Response Put(@PathVariable("userId") Long id, @RequestBody UserDto userDto) {
        return userService.updateUserById(id, userDto);
    }

    @DeleteMapping("/{userId}")
    Response Delete(@PathVariable("userId") Long id) {
        return userService.deleteUserById(id);
    }

}
