import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

public class AnswerService {

    private static final Logger LOGGER = LogManager.getLogger(AnswerService.class);

    private Assistant assistant;

    public void init(SearchAction action) {
        action.appendAnswer("Initiating...");
        initChat(action);
    }

    private void initChat(SearchAction action) {
        // 创建流式 ChatModel 后，AiServices 会把 Assistant 接口代理成可调用的 AI 服务。
        StreamingChatModel model = OpenAiStreamingChatModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI)
                .build();

        assistant = AiServices.builder(Assistant.class)
                .streamingChatModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
        action.appendAnswer("Done");
        action.setFinished();
    }

    void ask(SearchAction action) {
        LOGGER.info("Asking question '" + action.getQuestion() + "'");

        var responseHandler = new CustomStreamingResponseHandler(action);

        // 这里启动真正的流式调用：每个 partial response 都会追加到当前 SearchAction。
        assistant.chat(action.getQuestion())
                .onPartialResponse(responseHandler::onNext)
                .onCompleteResponse(responseHandler::onComplete)
                .onError(responseHandler::onError)
                .start();
    }
}
