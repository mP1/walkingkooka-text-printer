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
import walkingkooka.text.LineEnding;
import walkingkooka.text.printer.PrinterDelegatorTest.TestPrinterDelegator;

public final class PrinterDelegatorTest implements PrinterTesting<TestPrinterDelegator> {

    private final static LineEnding LINE_ENDING = LineEnding.NL;

    @Override
    public void testPrintNullFails() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void testPrintln() {
        final TestPrinterDelegator printer = new TestPrinterDelegator();

        final String text = "Text123";

        printer.println(text);

        this.checkEquals(
            text + LINE_ENDING,
            printer.toString()
        );
    }

    @Override
    public TestPrinterDelegator createPrinter() {
        return new TestPrinterDelegator();
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<TestPrinterDelegator> type() {
        return TestPrinterDelegator.class;
    }

    final static class TestPrinterDelegator implements PrinterDelegator {

        @Override
        public Printer printer() {
            return this.printer;
        }

        private final StringBuilder b = new StringBuilder();

        private final Printer printer = Printers.stringBuilder(
                this.b,
                LINE_ENDING
        );

        @Override
        public String toString() {
            return this.b.toString();
        }
    }
}
