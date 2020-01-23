package com.example.calculator;

import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

public class Calculate implements MainActivity.CalculateListener {
    ArrayList<Object> postfixExprList = new ArrayList<>();
    Stack<String> stackUnit = new Stack<>();
    String num = "";
    Boolean isFloat = false;

    @Override
    public ArrayList<Object> toPostfixExpression(String infixExpr, int start) {
        if (start >= infixExpr.length()) {
            while (!stackUnit.empty()) {
                postfixExprList.add(stackUnit.pop());
            }
            return postfixExprList;
        }
        String target = Character.toString(infixExpr.charAt(start++));
        if (target.equals("(")) {
            stackUnit.push(target);
        } else if (target.equals(")")) {
            while (true) {
                postfixExprList.add(stackUnit.pop());
                if (stackUnit.empty()) break;
                if (stackUnit.peek().equals("(")) {
                    stackUnit.pop();
                    break;
                }
            }
        } else if (target.equals("+") || target.equals("-")) {
            if (stackUnit.empty()) {
                stackUnit.push(target);
            } else {
                if (stackUnit.peek().equals("*") || stackUnit.peek().equals("/")) {
                    while (!stackUnit.empty()) {
                        if (stackUnit.peek().equals("(")) {
                            stackUnit.pop();
                            break;
                        }
                        postfixExprList.add(stackUnit.pop());
                    }
                    stackUnit.push(target);
                } else {
                    stackUnit.push(target);
                }
            }
        } else if (target.equals("X") || target.equals("%") || target.equals("/")) {
            stackUnit.push(target);
        } else if (target.equals(".")) {
            num += target;
            isFloat = true;
        } else {
            num += target;
            double operand_db;
            int operand_int;
            if (start != infixExpr.length()) {
                String targetNext = Character.toString(infixExpr.charAt(start));
                for (Operation o : Operation.values()) {
                    if (targetNext.equals(o.getName()) || targetNext.equals(")")) {
                        if (isFloat) {
                            operand_db = Double.parseDouble(num);
                            num = "";
                            postfixExprList.add(operand_db);
                            isFloat = false;
                            break;
                        } else {
                            operand_int = Integer.parseInt(num);
                            num = "";
                            postfixExprList.add(operand_int);
                            break;
                        }
                    }
                }
            } else {
                if (isFloat) {
                    operand_db = Double.parseDouble(num);
                    num = "";
                    postfixExprList.add(operand_db);
                    isFloat = false;
                } else {
                    operand_int = Integer.parseInt(num);
                    num = "";
                    postfixExprList.add(operand_int);
                }
            }
        }
        return toPostfixExpression(infixExpr, start);
    }

    @Override
    public Object postfixExpressionCalculate(ArrayList<Object> exprList) {
        Stack<Object> operandStack = new Stack<>();
        for (Object o : exprList) {
            Object operand_1, operand_2;
            if (o instanceof Double || o instanceof Integer) {
                operandStack.push(o);
            } else {
                if (o.equals("+")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    if (operand_1 instanceof Double || operand_2 instanceof Double) {
                        operandStack.push(toDouble(operand_2) + toDouble(operand_1));
                    } else {
                        operandStack.push(toInt(operand_2) + toInt(operand_1));
                    }
                } else if (o.equals("-")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    if (operand_1 instanceof Double || operand_2 instanceof Double) {
                        operandStack.push(toDouble(operand_2) - toDouble(operand_1));
                    } else {
                        operandStack.push(toInt(operand_2) - toInt(operand_1));
                    }
                } else if (o.equals("X")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    if (operand_1 instanceof Double || operand_2 instanceof Double) {
                        operandStack.push(toDouble(operand_2) * toDouble(operand_1));
                    } else {
                        operandStack.push(toInt(operand_2) * toInt(operand_1));
                    }
                } else if (o.equals("/")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    if (operand_1 instanceof Double || operand_2 instanceof Double) {
                        operandStack.push(toDouble(operand_2) / toDouble(operand_1));
                    } else {
                        operandStack.push(toInt(operand_2) / toInt(operand_1));
                    }
                } else if (o.equals("%")) {
                    operand_1 = operandStack.pop();
                    operand_2 = operandStack.pop();
                    if (operand_1 instanceof Double || operand_2 instanceof Double) {
                        operandStack.push(toDouble(operand_2) % toDouble(operand_1));
                    } else {
                        operandStack.push(toInt(operand_2) % toInt(operand_1));
                    }
                }
            }
        }
        return operandStack.pop();
    }
    public double toDouble(Object o) {
        return Double.parseDouble(o.toString());
    }
    public int toInt(Object o) {
        return Integer.parseInt(o.toString());
    }

}
