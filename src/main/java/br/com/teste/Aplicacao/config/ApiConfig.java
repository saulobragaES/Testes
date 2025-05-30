package br.com.teste.Aplicacao.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                            .title("API de Empacotamento")
                            .version("1.0")
                            .description("API para empacotamento e cálculo de volumes."));
    }
}
