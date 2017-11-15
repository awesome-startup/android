package info.gokit.androidarch.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by llitfkitfk on 11/14/17.
 */

@Module
class AppModule {


    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }
}
