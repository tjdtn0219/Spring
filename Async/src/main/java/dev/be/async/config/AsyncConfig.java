package dev.be.async.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync        //이제 Spring 에서 Async하게 동작할 수 있음
public class AsyncConfig {
}
