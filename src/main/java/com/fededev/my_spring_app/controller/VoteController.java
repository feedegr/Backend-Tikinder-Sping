package com.fededev.my_spring_app.controller;

import com.fededev.my_spring_app.model.Vote;
import com.fededev.my_spring_app.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        return voteService.createVote(vote);
    }

    @GetMapping
    public Iterable<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }

    @GetMapping("/{id}")
    public Vote getVoteById(@PathVariable Long id) {
        return voteService.getVoteById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
    }

    @GetMapping("/count/{memeId}/{voteType}")
    public long countVotes(@PathVariable Long memeId, @PathVariable String voteType) {
        return voteService.countVotesByMemeIdAndVoteType(memeId, voteType);
    }
}
