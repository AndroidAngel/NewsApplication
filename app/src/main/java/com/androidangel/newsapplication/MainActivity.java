package com.androidangel.newsapplication;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<News>>,SwipeRefreshLayout.OnRefreshListener {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final int NEWS_LOADER_ID = 0;
    private NewsAdapter adapter;
    SwipeRefreshLayout swipe;
    ListView mListView;
    public Activity activity;

    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        swipe = findViewById(R.id.refresh);
        swipe.setColorSchemeColors(getColor(R.color.colorAccent));
        mListView= findViewById(R.id.mainList);
        adapter = new NewsAdapter(this);
        mListView.setAdapter(adapter);

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                News news = adapter.getItem(i);
                String url = news.url;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

            }
        });
        getSupportLoaderManager().initLoader(NEWS_LOADER_ID, null, this);

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this);

    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
      if (data != null){
          adapter.setNotifyOnChange(false);
          adapter.clear();
          adapter.setNotifyOnChange(true);
          adapter.addAll(data);
      }
        }




    @Override
    public void onLoaderReset(Loader<List<News>> loader) {



    }

    @Override
    public void onRefresh() {
        getSupportLoaderManager().restartLoader(NEWS_LOADER_ID, null, this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if (id == R.id.action_settings){
            startActivity(new Intent(this,SettingsActivity.class));
            return true;


        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void showNewsDataView(){
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mListView.setVisibility(View.VISIBLE);

        
        
    }
    private void showErrorMessage(){
        mListView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    }



