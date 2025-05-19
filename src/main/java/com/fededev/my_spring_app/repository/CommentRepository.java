package com.fededev.my_spring_app.repository;

import com.fededev.my_spring_app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Métodos personalizados si es necesario
    List<Comment> findByMemeId(Long memeId);

    List<Comment> findByUserId(Long userId);
}
