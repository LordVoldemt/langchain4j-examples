package domain;

import dev.langchain4j.model.output.structured.Description;

public class CvReview {
    // 这个领域对象会在多个 Agent 之间传递：reviewer 写入评分和反馈，
    // 后续 tailor、条件分支或聚合器再根据它决定下一步动作。
    @Description("Score from 0 to 1 how likely you would invite this candidate to an interview")
    public double score;

    @Description("Feedback on the CV, what is good, what needs improvement, what skills are missing, what red flags, ...")
    public String feedback;

    public CvReview() {} // LangChain4j 反序列化结构化输出时需要无参构造器。

    public CvReview(double score, String feedback) {
        this.score = score;
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "\nCvReview: " +
                " - score = " + score +
                "\n- feedback = \"" + feedback + "\"\n";
    }
}
