class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class InsufficientBalace {
    static void withdraw(double balance, double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance.");
        }

        balance = balance - amount;
        System.out.println("Withdrawal successful.");
    }

    public static void main(String[] args) {
        try {
            withdraw(100, 200);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}