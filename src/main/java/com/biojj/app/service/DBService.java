package com.biojj.app.service;

import com.biojj.app.domain.Collaborator;
import com.biojj.app.domain.enums.Profile;
import com.biojj.app.repositories.CollaboratorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    private final CollaboratorRepository collaboratorRepository;


    private final BCryptPasswordEncoder encoder;

    public DBService(CollaboratorRepository collaboratorRepository, BCryptPasswordEncoder encoder) {
        this.collaboratorRepository = collaboratorRepository;
        this.encoder = encoder;
    }

    public void InstanciaDB() {

        Collaborator tec1 = new Collaborator(null, "adm", "adm@gmail.com", encoder.encode("adm123"), true);
        tec1.addProfiles(Profile.ADMIN);

        Collaborator cli1 = new Collaborator(
                null,
                "Jefferson Coelho",
                "bio.jeffcoelho@gmail.com",
                encoder.encode("pandora"),
                true);

        Collaborator cli2 = new Collaborator(
                null,
                "Snow Coelho",
                "snow@gmail.com",
                encoder.encode("snow123"),
                true);

        Collaborator cli3 = new Collaborator(
                null,
                "Brutus Coelho",
                "brutus@gmail.com",
                encoder.encode("brutus123"),
                true);

        Collaborator cli4 = new Collaborator(
                null,
                "Pandora Coelho",
                "pandora@gmail.com",
                encoder.encode("pandora123"),
                true);

        collaboratorRepository.saveAll(List.of(cli1, tec1, cli2, cli3, cli4));
    }
}
