package com.biojj.app.service;

import com.biojj.app.domain.Collaborator;
import com.biojj.app.domain.CpfValidationResponse;
import com.biojj.app.domain.User;
import com.biojj.app.domain.dtos.CollaboratorDTO;
import com.biojj.app.repositories.CollaboratorRepository;
import com.biojj.app.repositories.UserRepository;
import com.biojj.app.service.exception.DataIntegrityViolationException;
import com.biojj.app.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CollaboratorService {

    private static final String X_API_KEY = "x-api-key";

    private final CollaboratorRepository repository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${CPF.VALIDATION.API.URL}")
    private String cpfValidationApiUrl;

    @Value("${API.CPF.KEY}")
    private String apiKey;

    public CollaboratorService(CollaboratorRepository repository, UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public Collaborator findById(Integer id) {
        Optional<Collaborator> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public List<Collaborator> findAll() {
        return repository.findAll();
    }

    public Collaborator create(CollaboratorDTO objDTO) {
        objDTO.setId(null);
        validaCpfEmail(objDTO);
        validaCpf(objDTO.getCpf(), objDTO.getBirthDate());

        objDTO.setPassword(encoder.encode(objDTO.getPassword()));

        Collaborator obj = new Collaborator(objDTO);
        return repository.save(obj);
    }

    private void validaCpf(String cpf, String birthDate) {
        RestTemplate restTemplate = new RestTemplate();

        // Configurar headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(X_API_KEY, apiKey);

        // Criar o JSON de request
        String jsonRequest = String.format("{\"cpf\":\"%s\",\"birthDate\":\"%s\"}", cpf, birthDate);
        HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

        try {
            ResponseEntity<CpfValidationResponse> response = restTemplate.exchange(
                    cpfValidationApiUrl,
                    HttpMethod.POST,
                    entity,
                    CpfValidationResponse.class
            );

            if (response.getBody() == null || !response.getBody().isSuccess() || !response.getBody().getData().getStatus().equals("Aprovado")) {
                throw new DataIntegrityViolationException("CPF não é elegível para votar");
            }
        } catch (HttpClientErrorException e) {
            // 404 Not Found ou outros erros
            throw new DataIntegrityViolationException("CPF inválido ou não encontrado");
        }
    }

    private void validaCpfEmail(CollaboratorDTO objDTO) {
        Optional<User> obj = userRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !Objects.equals(obj.get().getId(), objDTO.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado");
        }
    }

    public Collaborator update(Integer id, @Valid CollaboratorDTO objDTO) {
        objDTO.setId(id);
        Collaborator oldObj = findById(id);
        validaCpfEmail(objDTO);

        objDTO.setPassword(encoder.encode(objDTO.getPassword()));
        oldObj = new Collaborator(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Collaborator obj = findById(id);
        repository.deleteById(id);
    }
}
