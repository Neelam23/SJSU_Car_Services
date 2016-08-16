package com.payment;

public abstract class PaymentAccount {
    protected PaymentType paymentTY;
    
    public PaymentAccount(PaymentType paymentTY)
    {
        this.paymentTY = paymentTY;
    }
    
    public abstract void payment();
    
    protected void cardPayment(){
    	paymentTY.cardPayment();
    }
    
    
}
