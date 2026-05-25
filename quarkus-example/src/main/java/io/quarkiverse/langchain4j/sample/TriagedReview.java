// 中文说明：Quarkus 示例中的业务结果对象，用来承载模型评估或分流后的结构化数据。
package io.quarkiverse.langchain4j.sample;

import com.fasterxml.jackson.annotation.JsonCreator;

public record TriagedReview(Evaluation evaluation, String message) {

    @JsonCreator
    public TriagedReview {
    }

}
