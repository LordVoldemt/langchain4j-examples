/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package dev.langchain4j.example.wildfly;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestApplication extends Application {
    // WildFly 中的 JAX-RS 应用根路径；SSE Resource 会挂在 /rest 下面。
}
