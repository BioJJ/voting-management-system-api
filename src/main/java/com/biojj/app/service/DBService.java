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

        Collaborator tec1 = new Collaborator(null, "adm", "adm@gmail.com", encoder.encode("adm123"), true, "12026636206",
                "08/05/1996");
        tec1.addProfiles(Profile.ADMIN);

        Collaborator cli1 = new Collaborator(
                null,
                "Jefferson Coelho",
                "bio.jeffcoelho@gmail.com",
                encoder.encode("pandora"),
                true,
                "02026636206",
                "08/05/1996");

        Collaborator cli2 = new Collaborator(
                null,
                "Snow Coelho",
                "snow@gmail.com",
                encoder.encode("snow123"),
                true,
                "02026636201",
                "08/02/1996");

        collaboratorRepository.saveAll(List.of(cli1, tec1, cli2));
    }
}
