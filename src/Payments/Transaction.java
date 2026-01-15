package Payments;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
	
	    private String transactionId;
	    private double amount;
	    private String paymentMode;
	    private PaymentStatus status;
	    private LocalDateTime dateTime;

	    public Transaction(double amount, String paymentMode, PaymentStatus status) {
	        this.transactionId = UUID.randomUUID().toString();
	        this.amount = amount;
	        this.paymentMode = paymentMode;
	        this.status = status;
	        this.dateTime = LocalDateTime.now();
	    }
	    
	    public PaymentStatus getStatus() {
	        return status;
	    }
	    public double getAmount() {
	        return amount;
	    }
	    public String getPaymentMode() {
	        return paymentMode;
	    }

	    public void printReceipt() {
	        System.out.println("\n----- PAYMENT RECEIPT -----");
	        System.out.println("Transaction ID : " + transactionId);
	        System.out.println("Payment Mode   : " + paymentMode);
	        System.out.println("Amount         : ₹" + amount);
	        System.out.println("Status         : " + status);
	        System.out.println("Date & Time    : " + dateTime);
	        System.out.println("----------------------------\n");
	    }
}
