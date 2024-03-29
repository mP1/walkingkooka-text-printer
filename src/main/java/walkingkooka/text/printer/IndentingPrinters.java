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

import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.text.Indentation;

final public class IndentingPrinters implements PublicStaticHelper {

    /**
     * {@see FakeIndentingPrinter}
     */
    public static IndentingPrinter fake() {
        return FakeIndentingPrinter.create();
    }

    /**
     * {@see BasicIndentingPrinter}
     */
    public static IndentingPrinter printer(final Printer printer,
                                           final Indentation indentation) {
        return BasicIndentingPrinter.with(printer, indentation);
    }

    /**
     * Stop creation
     */
    private IndentingPrinters() {
        throw new UnsupportedOperationException();
    }
}
