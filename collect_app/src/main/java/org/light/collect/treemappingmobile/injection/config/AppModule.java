package org.light.collect.treemappingmobile.injection.config;

import android.app.Application;
import android.content.Context;
import android.telephony.SmsManager;

import org.light.collect.treemappingmobile.dao.FormsDao;
import org.light.collect.treemappingmobile.dao.InstancesDao;
import org.light.collect.treemappingmobile.events.RxEventBus;
import org.light.collect.treemappingmobile.http.CollectServerClient;
import org.light.collect.treemappingmobile.http.HttpClientConnection;
import org.light.collect.treemappingmobile.http.OpenRosaHttpInterface;
import org.light.collect.treemappingmobile.injection.ViewModelBuilder;
import org.light.collect.treemappingmobile.injection.config.architecture.ViewModelFactoryModule;
import org.light.collect.treemappingmobile.injection.config.scopes.PerApplication;
import org.light.collect.treemappingmobile.tasks.sms.SmsSubmissionManager;
import org.light.collect.treemappingmobile.tasks.sms.contracts.SmsSubmissionManagerContract;
import org.light.collect.treemappingmobile.utilities.WebCredentialsUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Add Application level providers here, i.e. if you want to
 * inject something into the Collect instance.
 */
@Module(includes = {ViewModelFactoryModule.class, ViewModelBuilder.class})
public class AppModule {

    @Provides
    SmsManager provideSmsManager() {
        return SmsManager.getDefault();
    }

    @Provides
    SmsSubmissionManagerContract provideSmsSubmissionManager(Application application) {
        return new SmsSubmissionManager(application);
    }

    @Provides
    Context context(Application application) {
        return application;
    }

    @Provides
    InstancesDao provideInstancesDao() {
        return new InstancesDao();
    }

    @Provides
    FormsDao provideFormsDao() {
        return new FormsDao();
    }

    @PerApplication
    @Provides
    RxEventBus provideRxEventBus() {
        return new RxEventBus();
    }

    @Provides
    public OpenRosaHttpInterface provideHttpInterface() {
        return new HttpClientConnection();
    }

    @Provides
    public CollectServerClient provideCollectServerClient(OpenRosaHttpInterface httpInterface, WebCredentialsUtils webCredentialsUtils) {
        return new CollectServerClient(httpInterface, webCredentialsUtils);
    }

    @Provides
    public WebCredentialsUtils provideWebCredentials() {
        return new WebCredentialsUtils();
    }

}
