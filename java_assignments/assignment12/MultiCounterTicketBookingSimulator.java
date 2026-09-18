package java_assignments.assignment12;

import java.util.Scanner;

class TicketBookingSystem {

    private int availableTickets;

    public TicketBookingSystem(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    // Synchronized method prevents two counters from selling the same ticket.
    public synchronized boolean sellTicket(String counterName) {

        if (availableTickets > 0) {
            availableTickets--;

            System.out.println(
                    counterName + " sold ticket. Remaining: " + availableTickets
            );

            return true;
        }

        return false;
    }

    public synchronized int getAvailableTickets() {
        return availableTickets;
    }

    public synchronized void resetTickets(int tickets) {
        availableTickets = tickets;
    }
}

// Counter created by extending the Thread class.
class CounterThread extends Thread {

    private TicketBookingSystem bookingSystem;

    public CounterThread(String name, TicketBookingSystem bookingSystem) {
        super(name);
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void run() {

        while (true) {

            if (!bookingSystem.sellTicket(getName())) {
                break;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println(getName() + " finished selling.");
    }
}

// Counter created by implementing the Runnable interface.
class CounterRunnable implements Runnable {

    private TicketBookingSystem bookingSystem;

    public CounterRunnable(TicketBookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
    }

    @Override
    public void run() {

        while (true) {

            String counterName = Thread.currentThread().getName();

            if (!bookingSystem.sellTicket(counterName)) {
                break;
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(counterName + " was interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println(
                Thread.currentThread().getName() + " finished selling."
        );
    }
}

public class MultiCounterTicketBookingSimulator {

    private static final int INITIAL_TICKETS = 10;

    private static final TicketBookingSystem bookingSystem =
            new TicketBookingSystem(INITIAL_TICKETS);

    private static CounterThread counterA;
    private static Thread counterB;

    private static int counterAPriority = 8;
    private static int counterBPriority = 5;

    private static boolean counterAStarted = false;
    private static boolean counterBStarted = false;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        System.out.println("===== Multi-Counter Ticket Booking Simulator =====");

        do {
            System.out.println();
            System.out.println("Available tickets: "
                    + bookingSystem.getAvailableTickets());
            System.out.println();

            System.out.println("1. Start Counter using Thread Class");
            System.out.println("2. Start Counter using Runnable Interface");
            System.out.println("3. Set Thread Priority");
            System.out.println("4. Display Thread Status");
            System.out.println("5. Display Available Tickets");
            System.out.println("6. Wait for All Counters to Finish");
            System.out.println("7. Reset Ticket Pool");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        startCounterA();
                        break;

                    case 2:
                        startCounterB();
                        break;

                    case 3:
                        setThreadPriority();
                        break;

                    case 4:
                        displayThreadStatus();
                        break;

                    case 5:
                        displayAvailableTickets();
                        break;

                    case 6:
                        waitForCounters();
                        break;

                    case 7:
                        resetTicketPool();
                        break;

                    case 8:
                        System.out.println(
                                "Exiting Multi-Counter Ticket Booking Simulator."
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select a number from 1 to 8."
                        );
                }

            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println(
                        "Error: Please enter a valid menu number."
                );
            }

        } while (choice != 8);

        scanner.close();
    }

    // Option 1: Start a thread created by extending Thread.
    private static void startCounterA() {

        if (counterA != null && counterA.isAlive()) {
            System.out.println("Counter-A is already running.");
            return;
        }

        if (bookingSystem.getAvailableTickets() == 0) {
            System.out.println("No tickets available.");
            return;
        }

        // A Thread object cannot be started twice, so create a new one
        // whenever the previous counter has finished.
        counterA = new CounterThread("Counter-A", bookingSystem);
        counterA.setPriority(counterAPriority);

        System.out.println("Starting Counter-A (Thread class)...");
        counterA.start();
        counterAStarted = true;
    }

    // Option 2: Start a thread created using Runnable.
    private static void startCounterB() {

        if (counterB != null && counterB.isAlive()) {
            System.out.println("Counter-B is already running.");
            return;
        }

        if (bookingSystem.getAvailableTickets() == 0) {
            System.out.println("No tickets available.");
            return;
        }

        CounterRunnable runnable = new CounterRunnable(bookingSystem);

        counterB = new Thread(runnable, "Counter-B");
        counterB.setPriority(counterBPriority);

        System.out.println("Starting Counter-B (Runnable interface)...");
        counterB.start();
        counterBStarted = true;
    }

    // Option 3: Set the priority of a counter before it starts.
    private static void setThreadPriority() {

        System.out.println();
        System.out.println("===== Set Thread Priority =====");
        System.out.println("1. Counter-A");
        System.out.println("2. Counter-B");

        System.out.print("Select counter: ");

        try {
            int counterChoice = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter priority (1-10): ");
            int priority = Integer.parseInt(scanner.nextLine());

            if (priority < Thread.MIN_PRIORITY
                    || priority > Thread.MAX_PRIORITY) {

                System.out.println(
                        "Priority must be between 1 and 10."
                );
                return;
            }

            if (counterChoice == 1) {

                if (counterA != null && counterA.isAlive()) {
                    System.out.println(
                            "Counter-A is running. Set priority before starting it again."
                    );
                    return;
                }

                counterAPriority = priority;

                if (counterA != null) {
                    counterA.setPriority(priority);
                }

                System.out.println(
                        "Counter-A priority set to " + priority
                );

            } else if (counterChoice == 2) {

                if (counterB != null && counterB.isAlive()) {
                    System.out.println(
                            "Counter-B is running. Set priority before starting it again."
                    );
                    return;
                }

                counterBPriority = priority;

                if (counterB != null) {
                    counterB.setPriority(priority);
                }

                System.out.println(
                        "Counter-B priority set to " + priority
                );

            } else {
                System.out.println("Invalid counter selection.");
            }

        } catch (NumberFormatException e) {
            System.out.println(
                    "Error: Please enter valid numeric values."
            );
        }
    }

    // Option 4: Display thread status.
    private static void displayThreadStatus() {

        System.out.println();
        System.out.println("===== Thread Status =====");

        if (counterA != null) {
            System.out.println(
                    "Name: " + counterA.getName()
                            + " | Priority: " + counterA.getPriority()
                            + " | Alive: " + counterA.isAlive()
            );
        } else {
            System.out.println("Counter-A: Not started");
        }

        if (counterB != null) {
            System.out.println(
                    "Name: " + counterB.getName()
                            + " | Priority: " + counterB.getPriority()
                            + " | Alive: " + counterB.isAlive()
            );
        } else {
            System.out.println("Counter-B: Not started");
        }
    }

    // Option 5: Display the shared ticket count.
    private static void displayAvailableTickets() {

        System.out.println();
        System.out.println("===== Ticket Status =====");
        System.out.println(
                "Available Tickets: "
                        + bookingSystem.getAvailableTickets()
        );
    }

    // Option 6: Wait for all started counters using join().
    private static void waitForCounters() {

        System.out.println();
        System.out.println(
                "Waiting for all counters to finish (join)..."
        );

        try {
            if (counterA != null && counterAStarted) {
                counterA.join();
            }

            if (counterB != null && counterBStarted) {
                counterB.join();
            }

            System.out.println("All counters finished selling.");
            System.out.println(
                    "Final available tickets: "
                            + bookingSystem.getAvailableTickets()
            );

        } catch (InterruptedException e) {
            System.out.println(
                    "Error: Main thread was interrupted while waiting."
            );
            Thread.currentThread().interrupt();
        }
    }

    // Option 7: Reset the shared ticket pool.
    private static void resetTicketPool() {

        if ((counterA != null && counterA.isAlive())
                || (counterB != null && counterB.isAlive())) {

            System.out.println(
                    "Cannot reset while counters are running."
            );
            return;
        }

        bookingSystem.resetTickets(INITIAL_TICKETS);
        counterAStarted = false;
        counterBStarted = false;

        System.out.println("Ticket pool reset successfully.");
        System.out.println(
                "Available tickets: "
                        + bookingSystem.getAvailableTickets()
        );
    }
}
