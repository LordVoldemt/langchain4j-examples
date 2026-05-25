package domain;

import dev.langchain4j.model.output.structured.Description;

public class Cv {
    // 结构化输出示例：模型不再只返回一段文本，而是按这些字段填充一个 Java 对象。
    // @Description 会进入提示词，帮助模型理解每个字段应该放什么内容。
    @Description("skills of the cadidate, comma-concatenated")
    private String skills;

    @Description("professional experience of the candidate")
    private String professionalExperience;

    @Description("studies of the candidate")
    private String studies;

    @Override
    public String toString() {
        return "CV:\n" +
                "skills = \"" + skills + "\"\n" +
                "professionalExperience = \"" + professionalExperience + "\"\n" +
                "studies = \"" + studies + "\"\n";
    }
}
