// 中文说明：Azure OpenAI 客服 Agent 示例代码，用来演示业务对象、工具调用或应用启动流程。
package dev.langchain4j.example.booking;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException(String bookingNumber) {
        super("Booking " + bookingNumber + " not found");
    }
}
