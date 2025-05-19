package com.fededev.my_spring_app.dto.response;

import com.fededev.my_spring_app.model.User;
import lombok.Getter;

@Getter
public class UserDTO {
    private final Long id;
    private final String username;
    private final String email;


    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
