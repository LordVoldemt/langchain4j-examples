package dev.langchain4j.example.booking;

// Customer 与 Booking 分开建模，方便工具层按姓名校验归属关系。
public record Customer(String name, String surname) {
}
