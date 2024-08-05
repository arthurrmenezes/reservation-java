package entities;

import exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;

public class Reservation {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Integer roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation() {
    }

    public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckin() {
        return this.checkIn;
    }

    public LocalDate getCheckout() {
        return this.checkOut;
    }

    public long duration() {
        if (this.checkOut.isBefore(this.checkIn) || this.checkOut.isEqual(this.checkIn)) {
            throw new InvalidDateException("Error in reservation: Check-out date must be after check-in date");
        } else {
            return DAYS.between(this.checkIn, this.checkOut);
        }
    }

    public void updateDates(LocalDate checkin, LocalDate checkout) {
        if (checkout.getDayOfYear() < checkin.getDayOfYear()) {
            throw new InvalidDateException("Error in reservation: " +
                    "Check-out date must be after check-in date");
        } else if (checkin.isBefore(this.checkIn) || checkout.isBefore(this.checkOut)) {
            throw new InvalidDateException("Error in reservation: " +
                    "Reservations dates for update must be future dates");
        } else {
            this.checkIn = checkin;
            this.checkOut = checkout;
        }
    }

    @Override
    public String toString() {
        return "\n** Reservation Data **\nRoom number: " + getRoomNumber() + "\nCheck-In: "
                + getCheckin().format(dateTimeFormatter) + "\nCheck-Out: "
                + getCheckout().format(dateTimeFormatter)
                + "\nDuration: " + duration() + " nights";
    }
}
