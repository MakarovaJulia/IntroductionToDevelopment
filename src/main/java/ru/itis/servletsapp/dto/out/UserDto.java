package ru.itis.servletsapp.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.itis.servletsapp.model.User;

@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Long avatarId;
    private Long backId;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .avatarId(user.getAvatarId())
                .backId(user.getBackId())
                .build();
    }
}