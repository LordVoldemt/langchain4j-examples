package dev.langchain4j.example.booking;

import java.time.LocalDate;

// 业务领域对象：工具返回 Booking 后，LangChain4j 会把它序列化给模型，
// 模型再用这些字段组织面向客户的自然语言答复。
public record Booking(
        String bookingNumber,
        LocalDate bookingBeginDate,
        LocalDate bookingEndDate,
        Customer customer) {
}
