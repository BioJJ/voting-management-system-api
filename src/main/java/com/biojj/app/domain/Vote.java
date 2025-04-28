package com.biojj.app.domain;

import com.biojj.app.domain.enums.VoteType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vote")
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "vote", nullable = false)
    private VoteType vote;
}