import dev.langchain4j.service.TokenStream;

public interface Assistant {

    // TokenStream 是 LangChain4j 的流式接口，调用方可以注册 partial/complete/error 回调。
    TokenStream chat(String message);
}
