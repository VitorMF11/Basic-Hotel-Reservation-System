package br.com.application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static Integer moreOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                -----------------------------------
                Welcome booking menu!
                Choose one of the options below:
                1 - Update Check-in/Check-out dates
                2 - Get reservation data
                3 - Get room number
                -----------------------------------""");
        return scanner.nextInt();
    }

    public static Integer finishMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Choose an option:
                1 - Close program
                2 - Show more options""");
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.println("Let's start the reservation! Please enter your personal data!");

            System.out.print("Enter your name: ");
            String name = scanner.next();

            System.out.print("Enter your ID number: ");
            Integer idNumber = scanner.nextInt();

            System.out.println("Now choose check-in and check-out dates!");

            System.out.print("Check-in date (dd/MM/yyyy): ");
            Date checkIn = sdf.parse(scanner.next());

            System.out.print("Check-out date (dd/MM/yyyy): ");
            Date checkOut = sdf.parse(scanner.next());

            Reservation reservation = new Reservation(name, checkIn, checkOut, idNumber);

            System.out.println("Booking Completed!");

            boolean keepMenu = true;

            while (keepMenu) {
                int finishOption = finishMenu();
                if (finishOption == 1) {
                    keepMenu = false;
                } else if (finishOption == 2) {
                    int moreOptions = moreOptions();

                    if (moreOptions == 1) {
                        System.out.println("Enter your ID number to update the reservation: ");
                        int checkId = scanner.nextInt();
                        if (checkId == reservation.getIdNumber()) {

                            System.out.println("Please enter new check-in and check-out dates");

                            System.out.print("Check-in date (dd/MM/yyyy): ");
                            checkIn = sdf.parse(scanner.next());

                            System.out.print("Check-out date (dd/MM/yyyy): ");
                            checkOut = sdf.parse(scanner.next());

                            reservation.updateDates(checkIn, checkOut);
                            System.out.println("Booking Updated!");
                        }
                    } else if (moreOptions == 2) {
                        System.out.println("Reservation\n" + reservation);
                    } else if (moreOptions == 3) {
                        System.out.println("Please enter your ID number:");
                        int checkId = scanner.nextInt();

                        if (checkId == reservation.getIdNumber()) {
                            Random random = new Random();
                            int roomNumber = random.nextInt(501);
                            reservation.setRoomNumber(roomNumber);
                            System.out.println("This is your room number: " + roomNumber);
                        } else {
                            System.out.println("There are no reservations registered with this ID!");
                        }
                    }
                }
            }
        } catch (DomainException e) {
            System.out.println("Error in reservation: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input.");
        }
        scanner.close();
    }
}
