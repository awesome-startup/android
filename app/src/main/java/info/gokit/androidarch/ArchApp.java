package info.gokit.androidarch;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import info.gokit.androidarch.di.DaggerAppComponent;

/**
 * Created by llitfkitfk on 11/15/17.
 */

public class ArchApp extends Application implements HasActivityInjector {

    // must use DispatchingAndroidInjector
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
