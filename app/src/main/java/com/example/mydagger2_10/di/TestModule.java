package com.example.mydagger2_10.di;


import android.provider.SyncStateContract;

import com.example.mydagger2_10.model.User;
import com.example.mydagger2_10.network.AuthApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class TestModule {
    public static final String BASE_URL = "https://restcountries.eu/rest/v2/";

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static AuthApi provideSessionApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
    @Provides
    static User someString(){
        return new User("This is testing");
    }

   }
