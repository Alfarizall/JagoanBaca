package com.example.tokobuku.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TransactionRequest {
    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("customerAddress")
    private String customerAddress;

    @JsonProperty("customerPhone")
    private String customerPhone;

    @JsonProperty("paymentMethod")
    private PaymentMethod paymentMethod;

    @JsonProperty("items")
    private List<TransactionItemRequest> items;

    // Getters and Setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<TransactionItemRequest> getItems() {
        return items;
    }

    public void setItems(List<TransactionItemRequest> items) {
        this.items = items;
    }
}
