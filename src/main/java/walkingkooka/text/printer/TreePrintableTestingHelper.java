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

import java.util.Collection;

final class TreePrintableTestingHelper {

    static boolean shouldTreePrint(final Object maybe) {
        return maybe instanceof Collection &&
                shouldTreePrint0((Collection<?>) maybe);
    }

    static boolean shouldTreePrint0(final Collection<?> collection) {
        return collection.stream()
                .allMatch(TreePrintableTestingHelper::shouldTreePrint1);
    }

    private static boolean shouldTreePrint1(final Object value) {
        return null == value ||
                value instanceof Boolean ||
                value instanceof Byte ||
                value instanceof Character ||
                value instanceof Double ||
                value instanceof Float ||
                value instanceof Integer ||
                value instanceof Long ||
                value instanceof Short ||
                value instanceof String ||
                value instanceof Collection ||
                value instanceof TreePrintable;
    }

    static String treePrintWithClassName(final TreePrintable treePrintable) {
        final StringBuilder b = new StringBuilder();

        if (null != treePrintable) {
            try (final IndentingPrinter printer = Printers.stringBuilder(
                    b,
                    TreePrintableTesting.EOL
            ).indenting(TreePrintableTesting.INDENTATION)) {
                printer.println(treePrintable.getClass().getName());
                printer.indent();
                {
                    treePrintable.printTree(printer);
                }
                printer.outdent();
                printer.flush();
            }
        }
        return b.toString();
    }

    static String treePrint(final Collection<?> collection) {
        final StringBuilder b = new StringBuilder();
        final IndentingPrinter printer = Printers.stringBuilder(b, TreePrintableTesting.EOL)
                .indenting(TreePrintableTesting.INDENTATION);
        for (final Object element : collection) {
            TreePrintable.printTreeOrToString(element, printer);
            printer.println();
        }

        printer.close();

        return b.toString();
    }
}
