package io.quarkiverse.langchain4j.sample;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/review")
public class ReviewResource {

    @Inject
    TriageService triage;

    // 请求体映射成 record，JAX-RS 负责 JSON 反序列化，业务方法只关心 review 文本。
    record Review(String review) {
    }

    @POST
    public TriagedReview triage(Review review) {
        // REST 入口在这里进入 AI Service，返回的结构化对象会再序列化成 JSON 响应。
        return triage.triage(review.review());
    }

}
