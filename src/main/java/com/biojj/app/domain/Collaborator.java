package com.biojj.app.domain;

import com.biojj.app.domain.dtos.CollaboratorDTO;
import com.biojj.app.domain.enums.Profile;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Collaborator extends User {

    @Serial
    private static final long serialVersionUID = 1L;


    public Collaborator(Integer id, String name, String email, String password, Boolean status) {
        super(id, name, email, password, status);

        addProfiles(Profile.USER);
    }

    public Collaborator() {
        super();
        addProfiles(Profile.USER);
    }

    public Collaborator(CollaboratorDTO objDTO) {
        super();
        this.id = objDTO.getId();
        this.name = objDTO.getName();
        this.email = objDTO.getEmail();
        this.password = objDTO.getPassword();
        this.profiles = objDTO.getPerfis().stream().map(Profile::getCode).collect(Collectors.toSet());
    }
}
