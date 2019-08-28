package org.light.collect.treemappingmobile.injection.config;

import android.app.Application;

import org.light.collect.treemappingmobile.activities.FormDownloadList;
import org.light.collect.treemappingmobile.activities.FormEntryActivity;
import org.light.collect.treemappingmobile.activities.InstanceUploaderList;
import org.light.collect.treemappingmobile.activities.InstanceUploaderListBodged;
import org.light.collect.treemappingmobile.adapters.InstanceUploaderAdapter;
import org.light.collect.treemappingmobile.application.Collect;
import org.light.collect.treemappingmobile.fragments.DataManagerList;
import org.light.collect.treemappingmobile.http.CollectServerClient;
import org.light.collect.treemappingmobile.injection.ActivityBuilder;
import org.light.collect.treemappingmobile.injection.config.scopes.PerApplication;
import org.light.collect.treemappingmobile.logic.PropertyManager;
import org.light.collect.treemappingmobile.preferences.ServerPreferencesFragment;
import org.light.collect.treemappingmobile.tasks.InstanceServerUploaderTask;
import org.light.collect.treemappingmobile.tasks.sms.SmsSentBroadcastReceiver;
import org.light.collect.treemappingmobile.tasks.sms.SmsNotificationReceiver;
import org.light.collect.treemappingmobile.tasks.sms.SmsSender;
import org.light.collect.treemappingmobile.tasks.sms.SmsService;
import org.light.collect.treemappingmobile.utilities.AuthDialogUtility;
import org.light.collect.treemappingmobile.utilities.DownloadFormListUtils;
import org.light.collect.treemappingmobile.utilities.FormDownloader;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Primary module, bootstraps the injection system and
 * injects the main Collect instance here.
 * <p>
 * Shouldn't be modified unless absolutely necessary.
 */
@PerApplication
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(Collect collect);

    void inject(SmsService smsService);

    void inject(SmsSender smsSender);

    void inject(SmsSentBroadcastReceiver smsSentBroadcastReceiver);

    void inject(SmsNotificationReceiver smsNotificationReceiver);

    void inject(InstanceUploaderList instanceUploaderList);

    void inject(InstanceUploaderListBodged instanceUploaderList);

    void inject(InstanceUploaderAdapter instanceUploaderAdapter);

    void inject(DataManagerList dataManagerList);

    void inject(PropertyManager propertyManager);

    void inject(FormEntryActivity formEntryActivity);

    void inject(InstanceServerUploaderTask uploader);

    void inject(CollectServerClient collectClient);

    void inject(ServerPreferencesFragment serverPreferencesFragment);

    void inject(FormDownloader formDownloader);

    void inject(DownloadFormListUtils downloadFormListUtils);

    void inject(AuthDialogUtility authDialogUtility);
  
    void inject(FormDownloadList formDownloadList);
}
