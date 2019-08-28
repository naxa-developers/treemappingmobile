package org.light.collect.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import org.light.collect.android.R;
import org.light.collect.android.application.Collect;
import org.light.collect.android.listeners.PermissionListener;
import org.light.collect.android.utilities.DialogUtils;
import org.light.collect.android.utilities.PermissionUtils;
import org.light.collect.android.utilities.SharedPreferenceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import timber.log.Timber;

public class BodgedSplashScreenActivity extends Activity {

    private static final int SPLASH_TIMEOUT = 3000; // milliseconds
    private static final boolean EXIT = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bodged_splash_activity);
        copyAssets();
        new PermissionUtils(this).requestStoragePermissions(new PermissionListener() {
            @Override
            public void granted() {
                // must be at the beginning of any activity that can be called from an external intent
                try {
                    Collect.createODKDirs();
                } catch (RuntimeException e) {
                    DialogUtils.showDialog(DialogUtils.createErrorDialog(BodgedSplashScreenActivity.this,
                            e.getMessage(), EXIT), BodgedSplashScreenActivity.this);
                    return;
                }

                init();
            }

            @Override
            public void denied() {

                finish();
            }
        });


    }

    private void init() {


        new Handler().postDelayed(() -> {
            if (SharedPreferenceUtils.hasEmailSaved()) {
                startActivity(new Intent(this, MainMenuActivity.class));
            } else {
                startActivity(new Intent(this, EmailActivity.class));
            }

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_TIMEOUT);
    }


    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
            for (String filename : files) {

                if (!"afqPwUf5rqQqRJyX5zWokM.xml".equals(filename)) {
                    continue;
                }
                InputStream in;
                OutputStream out;

                in = assetManager.open(filename);
                File outFile = new File(Collect.FORMS_PATH, filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
                in.close();
                in = null;
                out.flush();
                out.close();
                out = null;

            }
        } catch (NullPointerException | IOException e) {
            Timber.e(e, "Failed to get asset file list.");
        }


    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

}
