package dev.matichelo.vg.ms.institution.app.application.services;


import dev.matichelo.vg.ms.institution.app.domain.models.Institution;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InstitutionService {
    Flux<Institution> findAll();
    Mono<Institution> findById(String id);
    Mono<Institution> save(Institution institution);
    Mono<Boolean> existsByInstitutionName(String institutionName);
    Mono<Boolean> existsByModularCode(String modularCode);
    Mono<Boolean> existsByInstitutionNameAndIdNot(String institutionName, String id);
    Mono<Boolean> existsByModularCodeAndIdNot(String modularCode, String id);
}