package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.service.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {

        System.out.println("Running Train Booking System");

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        Train trainSelectedForBooking = null;
        UserBookingService userBookingService;
        boolean loggedIn = false;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("Error loading system data");
            return;
        }

        while (option != 7) {

            System.out.println("\nChoose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit");

            option = scanner.nextInt();

            switch (option) {

                case 1:

                    System.out.println("Enter username");
                    String nameToSignUp = scanner.next();

                    System.out.println("Enter password");
                    String passwordToSignUp = scanner.next();

                    User userToSignup = new User(
                            nameToSignUp,
                            passwordToSignUp,
                            UserServiceUtil.hashPassword(passwordToSignUp),
                            new ArrayList<>(),
                            UUID.randomUUID().toString()
                    );

                    if (userBookingService.signUp(userToSignup)) {
                        System.out.println("Signup successful");
                    } else {
                        System.out.println("Signup failed");
                    }

                    break;

                case 2:

                    System.out.println("Enter username");
                    String nameToLogin = scanner.next();

                    System.out.println("Enter password");
                    String passwordToLogin = scanner.next();

                    User userToLogin = new User(
                            nameToLogin,
                            passwordToLogin,
                            UserServiceUtil.hashPassword(passwordToLogin),
                            new ArrayList<>(),
                            UUID.randomUUID().toString()
                    );

                    try {

                        userBookingService = new UserBookingService(userToLogin);

                        if (userBookingService.loginUser()) {
                            loggedIn = true;
                            System.out.println("Login successful");
                        } else {
                            System.out.println("Invalid username or password");
                        }

                    } catch (IOException ex) {
                        System.out.println("Login error");
                    }

                    break;

                case 3:

                    if (!loggedIn) {
                        System.out.println("Please login first");
                        break;
                    }

                    userBookingService.fetchBookings();

                    break;

                case 4:

                    System.out.println("Enter source station");
                    String source = scanner.next().toLowerCase();

                    System.out.println("Enter destination station");
                    String dest = scanner.next().toLowerCase();

                    List<Train> trains = userBookingService.getTrains(source, dest);

                    if (trains.isEmpty()) {
                        System.out.println("No trains found");
                        break;
                    }

                    int index = 1;

                    for (Train t : trains) {

                        System.out.println(index + " Train id : " + t.getTrainId());

                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.println(entry.getKey() + " : " + entry.getValue());
                        }

                        index++;
                    }

                    System.out.println("Select train");
                    int choice = scanner.nextInt();

                    trainSelectedForBooking = trains.get(choice - 1);

                    break;

                case 5:

                    if (!loggedIn) {
                        System.out.println("Please login first");
                        break;
                    }

                    List<List<Integer>> seats = userBookingService.fetchSeats(trainSelectedForBooking);

                    for (List<Integer> row : seats) {
                        for (Integer seat : row) {
                            System.out.print(seat + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Enter row");
                    int row = scanner.nextInt();

                    System.out.println("Enter column");
                    int col = scanner.nextInt();

                    String ticketId = userBookingService.bookTrainSeat(trainSelectedForBooking, row, col);

                    if (ticketId != null) {
                        System.out.println("Seat booked successfully");
                        System.out.println("Ticket ID: " + ticketId);
                    } else {
                        System.out.println("Seat booking failed");
                    }

                    break;

                case 6:

                    System.out.println("Enter ticket ID");
                    String ticketToCancel = scanner.next();

                    userBookingService.cancelBooking(ticketToCancel);

                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;
            }
        }
    }
}