package io.helidon.examples.integrations.langchain4j.se.coffee.shop.assistant.ai;

import java.util.function.Supplier;

import io.helidon.service.registry.Service;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;

/**
 * A service factory that provides an instance of {@link EmbeddingModel}.
 *
 * This class implements {@link Supplier} to supply a default embedding model instance.
 */
@Service.Singleton
public class EmbeddingModelFactory implements Supplier<EmbeddingModel> {
    @Override
    public EmbeddingModel get() {
        // EmbeddingModel 负责把菜单文本转换成向量，是 RAG 检索链路的第一步。
        return new AllMiniLmL6V2EmbeddingModel();
    }
}
