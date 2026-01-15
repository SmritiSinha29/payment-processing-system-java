package Payments;

public interface payment {
	
     PaymentStatus pay(double amount);
     
     String getPaymentMode();
     
     double getMaxLimit();
}
