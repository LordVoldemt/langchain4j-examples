package agentic.util;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.gpullama3.GPULlama3ChatModel;

import java.nio.file.Path;

/**
 * This class provides an instance of {@link ChatModel} for the {@link GPULlama3ChatModel}.
 */
public class GPULlama3ChatModelProvider {

    public static ChatModel createChatModel(boolean onGPU) {
        // Agent 教程复用这个 provider，把“本地模型路径和 GPU/CPU 选择”集中到一个地方。
        return GPULlama3ChatModel.builder()
                .modelPath(getModelPath())
                .maxTokens(1500)
                .onGPU(onGPU) //if false, runs on CPU though a lightweight implementation of llama3.java
                .build();
    }

    private static Path getModelPath() {
        // Read path to your *local* model files.
        // 只读取环境变量，不在示例中写死个人机器路径，便于不同开发者复用。
        String localLLMsPath = System.getenv("LOCAL_LLMS_PATH");

        // Check if the environment variable is set
        if (localLLMsPath == null || localLLMsPath.isEmpty()) {
            System.err.println("Error: LOCAL_LLMS_PATH environment variable is not set.");
            System.err.println("Please set this environment variable to the directory containing your local model files.");
            System.exit(1);
        }

        // Change this model file name to choose any of your *local* model files.
        // Supports Mistral, Llama3, Phi-3, Qwen2.5 and Qwen3 in gguf format.
        String modelFile = "beehive-llama-3.2-1b-instruct-fp16.gguf";

        return Path.of(localLLMsPath, modelFile);
    }
}
