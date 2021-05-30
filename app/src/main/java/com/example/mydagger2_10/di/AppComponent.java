package com.example.mydagger2_10.di;

import android.app.Application;

import com.example.mydagger2_10.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton // without it wont compile for retrofit instance
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,TestModule.class
        })
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}