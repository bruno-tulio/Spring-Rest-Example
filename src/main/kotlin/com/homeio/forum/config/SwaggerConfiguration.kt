package com.homeio.forum.config

import com.homeio.forum.ForumApplication
import com.homeio.forum.model.Usuario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.service.ApiInfo
import springfox.documentation.schema.ModelRef
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket



@EnableSwagger2
@Configuration
class SwaggerConfiguration {


    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.homeio.forum"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .apiInfo(apiInfo())
                .ignoredParameterTypes(Usuario::class.java)
                .globalOperationParameters(
                        arrayListOf(
                                ParameterBuilder()
                                        .name("Authorization")
                                        .description("Header para facilitar o envio do Authorization Bearer Token")
                                        .modelRef(ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build()
                        )
                )
    }

    private fun apiInfo(): ApiInfo {
        val contact = Contact("Homeio", "https://homeio.com.br/", "contato@homeio.com.br")
        return ApiInfoBuilder()
                .title("Homeio Forum API Documentation")
                .description("Esta é a documentação interativa da Rest API do Forum da Alura. Tente enviar algum request")
                .version("1.0")
                .contact(contact).build()
    }

}