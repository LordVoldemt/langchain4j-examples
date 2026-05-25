package io.helidon.examples.integrations.langchain4j.se.coffee.shop.assistant.ai;

import java.util.function.Supplier;

import io.helidon.service.registry.Service;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

/**
 * A factory service that provides an instance of {@link EmbeddingStore<TextSegment>}.
 *
 * This class implements {@link Supplier} to supply a named embedding store instance.
 */
@Service.Singleton
@Service.Named("EmbeddingStore")
public class EmbeddingStoreFactory implements Supplier<EmbeddingStore<TextSegment>> {
    @Override
    public EmbeddingStore<TextSegment> get() {
        // Service Registry 会通过这个 Supplier 创建命名向量库，供 Ingestor 和 AI 服务共享。
        return new InMemoryEmbeddingStore<>();
    }
}
