package dev.langchain4j.example.aiservice;

import dev.langchain4j.agent.tool.Tool;
import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class AssistantTools {

    /**
     * This tool is available to {@link Assistant}
     *
     * <p>@Tool 标记的方法可以被模型按需调用，把普通 Java 能力暴露给 AI，例如查询当前时间。</p>
     */
    @Tool
    @Observed
    public String currentTime() {
        return LocalTime.now().toString();
    }
}
