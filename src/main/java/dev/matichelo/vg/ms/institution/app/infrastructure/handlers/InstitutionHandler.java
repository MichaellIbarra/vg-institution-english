package dev.matichelo.vg.ms.institution.app.infrastructure.handlers;


import dev.matichelo.vg.ms.institution.app.domain.models.Institution;
import dev.matichelo.vg.ms.institution.app.application.services.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class InstitutionHandler {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private Validator validator;

    public Mono<ServerResponse> getAllInstitutions(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(institutionService.findAll(), Institution.class);
    }

    public Mono<ServerResponse> getInstitutionById(ServerRequest request) {
        String id = request.pathVariable("id");
        return institutionService.findById(id)
                .flatMap(institution -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(institution)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createInstitution(ServerRequest request) {
        Mono<Institution> institutionMono = request.bodyToMono(Institution.class);

        return institutionMono.flatMap(institution -> {
            Errors errors = new BeanPropertyBindingResult(institution, Institution.class.getName());
            validator.validate(institution, errors);

            if (errors.hasErrors()) {
                return Flux.fromIterable(errors.getFieldErrors())
                        .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                        .collectList()
                        .flatMap(list -> ServerResponse.badRequest().body(BodyInserters.fromValue(list)));
            }

            return institutionService.existsByInstitutionName(institution.getInstitutionName())
                    .flatMap(existsName -> {
                        if (existsName) {
                            Map<String, Object> response = new HashMap<>();
                            response.put("error", "Ya existe una institución con ese nombre");
                            return ServerResponse.badRequest().body(BodyInserters.fromValue(response));
                        }

                        return institutionService.existsByModularCode(institution.getModularCode())
                                .flatMap(existsCode -> {
                                    if (existsCode) {
                                        Map<String, Object> response = new HashMap<>();
                                        response.put("error", "Ya existe una institución con ese código modular");
                                        return ServerResponse.badRequest().body(BodyInserters.fromValue(response));
                                    }

                                    if (institution.getCreatedAt() == null) {
                                        institution.setCreatedAt(new Date());
                                    }

                                    return institutionService.save(institution)
                                            .flatMap(savedInstitution -> ServerResponse.created(URI.create("/api/v1/institutions/" + savedInstitution.getId()))
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .body(BodyInserters.fromValue(savedInstitution)));
                                });
                    });
        });
    }

    public Mono<ServerResponse> updateInstitution(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Institution> institutionMono = request.bodyToMono(Institution.class);
        Mono<Institution> institutionDb = institutionService.findById(id);

        return institutionDb.zipWith(institutionMono, (db, req) -> {
                    db.setInstitutionName(req.getInstitutionName());
                    db.setCodeName(req.getCodeName());
                    db.setModularCode(req.getModularCode());
                    db.setAddress(req.getAddress());
                    db.setContactEmail(req.getContactEmail());
                    db.setContactPhone(req.getContactPhone());
                    db.setStatus(req.getStatus());
                    db.setUserId(req.getUserId());
                    db.setUiSettings(req.getUiSettings());
                    db.setEvaluationSystem(req.getEvaluationSystem());
                    db.setScheduleSettings(req.getScheduleSettings());
                    db.setHeadquarters(req.getHeadquarters());

                    if (req.getInstitutionLogo() != null && !req.getInstitutionLogo().isEmpty()) {
                        db.setInstitutionLogo(req.getInstitutionLogo());
                    }

                    return db;
                })
                .flatMap(institution -> {
                    return institutionService.existsByInstitutionNameAndIdNot(institution.getInstitutionName(), institution.getId())
                            .flatMap(existsName -> {
                                if (existsName) {
                                    Map<String, Object> response = new HashMap<>();
                                    response.put("error", "Ya existe otra institución con ese nombre");
                                    return ServerResponse.badRequest().body(BodyInserters.fromValue(response));
                                }

                                return institutionService.existsByModularCodeAndIdNot(institution.getModularCode(), institution.getId())
                                        .flatMap(existsCode -> {
                                            if (existsCode) {
                                                Map<String, Object> response = new HashMap<>();
                                                response.put("error", "Ya existe otra institución con ese código modular");
                                                return ServerResponse.badRequest().body(BodyInserters.fromValue(response));
                                            }

                                            return institutionService.save(institution)
                                                    .flatMap(savedInstitution -> ServerResponse.created(URI.create("/api/v1/institutions/" + savedInstitution.getId()))
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .body(BodyInserters.fromValue(savedInstitution)));
                                        });
                            });
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteInstitution(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Institution> institutionDb = institutionService.findById(id);

        return institutionDb
                .flatMap(institution -> {
                    // Cambiar el status a "INACTIVE" o "DELETED" en lugar de eliminar
                    institution.setStatus("I");
                    return institutionService.save(institution)
                            .then(ServerResponse.noContent().build());
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> restoreInstitution(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Institution> institutionDb = institutionService.findById(id);

        return institutionDb
                .flatMap(institution -> {
                    // Cambiar el status a "INACTIVE" o "DELETED" en lugar de eliminar
                    institution.setStatus("A");
                    return institutionService.save(institution)
                            .then(ServerResponse.noContent().build());
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}