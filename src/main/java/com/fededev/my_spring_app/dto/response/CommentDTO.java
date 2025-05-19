package com.fededev.my_spring_app.dto.response;

import com.fededev.my_spring_app.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class CommentDTO {
    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
    //private final long userID;
    private final Long memeId;
    private final UserDTO user;

    //usar userID o el objeto completo

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
       // this.userID = comment.getUser().getId();
        this.user = new UserDTO(comment.getUser());
        this.memeId = comment.getMeme().getId();
    }
}
