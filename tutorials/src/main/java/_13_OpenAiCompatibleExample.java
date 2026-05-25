import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.internal.Utils.getOrDefault;
import static java.time.Duration.ofSeconds;

/**
 * 这个示例学习“OpenAI 兼容接口”：当第三方服务实现了类似 OpenAI 的 API 时，可以复用 OpenAiChatModel/OpenAiImageModel。
 * 运行前要确认 baseUrl、modelName 和 API Key 都来自目标服务，不要把真实密钥写进代码。
 */
public class _13_OpenAiCompatibleExample {

    private static final String BASE_URL = getOrDefault(
            System.getenv("OPENAI_COMPATIBLE_BASE_URL"),
            "https://codeapi.swpumc.cn/v1"
    );

    private static final String API_KEY = getOrDefault(
            System.getenv("OPENAI_COMPATIBLE_API_KEY"),
            "demo"
    );

    private static final String MODEL_NAME = getOrDefault(
            System.getenv("OPENAI_COMPATIBLE_MODEL_NAME"),
            "gpt-5.4"
    );

    private static final String IMAGE_MODEL_NAME = getOrDefault(
            System.getenv("OPENAI_COMPATIBLE_IMAGE_MODEL_NAME"),
            "gpt-image-2"
    );

    public static void main(String[] args) {

        chat();
        generateImage();
    }

    private static void chat() {

        // baseUrl 指向兼容服务的入口；其它配置仍然沿用 OpenAI 模型的 builder 风格。
        ChatModel model = OpenAiChatModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(API_KEY)
                .modelName(MODEL_NAME)
                .timeout(ofSeconds(60))
                .build();

        String prompt = "Tell me a joke about Java.";

        String response = model.chat(prompt);

        System.out.println(response);
    }

    private static void generateImage() {

        ImageModel model = OpenAiImageModel.builder()
                .baseUrl(BASE_URL)
                .apiKey(API_KEY)
                .modelName(IMAGE_MODEL_NAME)
                .timeout(ofSeconds(60))
                .build();

        Response<Image> response = model.generate("A small robot painting Java code, watercolor style");

        System.out.println(response.content().url());
    }
}
