package application;

import entities.Reservation;
import exceptions.InvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Check-in date: ");
            String checkintDateString = scanner.next();
            System.out.print("Check-out date: ");
            String checkoutDateString = scanner.next();

            LocalDate checkintDate = LocalDate.parse(checkintDateString, dateTimeFormatter);
            LocalDate checkoutDate = LocalDate.parse(checkoutDateString, dateTimeFormatter);

            Reservation r = new Reservation(roomNumber, checkintDate, checkoutDate);

            System.out.println(r.toString());
            System.out.println("*****************");

            System.out.print("New Check-in date: ");
            checkintDateString = scanner.next();
            System.out.print("New Check-out date: ");
            checkoutDateString = scanner.next();

            checkintDate = LocalDate.parse(checkintDateString, dateTimeFormatter);
            checkoutDate = LocalDate.parse(checkoutDateString, dateTimeFormatter);

            r.updateDates(checkintDate, checkoutDate);
            System.out.println(r.toString());
        } catch (InvalidDateException | DateTimeParseException exception) {
            System.out.println(exception.getMessage());
        } catch (InputMismatchException exception) {
            System.out.println("Error: you need write a number here");
        } finally {
            scanner.close();
        }

    }
}
