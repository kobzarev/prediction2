package com.prediction2.dto;

import com.prediction2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String nickname;

    private UserDTO(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getId(), user.getFirstName());
    }
}
