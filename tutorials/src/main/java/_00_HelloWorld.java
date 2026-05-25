import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

/**
 * 入门第一步：直接使用 ChatModel 发送一条消息，理解 LangChain4j 对聊天模型的最小封装。
 * 运行前请设置 OPENAI_API_KEY 环境变量；不要把真实 key 写到 ApiKeys 或本文件中。
 */
public class _00_HelloWorld {

    public static void main(String[] args) {

        // OpenAiChatModel.builder() 用来声明模型供应商、密钥和模型名，build() 后得到统一的 ChatModel 接口。
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .build();

        // chat(String) 是最简单的同步调用：传入用户消息，直接得到模型回复文本。
        String answer = model.chat("Say Hello World");

        System.out.println(answer);
    }
}
