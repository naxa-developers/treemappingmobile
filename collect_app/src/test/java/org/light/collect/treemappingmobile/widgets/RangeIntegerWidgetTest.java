package org.light.collect.treemappingmobile.widgets;

import android.support.annotation.NonNull;

import org.javarosa.core.model.data.IntegerData;
import org.light.collect.treemappingmobile.widgets.base.RangeWidgetTest;
import org.robolectric.RuntimeEnvironment;

/**
 * @author James Knight
 */

public class RangeIntegerWidgetTest extends RangeWidgetTest<RangeIntegerWidget, IntegerData> {

    @NonNull
    @Override
    public RangeIntegerWidget createWidget() {
        return new RangeIntegerWidget(RuntimeEnvironment.application, formEntryPrompt);
    }

    @NonNull
    @Override
    public IntegerData getNextAnswer() {
        return new IntegerData(random.nextInt());
    }
}
