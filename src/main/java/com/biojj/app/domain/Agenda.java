package com.biojj.app.domain;

import com.biojj.app.domain.enums.AgendaStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "agenda")
@Data
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private AgendaStatus state;

    @Column(name = "opening_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime openingTime;

    @Column(name = "closing_time")
    private LocalDateTime closingTime;

    public void close() {
        this.state = AgendaStatus.CLOSED;
    }

}


