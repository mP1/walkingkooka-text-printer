package walkingkooka.text.printer;

import walkingkooka.test.Testing;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public interface TreePrintableTesting<T extends TreePrintable> extends Testing {

    Indentation INDENTATION = Indentation.with("  ");
    LineEnding EOL = LineEnding.NL;

    T createTreePrintable();

    default void treePrintAndCheck(final String expected) {
        this.treePrintAndCheck(
                this.createTreePrintable(),
                expected
        );
    }

    default void treePrintAndCheck(final TreePrintable printable,
                                   final String expected) {
        assertEquals(
                expected,
                printable.treeToString(INDENTATION, EOL),
                () -> printable.toString()
        );
    }

    default void checkEquals(final TreePrintable expected,
                             final TreePrintable actual,
                             final String message) {
        this.checkEquals(
                expected,
                actual,
                () -> message
        );
    }

    default void checkEquals(final TreePrintable expected,
                             final TreePrintable actual,
                             final Supplier<String> message) {
        assertEquals(
                null != expected ? expected.treeToString(INDENTATION, EOL) : null,
                null != actual ? actual.treeToString(INDENTATION, EOL) : null,
                message
        );
    }
}
