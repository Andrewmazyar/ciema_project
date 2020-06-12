package web.cinema.controllers.mappers;

import org.springframework.stereotype.Component;
import web.cinema.model.User;
import web.cinema.model.dto.UserResponseDto;

@Component
public class UserMapper {

    public UserResponseDto convertUserToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setUserEmail(user.getEmail());
        dto.setUserId(user.getId());
        return dto;
    }
}
