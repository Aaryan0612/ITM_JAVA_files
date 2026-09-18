package java_assignments.assignment8.cab.booking;

public class SmartCabBooking {

    private final Integer passengerId;
    private final String passengerName;
    private final CabType cabType;
    private final Double baseFare;

    // Booking fee is final, so it cannot be changed after initialization.
    private final double bookingFee = 50.0;

    public SmartCabBooking(
            Integer passengerId,
            String passengerName,
            CabType cabType,
            Double baseFare) {

        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.cabType = cabType;
        this.baseFare = baseFare;
    }

    public void displayBookingSummary(String pickupLocation) {

        // Inner class for storing and displaying pickup location.
        class PickupDetails {
            private final String location;

            PickupDetails(String location) {
                this.location = location;
            }

            void display() {
                System.out.println("Pickup Location: " + location);
            }
        }

        // Unboxing: Integer -> int
        int id = passengerId;

        // Unboxing: Double -> double
        double fare = baseFare;

        double finalFare = fare + bookingFee;

        // StringBuilder is used to construct the booking summary.
        StringBuilder summary = new StringBuilder();

        summary.append("Passenger Name: ").append(passengerName).append("\n");
        summary.append("Cab Type: ").append(cabType).append("\n");
        summary.append("Base Fare: ₹").append(String.format("%.0f", fare)).append("\n");
        summary.append("Booking Fee: ₹").append(String.format("%.0f", bookingFee)).append("\n");
        summary.append("Final Fare: ₹").append(String.format("%.0f", finalFare)).append("\n");

        // Modify the StringBuilder before displaying it.
        summary.insert(0, "Passenger ID: " + id + "\n");

        System.out.println(summary);

        PickupDetails pickup = new PickupDetails(pickupLocation);
        pickup.display();

        // Anonymous class for booking confirmation.
        Runnable confirmation = new Runnable() {
            @Override
            public void run() {
                System.out.println("\nBooking confirmed successfully.");
            }
        };

        confirmation.run();
    }
}
