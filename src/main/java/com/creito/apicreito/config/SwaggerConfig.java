package com.creito.apicreito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements SwaggerAuthConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     *  gera docket default que mostrar todos os endpoints disponiveis na api
     * @return Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2);
    }

}
