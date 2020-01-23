package com.example.calculator;

public class HistoryItem {
    private String infixExpression;
    private String result;

    HistoryItem(String infixExpression, String result) {
        this.infixExpression = infixExpression;
        this.result = result;
    }
    HistoryItem() {

    };
    public String getInfixExpression() {
        return infixExpression;
    }
    public String getResult() {
        return result;
    }
    public void setInfixExpression(String infixExpression) {
        this.infixExpression = infixExpression;
    }
    public void setResult(String result) {
        this.result = result;
    }
}
