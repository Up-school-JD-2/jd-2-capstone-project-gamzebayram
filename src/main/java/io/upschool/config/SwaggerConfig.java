package io.upschool.config;

public class SwaggerConfig {

/*
@Configuration
@EnableSwagger2WebMvc
public class Swagger {

        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(ProjectConstant.BASE_PACKAGE))
                    .paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Swagger Restful APIs")
                    .description("API's example")
                    .version("1.0")
                    .build();
        }

    }







    created by @author suraj on 23/05/20-

    @EnableSwagger2
    @Configuration
    public class SwaggerConfig {

        // pass the global parameter list here - this will get added in all apis (in swagger)
        // automatically
        private List<Parameter> globalParameterList() {

            val authTokenHeader =
                    new ParameterBuilder()
                            .name("AUTH-TOKEN")
                            .modelRef(new ModelRef("string"))
                            .required(true)
                            .parameterType("header")
                            .description("Basic Auth Token")
                            .build();

            val userNameRequestParameter =
                    new ParameterBuilder()
                            .name("user-name")
                            .modelRef(new ModelRef("string"))
                            .required(false)
                            .parameterType("query")
                            .description("User Name")
                            .build();

            return Arrays.asList(authTokenHeader, userNameRequestParameter);
        }

        @Bean
        public Docket docket() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .forCodeGeneration(true)
                    .globalOperationParameters(globalParameterList())
                    .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                    .paths(PathSelectors.any())
                    .build();
        }
    }




*/










}
