import java.util.Scanner;

// User-defined exception
class InvalidCouponException extends Exception {

    public InvalidCouponException(String message) {
        super(message);
    }
}

public class EcommerceCouponValidator {

    // Method to validate coupon
    static double validateCoupon(double orderAmount, String couponCode)
            throws InvalidCouponException {

        double discount = 0;

        if (couponCode.equalsIgnoreCase("SAVE10")) {

            if (orderAmount < 1000) {
                throw new InvalidCouponException(
                    "SAVE10 requires a minimum order of Rs. 1,000."
                );
            }

            discount = 10;

        } else if (couponCode.equalsIgnoreCase("SAVE20")) {

            if (orderAmount < 2000) {
                throw new InvalidCouponException(
                    "SAVE20 requires a minimum order of Rs. 2,000."
                );
            }

            discount = 20;

        } else if (couponCode.equalsIgnoreCase("SAVE30")) {

            if (orderAmount < 3000) {
                throw new InvalidCouponException(
                    "SAVE30 requires a minimum order of Rs. 3,000."
                );
            }

            discount = 30;

        } else {

            throw new InvalidCouponException(
                "Invalid coupon code: " + couponCode
            );
        }

        return discount;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== E-Commerce Coupon Validator =====");

        System.out.print("Enter order amount: Rs. ");
        double orderAmount = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter coupon code: ");
        String couponCode = sc.nextLine();

        try {

            double discountPercent =
                validateCoupon(orderAmount, couponCode);

            double discountAmount =
                orderAmount * discountPercent / 100;

            double finalAmount =
                orderAmount - discountAmount;

            System.out.println("\nCoupon is valid!");
            System.out.println("Order Amount: Rs. " + orderAmount);
            System.out.println("Discount: " + discountPercent + "%");
            System.out.println("Discount Amount: Rs. " + discountAmount);
            System.out.println("Final Payable Amount: Rs. " + finalAmount);

        } catch (InvalidCouponException e) {

            System.out.println("\nCoupon validation failed!");
            System.out.println("Error: " + e.getMessage());

        } finally {

            System.out.println("\nCoupon validation completed.");
        }

        sc.close();
    }
}