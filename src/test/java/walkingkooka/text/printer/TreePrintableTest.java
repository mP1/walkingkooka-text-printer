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
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.map.Maps;
import walkingkooka.collect.set.Sets;
import walkingkooka.naming.Names;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.CharSequences;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.math.RoundingMode;
import java.util.Optional;

public final class TreePrintableTest implements ClassTesting<TreePrintable> {

    private final static LineEnding EOL = LineEnding.NL;

    // printTreeOrToString..............................................................................................

    @Test
    public void testPrintTreeOrToStringNullValue() {
        this.printTreeOrToStringAndCheck(
            null,
            "null\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithBoolean() {
        this.printTreeOrToStringAndCheck(
            true,
            "true\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithByte() {
        this.printTreeOrToStringAndCheck(
            Byte.parseByte("12"),
            "12 (Byte)\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithShort() {
        this.printTreeOrToStringAndCheck(
            Short.parseShort("34"),
            "34 (Short)\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithInt() {
        this.printTreeOrToStringAndCheck(
            56,
            "56\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithLong() {
        this.printTreeOrToStringAndCheck(
            78L,
            "78L\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithFloat() {
        this.printTreeOrToStringAndCheck(
            9.0f,
            "9.0\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithDouble() {
        this.printTreeOrToStringAndCheck(
            111.0,
            "111.0\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithCharacter() {
        this.printTreeOrToStringAndCheck(
            'Z',
            "'Z'\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithString() {
        this.printTreeOrToStringAndCheck(
            "123abc",
            "\"123abc\"\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithEnum() {
        this.printTreeOrToStringAndCheck(
            RoundingMode.DOWN,
            "DOWN\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithName() {
        this.printTreeOrToStringAndCheck(
            Names.string("Hello"),
            "Hello\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithOptional() {
        this.printTreeOrToStringAndCheck(
            Optional.ofNullable("Hello"),
            "\"Hello\"\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithList() {
        this.printTreeOrToStringAndCheck(
            Lists.of("Hello"),
            "[\"Hello\"]\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithSet() {
        this.printTreeOrToStringAndCheck(
            Sets.of("Hello"),
            "[\"Hello\"]\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithMap() {
        this.printTreeOrToStringAndCheck(
            Maps.of("Key1", "Value2"),
            "{\"Key1\"=\"Value2\"}\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringWithNonTreePrintable() {
        this.printTreeOrToStringAndCheck(
            new StringBuilder("\"123\""),
            "\"123\" (java.lang.StringBuilder)\n"
        );
    }

    @Test
    public void testPrintTreeOrToStringTreePrintable() {
        final String string1 = "111";
        final String string2 = "222";

        this.printTreeOrToStringAndCheck(
            new TreePrintable() {
                @Override
                public void printTree(final IndentingPrinter printer) {
                    printer.println(string1);
                    printer.println(string2);
                }
            },
            string1 + EOL +
                string2 + EOL
        );
    }

    private void printTreeOrToStringAndCheck(final Object value,
                                             final String expected) {
        final StringBuilder b = new StringBuilder();
        try (final IndentingPrinter printer = Printers.stringBuilder(b, EOL).indenting(Indentation.SPACES2)) {
            TreePrintable.printTreeOrToString(value, printer);
            printer.flush();
        }
        this.checkEquals(
            expected,
            b.toString(),
            () -> "printTreeOrToString(" + CharSequences.quoteIfChars(value) + ")"
        );
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<TreePrintable> type() {
        return TreePrintable.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
