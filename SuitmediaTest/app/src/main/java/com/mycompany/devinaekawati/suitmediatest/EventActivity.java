package com.mycompany.devinaekawati.suitmediatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

public class EventActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mRecyclerView = (RecyclerView) findViewById(R.id.eventsList);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event(R.drawable.events, "Android training", "5 April 2015", new ArrayList<>(Arrays.asList("Programming", "Android"))));
        events.add(new Event(R.drawable.events, "Unity training", "6 April 2015", new ArrayList<>(Arrays.asList("Programming", "Unity"))));
        events.add(new Event(R.drawable.events, "Web development sharing session", "10 April 2015", new ArrayList<>(Arrays.asList("Programming", "Web"))));
        events.add(new Event(R.drawable.events, "Docker training", "11 April 2015", new ArrayList<>(Arrays.asList("Docker"))));
        events.add(new Event(R.drawable.events, "Project management training", "15 April 2015", new ArrayList<>(Arrays.asList("PM"))));

        mAdapter = new EventAdapter(events, this, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_add:
                Intent intent = new Intent(this, EventMap.class);
                startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
