package com.mycompany.devinaekawati.suitmediatest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuestActivity extends AppCompatActivity {

    private int resultCode = 2;
    private GuestAdapter adapter;
    private GridView gridView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);
        setTitle("Guest");

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dry-sierra-6832.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<List<Guest>> call = service.getGuests();

        call.enqueue(new Callback<List<Guest>>() {
            @Override
            public void onResponse(Call<List<Guest>> call, Response<List<Guest>> response) {
                try {
                    final List<Guest> results = response.body();
                    for (int i = 0; i < results.size(); i++) {
                        adapter.add(new Guest(results.get(i).getId(), results.get(i).getNama(), results.get(i).getBirthDate()));

                        final int finalI = i;
                        realm.executeTransaction(new Realm.Transaction() {

                            @Override
                            public void execute(Realm realm) {
                                Guest guest = realm.createObject(Guest.class, results.get(finalI).getId());
                                guest.setNama(results.get(finalI).getNama());
                                guest.setBirthDate(results.get(finalI).getBirthDate());
                            }
                        });
                    }


                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Guest>> call, Throwable t) {
                Log.d("onFailure", t.toString());
                RealmResults<Guest> results = realm.where(Guest.class).findAll();
                for (int i = 0; i < results.size(); i++) {
                    adapter.add(new Guest(results.get(i).getId(), results.get(i).getNama(), results.get(i).getBirthDate()));
                }
            }
        });

        final ArrayList<Guest> guests = new ArrayList<>();
        adapter = new GuestAdapter(this, guests);

        gridView = (GridView) findViewById(R.id.guestGridView);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Guest guest = (Guest) gridView.getItemAtPosition(position);
                String guestName = guest.getNama();

                String guestBirthDate = guest.getBirthDate();
                String[] date = guestBirthDate.split("-");

                int birthDate = Integer.parseInt(date[2]);

                if ((birthDate % 2 ==0) && (birthDate % 3 == 0)) {
                    Toast.makeText(GuestActivity.this, "iOS", Toast.LENGTH_LONG).show();
                } else if (birthDate % 2 == 0) {
                    Toast.makeText(GuestActivity.this, "blackberry", Toast.LENGTH_LONG).show();
                } else if (birthDate % 3 == 0) {
                    Toast.makeText(GuestActivity.this, "android", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(GuestActivity.this, "feature phone", Toast.LENGTH_LONG).show();
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("GUEST-NAME", guestName);

                setResult(resultCode, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }
}
