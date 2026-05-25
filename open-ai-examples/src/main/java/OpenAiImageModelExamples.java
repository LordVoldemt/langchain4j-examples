import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.output.Response;

import static dev.langchain4j.model.openai.OpenAiImageModelName.DALL_E_3;

public class OpenAiImageModelExamples {

    public static void main(String[] args) {

        // ImageModel 用于生成图片；modelName 指向图像模型，返回结果通常包含远程图片 URL。
        ImageModel model = OpenAiImageModel.builder()
                .apiKey(ApiKeys.OPENAI_API_KEY)
                .modelName(DALL_E_3)
                .build();

        Response<Image> response = model.generate("Donald Duck in New York, cartoon style");

        System.out.println(response.content().url()); // Donald Duck is here :)
    }
}
