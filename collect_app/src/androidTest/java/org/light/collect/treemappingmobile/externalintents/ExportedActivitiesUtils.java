package org.light.collect.treemappingmobile.externalintents;

import junit.framework.Assert;

import java.io.File;

import timber.log.Timber;

import static org.light.collect.treemappingmobile.application.Collect.CACHE_PATH;
import static org.light.collect.treemappingmobile.application.Collect.FORMS_PATH;
import static org.light.collect.treemappingmobile.application.Collect.INSTANCES_PATH;
import static org.light.collect.treemappingmobile.application.Collect.METADATA_PATH;
import static org.light.collect.treemappingmobile.application.Collect.ODK_ROOT;
import static org.light.collect.treemappingmobile.application.Collect.OFFLINE_LAYERS;

class ExportedActivitiesUtils {

    private static final String[] DIRS = new String[]{
            ODK_ROOT, FORMS_PATH, INSTANCES_PATH, CACHE_PATH, METADATA_PATH, OFFLINE_LAYERS
    };

    private ExportedActivitiesUtils() {

    }

    static void clearDirectories() {
        for (String dirName : DIRS) {
            File dir = new File(dirName);
            if (dir.exists()) {
                if (dir.delete()) {
                    Timber.i("Directory was not deleted");
                }
            }
        }

    }

    static void testDirectories() {
        for (String dirName : DIRS) {
            File dir = new File(dirName);
            Assert.assertTrue("File " + dirName + "does not exist", dir.exists());
            Assert.assertTrue("File" + dirName + "does not exist", dir.isDirectory());
        }
    }

}
