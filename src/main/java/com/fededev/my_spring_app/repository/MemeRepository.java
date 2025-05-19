package com.fededev.my_spring_app.repository;

import com.fededev.my_spring_app.model.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Long> {
    // Métodos personalizados si es necesario

    List<Meme> findByUserId(Long userId);
}

