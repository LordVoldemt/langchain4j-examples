package _7_supervisor_orchestration;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.agentic.scope.ResultWithAgenticScope;
import dev.langchain4j.agentic.supervisor.AgentInvocation;
import dev.langchain4j.service.V;

import java.util.List;

public interface HiringSupervisor {
    // Supervisor 不是固定顺序流程，而是让顶层 Agent 根据 request 和上下文选择要调用的下级 Agent。
    // 返回 AgenticScope 便于查看它实际选择了哪些步骤、写入了哪些中间状态。
    @Agent("Top-level hiring supervisor orchestrating candidate evaluation and decision-making")
    ResultWithAgenticScope<String> invoke(@V("request") String request, @V("supervisorContext") String supervisorContext);
}
