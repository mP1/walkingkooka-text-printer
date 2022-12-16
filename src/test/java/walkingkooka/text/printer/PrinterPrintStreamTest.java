package walkingkooka.text.printer;

import org.junit.jupiter.api.Test;
import walkingkooka.ToStringTesting;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.LineEnding;

import java.io.PrintStream;

public final class PrinterPrintStreamTest implements ClassTesting2<PrinterPrintStream>, ToStringTesting<PrinterPrintStream> {

    @Test
    public void testPrintBooleanTrue() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print(true);

        this.printerCheck(
                b,
                "true"
        );
    }

    @Test
    public void testPrintBooleanFalse() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print(false);

        this.printerCheck(
                b,
                "false"
        );
    }

    @Test
    public void testPrintByte() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print((byte) 1);
        printStream.print((byte) 2);

        this.printerCheck(
                b,
                "12"
        );
    }

    @Test
    public void testPrintChar() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print('A');
        printStream.print('1');

        this.printerCheck(
                b,
                "A1"
        );
    }

    @Test
    public void testPrintDouble() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print(1.2);

        this.printerCheck(
                b,
                "1.2"
        );
    }

    @Test
    public void testPrintFloat() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print(1.2f);

        this.printerCheck(
                b,
                "1.2"
        );
    }

    @Test
    public void testPrintInt() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print(12345);

        this.printerCheck(
                b,
                "12345"
        );
    }

    @Test
    public void testPrintLong() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print(123456L);

        this.printerCheck(
                b,
                "123456"
        );
    }

    @Test
    public void testPrintShort() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print((short) 78);

        this.printerCheck(
                b,
                "78"
        );
    }

    @Test
    public void testPrintString() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.print("A1");

        this.printerCheck(
                b,
                "A1"
        );
    }

    @Test
    public void testPrintln() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println();

        this.printerCheck(
                b,
                "\n"
        );
    }

    @Test
    public void testPrintlnBooleanTrue() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println(true);

        this.printerCheck(
                b,
                "true\n"
        );
    }

    @Test
    public void testPrintlnBooleanFalse() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println(false);

        this.printerCheck(
                b,
                "false\n"
        );
    }

    @Test
    public void testPrintlnByte() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println((byte) 1);
        printStream.println((byte) 2);

        this.printerCheck(
                b,
                "1\n2\n"
        );
    }

    @Test
    public void testPrintlnChar() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println('A');

        this.printerCheck(
                b,
                "A\n"
        );
    }

    @Test
    public void testPrintlnDouble() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println(1.2);

        this.printerCheck(
                b,
                "1.2\n"
        );
    }

    @Test
    public void testPrintlnFloat() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println(1.2f);

        this.printerCheck(
                b,
                "1.2\n"
        );
    }

    @Test
    public void testPrintlnInt() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println(12345);

        this.printerCheck(
                b,
                "12345\n"
        );
    }

    @Test
    public void testPrintlnLong() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println(123456L);

        this.printerCheck(
                b,
                "123456\n"
        );
    }

    @Test
    public void testPrintlnShort() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println((short) 78);

        this.printerCheck(
                b,
                "78\n"
        );
    }

    @Test
    public void testPrintlnString() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println("A1");

        this.printerCheck(
                b,
                "A1\n"
        );
    }

    @Test
    public void testPrintFlushClose() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.println("A1");
        printStream.flush();
        printStream.close();

        this.printerCheck(
                b,
                "A1\n"
        );
    }

    // append...........................................................................................................

    @Test
    public void testAppendChar() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.append('A');

        this.printerCheck(
                b,
                "A"
        );
    }

    @Test
    public void testAppendCharSequence() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.append(new StringBuilder("ABC"));

        this.printerCheck(
                b,
                "ABC"
        );
    }

    @Test
    public void testAppendCharSequenceIntInt() {
        final StringBuilder b = new StringBuilder();
        final PrintStream printStream = this.printStream(b);

        printStream.append(
                new StringBuilder("ABCDE"),
                1,
                2
        );

        this.printerCheck(
                b,
                new StringBuilder("ABCDE").subSequence(1, 2)
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        final Printer printer = Printers.fake();

        this.toStringAndCheck(
                printer.asPrintStream(),
                printer.toString()
        );
    }

    // helpers..........................................................................................................

    private void printerCheck(final CharSequence printed,
                              final CharSequence expected) {
        this.checkEquals(
                printed.toString(),
                expected.toString()
        );
    }

    private PrintStream printStream(final StringBuilder b) {
        return Printers.stringBuilder(
                b,
                LineEnding.NL
        ).asPrintStream();
    }

    // ClassTesting....................................................................................................

    @Override
    public Class<PrinterPrintStream> type() {
        return PrinterPrintStream.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
