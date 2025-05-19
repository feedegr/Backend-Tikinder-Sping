package com.fededev.my_spring_app.controller;

import com.fededev.my_spring_app.dto.response.CommentDTO;
import com.fededev.my_spring_app.dto.response.UserDTO;
import com.fededev.my_spring_app.exception.ResourceNotFoundException;
import com.fededev.my_spring_app.model.User;
import com.fededev.my_spring_app.service.CommentService;
import com.fededev.my_spring_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public UserController(UserService userService, CommentService commentService) {
        this.userService = userService;
        this.commentService = commentService;
    }

   @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(new UserDTO(user));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByUserId(@PathVariable Long id) {
        userService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        List<CommentDTO> comments = commentService.getCommentsByUserId(id)
                .stream()
                .map(CommentDTO::new)
                .toList();
        return ResponseEntity.ok(comments);

    }



    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity.ok(userDTOs);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
