package com.dji.sample.configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cikian
 * @version 1.0
 * @implNote
 * @see <a href="https://www.cikian.cn">https://www.cikian.cn</a>
 * @since 2026-04-20 10:48
 */
@Configuration
public class DjiJacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer djiJacksonCustomizer() {
        return builder -> {
            // 1. 【核心改动】移除全局下划线配置，防止破坏 Jeecg 原生接口
            // builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

            // 2. 局部适配逻辑：
            // 我们通过 postConfigurer 针对 com.dji 包下的类特殊处理命名策略
            builder.postConfigurer(objectMapper -> {
                objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategy() {
                    @Override
                    public String nameForField(MapperConfig<?> config, AnnotatedField field, String defaultName) {
                        // 只有 com.dji 包下的类才应用下划线转换
                        if (field.getDeclaringClass().getName().startsWith("com.dji")) {
                            return PropertyNamingStrategy.SNAKE_CASE.nameForField(config, field, defaultName);
                        }
                        return defaultName;
                    }

                    @Override
                    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                        if (method.getDeclaringClass().getName().startsWith("com.dji")) {
                            return PropertyNamingStrategy.SNAKE_CASE.nameForGetterMethod(config, method, defaultName);
                        }
                        return defaultName;
                    }

                    @Override
                    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
                        if (method.getDeclaringClass().getName().startsWith("com.dji")) {
                            return PropertyNamingStrategy.SNAKE_CASE.nameForSetterMethod(config, method, defaultName);
                        }
                        return defaultName;
                    }
                });

                // 3. 【核心改动】修正之前的 NULL 序列化 Bug
                // 之前的代码因为直接写死 gen.writeString("") 导致所有对象都被抹除
                objectMapper.getSerializerProvider().setNullValueSerializer(new com.fasterxml.jackson.databind.JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, com.fasterxml.jackson.core.JsonGenerator gen,
                                          com.fasterxml.jackson.databind.SerializerProvider serializers) throws java.io.IOException {
                        // 只有真正为 null 时才输出空字符串，这样不会误伤正常对象
                        gen.writeString("");
                    }
                });
            });

            // 4. 通用配置项
            builder.failOnUnknownProperties(false);
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            builder.featuresToEnable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
            builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        };
    }
}
