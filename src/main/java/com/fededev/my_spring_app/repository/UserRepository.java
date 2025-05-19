package com.fededev.my_spring_app.repository;

import com.fededev.my_spring_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Aquí podrías agregar métodos personalizados si lo necesitas
    User findByEmail(String email);

}

