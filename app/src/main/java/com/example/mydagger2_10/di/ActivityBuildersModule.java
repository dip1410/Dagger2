package com.example.mydagger2_10.di;

import com.example.mydagger2_10.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class})
    abstract AuthActivity contributeAuthActivity();
    }