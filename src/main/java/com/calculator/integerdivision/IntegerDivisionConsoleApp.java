package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionFinalResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionNumberValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegerDivisionConsoleApp {

    private final Scanner consoleScanner = new Scanner(System.in);
    private final IntegerDivisionCalculator calculator = new IntegerDivisionCalculator(
            new DivisionMathProvider(),
            new DivisionViewProvider(),
            new DivisionNumberValidator()
    );

    /**
     * Runs integer division calculator in CLI
     * */
    public void run() {
            int dividend = requestDividend();
            int divider = requestDivider();
            try {
                printView(dividend, divider);
            } catch (IllegalArgumentException e) {
                println(e.getMessage() + "\n");
            } finally {
                run();
            }

    }

    private int requestDividend() {
        return requestInteger("Dividend");
    }

    private int requestDivider() {
        return requestInteger("Divider");
    }

    /**
     * Requests to type integer from the user via CLI and looks
     * like this: "{title}: {user_input}". If user doesn't input
     * integer, request is being done again
     *
     * @param title title of the input integer
     * */
    private int requestInteger(String title) {
        print(title + ": ");
        try {
            return consoleScanner.nextInt();
        } catch (InputMismatchException e) {
            print(title + " must be an integer");
            return requestInteger(title);
        }
    }

    /**
     * Prints the division view calculated with provided
     * dividend and divider to CLI
     * @throws IllegalArgumentException when dividend or divider
     * were input incorrectly
     * */
    private void printView(int dividend, int divider) throws IllegalArgumentException {
        DivisionFinalResult result = calculator.calc(dividend, divider);
        println(result.getView());
    }

    private void print(String s) {
        System.out.print(s);
    }

    private void print(Object o) {
        System.out.print(o);
    }

    private void println(String s) {
        System.out.println(s);
    }

    private void println(Object o) {
        System.out.println(o);
    }

}
