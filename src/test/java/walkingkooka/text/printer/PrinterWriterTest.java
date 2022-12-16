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
