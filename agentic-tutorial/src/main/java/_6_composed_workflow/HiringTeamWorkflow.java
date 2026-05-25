// 中文说明：Agentic 教程相关代码，用来演示多 Agent 工作流、状态传递或教学辅助工具。
package _6_composed_workflow;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;

public interface HiringTeamWorkflow {
    @Agent("Based on CV, phone interview and job description, this agent will either invite or reject the candidate")
    void processApplication(@V("candidateCv") String candidateCv,
                          @V("jobDescription") String jobDescription, 
                          @V("hrRequirements") String hrRequirements, 
                          @V("phoneInterviewNotes") String phoneInterviewNotes, 
                          @V("candidateContact") String candidateContact);
}
