package com.example.mydagger2_10.di;


import com.example.mydagger2_10.model.User;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class TestModule {

    @Provides
    static User someString(){
        return new User("This is testing");
    }

   }
