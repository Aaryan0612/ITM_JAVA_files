package day3;

public class arrayEx {
    static String[] partners = {"Partner 1", "Partner 2", "Partner 3", "Partner 4", "Partner 5"};
    static String[] criteria = {"Delivery Speed", "Food Quality", "Packaging", "Service"};

    static int[][] ratings = {
        {4, 5, 4, 3},
        {5, 4, 5, 4},
        {3, 4, 4, 5},
        {4, 5, 5, 4},
        {5, 3, 4, 5}
    };

    static int calculateTotal(int[] partnerRatings) {
        int total = 0;
        for (int value : partnerRatings) {
            total += value;
        }
        return total;
    }

    static int calculateTotal(int[][] ratingsArray, int partnerIndex) {
        return calculateTotal(ratingsArray[partnerIndex]);
    }

    static double calculateAverage(int[] partnerRatings) {
        return (double) calculateTotal(partnerRatings) / partnerRatings.length;
    }

    static double calculateAverage(int[][] ratingsArray, int partnerIndex) {
        return calculateAverage(ratingsArray[partnerIndex]);
    }

    static double calculateAverage(int total, int count) {
        return (double) total / count;
    }

    static int linearSearch(int[] values, int target) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == target) {
                return i;
            }
        }
        return -1;
    }

    static void bubbleSort(int[] totals, String[] rankedPartners) {
        int n = totals.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (totals[j] < totals[j + 1]) {
                    int tempTotal = totals[j];
                    totals[j] = totals[j + 1];
                    totals[j + 1] = tempTotal;

                    String tempPartner = rankedPartners[j];
                    rankedPartners[j] = rankedPartners[j + 1];
                    rankedPartners[j + 1] = tempPartner;
                }
            }
        }
    }

    static void printRatingTable() {
        System.out.println("\nFood Delivery Partner Ratings\n");
        System.out.print("Partner\t");
        for (String item : criteria) {
            System.out.print(item + "\t");
        }
        System.out.println("Total\tAverage");

        for (int i = 0; i < ratings.length; i++) {
            System.out.print(partners[i] + "\t");
            for (int j = 0; j < ratings[i].length; j++) {
                System.out.print(ratings[i][j] + "\t");
            }
            int total = calculateTotal(ratings, i);
            double average = calculateAverage(total, ratings[i].length);
            System.out.println(total + "\t" + String.format("%.2f", average));
        }
    }

    public static void main(String[] args) {
        int[] totals = new int[ratings.length];
        double[] averages = new double[ratings.length];

        for (int i = 0; i < ratings.length; i++) {
            totals[i] = calculateTotal(ratings, i);
            averages[i] = calculateAverage(ratings, i);
        }

        printRatingTable();

        int targetRating = 18;
        int foundIndex = linearSearch(totals, targetRating);
        if (foundIndex != -1) {
            System.out.println("\nLinear Search Result:");
            System.out.println("Target total rating " + targetRating + " found at Partner " + (foundIndex + 1));
        } else {
            System.out.println("\nLinear Search Result:");
            System.out.println("Target total rating " + targetRating + " not found.");
        }

        String[] rankedPartners = partners.clone();
        int[] rankedTotals = totals.clone();
        bubbleSort(rankedTotals, rankedPartners);

        System.out.println("\nRanking by Bubble Sort (Highest to Lowest):");
        for (int i = 0; i < rankedPartners.length; i++) {
            System.out.println((i + 1) + ". " + rankedPartners[i] + " -> Total: " + rankedTotals[i]);
        }

        System.out.println("\nHighest-rated delivery partner: " + rankedPartners[0] + " with total " + rankedTotals[0]);
    }
}
