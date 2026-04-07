package org.jeecg.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.constant.CommonConstant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author scott
 * 合并后完整的 Swagger2/Knife4j 配置
 */
@Configuration
@EnableSwagger2    // 开启 Swagger2
@EnableKnife4j     // 开启 knife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config implements WebMvcConfigurer {

    /**
     * 显示swagger-ui.html文档展示页，注入swagger资源：
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    // ================= 分组1：JeecgBoot 业务接口 =================

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("1-JeecgBoot业务接口")
                .apiInfo(jeecgApiInfo())
                .select()
                // 此包路径下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage("org.jeecg"))
                // 加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(jeecgSecurityScheme()))
                .securityContexts(jeecgSecurityContexts())
                .globalOperationParameters(setHeaderToken());
    }

    // ================= 分组2：大疆 CloudSDK 接口 =================

    @Bean(value = "djiApi")
    public Docket djiApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("2-CloudSDK接口")
                .apiInfo(djiApiInfo())
                .select()
                // 扫描大疆的包
                .apis(RequestHandlerSelectors.basePackage("com.dji"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(djiSecurityScheme()))
                .securityContexts(djiSecurityContexts());
    }

    // ================= 辅助方法：文档基本信息 =================

    private ApiInfo jeecgApiInfo() {
        return new ApiInfoBuilder()
                .title("JeecgBoot 后台服务API接口文档")
                .version("1.0")
                .description("后台API接口")
                .contact(new Contact("北京国炬信息技术有限公司","www.jeccg.com","jeecgos@163.com"))
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }

    private ApiInfo djiApiInfo() {
        return new ApiInfoBuilder()
                .title("CloudSDK API")
                .version("1.0.0")
                .description("All HTTP interfaces encapsulated by CloudSDK.")
                .license("LICENSE")
                .licenseUrl("https://github.com/dji-sdk/DJI-Cloud-API-Demo/blob/main/LICENSE")
                .build();
    }

    // ================= 辅助方法：安全与鉴权配置 =================
    // 注意：这里去掉了 @Bean 注解，避免 Spring 容器里发生重名冲突

    private SecurityScheme jeecgSecurityScheme() {
        return new ApiKey(CommonConstant.X_ACCESS_TOKEN, CommonConstant.X_ACCESS_TOKEN, "header");
    }

    private SecurityScheme djiSecurityScheme() {
        // 大疆要求的 header token 配置
        return new ApiKey("x-auth-token", "x-auth-token", "header");
    }

    private List<SecurityContext> jeecgSecurityContexts() {
        return new ArrayList<>(Collections.singleton(SecurityContext.builder()
                .securityReferences(defaultAuth(CommonConstant.X_ACCESS_TOKEN))
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build()));
    }

    private List<SecurityContext> djiSecurityContexts() {
        return new ArrayList<>(Collections.singleton(SecurityContext.builder()
                .securityReferences(defaultAuth("x-auth-token"))
                .forPaths(PathSelectors.any())
                .build()));
    }

    private List<SecurityReference> defaultAuth(String referenceName) {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return new ArrayList<>(Collections.singleton(new SecurityReference(referenceName, authorizationScopes)));
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(CommonConstant.X_ACCESS_TOKEN).description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    // ================= 修复 Spring Boot 2.6+ 兼容性问题的补丁 =================

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}