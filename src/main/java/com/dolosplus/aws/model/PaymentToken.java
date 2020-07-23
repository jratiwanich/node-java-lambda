package com.dolosplus.aws.model;

public class PaymentToken {
    private String id;
    private String token;
    //private String status;

// paymentData: ""
// paymentMethodDisplayName: "Simulated Instrument"
// paymentMethodNetwork: "Visa"
// paymentMethodTypeCard: "unknown"
// transactionIdentifier: "Simulated Identifier"

    public PaymentToken() {
    }

    public PaymentToken(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
