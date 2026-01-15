package Payments;

public class UpiPayment implements payment{


	@Override
	public PaymentStatus pay(double amount) {
		// TODO Auto-generated method stub
		System.out.println("Processing UPI Payment....");
		 if (amount > 5000) {
		        return PaymentStatus.FAILED;
		    }
		    return PaymentStatus.SUCCESS;
		}
	

	@Override
	public String getPaymentMode() {
		// TODO Auto-generated method stub
		return "UPI";
	}


	@Override
	public double getMaxLimit() {
		// TODO Auto-generated method stub
		return 5000;
	}


}
