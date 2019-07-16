package com.red.code.onlineshopping.common.enums;

public enum BloodGroup {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-");

    private String value;

    BloodGroup(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

}
