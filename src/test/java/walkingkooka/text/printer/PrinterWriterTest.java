package walkingkooka.text.printer;

import org.junit.jupiter.api.Test;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.LineEnding;

import java.io.IOException;

public final class PrinterWriterTest implements ClassTesting<PrinterWriter> {

    @Test
    public void testWrite() throws IOException  {
        final StringBuilder b = new StringBuilder();
        final Printer printer = Printers.stringBuilder(b, LineEnding.NL);
        final PrinterWriter printWriter = PrinterWriter.with(printer);
        printWriter.write(
                "1234567890",
                3,
                5
        );
        printWriter.flush();

        this.checkEquals(
                "45678",
                b.toString()
        );
    }

    @Override
    public Class<PrinterWriter> type() {
        return PrinterWriter.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
