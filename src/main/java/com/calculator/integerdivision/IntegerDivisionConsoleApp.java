package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionFinalResult;
import com.calculator.integerdivision.domain.view.DivisionViewResult;
import com.calculator.integerdivision.provider.DivisionMathProvider;
import com.calculator.integerdivision.provider.DivisionViewProvider;
import com.calculator.integerdivision.validator.DivisionNumberValidator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegerDivisionConsoleApp {

    private final Scanner consoleScanner;
    private final IntegerDivisionCalculator calculator;
    private final DivisionLogger logger;

    IntegerDivisionConsoleApp() {
        calculator = new IntegerDivisionCalculator(
                new DivisionMathProvider(),
                new DivisionViewProvider(),
                new DivisionNumberValidator()
        );
        consoleScanner = new Scanner(System.in);
        logger = new DivisionLogger();
    }

    public void run() {
        run(false);
    }

    /**
     * Runs integer division calculator in CLI
     *
     * @param doLog if true, all the info about program working
     *              is being recorded to the log file. By default,
     *              it set to false
     */
    public void run(boolean doLog) {
        int dividend = requestDividend();
        int divider = requestDivider();
        try {
            DivisionFinalResult finalResult = calc(dividend, divider);
            if (doLog) {
                log(finalResult);

            }
            printView(finalResult.getView());
        } catch (IllegalArgumentException e) {
            println(e.getMessage() + "\n");
        } finally {
            run(doLog);
        }
    }

    public void log(DivisionFinalResult finalResult) {
        String msg = finalResult.getMath().toString() + "\n" + finalResult.getView().toString();
        logger.log(msg);
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
     * @return input integer by user
     */
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
     * Calculates result of division with provided dividend
     * and divider
     *
     * @return calculated DivisionFinalResult instance
     * @throws IllegalArgumentException when dividend or divider were input incorrectly
     *                                  corresponding to the set validator
     */
    private DivisionFinalResult calc(int dividend, int divider) throws IllegalArgumentException {
        return calculator.calc(dividend, divider);
    }

    private void printView(DivisionViewResult viewResult) {
        println(viewResult);
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
