package org.light.collect.treemappingmobile.injection;

import android.app.Application;

import org.light.collect.treemappingmobile.http.CollectServerClientTest;
import org.light.collect.treemappingmobile.injection.config.AppComponent;
import org.light.collect.treemappingmobile.injection.config.scopes.PerApplication;
import org.light.collect.treemappingmobile.sms.SmsSenderJobTest;
import org.light.collect.treemappingmobile.sms.SmsServiceTest;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@PerApplication
@Component(modules = {
        AndroidSupportInjectionModule.class,
        TestModule.class,
        ActivityBuilder.class
})
public interface TestComponent extends AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        TestComponent.Builder application(Application application);

        TestComponent build();
    }

    void inject(SmsSenderJobTest smsSenderJobTest);

    void inject(SmsServiceTest smsServiceTest);

    void inject(CollectServerClientTest collectServerClientTest);
}
