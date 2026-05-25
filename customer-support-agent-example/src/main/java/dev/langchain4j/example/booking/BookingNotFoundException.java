package dev.langchain4j.example.booking;

public class BookingNotFoundException extends RuntimeException {

    // 抛出业务异常后，Agent 会收到工具调用失败的信息，并应按系统提示继续向用户解释或追问。
    public BookingNotFoundException(String bookingNumber) {
        super("Booking " + bookingNumber + " not found");
    }
}
