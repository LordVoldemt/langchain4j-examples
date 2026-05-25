package com.example.demo;

import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * NOTE:
 * 本示例假设已有 Neo4j 实例，连接信息在 application.properties 中配置：
 * bolt://localhost:7687，用户名 neo4j，密码 pass1234。
 * 如需连接其他外部 Neo4j 服务，请修改配置文件，不要在代码中写入真实凭据。
 * 
 * 添加一条 embedding：
 * curl -X POST localhost:8083/api/embeddings/add -H "Content-Type: text/plain" -d "embeddingTest"
 * 
 * 搜索相似 embedding：
 * curl -X POST localhost:8083/api/embeddings/search -H "Content-Type: text/plain" -d "querySearchTest"
 */
@SpringBootApplication
public class SpringBootExample {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExample.class, args);
    }

    @Bean
    public AllMiniLmL6V2EmbeddingModel embeddingModel() {
        // Spring 容器复用同一个 embedding 模型，Controller 写入和查询时保持向量维度一致。
        return new AllMiniLmL6V2EmbeddingModel();
    }
    
}
