package Payments;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckoutService {

    private final List<Transaction> history = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public void processPayment(payment pymnt, double amnt) {

        while (true) {

            if (amnt <= 0) {
                throw new InvalidAmountException("Amount must be greater than 0");
            }

            if (amnt > pymnt.getMaxLimit()) {
                throw new InvalidAmountException(
                    "Invalid amount for " + pymnt.getPaymentMode() +
                    ".\nAllowed range: ₹1 – ₹" + (int) pymnt.getMaxLimit()
                );
            }

            System.out.println(
                "\nYou are paying ₹" + amnt + " using " +
                pymnt.getPaymentMode()
            );
            System.out.print("Confirm payment? (Y/N): ");

            char confirm = sc.next().toLowerCase().charAt(0);

            if (confirm != 'y') {
                System.out.println("❌ Payment cancelled by user.");
                System.out.println("1. Try again");
                System.out.println("2. Back to main menu");
                System.out.print("Select option: ");

                int opt = sc.nextInt();

                if (opt == 1) {
                    System.out.print("Enter amount (₹): ");
                    amnt = sc.nextDouble();   
                    continue;
                } else {
                    return;                
                }
            }

            PaymentStatus status = pymnt.pay(amnt);

            Transaction tx =
                    new Transaction(amnt, pymnt.getPaymentMode(), status);

            history.add(tx);

            if (status == PaymentStatus.SUCCESS) {
                System.out.println("✅ Payment Successful!");
                tx.printReceipt();
            } else {
                System.out.println("❌ Payment Failed.");
            }

            return; 
        }
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.println("\n==== TRANSACTION HISTORY ====");
        for (Transaction t : history) {
            System.out.println(
                t.getStatus() + " | " +
                t.getPaymentMode() + " | ₹" +
                t.getAmount()
            );
        }
        System.out.println("==============================");
    }

    public void showSummary() {
        long success = history.stream()
                .filter(t -> t.getStatus() == PaymentStatus.SUCCESS)
                .count();

        long failed = history.size() - success;

        System.out.println("\n==== TRANSACTION SUMMARY ====");
        System.out.println("Total   : " + history.size());
        System.out.println("Success : " + success);
        System.out.println("Failed  : " + failed);
        System.out.println("==============================");
    }
}
