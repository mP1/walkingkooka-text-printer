package walkingkooka.text.printer;

import org.junit.jupiter.api.Test;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.CharSequences;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

public final class TreePrintableTest implements ClassTesting<TreePrintable> {

    private final static LineEnding EOL = LineEnding.NL;

    // printTreeOrToString..............................................................................................

    @Test
    public void testPrintTreeOrToStringNullValue() {
        this.printTreeOrToStringAndCheck(
                null,
                "null"
        );
    }

    @Test
    public void testPrintTreeOrToStringNonTreePrintable() {
        final String string = "123abc";

        this.printTreeOrToStringAndCheck(
                string,
                string
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
