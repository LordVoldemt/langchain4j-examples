import dev.langchain4j.model.chat.response.ChatResponse;
import javafx.application.Platform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomStreamingResponseHandler {

    private static final Logger LOGGER = LogManager.getLogger(CustomStreamingResponseHandler.class);

    private final SearchAction action;

    public CustomStreamingResponseHandler(SearchAction action) {
        this.action = action;
    }

    public void onNext(String token) {
        // 模型回调线程不是 JavaFX UI 线程，必须通过 Platform.runLater 安全更新界面。
        Platform.runLater(() -> action.appendAnswer(token));
    }

    public void onComplete(ChatResponse response) {
        Platform.runLater(() -> {
            LOGGER.info("Complete response: " + response.toString());
            LOGGER.info("Answer is complete for '" + action.getQuestion() + "', size: "
                    + action.getAnswer().length());
            action.setFinished();
        });
    }

    public void onError(Throwable error) {
        // 错误也回到 UI 线程展示，避免后台异常让用户看不到反馈。
        Platform.runLater(() -> {
            LOGGER.error("Error while receiving answer: " + error.getMessage());
            action.appendAnswer("\nSomething went wrong: " + error.getMessage());
            action.setFinished();
        });
    }
}
