package com.self.thoughtApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import com.self.thoughtApp.entity.thoughtEntry;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class ThoughtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThoughtApplication.class, args);
    }

    @Bean
    public PlatformTransactionManager falana(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
        
    }


}