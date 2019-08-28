package org.light.collect.android.injection;

import android.app.Application;
import android.content.Context;
import android.telephony.SmsManager;

import org.light.collect.android.dao.FormsDao;
import org.light.collect.android.dao.InstancesDao;
import org.light.collect.android.events.RxEventBus;
import org.light.collect.android.http.CollectServerClient;
import org.light.collect.android.http.HttpClientConnection;
import org.light.collect.android.http.OpenRosaHttpInterface;
import org.light.collect.android.http.mock.MockHttpClientConnection;
import org.light.collect.android.http.mock.MockHttpClientConnectionError;
import org.light.collect.android.injection.config.scopes.PerApplication;
import org.light.collect.android.tasks.sms.SmsSubmissionManager;
import org.light.collect.android.tasks.sms.contracts.SmsSubmissionManagerContract;
import org.light.collect.android.utilities.WebCredentialsUtils;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test Module used for unit testing.
 * Providing seemingly production dependencies because they are all
 * running on the Shadows of Robolectric.
 */
@Module
public class TestModule {

    @Provides
    SmsManager provideSmsManager() {
        return SmsManager.getDefault();
    }

    @Provides
    SmsSubmissionManagerContract provideSmsSubmissionManager(Application application) {
        return new SmsSubmissionManager(application);
    }

    @Provides
    FormsDao provideFormsDao() {
        FormsDao formsDao = mock(FormsDao.class);
        when(formsDao.isFormEncrypted(anyString(), anyString())).thenReturn(false);
        return formsDao;
    }

    @Provides
    InstancesDao provideInstancesDao() {
        return mock(InstancesDao.class);
    }

    @Provides
    Context context(Application application) {
        return application;
    }

    @PerApplication
    @Provides
    RxEventBus provideRxEventBus() {
        return new RxEventBus();
    }

    @Provides
    MockHttpClientConnection provideMockHttpClientConnection() {
        return new MockHttpClientConnection();
    }

    @Provides
    MockHttpClientConnectionError provideMockHttpClientConnectionError() {
        return new MockHttpClientConnectionError();
    }

    @Provides
    @Named("StubbedData")
    CollectServerClient provideTestCollectServerClient(MockHttpClientConnection httpClientConnection) {
        return new CollectServerClient(httpClientConnection, new WebCredentialsUtils());
    }

    @Provides
    @Named("NullGet")
    CollectServerClient provideTestCollectServerClientError(MockHttpClientConnectionError httpClientConnection) {
        return new CollectServerClient(httpClientConnection, new WebCredentialsUtils());
    }

    @Provides
    public OpenRosaHttpInterface provideHttpInterface() {
        return new HttpClientConnection();
    }

    @Provides
    public WebCredentialsUtils provideWebCredentials() {
        return new WebCredentialsUtils();
    }

}
