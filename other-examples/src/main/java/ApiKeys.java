import static dev.langchain4j.internal.Utils.getOrDefault;

/**
 * 这些通用示例统一从环境变量读取密钥，方便本地运行时替换不同服务。
 * 请不要把真实 API key/token 写进示例代码；没有配置时只有少数 demo 场景能运行。
 */
public class ApiKeys {

    // You can use "demo" api key for demonstration purposes.
    // You can get your own OpenAI API key here: https://platform.openai.com/account/api-keys
    public static final String OPENAI_API_KEY = getOrDefault(System.getenv("OPENAI_API_KEY"), "demo");

    // You can get your own Judge0 RapidAPI key here: https://rapidapi.com/judge0-official/api/judge0-ce
    public static final String RAPID_API_KEY = System.getenv("RAPID_API_KEY");
}
