package Payments;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final CheckoutService CHECKOUT_SERVICE = new CheckoutService();

    public static void main(String[] args) {
        runApp();
        SCANNER.close();
    }

    private static void runApp() {
        boolean running = true;

        while (running) {
            try {
                showMenu();
                int choice = readChoice();

                if (choice == 0) {
                    CHECKOUT_SERVICE.showSummary();
                    System.out.println("Thank you for using the Payment System 🙏");
                    running = false;
                    continue;
                }

                if (choice == 4) {
                    CHECKOUT_SERVICE.showHistory();
                    continue;
                }

                payment payment = PaymentFactory.getPayment(choice);

                System.out.println(
                        payment.getPaymentMode() +
                        " allows payment between ₹1 and ₹" +
                        (int) payment.getMaxLimit()
                );

                boolean amountFlow = true;

                while (amountFlow) {
                    try {
                        double amount = readAmount();
                        CHECKOUT_SERVICE.processPayment(payment, amount);
                        amountFlow = false; // payment attempt done

                    } catch (InvalidAmountException e) {
                        System.out.println("❌ " + e.getMessage());
                        System.out.println("\n1. Re-enter amount");
                        System.out.println("2. Back to main menu");
                        System.out.print("Select an option: ");

                        int opt = SCANNER.nextInt();

                        if (opt == 1) {
                            continue; 
                        } else {
                            amountFlow = false; 
                        }
                    }
                }

            } catch (InputMismatchException e) {
                SCANNER.next(); 
                showError("Please enter numeric values only");
            } catch (Exception e) {
                showError(e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n==== PAYMENT MENU ====");
        System.out.println("1. UPI");
        System.out.println("2. Net Banking");
        System.out.println("3. Credit Card");
        System.out.println("4. View Transaction History");
        System.out.println("0. Exit");
        System.out.println("======================");
    }

    private static int readChoice() {
        System.out.print("Select an option: ");
        return SCANNER.nextInt();
    }

    private static double readAmount() {
        System.out.print("Enter amount (₹): ");
        return SCANNER.nextDouble();
    }

    private static void showError(String message) {
        System.out.println("❌ Error: " + message);
    }
}
