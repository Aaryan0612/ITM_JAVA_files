import java.util.Scanner;

public class FoodDeliveryRatings {

    // Method to calculate total rating of one partner
    static int calculateTotal(int ratings[]) {
        int total = 0;

        for (int i = 0; i < ratings.length; i++) {
            total = total + ratings[i];
        }

        return total;
    }

    // Overloaded method to calculate total rating of all partners
    static int calculateTotal(int ratings[][], int partner) {
        int total = 0;

        for (int i = 0; i < ratings[partner].length; i++) {
            total = total + ratings[partner][i];
        }

        return total;
    }

    // Method to calculate average
    static double calculateAverage(int total, int count) {
        return (double) total / count;
    }

    // Overloaded method to calculate average directly from ratings
    static double calculateAverage(int ratings[]) {
        int total = calculateTotal(ratings);
        return (double) total / ratings.length;
    }

    // Linear Search method
    static int linearSearch(int ratings[][], int searchRating) {

        for (int i = 0; i < ratings.length; i++) {
            for (int j = 0; j < ratings[i].length; j++) {

                if (ratings[i][j] == searchRating) {
                    return i;
                }
            }
        }

        return -1;
    }

    // Bubble Sort to rank partners from highest to lowest
    static void bubbleSort(int totals[], String partners[]) {

        for (int i = 0; i < totals.length - 1; i++) {

            for (int j = 0; j < totals.length - i - 1; j++) {

                if (totals[j] < totals[j + 1]) {

                    // Swap totals
                    int temp = totals[j];
                    totals[j] = totals[j + 1];
                    totals[j + 1] = temp;

                    // Swap partner names
                    String tempName = partners[j];
                    partners[j] = partners[j + 1];
                    partners[j + 1] = tempName;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] partners = {
            "Swiggy",
            "Zomato",
            "Uber Eats",
            "Blinkit Food",
            "EatSure"
        };

        String[] parameters = {
            "Delivery Speed",
            "Food Quality",
            "Packaging",
            "Service"
        };

        // 2-D array to store ratings
        int[][] ratings = {
            {5, 4, 4, 5},
            {4, 5, 5, 4},
            {3, 4, 4, 3},
            {5, 3, 4, 4},
            {4, 5, 3, 5}
        };

        // 1-D array to store totals
        int[] totals = new int[5];

        System.out.println("==============================================");
        System.out.println("       FOOD DELIVERY PARTNER RATINGS");
        System.out.println("==============================================");

        // Display ratings
        for (int i = 0; i < partners.length; i++) {

            System.out.println("\n" + partners[i] + ":");

            for (int j = 0; j < parameters.length; j++) {
                System.out.println(parameters[j] + " : " + ratings[i][j]);
            }

            // Calculate total using overloaded method
            totals[i] = calculateTotal(ratings, i);

            // Calculate average
            double average = calculateAverage(totals[i], ratings[i].length);

            System.out.println("Total Rating   : " + totals[i]);
            System.out.printf("Average Rating : %.2f\n", average);
        }

        // Linear Search
        System.out.println("\n==============================================");
        System.out.println("             LINEAR SEARCH");
        System.out.println("==============================================");

        System.out.print("Enter a rating to search (1-5): ");
        int searchRating = sc.nextInt();

        int position = linearSearch(ratings, searchRating);

        if (position != -1) {
            System.out.println("Rating " + searchRating +
                    " found for partner: " + partners[position]);
        } else {
            System.out.println("Rating not found.");
        }

        // Bubble Sort
        System.out.println("\n==============================================");
        System.out.println("          PARTNER RANKING");
        System.out.println("==============================================");

        bubbleSort(totals, partners);

        for (int i = 0; i < partners.length; i++) {

            double average = calculateAverage(totals[i], 4);

            System.out.println(
                (i + 1) + ". " + partners[i] +
                " - Total: " + totals[i] +
                ", Average: " + String.format("%.2f", average)
            );
        }

        // Highest-rated partner
        System.out.println("\n==============================================");
        System.out.println("       HIGHEST-RATED PARTNER");
        System.out.println("==============================================");

        System.out.println(
            "Highest-rated delivery partner: " +
            partners[0]
        );

        System.out.println(
            "Total Rating: " + totals[0]
        );

        System.out.printf(
            "Average Rating: %.2f\n",
            calculateAverage(totals[0], 4)
        );

        sc.close();
    }
} 
