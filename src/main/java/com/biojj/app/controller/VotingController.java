package com.biojj.app.controller;

import com.biojj.app.domain.Agenda;
import com.biojj.app.service.VotingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voting")
public class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping("/agenda")
    public Agenda createAgenda(@RequestParam String description, @RequestParam(required = false) Long duration) {
        return votingService.createAgenda(description, duration);
    }


    @PostMapping("/agenda/{agendaId}/vote")
    public void registerVote(@PathVariable Long agendaId, @RequestParam Integer userId, @RequestParam String vote) {
        votingService.registerVote(agendaId, userId, vote);
    }

    @GetMapping("/agenda/{agendaId}/result")
    public ResponseEntity<?> tallyResults(@PathVariable Long agendaId) {
        return votingService.tallyResults(agendaId);
    }
}
