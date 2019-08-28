package org.light.collect.treemappingmobile.widgets;

import android.support.annotation.NonNull;

import org.light.collect.treemappingmobile.widgets.base.GeneralSelectOneWidgetTest;
import org.robolectric.RuntimeEnvironment;

/**
 * @author James Knight
 */

public class ListWidgetTest extends GeneralSelectOneWidgetTest<ListWidget> {
    @NonNull
    @Override
    public ListWidget createWidget() {
        return new ListWidget(RuntimeEnvironment.application, formEntryPrompt, false, false);
    }
}
