package com.biojj.app.domain.dtos;

import com.biojj.app.domain.Collaborator;
import com.biojj.app.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CollaboratorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected String name;
    protected String email;
    protected String password;
    protected Boolean status;


    protected Set<Integer> profile = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")

    public Set<Profile> getPerfis() {
        return profile.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Profile perfil) {
        this.profile.add(perfil.getCode());
    }

    public CollaboratorDTO() {
        super();
        addPerfil(Profile.USER);
    }

    public CollaboratorDTO(Collaborator obj) {
        super();
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.status = obj.getStatus();
        this.profile = obj.getProfiles().stream().map(Profile::getCode).collect(Collectors.toSet());
        addPerfil(Profile.USER);
    }
}
