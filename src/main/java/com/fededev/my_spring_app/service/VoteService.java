package com.fededev.my_spring_app.service;

import com.fededev.my_spring_app.model.Vote;
import com.fededev.my_spring_app.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository  voteRepository ) {
        this.voteRepository = voteRepository;
    }

    public Vote createVote(Vote vote) {
        return voteRepository.save(vote);
    }

    public Optional<Vote> getVoteById(Long id) {
        return voteRepository.findById(id);
    }

    public Iterable<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }

    public long countVotesByMemeIdAndVoteType(Long memeId, String voteType) {
        return voteRepository.countByMemeIdAndVoteType(memeId, voteType);
    }
}
