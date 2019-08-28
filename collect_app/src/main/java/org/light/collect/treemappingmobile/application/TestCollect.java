package org.light.collect.treemappingmobile.application;

import com.squareup.leakcanary.RefWatcher;

/**
 * @author James Knight
 */

public class TestCollect extends Collect {
    @Override
    protected RefWatcher setupLeakCanary() {
        // No leakcanary in unit tests.
        return RefWatcher.DISABLED;
    }
}