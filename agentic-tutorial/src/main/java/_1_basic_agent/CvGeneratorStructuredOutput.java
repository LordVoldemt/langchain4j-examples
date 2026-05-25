package _1_basic_agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import domain.Cv;

public interface CvGeneratorStructuredOutput {
    // 返回值是 Cv 时，LangChain4j 会尝试把模型输出解析成结构化对象，
    // 这适合后续代码继续读取字段，而不是再解析一大段自然语言文本。
    @UserMessage("""
            Here is information on my life and professional trajectory
            that you should turn into a clean and complete CV.
            Do not invent facts and do not leave out skills or experiences.
            This CV will later be cleaned up, for now, make sure it is complete.
            My life story: {{lifeStory}}
            """)
    @Agent("Generates a clean CV based on user-provided information")
    Cv generateCv(@V("lifeStory") String userInfo);
}
