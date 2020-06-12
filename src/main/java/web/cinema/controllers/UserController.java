package web.cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.controllers.mappers.UserMapper;
import web.cinema.model.dto.UserResponseDto;
import web.cinema.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto get(@RequestParam String email) {
        return userMapper.convertUserToDto(userService.findByEmail(email));
    }

    @RequestMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(userMapper::convertUserToDto)
                .collect(Collectors.toList());
    }
}
