package dev.matichelo.vg.ms.institution.app.infrastructure.config;

import dev.matichelo.vg.ms.institution.app.infrastructure.handlers.InstitutionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfig {


    @Bean
    public RouterFunction<ServerResponse> routes(InstitutionHandler handler){
        return route(GET("/api/v1/institutions"), handler::getAllInstitutions)
                .andRoute(GET("/api/v1/institutions/{id}"), handler::getInstitutionById)
                .andRoute(POST("/api/v1/institutions"), handler::createInstitution)
                .andRoute(PUT("/api/v1/institutions/{id}"), handler::updateInstitution)
                .andRoute(DELETE("/api/v1/institutions/{id}"), handler::deleteInstitution)
                .andRoute(PUT("/api/v1/institutions/restore/{id}"), handler::restoreInstitution);
    }

}
