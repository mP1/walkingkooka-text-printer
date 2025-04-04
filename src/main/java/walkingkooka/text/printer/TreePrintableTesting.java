/*
 * Copyright 2022 Miroslav Pokorny (github.com/mP1)
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

import org.opentest4j.AssertionFailedError;
import walkingkooka.Cast;
import walkingkooka.test.Testing;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public interface TreePrintableTesting extends Testing {

    Indentation INDENTATION = Indentation.SPACES2;

    /**
     * {@link LineEnding} are particularly important especially in {@link #treePrintAndCheck(TreePrintable, String)} where the {@link String} will have fixed line endings.
     * <br>
     * {@link LineEnding#NL} or <pre>\n</pre> should be used in all expected {@link String}.
     */
    LineEnding EOL = LineEnding.NL;

    default void treePrintAndCheck(final Optional<? extends TreePrintable> printable,
                                   final String expected) {
        this.treePrintAndCheck(
                printable.orElse(null),
                expected
        );
    }

    default void treePrintAndCheck(final TreePrintable printable,
                                   final String expected) {
        if (null == printable) {
            this.checkEquals(
                    expected,
                    null
            );
        } else {
            this.checkEquals(
                    expected,
                    printable.treeToString(
                            Indentation.SPACES2,
                            EOL
                    ),
                    printable::toString
            );
        }
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
            this.checkEquals(
                    TreePrintableTestingHelper.treePrintWithClassName(expected),
                    TreePrintableTestingHelper.treePrintWithClassName(actual),
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
            final String expectedString = TreePrintableTestingHelper.treePrintWithClassName(
                    (TreePrintable)expected
            );
            final String actualString = TreePrintableTestingHelper.treePrintWithClassName(
                    (TreePrintable)actual
            );

            Testing.super.checkNotEquals(
                    expectedString,
                    actualString,
                    message
            );

            throw new AssertionFailedError("Expected different but got <" + expectedString + "> and <" + actualString + ">");
        }
    }

    // Testing.........................................................................................................

    @Override
    default void checkEquals(final Object expected,
                             final Object actual,
                             final Supplier<String> message) {
        if (expected instanceof Optional && actual instanceof Optional) {
            final Optional<?> expectedOptional = Cast.to(expected);
            final Optional<?> actualOptional = Cast.to(actual);

            this.checkEquals(
                    expectedOptional.orElse(null),
                    actualOptional.orElse(null),
                    message
            );
        } else {
            if (expected instanceof TreePrintable && actual instanceof TreePrintable) {
                this.checkEquals((TreePrintable) expected, (TreePrintable) actual, message);
            } else {
                if (TreePrintableTestingHelper.shouldTreePrint(actual) && TreePrintableTestingHelper.shouldTreePrint(expected)) {
                    // extra test here because some TreePrintables might be equal but print differently.
                    // SpreadsheetDelta.deletedCell Set<SpreadsheetCellReference> ignores SpreadsheetReferenceKind
                    if(false == Objects.equals(expected, actual)) {
                        Testing.super.checkEquals(
                                TreePrintableTestingHelper.treePrint((Collection<?>) expected),
                                TreePrintableTestingHelper.treePrint((Collection<?>) actual),
                                message
                        );
                    }
                } else {
                    Testing.super.checkEquals(
                            expected,
                            actual,
                            message
                    );
                }
            }
        }
    }

    @Override
    default void checkNotEquals(final Object expected,
                                final Object actual,
                                final Supplier<String> message) {
        if (expected instanceof Optional && actual instanceof Optional) {
            final Optional<?> expectedOptional = Cast.to(expected);
            final Optional<?> actualOptional = Cast.to(actual);

            this.checkNotEquals(
                    expectedOptional.orElse(null),
                    actualOptional.orElse(null),
                    message
            );
        } else {
            if (expected instanceof TreePrintable && actual instanceof TreePrintable) {
                this.checkNotEquals(
                        (TreePrintable) expected,
                        (TreePrintable) actual,
                        message
                );
            } else {
                if (TreePrintableTestingHelper.shouldTreePrint(actual) && TreePrintableTestingHelper.shouldTreePrint(expected)) {
                    // extra test here because some TreePrintables might be equal but print differently.
                    // SpreadsheetDelta.deletedCell Set<SpreadsheetCellReference> ignores SpreadsheetReferenceKind
                    if(Objects.equals(expected, actual)) {
                        final String expectedString = TreePrintableTestingHelper.treePrint((Collection<?>) expected);
                        final String actualString = TreePrintableTestingHelper.treePrint((Collection<?>) actual);

                        Testing.super.checkNotEquals(
                                expectedString,
                                actualString,
                                message
                        );

                        throw new AssertionFailedError("Expected different but got <" + expectedString + "> and <" + actualString + ">");
                    };
                } else {
                    Testing.super.checkNotEquals(
                            expected,
                            actual,
                            message
                    );
                }
            }
        }
    }
}
