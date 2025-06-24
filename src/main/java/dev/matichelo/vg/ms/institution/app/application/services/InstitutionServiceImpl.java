package dev.matichelo.vg.ms.institution.app.application.services;


import dev.matichelo.vg.ms.institution.app.domain.models.Institution;
import dev.matichelo.vg.ms.institution.app.infrastructure.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public Flux<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public Mono<Institution> findById(String id) {
        return institutionRepository.findById(id);
    }

    @Override
    public Mono<Institution> save(Institution institution) {
        if (institution.getCreatedAt() == null) {
            institution.setCreatedAt(new Date());
        }
        return institutionRepository.save(institution);
    }

    @Override
    public Mono<Boolean> existsByInstitutionName(String institutionName) {
        return institutionRepository.existsByInstitutionName(institutionName);
    }

    @Override
    public Mono<Boolean> existsByModularCode(String modularCode) {
        return institutionRepository.existsByModularCode(modularCode);
    }

    @Override

    public Mono<Boolean> existsByInstitutionNameAndIdNot(String institutionName, String id) {
        return institutionRepository.existsByInstitutionNameAndIdNot(institutionName, id);
    }

    @Override
    public Mono<Boolean> existsByModularCodeAndIdNot(String modularCode, String id) {
        return institutionRepository.existsByModularCodeAndIdNot(modularCode, id);
    }
}