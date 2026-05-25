// 中文说明：Agentic 教程相关代码，用来演示多 Agent 工作流、状态传递或教学辅助工具。
package util;

import java.io.IOException;
import java.io.InputStream;

public class StringLoader {
    
    public static String loadFromResource(String resourcePath) throws IOException {
        try (InputStream inputStream = StringLoader.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            return new String(inputStream.readAllBytes());
        }
    }
    
    public static String loadFromResource(Class<?> clazz, String resourcePath) throws IOException {
        try (InputStream inputStream = clazz.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath + " for class: " + clazz.getName());
            }
            return new String(inputStream.readAllBytes());
        }
    }
}