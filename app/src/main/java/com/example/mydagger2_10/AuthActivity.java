package com.example.mydagger2_10;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mydagger2_10.model.Country;
import com.example.mydagger2_10.model.User;
import com.example.mydagger2_10.network.AuthApi;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthActivity extends DaggerAppCompatActivity {
    private static final String TAG = "AuthActivity";

    @Inject
    User user;

    @Inject
    Retrofit rt;

    @Inject
    AuthApi authapi;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Log.d(TAG,rt.toString()+"-"+authapi.toString());
        listView = (ListView) findViewById(R.id.listViewCountries);
        getCountries(authapi);
    }

    private void getCountries(AuthApi api) {
    //    Api api = retrofit.create(Api.class);

        // Call<List<Country>> call = RetrofitClient.getInstance().getMyApi().getCountries();
        Call<List<Country>> call = api.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                Log.d(TAG,response.body().toString());
                List<Country> countryList = response.body();

                // Creating an String array for the ListView
                String[] countries = new String[countryList.size()];

                // looping through all the countries and inserting
                // the names inside the string array
                for (int i = 0; i < countryList.size(); i++) {
                    countries[i] = countryList.get(i).getName();
                    Log.d(TAG,countries[i].toString());
                }

                // displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, countries));
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}