package walkingkooka.text.printer;

import org.junit.jupiter.api.Test;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.LineEnding;

import java.io.PrintWriter;

public final class PrinterTest implements ClassTesting<Printer> {

    @Test
    public void testPrintWriterPrintln() {
        final StringBuilder b = new StringBuilder();
        final Printer printer = Printers.stringBuilder(b, LineEnding.NL);
        final PrintWriter printWriter = printer.asPrintWriter();
        printWriter.println("Hello");
        printWriter.flush();

        this.checkEquals(
                "Hello" + LineEnding.NL,
                b.toString()
        );
    }

    @Override
    public Class<Printer> type() {
        return Printer.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
