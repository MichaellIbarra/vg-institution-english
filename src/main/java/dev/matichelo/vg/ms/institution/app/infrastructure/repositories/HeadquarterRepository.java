package dev.matichelo.vg.ms.institution.app.infrastructure.repositories;

import dev.matichelo.vg.ms.institution.app.domain.models.Headquarter;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;


public interface HeadquarterRepository
        extends ReactiveMongoRepository<Headquarter, String> {
}