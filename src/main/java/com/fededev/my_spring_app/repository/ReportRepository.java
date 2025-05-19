package com.fededev.my_spring_app.repository;

import com.fededev.my_spring_app.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // Métodos personalizados si es necesario
    List<Report> findByMemeId(Long memeId);
}
