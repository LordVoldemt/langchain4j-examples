import java.util.List;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.TokenCountEstimator;
import dev.langchain4j.model.watsonx.WatsonxTokenCountEstimator;

public class WatsonxTokenCounterEstimatorTest {

    public static void main(String... args) throws Exception {

        try {

        // TokenCountEstimator 只估算消息会消耗多少 token，不会真正向模型发起聊天请求。
        // 这里仍然需要 watsonx.ai 的连接信息，因为不同模型的 token 规则可能不同。
        TokenCountEstimator tokenCounterEstimator = WatsonxTokenCountEstimator.builder()
            .baseUrl(System.getenv("WATSONX_URL"))
            .apiKey(System.getenv("WATSONX_API_KEY"))
            .projectId(System.getenv("WATSONX_PROJECT_ID"))
            .modelName("ibm/granite-4-h-small")
            .build();

        // 构造一个工具调用消息，方便演示“带工具调用的对话历史”如何计入 token。
        var toolExecutionRequest = ToolExecutionRequest.builder()
            .id("id")
            .name("sum")
            .arguments("{ \"firstNumber\": 1, \"secondNumber\": 2 }")
            .build();

        // 真实应用里通常会把完整对话历史交给估算器，用来判断是否需要裁剪上下文。
        Iterable<ChatMessage> messages = List.of(
            SystemMessage.from("You are an helpful assistant."),
            UserMessage.from("John", "What is the date today?"),
            AiMessage.aiMessage("Today is 2025-03-20"),
            UserMessage.from("John", "Can you execute 2 + 2"),
            AiMessage.aiMessage(toolExecutionRequest),
            ToolExecutionResultMessage.from(toolExecutionRequest, "4")
        );

        int count = tokenCounterEstimator.estimateTokenCountInMessages(messages);
        System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
