package com.fededev.my_spring_app.dto.response;

import com.fededev.my_spring_app.model.Meme;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MemeDTO {
    private final Long id;
    private final String title;
    private final String imageUrl;
    private final UserDTO user;

    public MemeDTO(Meme meme) {
        this.id = meme.getId();
        this.title = meme.getTitle();
        this.imageUrl = meme.getImageUrl();
        this.user = new UserDTO(meme.getUser());
    }
}
