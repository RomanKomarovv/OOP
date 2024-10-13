package org.example;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ExpressionTest {

    @Test
    void testNumberEvaluation() {
        Expression number = new Number(5);
        Map<String, Integer> values = new HashMap<>();
        assertEquals(5, number.eval(values));
    }

    @Test
    void testVariableEvaluation() {
        Expression variable = new Variable("x");
        Map<String, Integer> values = new HashMap<>();
        values.put("x", 10);
        assertEquals(10, variable.eval(values));
    }

    @Test
    void testVariableNotFound() {
        Expression variable = new Variable("x");
        Map<String, Integer> values = new HashMap<>();
        Exception exception = assertThrows(RuntimeException.class, () -> variable.eval(values));
        assertEquals("Variable x is not defined.", exception.getMessage());
    }

    @Test
    void testAdditionEvaluation() {
        Expression expr = new Add(new Number(3), new Number(7));
        Map<String, Integer> values = new HashMap<>();
        assertEquals(10, expr.eval(values));
    }

    @Test
    void testSubtractionEvaluation() {
        Expression expr = new Sub(new Number(10), new Number(4));
        Map<String, Integer> values = new HashMap<>();
        assertEquals(6, expr.eval(values));
    }

    @Test
    void testMultiplicationEvaluation() {
        Expression expr = new Mul(new Number(2), new Number(3));
        Map<String, Integer> values = new HashMap<>();
        assertEquals(6, expr.eval(values));
    }

    @Test
    void testDivisionEvaluation() {
        Expression expr = new Div(new Number(10), new Number(2));
        Map<String, Integer> values = new HashMap<>();
        assertEquals(5, expr.eval(values));
    }

    @Test
    void testDivisionByZero() {
        Expression expr = new Div(new Number(10), new Number(0));
        Map<String, Integer> values = new HashMap<>();
        Exception exception = assertThrows(ArithmeticException.class, () -> expr.eval(values));
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void testComplexExpressionEvaluation() {
        Expression expr = new Add(
                new Number(3),
                new Mul(
                        new Number(2),
                        new Variable("x")
                )
        );
        Map<String, Integer> values = new HashMap<>();
        values.put("x", 10);
        assertEquals(23, expr.eval(values));
    }

    @Test
    void testPrintExpression() {
        Expression expr = new Add(
                new Number(3),
                new Mul(
                        new Number(2),
                        new Variable("x")
                )
        );
        assertEquals("(3+(2*x))", expr.toString());
    }

    @Test
    void testSimpleDerivative() {
        Expression expr = new Add(new Number(3), new Variable("x"));
        Expression derivative = expr.derivative("x");
        assertEquals("(0+1)", derivative.toString());
    }

    @Test
    void testComplexDerivative() {
        Expression expr = new Add(
                new Number(3),
                new Mul(
                        new Number(2),
                        new Variable("x")
                )
        );
        Expression derivative = expr.derivative("x");
        assertEquals("(0+((0*x)+(2*1)))", derivative.toString());
    }

    @Test
    void testDerivativeWithNoVariable() {
        Expression expr = new Number(5);
        Expression derivative = expr.derivative("x");
        assertEquals("0", derivative.toString());
    }

    @Test
    void testSubtractionDerivative() {
        Expression expr = new Sub(new Variable("x"), new Number(2));
        Expression derivative = expr.derivative("x");
        assertEquals("(1-0)", derivative.toString());
    }

    @Test
    void testMultiplicationDerivative() {
        Expression expr = new Mul(new Variable("x"), new Number(2));
        Expression derivative = expr.derivative("x");
        assertEquals("((1*2)+(x*0))", derivative.toString());
    }

    @Test
    void testDivisionDerivative() {
        Expression expr = new Div(new Variable("x"), new Number(2));
        Expression derivative = expr.derivative("x");
        assertEquals("(((1*2)-(x*0))/(2*2))", derivative.toString());
    }

    @Test
    void testDerivativeWithMultipleVariables() {
        Expression expr = new Add(new Variable("x"), new Variable("y"));
        Expression derivativeX = expr.derivative("x");
        assertEquals("(1+0)", derivativeX.toString());

        Expression derivativeY = expr.derivative("y");
        assertEquals("(0+1)", derivativeY.toString());
    }

}
