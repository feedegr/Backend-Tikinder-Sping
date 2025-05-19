package com.fededev.my_spring_app.service;

import com.fededev.my_spring_app.dto.request.CreateCommentDTO;
import com.fededev.my_spring_app.dto.response.CommentDTO;
import com.fededev.my_spring_app.exception.ResourceNotFoundException;
import com.fededev.my_spring_app.model.Comment;
import com.fededev.my_spring_app.model.Meme;
import com.fededev.my_spring_app.model.User;
import com.fededev.my_spring_app.repository.CommentRepository;
import com.fededev.my_spring_app.repository.MemeRepository;
import com.fededev.my_spring_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemeRepository memeRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Comment> getCommentsByUserId(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return commentRepository.findByUserId(userId);
    }


    public Comment createComment(CreateCommentDTO dto) {
        // Buscar el meme y el user por id
        Meme meme = memeRepository.findById(dto.getMemeId())
                .orElseThrow(() -> new ResourceNotFoundException("Meme not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setMeme(meme);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
