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
import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.PackagePrivateClassTesting;

import java.util.Collection;

public final class TreePrintableTestingHelperTest implements PackagePrivateClassTesting<TreePrintableTestingHelper> {

    @Test
    public void testTreePrintCollectionWithEmptyList() {
        this.treePrintCollectionAndCheck(
            Lists.empty(),
            ""
        );
    }

    @Test
    public void testTreePrintCollectionWithListOrList() {
        this.treePrintCollectionAndCheck(
            Lists.of(
                Lists.empty()
            ),
            "[]\n"
        );
    }

    @Test
    public void testTreePrintCollectionWithListOrList2() {
        this.treePrintCollectionAndCheck(
            Lists.of(
                Lists.empty(),
                Lists.empty()
            ),
            "[]\n" +
                "[]\n"
        );
    }

    private void treePrintCollectionAndCheck(final Collection<?> collection,
                                             final String expected) {
        this.checkEquals(
            expected,
            TreePrintableTestingHelper.treePrint(collection)
        );
    }

    // class............................................................................................................

    @Override
    public Class<TreePrintableTestingHelper> type() {
        return TreePrintableTestingHelper.class;
    }
}
