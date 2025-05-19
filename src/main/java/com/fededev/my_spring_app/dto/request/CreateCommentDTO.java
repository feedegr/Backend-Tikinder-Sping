package com.fededev.my_spring_app.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateCommentDTO {

    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Meme ID is required")
    private Long memeId;


}