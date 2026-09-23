package walkingkooka.text.printer.sample;

import org.junit.jupiter.api.Test;
import walkingkooka.test.Testing;
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.Printer;
import walkingkooka.text.printer.Printers;

import java.io.PrintStream;

public final class Sample implements Testing {

    public static void main(final String[] args) {
        final Sample sample = new Sample();
        sample.testPrinterStringBuilderPrint();
        sample.testPrinterAsPrintStream();
    }

    @Test
    public void testPrinterStringBuilderPrint() {
        final StringBuilder b = new StringBuilder();

        final Printer printer = Printers.stringBuilder(b, LineEnding.NL);
        printer.print("Hello");
        printer.flush();

        this.checkEquals(
            "Hello",
            b.toString()
        );
    }

    @Test
    public void testPrinterAsPrintStream() {
        final StringBuilder b = new StringBuilder();
        final Printer printer = Printers.stringBuilder(
            b,
            LineEnding.NL
        );

        final PrintStream printStream = printer.asPrintStream();
        printStream.println("Hello");
        printStream.flush();

        checkEquals(
            "Hello\n",
            b.toString()
        );
    }
}
