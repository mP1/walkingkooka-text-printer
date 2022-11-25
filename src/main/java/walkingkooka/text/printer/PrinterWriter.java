package walkingkooka.text.printer;

import java.io.IOException;
import java.io.Writer;

/**
 * Acts as an adapter supporting the return of a {@link java.io.PrintWriter} from {@link Printer#asPrintWriter()}.
 */
final class PrinterWriter extends Writer {

    static PrinterWriter with(final Printer printer) {
        return new PrinterWriter(printer);
    }

    private PrinterWriter(final Printer printer) {
        this.printer = printer;
    }

    @Override
    public void write(final char[] buffer,
                      final int offset,
                      final int length) {
        this.printer.print(
                new String(buffer, offset, length)
        );
    }

    @Override
    public void flush() throws IOException {
        this.printer.flush();
    }

    @Override
    public void close() throws IOException {
        this.printer.close();
    }

    /**
     * The printer that receives all writes.
     */
    private final Printer printer;

    @Override
    public String toString() {
        return this.printer.toString();
    }
}
