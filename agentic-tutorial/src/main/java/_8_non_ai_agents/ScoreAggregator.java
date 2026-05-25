package _8_non_ai_agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;
import domain.CvReview;

/**
 * Non-AI agent that aggregates multiple CV reviews into a combined review.
 * This demonstrates how plain Java operators can be used as first-class agents
 * in agentic workflows, making them interchangeable with AI-powered agents.
 */
public class ScoreAggregator {

    @Agent(description = "Aggregates HR/Manager/Team reviews into a combined review", outputKey = "combinedCvReview")
    public CvReview aggregate(@V("hrReview") CvReview hr,
                             @V("managerReview") CvReview mgr,
                             @V("teamMemberReview") CvReview team) {

        // 非 AI Agent 的好处是确定性强：平均分和拼接反馈不会受模型随机性影响。
        // 它仍然通过 @V 读取 AgenticScope 中由上游 Agent 写入的状态。
        System.out.println("ScoreAggregator called with hrReview: " + hr +
                ", managerReview: " + mgr +
                ", teamMemberReview: " + team);

        double avgScore = (hr.score + mgr.score + team.score) / 3.0;
        
        String combinedFeedback = String.join("\n\n",
                "HR Review: " + hr.feedback,
                "Manager Review: " + mgr.feedback,
                "Team Member Review: " + team.feedback
        );
        
        return new CvReview(avgScore, combinedFeedback);
    }
}

