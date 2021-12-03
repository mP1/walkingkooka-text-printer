/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package walkingkooka.text.printer;

import walkingkooka.test.Testing;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.util.Objects;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface TreePrintableTesting extends Testing {

    Indentation INDENTATION = Indentation.with("  ");
    LineEnding EOL = LineEnding.NL;

    default void treePrintAndCheck(final TreePrintable printable,
                                   final String expected) {
        assertEquals(
                expected,
                printable.treeToString(INDENTATION, EOL),
                () -> printable.toString()
        );
    }

    default void checkEquals(final TreePrintable expected,
                             final TreePrintable actual) {
        this.checkEquals(
                expected,
                actual,
                (String) null
        );
    }

    default void checkEquals(final TreePrintable expected,
                             final TreePrintable actual,
                             final String message) {
        this.checkEquals(
                expected,
                actual,
                () -> message
        );
    }

    default void checkEquals(final TreePrintable expected,
                             final TreePrintable actual,
                             final Supplier<String> message) {
        if (!Objects.equals(expected, actual)) {
            assertEquals(
                    null != expected ? expected.treeToString(INDENTATION, EOL) : null,
                    null != actual ? actual.treeToString(INDENTATION, EOL) : null,
                    message
            );
        }
    }

    default void checkNotEquals(final TreePrintable expected,
                                final TreePrintable actual) {
        this.checkNotEquals(
                expected,
                actual,
                (String) null
        );
    }

    default void checkNotEquals(final TreePrintable expected,
                                final TreePrintable actual,
                                final String message) {
        this.checkNotEquals(
                expected,
                actual,
                () -> message
        );
    }

    default void checkNotEquals(final TreePrintable expected,
                                final TreePrintable actual,
                                final Supplier<String> message) {
        if (Objects.equals(expected, actual)) {
            assertNotEquals(
                    null != expected ? expected.treeToString(INDENTATION, EOL) : null,
                    null != actual ? actual.treeToString(INDENTATION, EOL) : null,
                    message
            );
        }
    }
}
