package com.calculator.integerdivision;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class DivisionViewArgumentsProvider implements ArgumentsProvider {
    String expected1 = """
            _78945|4
             4    |-----
             -    |19736
            _38
             36
             --
             _29
              28
              --
              _14
               12
               --
               _25
                24
                --
                1""";

    String expected2 = """
            _1000|10
             10  |-----
             --  |100
              _0
               0
               -
               _0
                0
                -""";

    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(78945, 4, expected1),
                Arguments.of(1000, 10, expected2)
        );
    }
}
