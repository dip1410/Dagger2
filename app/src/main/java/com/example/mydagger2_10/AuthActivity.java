package com.example.mydagger2_10;

import android.os.Bundle;
import android.util.Log;

import com.example.mydagger2_10.model.User;
import com.example.mydagger2_10.network.AuthApi;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Retrofit;

public class AuthActivity extends DaggerAppCompatActivity {
    private static final String TAG = "AuthActivity";

    @Inject
    User user;

    @Inject
    Retrofit rt;

    @Inject
    AuthApi authapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Log.d(TAG,rt.toString()+"-"+authapi.toString());
    }
}