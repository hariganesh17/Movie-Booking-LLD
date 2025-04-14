import Enum.City;
public class BookMyShowClient {
    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();

        BookingManager bookingManager = BookingManager.getInstance();
        bookingManager.createBooking(City.Chennai, "Joker", 15);
        bookingManager.createBooking(City.Chennai, "Joker", 15); // Attempting to book the same seat again
    }
}
