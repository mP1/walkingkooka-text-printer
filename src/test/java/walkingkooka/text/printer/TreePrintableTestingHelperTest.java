package walkingkooka.text.printer;

import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

public final class TreePrintableTestingHelperTest implements ClassTesting<TreePrintableTestingHelper> {

    @Override
    public Class<TreePrintableTestingHelper> type() {
        return TreePrintableTestingHelper.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
