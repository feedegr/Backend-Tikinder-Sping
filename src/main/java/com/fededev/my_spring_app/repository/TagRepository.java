package com.fededev.my_spring_app.repository;

import com.fededev.my_spring_app.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    // Métodos personalizados si es necesario
    List<Tag> findByName(String name);
}
