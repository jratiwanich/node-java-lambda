package com.dolosplus.aws.model;

public class PaymentTokenRequest {
    private String id;

    public PaymentTokenRequest() {
    }

    public PaymentTokenRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
