import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个示例学习 PromptTemplate 的变量替换能力。
 * 它适合把固定提示词和业务变量分离，减少手写字符串拼接带来的遗漏和转义问题。
 */
public class PromptTemplateExamples {

    static class PromptTemplate_with_One_Variable_Example {

        public static void main(String[] args) {

            // 单变量模板可以直接 apply(value)，变量名会按模板中的占位符绑定。
            PromptTemplate promptTemplate = PromptTemplate.from("Say 'hi' in {{it}}.");

            Prompt prompt = promptTemplate.apply("German");

            System.out.println(prompt.text()); // Say 'hi' in German.
        }
    }

    static class PromptTemplate_With_Multiple_Variables_Example {

        public static void main(String[] args) {

            // 多变量模板使用 Map，key 必须和 {{占位符}} 名称一致。
            PromptTemplate promptTemplate = PromptTemplate.from("Say '{{text}}' in {{language}}.");

            Map<String, Object> variables = new HashMap<>();
            variables.put("text", "hi");
            variables.put("language", "German");

            Prompt prompt = promptTemplate.apply(variables);

            System.out.println(prompt.text()); // Say 'hi' in German.
        }
    }
}
