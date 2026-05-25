import static dev.langchain4j.internal.Utils.getOrDefault;

/**
 * 教程示例统一从环境变量读取密钥，避免把真实 API Key 写进代码仓库。
 * 如果没有配置 OPENAI_API_KEY，部分 OpenAI 示例会使用 "demo" 占位值，但需要真实调用时仍应设置环境变量。
 */
public class ApiKeys {

    public static final String OPENAI_API_KEY = getOrDefault(System.getenv("OPENAI_API_KEY"), "demo");

    public static final String RAPID_API_KEY = System.getenv("RAPID_API_KEY");
}
