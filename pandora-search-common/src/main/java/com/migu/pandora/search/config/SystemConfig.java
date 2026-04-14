package com.migu.pandora.search.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;

/**
 * Created by piguangtao on 27/7/17.
 */
@Service
public class SystemConfig {

    /**
     * 自定义web请求过滤器
     *
     * @param repository
     * @param properties
     * @return
     */
//    @Bean
//    public CustomerWebRequestTraceFilter getWebRequestTraceFilter(TraceRepository repository, TraceProperties
//            properties) {
//        return new CustomerWebRequestTraceFilter(repository, properties);
//    }


    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new ConcurrentTaskExecutor();
    }

//    @Bean("objectMapper")
//    @Primary
//    public ObjectMapper getObjectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        return objectMapper;
//    }


    @Bean
    @Primary
    public RestTemplate getRestTemplate() {
        restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(1000));
        restTemplateBuilder.setReadTimeout(Duration.ofSeconds(1000));
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

}
