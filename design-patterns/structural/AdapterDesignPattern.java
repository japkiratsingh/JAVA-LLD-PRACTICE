interface PaymentGateway {
    void pay(double amount);
}

class RazorPayPaymentGateway implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using RazorPay");
    }
}

class StripePaymentGateway {

    public void makePayment(double amount) {
        System.out.println("Paid " + amount + " using Stripe");
    }
}

class StripePaymentGatewayAdapter implements PaymentGateway {
    private StripePaymentGateway stripePaymentGateway;

    public StripePaymentGatewayAdapter(StripePaymentGateway stripePaymentGateway) {
        this.stripePaymentGateway = stripePaymentGateway;
    }

    @Override
    public void pay(double amount) {
        stripePaymentGateway.makePayment(amount);
    }
}
public class AdapterDesignPattern {
    public static void main(String[] args) {
        PaymentGateway razorPayPaymentGateway = new RazorPayPaymentGateway();
        PaymentGateway stripePaymentGatewayAdapter = new StripePaymentGatewayAdapter(new StripePaymentGateway());
        razorPayPaymentGateway.pay(100);
        stripePaymentGatewayAdapter.pay(200);
    }
}
