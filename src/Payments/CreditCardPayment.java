package Payments;

public class CreditCardPayment implements payment{

	@Override
	public PaymentStatus pay(double amount) {
		// TODO Auto-generated method stub
	System.out.println("Processing Credit Card Payment...");

    if (amount < 100) {
        return PaymentStatus.FAILED;
    }
    return PaymentStatus.SUCCESS;
}
	@Override
	public String getPaymentMode() {
		// TODO Auto-generated method stub
		return "Credit Card";
	}
	@Override
	public double getMaxLimit() {
		// TODO Auto-generated method stub
		return 50000;
	}

}
