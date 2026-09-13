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

import org.junit.jupiter.api.Test;
import walkingkooka.ToStringTesting;
import walkingkooka.reflect.PackagePrivateClassTesting;
import walkingkooka.reflect.TypeNameTesting;
import walkingkooka.text.LineEnding;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Interface with default methods which can be mixed in to assist testing of an {@link PrintedLineHandler}.
 */
public interface PrintedLineHandlerTesting2<H extends PrintedLineHandler>
    extends PrintedLineHandlerTesting,
    ToStringTesting<H>,
    TypeNameTesting<H>,
    PackagePrivateClassTesting<H> {

    // tests

    @Test
    default void testNullLineFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createLineHandler()
                .linePrinted(null,
                    LineEnding.NL,
                    Printers.fake()
                )
        );
    }

    @Test
    default void testNullLineEndingFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createLineHandler()
                .linePrinted(
                    "",
                    null,
                    Printers.fake()
                )
        );
    }

    @Test
    default void testNullPrinterFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createLineHandler()
                .linePrinted(
                    "",
                    LineEnding.NL,
                    null
                )
        );
    }

    H createLineHandler();

    default void linePrintedAndCheck(final CharSequence line,
                                     final LineEnding lineEnding) {
        this.linePrintedAndCheck(
            line,
            lineEnding,
            line.toString()
        );
    }

    default void linePrintedAndCheck(final CharSequence line, final LineEnding lineEnding,
                                     final String expected) {
        this.linePrintedAndCheck(
            line,
            lineEnding,
            expected,
            null
        );
    }

    default void linePrintedAndCheck(final CharSequence line,
                                     final LineEnding lineEnding,
                                     final String expected,
                                     final String message) {
        this.linePrintedAndCheck(
            this.createLineHandler(),
            line,
            lineEnding,
            expected,
            message
        );
    }

    // class.................. .........................................................................................

    @Override
    default String typeNamePrefix() {
        return "";
    }

    @Override
    default String typeNameSuffix() {
        return PrintedLineHandler.class.getSimpleName();
    }
}
