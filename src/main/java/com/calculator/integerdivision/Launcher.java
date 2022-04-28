package com.calculator.integerdivision;

public class Launcher {

    public static void main(String[] args) {
        IntegerDivisionConsoleApp consoleApp = new IntegerDivisionConsoleApp(
                args.length > 0 && args[0].equals("-l")
        );
        consoleApp.run();
    }

}
