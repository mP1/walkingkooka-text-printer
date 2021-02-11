package walkingkooka.text.printer;

import walkingkooka.test.Testing;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

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
}