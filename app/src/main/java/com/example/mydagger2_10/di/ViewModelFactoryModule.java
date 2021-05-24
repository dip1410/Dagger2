package com.example.mydagger2_10.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.mydagger2_10.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}
