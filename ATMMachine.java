import java.util.Scanner;

class ATM {
    private double balance;
    private String pin;
    private Scanner scanner;
    private final double MIN_BALANCE = 10000.00;

    public ATM(double initialBalance, String correctPin) {
        this.balance = initialBalance;
        this.pin = correctPin;
        this.scanner = new Scanner(System.in);
    }

    public boolean checkPin() {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine();
            if (enteredPin.equals(this.pin)) {
                System.out.println("PIN verified successfully!");
                return true;
            } else {
                attempts--;
                System.out.println("Incorrect PIN. You have " + attempts + " attempts remaining.");
            }
        }
        System.out.println("Too many incorrect attempts. Account locked.");
        return false;
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawMoney();
                    break;
                case 3:
                    depositMoney();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
        scanner.close();
    }

    public void checkBalance() {
        System.out.println("Your current balance is: Rs " + String.format("%.2f", balance));
        if (balance < MIN_BALANCE) {
            System.out.println("Warning: Please maintain a minimum balance of Rs " + String.format("%.2f", MIN_BALANCE) + " for further transactions.");
        }
    }

    public void withdrawMoney() {
        System.out.print("Enter the amount to withdraw (in Rupees): ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        if (amount > 0 && (balance - amount) >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Rs " + String.format("%.2f", amount) + " withdrawn successfully.");
            System.out.println("Your new balance is: Rs " + String.format("%.2f", balance));
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            System.out.println("Insufficient funds or withdrawal would violate the minimum balance requirement.");
        }
    }

    public void depositMoney() {
        System.out.print("Enter the amount to deposit (in Rupees): ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        if (amount > 0) {
            balance += amount;
            System.out.println("Rs " + String.format("%.2f", amount) + " deposited successfully.");
            System.out.println("Your new balance is: Rs " + String.format("%.2f", balance));
            if (balance < MIN_BALANCE) {
                System.out.println("Warning: Please maintain a minimum balance of Rs " + String.format("%.2f", MIN_BALANCE) + " for further transactions.");
            }
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
}

public class ATMMachine {
    public static void main(String[] args) {
        // You can customize the initial balance and PIN here
        ATM atm = new ATM(15000.00, "1234"); // Initial balance in Rupees

        if (atm.checkPin()) {
            atm.menu();
        }
    }
}