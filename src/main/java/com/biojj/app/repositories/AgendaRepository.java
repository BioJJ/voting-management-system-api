package com.biojj.app.repositories;

import com.biojj.app.domain.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    boolean existsByDescription(String description);
}


