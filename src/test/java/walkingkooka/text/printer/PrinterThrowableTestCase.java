package walkingkooka.text.printer;

import walkingkooka.reflect.PackagePrivateClassTesting;
import walkingkooka.text.HasLineEndingTesting;

public abstract class PrinterThrowableTestCase<T extends PrinterThrowableGwt> implements PackagePrivateClassTesting<T>,
    HasLineEndingTesting {

    PrinterThrowableTestCase() {
        super();
    }

    final void printAndCheck(final Throwable cause,
                             final String expected) {
        final StringBuilder b = new StringBuilder();
        final Printer printer = Printers.stringBuilder(
            b,
            LINE_ENDING
        );

        this.print(
            printer,
            cause
        );

        this.checkEquals(
            expected,
            b.toString()
        );
    }

    abstract void print(final Printer printer,
                        final Throwable cause);
}
