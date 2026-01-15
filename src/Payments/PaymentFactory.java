package Payments;

public class PaymentFactory {
	
	public static payment getPayment(int choice) {

	        switch (choice) {
	            case 1:
	                return new UpiPayment();
	            case 2:
	                return new NetBankingPayment();
	            case 3:
	                return new CreditCardPayment();
	            default:
	                throw new InvalidPaymentException("Invalid payment option selected");
	        }
	    }
}
