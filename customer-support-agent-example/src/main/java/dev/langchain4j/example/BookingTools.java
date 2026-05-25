package dev.langchain4j.example;

import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.example.booking.Booking;
import dev.langchain4j.example.booking.BookingService;
import org.springframework.stereotype.Component;

@Component
public class BookingTools {

    private final BookingService bookingService;

    public BookingTools(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // 这些 @Tool 方法是模型能触达业务系统的唯一入口；
    // 因此真正的校验仍放在 BookingService 中，而不是只依赖提示词约束模型。
    @Tool
    public Booking getBookingDetails(String bookingNumber, String customerName, String customerSurname) {
        return bookingService.getBookingDetails(bookingNumber, customerName, customerSurname);
    }

    @Tool
    public void cancelBooking(String bookingNumber, String customerName, String customerSurname) {
        bookingService.cancelBooking(bookingNumber, customerName, customerSurname);
    }
}
