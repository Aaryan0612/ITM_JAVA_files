package java_assignments.assignment11;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class ProductInventoryManagementSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // HashSet stores unique product categories.
        HashSet<String> categories = new HashSet<>();

        // TreeSet stores unique product IDs in sorted order.
        TreeSet<Integer> productIds = new TreeSet<>();

        // TreeMap stores product ID -> product details.
        TreeMap<Integer, String> productCatalog = new TreeMap<>();

        int choice;

        System.out.println("===== Product Inventory Management System =====");

        do {
            System.out.println();
            System.out.println("1. Add Product Category");
            System.out.println("2. Add Product ID");
            System.out.println("3. Add Product to Catalog");
            System.out.println("4. Display All Products");
            System.out.println("5. Find Nearest Product ID");
            System.out.println("6. Display Products in ID Range");
            System.out.println("7. Remove Product");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Enter category name: ");
                        String category = scanner.nextLine().trim();

                        if (category.isEmpty()) {
                            System.out.println("Error: Category name cannot be empty.");
                        } else if (categories.add(category)) {
                            System.out.println("Category added: " + category);
                        } else {
                            System.out.println(
                                    "Category already exists. Duplicate ignored."
                            );
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Enter product ID: ");
                            int productId = Integer.parseInt(scanner.nextLine());

                            if (productIds.add(productId)) {
                                System.out.println("Product ID added successfully.");

                                System.out.print("Sorted Product IDs: ");
                                Iterator<Integer> iterator = productIds.iterator();

                                while (iterator.hasNext()) {
                                    System.out.print(iterator.next());

                                    if (iterator.hasNext()) {
                                        System.out.print(", ");
                                    }
                                }
                                System.out.println();

                            } else {
                                System.out.println(
                                        "Product ID already exists. Duplicate ignored."
                                );
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Error: Product ID must be a valid integer."
                            );
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Enter product ID: ");
                            int id = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter product name and price: ");
                            String details = scanner.nextLine().trim();

                            if (details.isEmpty()) {
                                System.out.println(
                                        "Error: Product details cannot be empty."
                                );
                                break;
                            }

                            productCatalog.put(id, details);

                            // Keep the TreeSet of IDs synchronized.
                            productIds.add(id);

                            // Demonstrate TreeMap get().
                            System.out.println(
                                    "Product added: "
                                            + productCatalog.get(id)
                            );

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Error: Product ID must be a valid integer."
                            );
                        }
                        break;

                    case 4:
                        System.out.println();
                        System.out.println("===== All Products (Sorted by ID) =====");

                        if (productCatalog.isEmpty()) {
                            System.out.println("Catalog is empty.");
                        } else {
                            // TreeMap entrySet() is naturally sorted by key.
                            for (Map.Entry<Integer, String> entry
                                    : productCatalog.entrySet()) {

                                System.out.println(
                                        entry.getKey() + " -> " + entry.getValue()
                                );
                            }
                        }
                        break;

                    case 5:
                        try {
                            if (productCatalog.isEmpty()) {
                                throw new NoSuchElementException(
                                        "Product catalog is empty. Add products first."
                                );
                            }

                            System.out.print("Enter ID to search nearest: ");
                            int searchId = Integer.parseInt(scanner.nextLine());

                            Integer floorId = productCatalog.floorKey(searchId);
                            Integer ceilingId = productCatalog.ceilingKey(searchId);

                            if (floorId != null) {
                                System.out.println("Floor ID  : " + floorId);
                            } else {
                                System.out.println(
                                        "Floor ID  : No product ID at or below the given ID."
                                );
                            }

                            if (ceilingId != null) {
                                System.out.println("Ceiling ID: " + ceilingId);
                            } else {
                                System.out.println(
                                        "Ceiling ID: No product ID at or above the given ID."
                                );
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Error: Product ID must be a valid integer."
                            );
                        } catch (NoSuchElementException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 6:
                        try {
                            if (productCatalog.isEmpty()) {
                                throw new NoSuchElementException(
                                        "Product catalog is empty. Add products first."
                                );
                            }

                            System.out.print("Enter starting ID: ");
                            int fromId = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter ending ID: ");
                            int toId = Integer.parseInt(scanner.nextLine());

                            if (fromId > toId) {
                                System.out.println(
                                        "Error: Starting ID cannot be greater than ending ID."
                                );
                                break;
                            }

                            // subMap() includes both the starting and ending IDs.
                            var productsInRange =
                                    productCatalog.subMap(fromId, true, toId, true);

                            System.out.println();
                            System.out.println("===== Products in Range =====");

                            if (productsInRange.isEmpty()) {
                                System.out.println(
                                        "No products found in the specified range."
                                );
                            } else {
                                for (Map.Entry<Integer, String> entry
                                        : productsInRange.entrySet()) {

                                    System.out.println(
                                            entry.getKey() + " -> " + entry.getValue()
                                    );
                                }
                            }

                            // Demonstrate headMap() and tailMap().
                            System.out.println(
                                    "Products from beginning through "
                                            + toId + ": "
                                            + productCatalog.headMap(toId, true).size()
                            );

                            System.out.println(
                                    "Products from " + fromId
                                            + " onward: "
                                            + productCatalog.tailMap(fromId, true).size()
                            );

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Error: Product IDs must be valid integers."
                            );
                        } catch (NoSuchElementException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 7:
                        try {
                            if (productCatalog.isEmpty()) {
                                throw new NoSuchElementException(
                                        "Product catalog is empty. Nothing to remove."
                                );
                            }

                            System.out.print("Enter product ID to remove: ");
                            int removeId = Integer.parseInt(scanner.nextLine());

                            if (productCatalog.containsKey(removeId)) {
                                productCatalog.remove(removeId);
                                productIds.remove(removeId);

                                System.out.println(
                                        "Product removed successfully."
                                );
                            } else {
                                System.out.println(
                                        "Product ID not found in the catalog."
                                );
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Error: Product ID must be a valid integer."
                            );
                        } catch (NoSuchElementException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 8:
                        System.out.println(
                                "Exiting Product Inventory Management System."
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
                        "Error: Please enter a valid menu number from 1 to 8."
                );
            }

        } while (choice != 8);

        scanner.close();
    }
}
