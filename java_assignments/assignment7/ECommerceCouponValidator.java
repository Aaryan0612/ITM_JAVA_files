package java_assignments.assignment7;

import java.util.Scanner;

// Custom exception for invalid coupon conditions
class InvalidCouponException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidCouponException(String message) {
        super(message);
    }
}

public class ECommerceCouponValidator {

    // Validates the coupon code and order amount.
    // Returns the discount rate for a valid coupon.
    public static double validateCoupon(String couponCode, double orderAmount)
            throws InvalidCouponException {

        double discountRate;

        switch (couponCode.trim().toUpperCase()) {

            case "SAVE10":
                if (orderAmount < 1000) {
                    throw new InvalidCouponException(
                            "Order amount does not meet the minimum requirement for SAVE10."
                    );
                }
                discountRate = 0.10;
                break;

            case "SAVE20":
                if (orderAmount < 2000) {
                    throw new InvalidCouponException(
                            "Order amount does not meet the minimum requirement for SAVE20."
                    );
                }
                discountRate = 0.20;
                break;

            case "SAVE30":
                if (orderAmount < 3000) {
                    throw new InvalidCouponException(
                            "Order amount does not meet the minimum requirement for SAVE30."
                    );
                }
                discountRate = 0.30;
                break;

            default:
                throw new InvalidCouponException("Invalid coupon code entered.");
        }

        return discountRate;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== E-Commerce Coupon Validator =====");

        System.out.print("\nOrder Amount : ₹");
        double orderAmount = scanner.nextDouble();

        System.out.print("Coupon Code : ");
        String couponCode = scanner.next();

        try {

            double discountRate = validateCoupon(couponCode, orderAmount);

            double discountAmount = orderAmount * discountRate;
            double finalAmount = orderAmount - discountAmount;

            System.out.println("\nCoupon applied successfully.");
            System.out.printf("Discount Amount : ₹%.2f%n", discountAmount);
            System.out.printf("Final Payable Amount : ₹%.2f%n", finalAmount);

        } catch (InvalidCouponException e) {

            System.out.println("\nError: " + e.getMessage());

        } finally {

            System.out.println("\nCoupon validation completed.");
        }

        scanner.close();
    }
}
