package org.light.collect.android.injection;

import android.app.Activity;

import org.light.collect.android.injection.config.scopes.PerActivity;
import org.light.collect.android.location.GeoActivity;

import dagger.Module;

/**
 * Module for binding injectable Activities.
 * <p>
 * To make your Activity injectable, copy the GeoActivity binding below to match your Activity.
 * <p>
 * If you don't want to override InjectableActivity, make sure you call
 * {@link dagger.android.AndroidInjection#inject(Activity)} in your Activity's onCreate.
 * @see Activity (PMD doesn't see Activity in the line above).
 */
@Module
public abstract class ActivityBuilder {

    @PerActivity
    abstract GeoActivity bindGeoActivity();
}
