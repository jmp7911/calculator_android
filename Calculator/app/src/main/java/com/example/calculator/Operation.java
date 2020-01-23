package com.example.calculator;

public enum Operation {
    PLUS("+"),MINUS("-"),MULTIPLY("X"),DIVIDE("/"),REMAIN("%");
    final private String op;

    Operation(String op) {
        this.op = op;
    }
    public String getName() {
        return op;
    }
}
