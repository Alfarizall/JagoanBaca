package com.example.tokobuku.model;

public enum PaymentMethod {
    QRIS("QRIS"),
    GOPAY("GoPay"),
    OVO("OVO"),
    BCA("BCA"),
    BNI("BNI"),
    BRI("BRI"),
    MANDIRI("Mandiri");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
