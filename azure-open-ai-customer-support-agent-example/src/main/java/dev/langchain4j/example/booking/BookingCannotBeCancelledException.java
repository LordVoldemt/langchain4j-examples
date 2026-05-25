package dev.langchain4j.example.booking;

public class BookingCannotBeCancelledException extends RuntimeException {

    // 业务异常用于区分“找不到订单”和“订单存在但规则不允许取消”这两种对话分支。
    public BookingCannotBeCancelledException(String bookingNumber) {
        super("Booking " + bookingNumber + " cannot be canceled");
    }
}
