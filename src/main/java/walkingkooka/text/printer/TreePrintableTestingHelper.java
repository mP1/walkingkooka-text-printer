package walkingkooka.text.printer;

import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

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

    static String treePrint(final Collection<?> collection) {
        final StringBuilder b = new StringBuilder();
        final IndentingPrinter printer = Printers.stringBuilder(b, LineEnding.SYSTEM)
                .indenting(Indentation.with("  "));
        for (final Object element : collection) {
            TreePrintable.printTreeOrToString(element, printer);
            printer.println();
        }

        printer.close();

        return b.toString();
    }
}
