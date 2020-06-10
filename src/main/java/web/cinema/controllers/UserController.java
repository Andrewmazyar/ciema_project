package web.cinema.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web.cinema.model.User;
import web.cinema.model.dto.UserResponseDto;
import web.cinema.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/byemail")
    public UserResponseDto get(@RequestParam String email) {
        return convertUserToDto(userService.findByEmail(email));
    }

    @RequestMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll()
                .stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto convertUserToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserEmail(user.getEmail());
        dto.setUserId(user.getId());
        return dto;
    }
}
