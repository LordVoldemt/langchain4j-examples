import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.model.openai.OpenAiImageModelName.DALL_E_3;

/**
 * 这个示例学习 ImageModel：它和 ChatModel 类似，但输出的是图片信息而不是文本。
 * 运行会调用 OpenAI 图片生成接口，通常需要真实 API Key，并可能产生费用。
 */
public class _02_OpenAiImageModelExamples {

    public static void main(String[] args) {

        ImageModel model = OpenAiImageModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(DALL_E_3)
                .build();

        // generate() 返回 Response<Image>，其中 content() 才是本次生成的图片结果。
        Response<Image> response = model.generate(
                "Swiss software developers with cheese fondue, a parrot and a cup of coffee");

        System.out.println(response.content().url());
    }
}
