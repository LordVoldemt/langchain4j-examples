package _6_composed_workflow;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;


public interface CandidateWorkflow {
    // 组合工作流把“生成 CV、按岗位改写、循环评审”等多个子流程包装成一个高层入口。
    // 调用方只关心候选人资料和岗位描述，不需要知道内部有多少个 Agent。
    @Agent("Based on life story and job description, generates master CV, tailors it to job description with feedback loop until passing score")
    String processCandidate(@V("lifeStory") String userInfo, @V("jobDescription") String jobDescription);
}
