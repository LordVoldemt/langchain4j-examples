package dev.langchain4j.example.resource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures RESTful Web Services for the application.
 *
 * <p>Payara Micro 会读取这个 JAX-RS Application，所有 Resource 路径都会挂在 {@code /api} 下。</p>
 */
@ApplicationPath("api")
public class RestConfiguration extends Application {
    
}
