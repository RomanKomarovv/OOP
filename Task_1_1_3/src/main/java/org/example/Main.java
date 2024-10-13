package org.example;

import java.util.HashMap;
import java.util.Map;


/**
 * Abstract class representing a mathematical expression.
 */
abstract class Expression {
    /**
     * Evaluates the expression with the given variable values.
     *
     * @param variableValues a map of variable names and their corresponding integer values
     *
     * @return the result of the evaluation
     */
    public abstract int eval(Map<String, Integer> variableValues);

    /**
     * Returns the derivative of the expression with respect to a given variable.
     *
     * @param variable the variable with respect to which the derivative is taken
     *
     * @return the derivative of the expression
     */
    public abstract Expression derivative(String variable);

    /**
     * Returns the string representation of the expression.
     *
     * @return the string representation
     */
    public abstract String toString();
}

/**
 * Class representing a constant number in an expression.
 */
class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public int eval(Map<String, Integer> variableValues) {
        return value;
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

/**
 * Class representing a variable in an expression.
 */
class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int eval(Map<String, Integer> variableValues) {
        if (!variableValues.containsKey(name)) {
            throw new RuntimeException("Variable " + name + " is not defined.");
        }
        return variableValues.get(name);
    }

    @Override
    public Expression derivative(String variable) {
        return name.equals(variable) ? new Number(1) : new Number(0);
    }

    @Override
    public String toString() {
        return name;
    }
}

/**
 * Class representing the addition of two expressions.
 */
class Add extends Expression {
    private final Expression left;
    private final Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(Map<String, Integer> variableValues) {
        return left.eval(variableValues) + right.eval(variableValues);
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }
}

/**
 * Class representing the subtraction of two expressions.
 */
class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(Map<String, Integer> variableValues) {
        return left.eval(variableValues) - right.eval(variableValues);
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }
}

/**
 * Class representing the multiplication of two expressions.
 */
class Mul extends Expression {
    private final Expression left;
    private final Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(Map<String, Integer> variableValues) {
        return left.eval(variableValues) * right.eval(variableValues);
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(
                new Mul(left.derivative(variable), right),
                new Mul(left, right.derivative(variable))
        );
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }
}

/**
 * Class representing the division of two expressions.
 */
class Div extends Expression {
    private final Expression left;
    private final Expression right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(Map<String, Integer> variableValues) {
        int denominator = right.eval(variableValues);
        if (denominator == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left.eval(variableValues) / denominator;
    }

    @Override
    public Expression derivative(String variable) {
        return new Div(
                new Sub(
                        new Mul(left.derivative(variable), right),
                        new Mul(left, right.derivative(variable))
                ),
                new Mul(right, right)
        );
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }
}

/**
 * Main class for testing mathematical expressions.
 */
public class Main {
    /**
     * Main method for testing the expression evaluation and differentiation.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        System.out.println("Expression: " + e);

        Map<String, Integer> variableValues = new HashMap<>();
        variableValues.put("x", 10);
        int result = e.eval(variableValues);
        System.out.println("Evaluated result for x=10: " + result);

        Expression de = e.derivative("x");
        System.out.println("Derivative with respect to x: " + de);

        Expression deY = e.derivative("y");
        System.out.println("Derivative with respect to y: " + deY);
    }
}
