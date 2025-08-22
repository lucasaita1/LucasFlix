package br.com.lucasflix.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPIO() {
        Contact contact = new Contact()
                .name("Lucas")
                .email("lucasaita4000@gmail.com");

        Info info = new Info()
                .title("LucasFlix")
                .version("v1")
                .description("Aplicação para gerenciamento de catálogo de filmes")
                .contact(contact);

        return new OpenAPI().info(info);
    }
}
