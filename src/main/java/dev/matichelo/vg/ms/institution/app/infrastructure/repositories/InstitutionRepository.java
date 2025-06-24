package dev.matichelo.vg.ms.institution.app.infrastructure.repositories;

import dev.matichelo.vg.ms.institution.app.domain.models.Institution;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;


public interface InstitutionRepository extends ReactiveMongoRepository<Institution, String> {
    Mono<Boolean> existsByInstitutionName(String institutionName);
    Mono<Boolean> existsByModularCode(String modularCode);
    Mono<Boolean> existsByInstitutionNameAndIdNot(String institutionName, String id);
    Mono<Boolean> existsByModularCodeAndIdNot(String modularCode, String id);
}