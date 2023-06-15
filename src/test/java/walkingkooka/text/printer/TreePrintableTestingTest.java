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

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TreePrintableTestingTest implements TreePrintableTesting {

    private final static TreePrintable NULL_TREE_PRINTABLE = null;

    private final static TreePrintable TREE_PRINTABLE = new TreePrintable() {

        @Override
        public void printTree(final IndentingPrinter printer) {
            printer.print("Before1\n");
            printer.indent();
            printer.print("Between2\n");
            printer.outdent();
            printer.print("After3\n");
        }

        @Override
        public String toString() {
            return "TREE_PRINTABLE";
        }
    };

    @Test
    public void testTreePrintAndCheck() {
        this.treePrintAndCheck(
                TREE_PRINTABLE,
                "Before1\n" +
                        "  Between2\n" +
                        "After3\n"
        );
    }

    // checkEquals Collection...........................................................................................

    @Test
    public void testCheckEqualsList() {
        this.checkEquals(
                Lists.of(1, 2, 3),
                Lists.of(1, 2, 3)
        );
    }

    @Test
    public void testCheckEqualsListTreePrintedFails() {
        final AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> this.checkEquals(
                            Lists.of(null, TREE_PRINTABLE, true, 'A', 1.0, 2.5f, 3, Long.MAX_VALUE, Short.MAX_VALUE, "String"),
                            Lists.of(TREE_PRINTABLE)
                    )
        );

        this.checkEquals(
                "expected: <null\n" +
                        "Before1\n" +
                        "  Between2\n" +
                        "After3\n" +
                        "\n" + // should TreePrintable.treePrintOrString print the String followed by a new line.
                        "true\n" +
                        "A\n" +
                        "1.0\n" +
                        "2.5\n" +
                        "3\n" +
                        "9223372036854775807\n" +
                        "32767\n" +
                        "String\n" +
                        "> but was: <Before1\n" +
                        "  Between2\n" +
                        "After3\n" +
                        "\n" +
                        ">",
                thrown.getMessage()
        );
    }

    // checkNotEquals Collection...........................................................................................

    @Test
    public void testCheckNotEqualsList() {
        this.checkNotEquals(
                Lists.of(1, 2, 3),
                Lists.of(3, 2, TREE_PRINTABLE)
        );
    }

    @Test
    public void testCheckNotEqualsListTreePrintedFails() {
        final Collection<?> collection = Lists.of(null, TREE_PRINTABLE, true, 'A', 1.0, 2.5f, 3, Long.MAX_VALUE, Short.MAX_VALUE, "String");

        final AssertionError thrown = assertThrows(
                AssertionError.class,
                () -> this.checkNotEquals(
                        collection,
                        collection
                )
        );

        this.checkEquals(
                "expected: not equal but was: <null\n" +
                        "Before1\n" +
                        "  Between2\n" +
                        "After3\n" +
                        "\n" +
                        "true\n" +
                        "A\n" +
                        "1.0\n" +
                        "2.5\n" +
                        "3\n" +
                        "9223372036854775807\n" +
                        "32767\n" +
                        "String\n" +
                        ">",
                thrown.getMessage()
        );
    }

    // checkEquals TreePrintable........................................................................................

    @Test
    public void testCheckEqualsTreePrintableNullNull() {
        this.checkEquals(NULL_TREE_PRINTABLE, NULL_TREE_PRINTABLE, "message");
    }

    @Test
    public void testCheckEqualsTreePrintableNullTreePrintableFails() {
        boolean failed = false;
        try {
            this.checkEquals(NULL_TREE_PRINTABLE, this.createTreePrintable(), "message");
        } catch (final AssertionError expected) {
            failed = true;
        }
        this.checkEquals(true, failed);
    }

    @Test
    public void testCheckEqualsTreePrintable() {
        this.checkEquals(this.createTreePrintable(), this.createTreePrintable(), "message");
    }

    @Test
    public void testCheckEqualsTreePrintableNullFails() {
        boolean failed = false;
        try {
            this.checkEquals(this.createTreePrintable(), NULL_TREE_PRINTABLE, "message");
        } catch (final AssertionError expected) {
            failed = true;
        }
        assertEquals(true, failed);
    }

    // checkNotEquals TreePrintable........................................................................................

    @Test
    public void testCheckNotEqualsTreePrintableNullNull() {
        boolean failed = false;
        try {
            this.checkNotEquals(NULL_TREE_PRINTABLE, NULL_TREE_PRINTABLE, "message");
        } catch (final AssertionError expected) {
            failed = true;
        }
        this.checkEquals(true, failed);
    }

    @Test
    public void testCheckNotEqualsTreePrintableNullTreePrintableFails() {
        this.checkNotEquals(NULL_TREE_PRINTABLE, this.createTreePrintable(), "message");
    }

    @Test
    public void testCheckNotEqualsTreePrintable() {
        this.checkNotEquals(new TestTreePrintable("111"), new TestTreePrintable("222"), "message");
    }

    @Test
    public void testCheckNotEqualsTreePrintableFails() {
        final String printTree = "111";

        boolean failed = false;
        try {
            this.checkNotEquals(new TestTreePrintable(printTree), new TestTreePrintable(printTree), "message");
        } catch (final AssertionError expected) {
            assertEquals(
                    "message ==> expected: not equal but was: <walkingkooka.text.printer.TreePrintableTestingTest$TestTreePrintable\n" +
                            "  111>",
                    expected.getMessage(),
                    "message"
            );
            failed = true;
        }
        assertEquals(true, failed);
    }

    // checkEquals(Object,Object).......................................................................................

    @Test
    public void testCheckEqualsOptionalTreePrintable() {
        this.checkEquals(
                Optional.of(
                        this.createTreePrintable()
                ),
                Optional.of(
                        this.createTreePrintable()
                ),
                "message"
        );
    }

    @Test
    public void testCheckEqualsOptionalTreePrintableNullFails() {
        boolean failed = false;
        try {
            this.checkEquals(
                    Optional.of(
                            this.createTreePrintable()
                    ),
                    Optional.ofNullable(
                            NULL_TREE_PRINTABLE
                    ), "message"
            );
        } catch (final AssertionError expected) {
            failed = true;
        }
        assertEquals(true, failed);
    }

    // checkNotEquals(Object,Object)....................................................................................

    @Test
    public void testCheckNotEqualsOptionalTreePrintable() {
        this.checkNotEquals(
                Optional.of(
                        new TestTreePrintable("111")
                ),
                Optional.of(
                        new TestTreePrintable("222")
                ),
                "message"
        );
    }

    @Test
    public void testCheckNotEqualsOptionalTreePrintableFails() {
        final String printTree = "111";

        boolean failed = false;
        try {
            this.checkNotEquals(
                    Optional.of(
                            new TestTreePrintable(printTree)
                    ),
                    Optional.of(
                            new TestTreePrintable(printTree)
                    ),
                    "message"
            );
        } catch (final AssertionError expected) {
            assertEquals(
                    "message ==> expected: not equal but was: <walkingkooka.text.printer.TreePrintableTestingTest$TestTreePrintable\n" +
                            "  111>",
                    expected.getMessage(),
                    "message"
            );
            failed = true;
        }
        assertEquals(true, failed);
    }

    // Testing..........................................................................................................

    @Test
    public void testCheckEqualsObject() {
        this.checkEquals(1, 1);
    }

    @Test
    public void testCheckEqualsObjectFails() {
        boolean failed = false;
        try {
            this.checkEquals(
                    (Object) new TestTreePrintable("123"),
                    (Object) new TestTreePrintable("different")
            );
        } catch (final AssertionError expected) {
            assertEquals(
                    "expected: <walkingkooka.text.printer.TreePrintableTestingTest$TestTreePrintable\n" +
                            "  123> but was: <walkingkooka.text.printer.TreePrintableTestingTest$TestTreePrintable\n" +
                            "  different>",
                    expected.getMessage(),
                    "message"
            );
            failed = true;
        }
        assertEquals(true, failed);
    }

    @Test
    public void testCheckNotEqualsObject() {
        this.checkNotEquals(1, 2);
    }

    @Test
    public void testCheckNotEqualsObjectFails() {
        boolean failed = false;
        try {
            this.checkNotEquals(
                    (Object) new TestTreePrintable("same"),
                    (Object) new TestTreePrintable("same")
            );
        } catch (final AssertionError expected) {
            assertEquals(
                    "expected: not equal but was: <walkingkooka.text.printer.TreePrintableTestingTest$TestTreePrintable\n" +
                            "  same>",
                    expected.getMessage(),
                    "message"
            );
            failed = true;
        }
        assertEquals(true, failed);
    }

    // TreePrintableTesting.............................................................................................

    private TestTreePrintable createTreePrintable() {
        return new TestTreePrintable("123");
    }

    static class TestTreePrintable implements TreePrintable {

        TestTreePrintable(final String printTree) {
            this.printTree = printTree;
        }

        public int hashCode() {
            return this.printTree.hashCode();
        }

        public boolean equals(final Object other) {
            return this == other || other instanceof TestTreePrintable && this.printTree.equals(((TestTreePrintable) other).printTree);
        }

        @Override
        public void printTree(final IndentingPrinter printer) {
            printer.print(this.printTree);
        }

        private final String printTree;

        @Override
        public String toString() {
            return "toString: " + this.printTree;
        }
    }
}
