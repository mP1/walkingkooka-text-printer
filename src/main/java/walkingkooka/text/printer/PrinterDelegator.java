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

import walkingkooka.text.LineEnding;

/**
 * A delegator that delegates all methods to a {@link Printer}.
 */
public interface PrinterDelegator extends Printer {

    @Override
    default void print(final CharSequence chars) {
        this.printer()
                .print(chars);
    }

    @Override
    default LineEnding lineEnding() {
        return this.printer()
                .lineEnding();
    }

    @Override
    default void flush() {
        this.printer()
                .flush();
    }

    @Override
    default void close() {
        this.printer()
                .close();
    }

    Printer printer();
}
