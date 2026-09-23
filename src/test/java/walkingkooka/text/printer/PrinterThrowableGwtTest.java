package walkingkooka.text.printer;

import org.junit.jupiter.api.Test;

public final class PrinterThrowableGwtTest extends PrinterThrowableTestCase<PrinterThrowableGwt> {

    @Test
    public void testPrint() {
        this.printAndCheck(
            new Throwable("hello"),
            "hello\n"
        );
    }

    @Override
    void print(final Printer printer,
               final Throwable cause) {
        PrinterThrowableGwt.print(
            cause,
            printer
        );
    }

    @Override
    public Class<PrinterThrowableGwt> type() {
        return PrinterThrowableGwt.class;
    }
}
