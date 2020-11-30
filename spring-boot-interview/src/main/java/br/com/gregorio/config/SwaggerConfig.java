package br.com.gregorio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private static final String IO_PLATFORM_CLIENT_RESOURCE = "br.com.gregorio.controller";

	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis( RequestHandlerSelectors.basePackage(IO_PLATFORM_CLIENT_RESOURCE) )
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(apiInfo());
    }    


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("Client API")
            .description("Serviços para Manutenção de Cidades e Clientes")
            .version("1.0")
            .contact(contact())
            .build();
    }

    private Contact contact(){
        return new Contact("Gregorio Dantas",
                "http://github.com/cgregoriodantas",
                "cgregoriodantas@gmail.com");
    }
}