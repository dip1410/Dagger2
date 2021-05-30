package com.example.mydagger2_10.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthApi {

    @GET("fake")
    Call<ResponseBody> getFakeStuff();
}
