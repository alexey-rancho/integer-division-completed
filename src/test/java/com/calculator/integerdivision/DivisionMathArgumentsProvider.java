package com.calculator.integerdivision;

import com.calculator.integerdivision.domain.DivisionMathResult;
import com.calculator.integerdivision.domain.DivisionMathStep;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class DivisionMathArgumentsProvider implements ArgumentsProvider {
    DivisionMathResult expected1 = new DivisionMathResult(78945, 4)
            .addAllSteps(
                    buildStep(7, 1, 4, 3),
                    buildStep(38, 9, 36, 2),
                    buildStep(29, 7, 28, 1),
                    buildStep(14, 3, 12, 2),
                    buildStep(25, 6, 24, 1)
            );

    DivisionMathResult expected2 = new DivisionMathResult(1000, 10)
            .addAllSteps(
                    buildStep(1000, 100, 1000, 0)
            );

    DivisionMathResult expected3 = new DivisionMathResult(99999, 9)
            .addAllSteps(
                    buildStep(99999, 11111, 99999, 0)
            );

    DivisionMathResult expected4 = new DivisionMathResult(93882, 33)
            .addAllSteps(
                    buildStep(93, 2, 66, 27),
                    buildStep(278, 8, 264, 14),
                    buildStep(148, 4, 132, 16),
                    buildStep(162, 4, 132, 30)
            );

    DivisionMathResult expected5 = new DivisionMathResult(1, 1)
            .addAllSteps(
                    buildStep(1, 1, 1, 0)
            );

    DivisionMathResult expected6 = new DivisionMathResult(111111, 111111)
            .addAllSteps(
                    buildStep(111111, 1, 111111, 0)
            );

    DivisionMathResult expected7 = new DivisionMathResult(1111111, 111111)
            .addAllSteps(
                    buildStep(1111111, 10, 1111110, 1)
            );

    DivisionMathResult expected8 = new DivisionMathResult(3123123, 111111)
            .addAllSteps(
                    buildStep(312312, 2, 222222, 90090),
                    buildStep(900903, 8, 888888, 12015)
            );

    DivisionMathResult expected9 = new DivisionMathResult(671034, 10000)
            .addAllSteps(
                    buildStep(67103, 6, 60000, 7103),
                    buildStep(71034, 7, 70000, 1034)
            );

    DivisionMathResult expected10 = new DivisionMathResult(7702, 7)
            .addAllSteps(
                    buildStep(7702, 1100, 7700, 2)
            );

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(expected1),
                Arguments.of(expected2),
                Arguments.of(expected3),
                Arguments.of(expected4),
                Arguments.of(expected5),
                Arguments.of(expected6),
                Arguments.of(expected7),
                Arguments.of(expected8),
                Arguments.of(expected9),
                Arguments.of(expected10)
        );
    }

    private DivisionMathStep buildStep(int digit, int multiplier, int subNumber, int remainder) {
        return DivisionMathStep.newBuilder()
                .setDigit(digit)
                .setMultiplier(multiplier)
                .setSubNumber(subNumber)
                .setRemainder(remainder)
                .build();
    }
}
