package io.helidon.examples.integrations.langchain4j.se.coffee.shop.assistant;

import io.helidon.common.config.Config;
import io.helidon.examples.integrations.langchain4j.se.coffee.shop.assistant.ai.MenuItemsIngestor;
import io.helidon.examples.integrations.langchain4j.se.coffee.shop.assistant.rest.ChatBotService;
import io.helidon.logging.common.LogConfig;
import io.helidon.service.registry.Services;
import io.helidon.webserver.WebServer;

/**
 * Coffee Shop Assistant application.
 */
public class ApplicationMain {

    /**
     * Cannot be instantiated.
     */
    private ApplicationMain() {
    }

    /**
     * Application main entry point.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // Make sure logging is enabled as the first thing
        LogConfig.configureRuntime();

        var config = Services.get(Config.class);

        // Initialize embedding store
        // Helidon SE 没有 CDI 启动事件，这里在 WebServer 启动前手动预热 RAG 所需的向量库。
        Services.get(MenuItemsIngestor.class)
                .ingest();

        WebServer.builder()
                .config(config.get("server"))
                .routing(routing -> routing.register("/", Services.get(ChatBotService.class)))
                .build()
                .start();
    }
}
