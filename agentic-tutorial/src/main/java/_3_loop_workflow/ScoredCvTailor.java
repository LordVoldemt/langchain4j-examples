package _3_loop_workflow;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import domain.CvReview;

public interface ScoredCvTailor {

    // 这个 Agent 把上一轮 review 当作改写指令，让“评审 -> 修改”形成闭环。
    // 注意提示词强调不能编造事实，避免为了提高分数而产生不真实履历。
    @Agent("Tailors a CV according to specific instructions")
    @SystemMessage("""
            Here is a CV that needs tailoring to a specific job description, feedback or other instruction.
            You can make the CV look good to meet the requirements, but don't invent facts.
            You can drop irrelevant things if it makes the CV better suited to the instructions.
            The goal is that the applicant gets an interview and can then live up to the CV.
            The current CV: {{cv}}
            """)
    @UserMessage("""
            Here are the instructions and feedback for tailoring the CV:
            (Again, do not invent facts that are not part of the original CV. 
            If the applicant is not suitable, highlight his existing features 
            that match most closely, but do not make up facts)
            The review: {{cvReview}}
            """)
    String tailorCv(@V("cv") String cv, @V("cvReview") CvReview cvReview);
}
