package com.androidangel.newsapplication;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {


    public NewsLoader(Context context) {
        super(context);

    }
    @Override
    protected void onStartLoading(){
        super.onStartLoading();
        forceLoad();

    }

    @Override
    public List<News>loadInBackground() {

        List<News> listOfAllNews = null;
        try {
            URL url = NetworkUtils.createUrl();
            String jsonResponse = NetworkUtils.makeHttpRequest(url);
            try {
                listOfAllNews = NetworkUtils.jsonParse(jsonResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (NetworkErrorException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            Log.e("NetworkUtils", "Error Loader LoaderBackground:---------------------", e);
        }
        return listOfAllNews;
    }
}
