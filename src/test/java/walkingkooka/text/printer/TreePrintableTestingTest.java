package walkingkooka.text.printer;

import org.junit.jupiter.api.Test;
import walkingkooka.text.printer.TreePrintableTestingTest.TestTreePrintable;

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
