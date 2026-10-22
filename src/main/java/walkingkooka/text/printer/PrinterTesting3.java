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

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Interface with default methods which can be mixed in to assist testing of an {@link Printer}.
 */
public interface PrinterTesting3<P extends Printer> extends PrinterTesting2<P> {

    @Test
    default void testTransformText() {
        final Printer printer = this.createPrinter();
        final Function<CharSequence, CharSequence> transformer = (s) -> s.toString().toUpperCase();
        final Printer transformingPrinter = printer.transformText(transformer);

        this.checkEquals(
            PrinterTextTransforming.class,
            transformingPrinter.getClass(),
            transformingPrinter::toString
        );

        assertSame(
            transformer,
            ((PrinterTextTransforming) transformingPrinter).transformer,
            "transformer"
        );
    }

    @Override
    default P createPrinter() {
        return this.createPrinter(new StringBuilder());
    }

    P createPrinter(final StringBuilder target);

    default void printAndCheck(final CharSequence printed) {
        this.printAndCheck(
            printed,
            printed.toString()
        );
    }

    default void printAndCheck(final CharSequence printed,
                               final String expected) {
        this.printAndCheck(
            printed,
            expected,
            null
        );
    }

    default void printAndCheck(final CharSequence printed,
                               final String expected,
                               final String message) {
        this.printAndCheck(
            new CharSequence[]{printed},
            expected,
            message
        );
    }

    default void printAndCheck(final CharSequence[] chars,
                               final String expected) {
        this.printAndCheck(
            chars,
            expected,
            null
        );
    }

    default void printAndCheck(final CharSequence[] chars,
                               final String expected,
                               final String message) {
        final StringBuilder target = new StringBuilder();
        this.printAndCheck(
            this.createPrinter(target),
            chars,
            target,
            expected,
            message
        );
    }
}
