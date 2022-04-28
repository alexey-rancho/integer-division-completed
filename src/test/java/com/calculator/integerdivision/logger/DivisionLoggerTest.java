package com.calculator.integerdivision.logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

public class DivisionLoggerTest {

    @Test
    void testLog() {
        DivisionLogger logger;
        try {
            logger = new DivisionLogger();
        } catch (IOException | SecurityException | IllegalArgumentException e) {
            exceptionFail(e);
            return;
        }

        String logMessage = "test log method";
        logger.log(logMessage);

        Scanner scanner;
        try {
            scanner = new Scanner(Path.of(logger.getLogFilePath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            exceptionFail(e);
            return;
        }

        Stream<String> actual = scanner.findAll(logMessage)
                .map(MatchResult::group);
        Stream<String> expected = Stream.of(logMessage);
        String errorMessage = "expected message '" + logMessage + "' to be logged in log-file once";

        Assertions.assertLinesMatch(expected, actual, errorMessage);
    }

    private void exceptionFail(Throwable t) {
        t.printStackTrace();
        Assertions.fail();
    }

}
