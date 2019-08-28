package org.light.collect.treemappingmobile.widgets.base;

import org.javarosa.core.model.data.IAnswerData;
import org.light.collect.treemappingmobile.widgets.ExStringWidget;

/**
 * @author James Knight
 */

public abstract class GeneralExStringWidgetTest<W extends ExStringWidget, A extends IAnswerData> extends BinaryWidgetTest<W, A> {

    @Override
    public Object createBinaryData(A answerData) {
        return answerData.getDisplayText();
    }
}
