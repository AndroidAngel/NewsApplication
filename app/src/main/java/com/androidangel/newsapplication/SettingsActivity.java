package com.androidangel.newsapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SettingsActivity extends AppCompatActivity {

    private SettingsViewAdapter adapterSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ListView listViewSet = findViewById(R.id.settingsListView);
        adapterSettings =  new SettingsViewAdapter(this);
        listViewSet.setAdapter(adapterSettings);
        listViewSet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                News news = adapterSettings.getItem(i);

            }
        });


    }
    }