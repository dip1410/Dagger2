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
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

//    @Inject
//    AuthApi authapi;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        //    Log.d(TAG,rt.toString()+"-"+authapi.toString());
        AuthApi authapi = rt.create(AuthApi.class);
        Toast.makeText(this, authapi.toString(), Toast.LENGTH_SHORT).show();
        listView = (ListView) findViewById(R.id.listViewCountries);
        getCountries(authapi);
    }

    private void getCountries(AuthApi api) {
        //    Api api = retrofit.create(Api.class);
        Observable<List<Country>> countryObservable = api.getCountries();
        countryObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .map(result -> Observable.fromIterable(result))
                .flatMap(x -> x).filter(y -> {
            return true;
        }).toList().toObservable()
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleResults(List<Country> countryList) {
        String[] countries = new String[countryList.size()];
        if (countryList != null && countryList.size() != 0) {
            for (int i = 0; i < countryList.size(); i++) {
                countries[i] = countryList.get(i).getName();
                Log.d(TAG, countries[i]);
            }
            //displaying the string array into listview
            listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, countries));
        } else {
            Toast.makeText(this, "NO RESULTS FOUND",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void handleError(Throwable t) {
        Toast.makeText(this, "ERROR IN FETCHING API RESPONSE. Try again",
                Toast.LENGTH_LONG).show();
    }
}