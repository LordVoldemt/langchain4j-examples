package dev.langchain4j.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExampleApplication {

    // Spring Boot 启动入口。run() 会创建应用上下文，并自动扫描 Controller、Configuration
    // 以及 LangChain4j Spring Boot starter 暴露的模型 Bean。
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
