package CalculatorEtc.calculator;

public class Main {
    public static void main(String[] args) {
        int a = 5;
        float b = 7;

        double sumResult = Calculator.sum(a, b);
        System.out.println("Sum: " + sumResult);

        double multiplyResult = Calculator.multiply(a, b);
        System.out.println("Multiplication: " + multiplyResult);

        double divideResult = Calculator.divide(a, b);
        System.out.println("Division: " + divideResult);

        double subtractResult = Calculator.subtract(a, b);
        System.out.println("Subtraction: " + subtractResult);
    }
}
