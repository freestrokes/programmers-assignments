package com.freestrokes.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springdoc.core.converters.PageableOpenAPIConverter;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;

import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static org.springdoc.core.SpringDocUtils.getConfig;

//@SecurityScheme(
//    type = SecuritySchemeType.APIKEY,
//    description =  "marketplace KIC IAM Access Token",
//    name = "X-Auth-Token",
//    in = SecuritySchemeIn.HEADER
//)
@OpenAPIDefinition(
    info = @Info(
        title = "Spring Boot Examples API",
        description = "Spring Boot Examples API",
        version = "v1.0.0"
    )
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
            .group("All")
            .pathsToMatch("/api/v1/**")
            .packagesToScan("com.freestrokes") // package 필터 설정
            .build();
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
            .group("Auth")
            .pathsToMatch("/api/v1/auth/**")
            .packagesToScan("com.freestrokes.auth.controller")
            .build();
    }

    @Bean
    public GroupedOpenApi boardApi() {
        return GroupedOpenApi.builder()
            .group("Board")
            .pathsToMatch("/api/v1/boards/**")
            .build();
    }

    @Bean
    public GroupedOpenApi boardCommentApi() {
        String[] api = {"/api/v1/board-comments/**"};
        String[] excludeApi = {"/api/health-check"};
        return GroupedOpenApi.builder()
            .group("Board Comment")
            .pathsToMatch(api)
            .pathsToExclude(excludeApi)
            .build();
    }

//    private final ApplicationProperties applicationProperties;
//
//    public SwaggerConfiguration(ApplicationProperties applicationProperties) {
//        this.applicationProperties = applicationProperties;
//        // Pageable interface 를 openapi spec doc 에서 인식하기 위한 설정
//        SpringDocUtils.getConfig()
//                .addRequestWrapperToIgnore(HttpEntity.class);
//    }
//
//    @Bean
//    public GroupedOpenApi vmAPI() {
//        String[] vm = {"/v1/**"};
//        return GroupedOpenApi.builder().group("공유 마켓플레이스 상품 API").pathsToMatch(vm).addOpenApiCustomiser(buildSecurity
//        OpenApi())
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi noneGateWayAPI() {
//        String[] api = {"/api/health-check","/api/files/**"};
//        return GroupedOpenApi.builder().group("마켓플레이스 api - no API GW").pathsToMatch(api)
//                .build();
//    }
//
//    @Bean
//    public GroupedOpenApi gateWayAPI() {
//        String[] api = {"/api/**"};
//        String[] excludeApi = {"/api/health-check","/api/files/**"};
//        return GroupedOpenApi.builder().group("마켓플레이스 api - API GW").pathsToMatch(api).pathsToExclude(excludeApi).addOpenApiCustomiser(buildSecurityOpenApi())
//                .build();
//    }
//
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .info(info())
//                .servers(servers());
//    }
//
//    private Info info() {
//        return new Info()
//                .title("Marketplace KIC API")
//                .description("Marketplace KIC API Documentation")
//                .version("1.0.0");
//    }
//
//    private List<SecurityRequirement> securityRequirements() {
//        return Collections.singletonList(new SecurityRequirement().addList("X-Auth-Token"));
//    }
//
//    private List<Server> servers() {
//        return Collections.singletonList(
//                new Server()
//                        .url(applicationProperties.getOutboundUrl())
//                        .description("API Server"));
//    }
//
//    public OpenApiCustomiser buildSecurityOpenApi() {
//        return OpenApi -> OpenApi.setSecurity(securityRequirements());
//    }
//
//    /**
//     * Swagger UI 표시 용도의 Pageable Parameter
//     */
//    @Getter
//    @Setter
//    public static class SwaggerPageable {
//
//        @Min(0)
//        @Parameter(description = "0부터 시작하는 page index (0..N)", schema = @Schema(type = "integer", defaultValue = "0"))
//        private Integer page;
//
//        /**
//         * The Limit.
//         */
//        @Min(1)
//        @Parameter(description = "페이지 당 표시할 갯수", schema = @Schema(type = "integer", defaultValue = "9"))
//        private Integer size;
//
//        /**
//         * The Sort.
//         */
//        @Parameter(description =
//                "정렬 조건을 다음과 같은 포멧으로 전달: property(,asc|desc). "
//                        + "정렬 순서는 오름차순(asc)이 기본값. "
//                , name = "sort")
//        private String sort;
//    }
//
//    @Bean
//    public PageableOpenAPIConverter pageableOpenAPIConverter() {
//        getConfig().replaceParameterObjectWithClass(org.springframework.data.domain.Pageable.class, SwaggerPageable.class)
//                .replaceParameterObjectWithClass(org.springframework.data.domain.PageRequest.class, SwaggerPageable.class);
//        return new PageableOpenAPIConverter();
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(BuildProperties.class)
//    BuildProperties buildProperties() {
//        return new BuildProperties(new Properties());
//    }
}
