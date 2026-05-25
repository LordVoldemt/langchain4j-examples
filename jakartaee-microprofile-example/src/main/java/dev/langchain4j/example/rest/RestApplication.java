package dev.langchain4j.example.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {

    // JAX-RS 应用入口：所有 Resource 的路径都会挂在 /api 下面。
}
