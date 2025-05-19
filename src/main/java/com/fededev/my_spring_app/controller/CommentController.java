package com.fededev.my_spring_app.controller;

import com.fededev.my_spring_app.dto.request.CreateCommentDTO;
import com.fededev.my_spring_app.dto.response.CommentDTO;
import com.fededev.my_spring_app.model.Comment;
import com.fededev.my_spring_app.service.CommentService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CreateCommentDTO dto) {
        Comment comment = commentService.createComment(dto); // el entity creado y guardado
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommentDTO(comment)); // lo transformás en DTO de respuesta
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments = commentService.getAllComments()
                .stream()
                .map(CommentDTO::new)
                .toList();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(value -> ResponseEntity.ok(new CommentDTO(value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
