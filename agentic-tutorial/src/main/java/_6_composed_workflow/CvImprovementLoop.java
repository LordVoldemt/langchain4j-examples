// 中文说明：Agentic 教程相关代码，用来演示多 Agent 工作流、状态传递或教学辅助工具。
package _6_composed_workflow;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;

public interface CvImprovementLoop {
    @Agent("Improves CV through iterative tailoring and review until passing score")
    String improveCv(@V("cv") String cv, @V("jobDescription") String jobDescription);
}
