package com.fededev.my_spring_app.repository;

import com.fededev.my_spring_app.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    // Métodos personalizados si es necesario
    long countByMemeIdAndVoteType(Long memeId, String voteType);
}
