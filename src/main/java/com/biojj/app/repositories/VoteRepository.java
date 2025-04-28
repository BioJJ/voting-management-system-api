package com.biojj.app.repositories;

import com.biojj.app.domain.Vote;
import com.biojj.app.domain.enums.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    long countByAgendaIdAndVote(Long agendaId, VoteType voto);

    boolean existsByAgendaIdAndUserId(Long agendaId, Integer userId);
}