package Entity;
import Enum.SeatCategory;
public class Seat {

    int seatId;
    int row;
    SeatCategory seatCategory;

    public Seat(int seatId, SeatCategory seatCategory) {
        this.seatId = seatId;
        this.seatCategory = seatCategory;
    }

    public int getSeatId() {
        return seatId;
    }

    public int getRow() {
        return row;
    }

    public SeatCategory getSeatCategory() {
        return seatCategory;
    }
}