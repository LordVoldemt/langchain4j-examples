import com.ibm.watsonx.ai.detection.detector.GraniteGuardian;
import com.ibm.watsonx.ai.detection.detector.Hap;
import dev.langchain4j.model.moderation.Moderation;
import dev.langchain4j.model.moderation.ModerationModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.watsonx.WatsonxModerationModel;

public class WatsonxModerationModelTest {

    public static void main(String... args) {

        try {

            // moderation 模型用于内容安全检测，返回是否触发风险而不是生成文本。
            ModerationModel model = WatsonxModerationModel.builder()
                .baseUrl(System.getenv("WATSONX_URL"))
                .apiKey(System.getenv("WATSONX_API_KEY"))
                .projectId(System.getenv("WATSONX_PROJECT_ID"))
                .detectors(Hap.ofDefaults(), GraniteGuardian.ofDefaults())
                .build();

            Response<Moderation> response = model.moderate("I hate you!");
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }
}
