package walkingkooka.text.printer;

import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

public final class PrinterThrowableTest extends PrinterThrowableTestCase<PrinterThrowable> {

    @Test
    public void testPrint() {
        final String text = "message1\ndump1\ndump2\n";

        this.printAndCheck(
            new Throwable("hello") {
                @Override
                public void printStackTrace(final PrintWriter printWriter) {
                    printWriter.print(text);
                }
            },
            text
        );

    }

    @Override
    void print(final Printer printer,
               final Throwable cause) {
        PrinterThrowable.print(
            cause,
            printer
        );
    }

    @Override
    public Class<PrinterThrowable> type() {
        return PrinterThrowable.class;
    }
}
