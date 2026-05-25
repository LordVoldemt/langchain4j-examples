package _2_sequential_workflow;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.agentic.scope.ResultWithAgenticScope;
import dev.langchain4j.service.V;

import java.util.Map;

public interface SequenceCvGenerator {
    // ResultWithAgenticScope 既返回最终结果，也保留编排过程中的中间状态，
    // 适合教学和调试时观察每个子 Agent 写入了哪些 key。
    @Agent("Generates a CV based on user-provided information and tailored to instructions, don't make it too long, avoid empty lines")
    ResultWithAgenticScope<Map<String, String>> generateTailoredCv(@V("lifeStory") String lifeStory, @V("instructions") String instructions);
}
