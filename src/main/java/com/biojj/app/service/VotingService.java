package com.biojj.app.service;

import com.biojj.app.domain.Agenda;
import com.biojj.app.domain.Collaborator;
import com.biojj.app.domain.Vote;
import com.biojj.app.domain.enums.AgendaStatus;
import com.biojj.app.domain.enums.VoteType;
import com.biojj.app.repositories.AgendaRepository;
import com.biojj.app.repositories.CollaboratorRepository;
import com.biojj.app.repositories.VoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class VotingService {

    private final AgendaRepository agendaRepository;

    private final VoteRepository voteRepository;

    private final CollaboratorRepository collaboratorRepository;

    public VotingService(AgendaRepository agendaRepository, VoteRepository voteRepository, CollaboratorRepository collaboratorRepository) {
        this.agendaRepository = agendaRepository;
        this.voteRepository = voteRepository;
        this.collaboratorRepository = collaboratorRepository;
    }

    public Agenda createAgenda(String description, Long durationInMinutes) {
        if (agendaRepository.existsByDescription(description)) {
            throw new RuntimeException("Pauta com essa descrição já existe");
        }
        Agenda agenda = new Agenda();
        agenda.setDescription(description);
        agenda.setOpeningTime(LocalDateTime.now());
        agenda.setState(AgendaStatus.OPEN);
        // Se a duração não for especificada, usa 1 minuto
        agenda.setClosingTime(LocalDateTime.now().plusMinutes(durationInMinutes != null ? durationInMinutes : 1));
        return agendaRepository.save(agenda);
    }

    public void registerVote(Long agendaId, Integer userId, String vote) {

        // Verifica se o voto é válido
        VoteType voteType;
        try {
            voteType = VoteType.valueOf(vote.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Voto inválido. Deve ser 'Sim' ou 'Não'");
        }
        // Validate if the voting session is open
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RuntimeException("Agenda not found"));
        if (LocalDateTime.now().isAfter(agenda.getClosingTime())) {
            throw new RuntimeException("Voting session closed");
        }

        Collaborator user = collaboratorRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if user has already voted
        boolean hasVoted = voteRepository.existsByAgendaIdAndUserId(agendaId, userId);
        if (hasVoted) {
            throw new RuntimeException("User has already voted on this agenda");
        }

        // Register the vote
        Vote voteEntity = new Vote();
        voteEntity.setAgenda(agenda);
        voteEntity.setUser(user);
        voteEntity.setVote(voteType);
        voteRepository.save(voteEntity);
    }

    public ResponseEntity<?> tallyResults(Long agendaId) {
        long votesYes = voteRepository.countByAgendaIdAndVote(agendaId, VoteType.SIM);
        long votesNo = voteRepository.countByAgendaIdAndVote(agendaId, VoteType.NAO);

        Map<String, Long> result = new HashMap<>();
        result.put("YES", votesYes);
        result.put("NO", votesNo);

        return ResponseEntity.ok(result);
    }

}
