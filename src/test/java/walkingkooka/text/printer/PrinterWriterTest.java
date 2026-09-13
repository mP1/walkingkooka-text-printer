/*
 * Copyright 2022 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.text.printer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

final public class PrinterWriterTest extends PrinterTestCase<PrinterWriter> {

    // constants

    private final static Writer WRITER = new Writer() {
        @Override
        public void write(final char[] cbuf, final int off, final int len) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void flush() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void close() {
            throw new UnsupportedOperationException();
        }
    };

    // tests

    @Test
    public void testWithNullWriterFails() {
        assertThrows(
            NullPointerException.class,
            () -> PrinterWriter.with(
                null,
                LINE_ENDING
            )
        );
    }

    @Test
    public void testWithNullHasLineEndingFails() {
        assertThrows(
            NullPointerException.class,
            () -> PrinterWriter.with(
                WRITER,
                null
            )
        );
    }

    @Test
    public void testPrint() {
        final StringWriter writer = new StringWriter();
        final PrinterWriter printer = this.createPrinter(writer);
        printer.print("1");
        printer.print("23");
        printer.print("456");
        this.checkEquals("123456", writer.toString());
    }

    @Test
    public void testPrintNull() {
        final StringWriter writer = new StringWriter();
        final PrinterWriter printer = this.createPrinter(writer);
        printer.print("1");
        printer.print(null);
        printer.print("456");
        this.checkEquals("1null456", writer.toString());
    }

    @Test
    public void testPrintThenWriterThrowsFails() {
        final String written = "printed";
        final IOException thrown = new IOException("thrown");
        final PrinterWriter printer = this.createPrinter(//
            new Writer() {
                @Override
                public void write(final String string) throws IOException {
                    assertSame(written, string, "written");
                    throw thrown;
                }

                @Override
                public void write(final char[] cbuf, final int off, final int len) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public void flush() {
                    throw new UnsupportedOperationException();
                }

                @Override
                public void close() {
                    throw new UnsupportedOperationException();
                }
            });
        final IllegalStateException expected = assertThrows(
            IllegalStateException.class,
            () -> printer.print(written)
        );
        assertSame(thrown, expected.getCause(), "cause");
    }

    @Override
    @Test
    public void testLineEndingAfterCloseFails() {
        // nop
    }

    @Test
    public void testToString() {
        this.checkEquals(WRITER.toString(),
            this.createPrinter(WRITER).toString());
    }

    @Override
    public PrinterWriter createPrinter() {
        return this.createPrinter(//
            new Writer() {

                @Override
                public void write(final char[] cbuf, final int off, final int len)
                    throws IOException {
                    if (this.closed) {
                        throw new IOException("Writer already closed");
                    }
                }

                @Override
                public void flush() throws IOException {
                    if (this.closed) {
                        throw new IOException("Writer already closed");
                    }
                }

                @Override
                public void close() {
                    this.closed = true;
                }

                private boolean closed;
            });
    }

    private PrinterWriter createPrinter(final Writer writer) {
        return PrinterWriter.with(writer, LINE_ENDING);
    }

    @Override
    public Class<PrinterWriter> type() {
        return PrinterWriter.class;
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }
}
