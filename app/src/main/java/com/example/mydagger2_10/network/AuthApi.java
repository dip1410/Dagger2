package com.example.mydagger2_10.network;

import com.example.mydagger2_10.model.Country;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthApi {
    @GET("all")
    Observable<List<Country>> getCountries();
}
