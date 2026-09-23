package walkingkooka.text.printer;

import javaemul.internal.annotations.GwtIncompatible;

import java.io.PrintWriter;
import java.util.Objects;

abstract class PrinterThrowable extends PrinterThrowableGwt {

    @GwtIncompatible
    static void print(final Throwable cause,
                      final Printer printer) {
        Objects.requireNonNull(cause, "cause");

        try (final PrintWriter printWriter = printer.asPrintWriter()) {
            cause.printStackTrace(printWriter);
            printWriter.flush();
        }
    }
}
