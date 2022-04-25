package com.calculator.integerdivision.provider.arumentsproviders;

import com.calculator.integerdivision.domain.view.DivisionViewResult;
import com.calculator.integerdivision.domain.view.DivisionViewStep;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class DivisionViewArgumentsProvider implements ArgumentsProvider {
    static final private String LF = "\n";

    DivisionViewResult expected1 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_78945|4" + LF,
                    " 4    |-----" + LF,
                    " -----|19736" + LF
            ),
            buildStep(
                    "_38   |" + LF,
                    " 36   |" + LF,
                    " -----|" + LF
            ),
            buildStep(
                    " _29  |" + LF,
                    "  28  |" + LF,
                    "  ----|" + LF
            ),
            buildStep(
                    "  _14 |" + LF,
                    "   12 |" + LF,
                    "   ---|" + LF
            ),
            buildStep(
                    "   _25|" + LF,
                    "    24|" + LF,
                    "    --|" + LF
            )
    );

    DivisionViewResult expected2 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_1000|10" + LF,
                    " 1000|---" + LF,
                    " ----|100" + LF
            )
    );

    DivisionViewResult expected3 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_99999|9" + LF,
                    " 99999|-----" + LF,
                    " -----|11111" + LF
            )
    );

    DivisionViewResult expected4 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_93882|33" + LF,
                    " 66   |----" + LF,
                    " -----|2844" + LF
            ),
            buildStep(
                    "_278  |" + LF,
                    " 264  |" + LF,
                    " -----|" + LF
            ),
            buildStep(
                    " _148 |" + LF,
                    "  132 |" + LF,
                    "  ----|" + LF
            ),
            buildStep(
                    "  _162|" + LF,
                    "   132|" + LF,
                    "   ---|" + LF
            )
    );

    DivisionViewResult expected5 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_1|1" + LF,
                    " 1|-" + LF,
                    " -|1" + LF
            )
    );

    DivisionViewResult expected6 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_111111|111111" + LF,
                    " 111111|-" + LF,
                    " ------|1" + LF
            )
    );

    DivisionViewResult expected7 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_1111111|111111" + LF,
                    " 1111110|--" + LF,
                    " -------|10" + LF
            )
    );

    DivisionViewResult expected8 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_3123123|111111" + LF,
                    " 222222 |--" + LF,
                    " -------|28" + LF
            ),
            buildStep(
                    " _900903|" + LF,
                    "  888888|" + LF,
                    "  ------|" + LF
            )
    );

    DivisionViewResult expected9 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_671034|10000" + LF,
                    " 60000 |--" + LF,
                    " ------|67" + LF
            ),
            buildStep(
                    " _71034|" + LF,
                    "  70000|" + LF,
                    "  -----|" + LF
            )
    );

    DivisionViewResult expected10 = new DivisionViewResult().addAllSteps(
            buildStep(
                    "_7702|7" + LF,
                    " 7700|----" + LF,
                    " ----|1100" + LF
            )
    );

    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(78945, 4, expected1),
                Arguments.of(1000, 10, expected2),
                Arguments.of(99999, 9, expected3),
                Arguments.of(93882, 33, expected4),
                Arguments.of(1, 1, expected5),
                Arguments.of(111111, 111111, expected6),
                Arguments.of(1111111, 111111, expected7),
                Arguments.of(3123123, 111111, expected8),
                Arguments.of(671034, 10000, expected9),
                Arguments.of(7702, 7, expected10)
        );
    }

    private DivisionViewStep buildStep(String... viewLines) {
        return DivisionViewStep.newBuilder().addAllViewLines(viewLines).build();
    }
}
