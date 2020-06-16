/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
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

import javaemul.internal.annotations.GwtIncompatible;
import walkingkooka.text.LineEnding;

/**
 * Callback that is invoked by a {@link Printer} each time a complete line which may be empty is
 * printed.
 */
public interface PrintedLineHandler extends PrinterLike {

    /**
     * This method is called after a complete buffered but not yet printed long line.
     */
    void linePrinted(CharSequence line, LineEnding lineEnding, Printer printer)
            throws PrinterException;

    /**
     * <pre>
     * >>First line
     * >>Second
     * >>Third
     * >>Fourth lines
     * >>last
     * </pre>
     */
    @GwtIncompatible
    public static void main(final String[] args) {
        final Printer printer = Printers.sysOut()
                .printedLine(new PrintedLineHandler() {
                    @Override
                    public void linePrinted(final CharSequence line,
                                            final LineEnding lineEnding,
                                            final Printer printer) throws PrinterException {
                        printer.print(">>" + line + lineEnding);
                    }
                });
        printer.print("First line\n");
        printer.print("Second\nThird\nFourth lines\n");
        printer.print("last");
        printer.flush(); // forces PrintedLineHandler to be called
    }
}
