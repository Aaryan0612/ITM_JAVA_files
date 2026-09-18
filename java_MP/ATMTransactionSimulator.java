import java.util.Scanner;

// Represents a bank account and its basic ATM operations.
class Account {
    // Private field demonstrates encapsulation.
    private double balance;

    // Constructor initializes the account with the given balance.
    public Account(double balance) {
        this.balance = balance;
    }

    // Deposits a positive amount into the account.
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive amount.");
            return;
        }

        balance += amount;
        System.out.printf("Deposited. Balance: ₹%.2f%n", balance);
    }

    // Withdraws money only when sufficient balance is available.
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive amount.");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds.");
            return;
        }

        balance -= amount;
        System.out.printf("Withdrawn. Balance: ₹%.2f%n", balance);
    }

    // Returns the current account balance.
    public double getBalance() {
        return balance;
    }
}

public class ATMTransactionSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account(0.0);
        int choice;

        System.out.println("===== ATM Transaction Simulator =====");

        // Menu repeats until the user selects option 4.
        do {
            System.out.println("1.Deposit 2.Withdraw 3.Check Balance 4.Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.printf("Balance: ₹%.2f%n", account.getBalance());
                    break;

                case 4:
                    // Exit the menu.
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        System.out.printf(
                "Thank you for banking with us. Final Balance: ₹%.2f%n",
                account.getBalance()
        );

        scanner.close();
    }
}
