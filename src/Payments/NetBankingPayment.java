package Payments;

public class NetBankingPayment implements payment {

	@Override
	public PaymentStatus pay(double amount) {
		// TODO Auto-generated method stub
		System.out.println("Processing Net Banking Payment....");


        if (amount < 10) {
            return PaymentStatus.FAILED;
        }
        return PaymentStatus.SUCCESS;
    }

	@Override
	public String getPaymentMode() {
		// TODO Auto-generated method stub
		return "Net Banking";
	}

	@Override
	public double getMaxLimit() {
		// TODO Auto-generated method stub
		return 100000;
	}	
}
