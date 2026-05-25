// 中文说明：Agentic 教程相关代码，用来演示多 Agent 工作流、状态传递或教学辅助工具。
package _8_non_ai_agents;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.V;
import domain.CvReview;

/**
 * Non-AI agent that aggregates multiple CV reviews into a combined review.
 * This demonstrates how plain Java operators can be used as first-class agents
 * in agentic workflows, making them interchangeable with AI-powered agents.
 */
public class StatusUpdate {

    @Agent(description = "Update application status based on score")
    public void update(@V("combinedCvReview") CvReview aggregateCvReview) {
        double score = aggregateCvReview.score;
        System.out.println("StatusUpdate called with score: " + score);

        if (score >= 8.0) {
            // dummy database update for demo
            System.out.println("Application status updated to: INVITED");
        } else {
            // dummy database update for demo
            System.out.println("Application status updated to: REJECTED");
        }
    }
}

