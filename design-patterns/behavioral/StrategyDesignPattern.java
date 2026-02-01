interface PaymentStrategy{
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy{
    @Override
    public void pay(double amount){
        System.out.println("Paid " + amount + " using Credit Card");
    }

}

class UPIPayment implements PaymentStrategy{
    @Override
    public void pay(double amount){
        System.out.println("Paid " + amount + " using UPI");
    }

}

class CashPayment implements PaymentStrategy{
    @Override
    public void pay(double amount){
        System.out.println("Paid " + amount + " using Cash");
    }
}

class PaymentContext{
    PaymentStrategy paymentStrategy;
    PaymentContext(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
    public void pay(double amount){
        this.paymentStrategy.pay(amount);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }
}

public class StrategyDesignPattern {
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext(new CreditCardPayment());
        paymentContext.pay(100);
        paymentContext.setPaymentStrategy(new UPIPayment());
        paymentContext.pay(200);
        paymentContext.setPaymentStrategy(new CashPayment());
        paymentContext.pay(300);
    }
}