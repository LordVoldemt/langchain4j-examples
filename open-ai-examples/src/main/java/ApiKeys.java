import static dev.langchain4j.internal.Utils.getOrDefault;

public class ApiKeys {

    // 示例统一从环境变量读取密钥；"demo" 仅用于演示占位，不应替代真实的 OPENAI_API_KEY。
    public static final String OPENAI_API_KEY = getOrDefault(System.getenv("OPENAI_API_KEY"), "demo");
}
