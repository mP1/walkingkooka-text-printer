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

import walkingkooka.naming.Name;
import walkingkooka.text.CharSequences;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Advertises support for printing a value as a tree, with values and child values, which is particularly useful as
 * content for debugging failed assertions in tests.
 */
public interface TreePrintable {

    /**
     * Helper which if the object is {@link TreePrintable} prints it otherwise prints something like {@link Object#toString()}
     * and the class name in parens.
     */
    static void printTreeOrToString(final Object object,
                                    final IndentingPrinter printer) {
        if (object instanceof TreePrintable) {
            final TreePrintable treePrintable = (TreePrintable) object;
            treePrintable.printTree(printer);
        } else {
            printer.print(
                    CharSequences.quoteIfChars(object)
            );

            if (object instanceof Byte) {
                printer.print(" (Byte)");
            } else {
                if (object instanceof Short) {
                    printer.print(" (Short)");
                } else {
                    if (object instanceof Long) {
                        printer.print("L");
                    } else {
                        if (null != object &&
                                false ==
                                        (object instanceof Boolean ||
                                                object instanceof Integer ||
                                                object instanceof Float ||
                                                object instanceof Double ||
                                                object instanceof Character ||
                                                object instanceof String ||
                                                object instanceof Enum ||
                                                object instanceof Name ||
                                                object instanceof Optional ||
                                                object instanceof Collection ||
                                                object instanceof Map<?, ?>)) {
                            printer.print(" (" + object.getClass().getName() + ")");
                        }

                    }
                }
            }

            printer.println();
        }
    }

    /**
     * Requests the implementation to print itself and its fields to the {@link IndentingPrinter}.
     */
    void printTree(IndentingPrinter printer);

    /**
     * Returns as a {@link String} the result of calling {@link #printTree(IndentingPrinter)}.
     */
    default String treeToString(final Indentation indentation,
                                final LineEnding eol) {
        final StringBuilder b = new StringBuilder();
        try (final IndentingPrinter printer = Printers.stringBuilder(b, eol).indenting(indentation)) {
            this.printTree(printer);
            printer.flush();
        }
        return b.toString();
    }
}
