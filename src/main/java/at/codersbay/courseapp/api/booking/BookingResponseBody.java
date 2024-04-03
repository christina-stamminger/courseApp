package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.ResponseBody;
import at.codersbay.courseapp.api.course.Course;

public class BookingResponseBody extends ResponseBody {

    private Booking booking;

    public BookingResponseBody(){

    }
    public BookingResponseBody(Booking booking) {
        this.booking = booking;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
