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

import org.junit.jupiter.api.Test;
import walkingkooka.text.printer.TreePrintableTestingTest.TestTreePrintable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TreePrintableTestingTest implements TreePrintableTesting<TestTreePrintable> {

    @Test
    public void testTreePrintAndCheck() {
        this.treePrintAndCheck(
                "Before\n" +
                        "  Between\n" +
                        "After\n"
        );
    }

    @Test
    public void testTreePrintAndCheck2() {
        this.treePrintAndCheck(
                new TreePrintable() {

                    @Override
                    public void printTree(final IndentingPrinter printer) {
                        printer.print("Before1\n");
                        printer.indent();
                        printer.print("Between2\n");
                        printer.outdent();
                        printer.print("After3\n");
                    }
                },
                "Before1\n" +
                        "  Between2\n" +
                        "After3\n"
        );
    }

    @Test
    public void testCheckEqualsNullNull() {
        this.checkEquals(null, null, "message");
    }

    @Test
    public void testCheckEqualsNullTreePrintableFails() {
        boolean failed = false;
        try {
            this.checkEquals(null, this.createTreePrintable(), "message");
        } catch (final AssertionError expected) {
            failed = true;
        }
        assertEquals(true, failed);
    }

    @Test
    public void testCheckEquals() {
        this.checkEquals(this.createTreePrintable(), this.createTreePrintable(), "message");
    }

    @Test
    public void testCheckEqualsTreePrintableNullFails() {
        boolean failed = false;
        try {
            this.checkEquals(this.createTreePrintable(), null, "message");
        } catch (final AssertionError expected) {
            failed = true;
        }
        assertEquals(true, failed);
    }

    @Override
    public TestTreePrintable createTreePrintable() {
        return new TestTreePrintable();
    }

    static class TestTreePrintable implements TreePrintable {

        @Override
        public void printTree(final IndentingPrinter printer) {
            printer.print("Before\n");
            printer.indent();
            printer.print("Between\n");
            printer.outdent();
            printer.print("After\n");
        }
    }
}
