import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.input.structured.StructuredPrompt;
import dev.langchain4j.model.input.structured.StructuredPromptProcessor;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;

/**
 * 这个示例学习两种提示词模板：手动变量替换和结构化提示词对象。
 * 模板适合把可变输入和固定指令分开，避免在业务代码里拼接一大段 prompt。
 */
public class _03_PromptTemplate {

    static class Simple_Prompt_Template_Example {

        public static void main(String[] args) {

            ChatModel model = OpenAiChatModel.builder()
                    .apiKey(ApiKeys.OPENAI_API_KEY)
                    .modelName(GPT_4_O_MINI)
                    .timeout(ofSeconds(60))
                    .build();

            String template = "Create a recipe for a {{dishType}} with the following ingredients: {{ingredients}}";
            // PromptTemplate 会校验并替换 {{变量名}}，比手写字符串拼接更清晰。
            PromptTemplate promptTemplate = PromptTemplate.from(template);

            Map<String, Object> variables = new HashMap<>();
            variables.put("dishType", "oven dish");
            variables.put("ingredients", "potato, tomato, feta, olive oil");

            // apply() 把业务变量填入模板，得到可发送给模型的 Prompt。
            Prompt prompt = promptTemplate.apply(variables);

            String response = model.chat(prompt.text());

            System.out.println(response);
        }

    }

    static class Structured_Prompt_Template_Example {
        @StructuredPrompt({
                "Create a recipe of a {{dish}} that can be prepared using only {{ingredients}}.",
                "Structure your answer in the following way:",

                "Recipe name: ...",
                "Description: ...",
                "Preparation time: ...",

                "Required ingredients:",
                "- ...",
                "- ...",

                "Instructions:",
                "- ...",
                "- ..."
        })
        static class CreateRecipePrompt {

            String dish;
            List<String> ingredients;

            CreateRecipePrompt(String dish, List<String> ingredients) {
                this.dish = dish;
                this.ingredients = ingredients;
            }
        }

        public static void main(String[] args) {

            ChatModel model = OpenAiChatModel.builder()
                    .apiKey(ApiKeys.OPENAI_API_KEY)
                    .modelName(GPT_4_O_MINI)
                    .timeout(ofSeconds(60))
                    .build();

            Structured_Prompt_Template_Example.CreateRecipePrompt createRecipePrompt = new Structured_Prompt_Template_Example.CreateRecipePrompt(
                    "salad",
                    asList("cucumber", "tomato", "feta", "onion", "olives")
            );

            // StructuredPromptProcessor 会读取 @StructuredPrompt，把对象字段映射到模板变量。
            Prompt prompt = StructuredPromptProcessor.toPrompt(createRecipePrompt);

            String recipe = model.chat(prompt.text());

            System.out.println(recipe);
        }
    }
}
