package com.androidangel.newsapplication;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NetworkUtils {

    static String createStringUrl(){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("order-by", "newest")
                .appendQueryParameter("show-references", "author")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("q", "q")
                .appendQueryParameter("api-key", "key");
        String url = builder.build().toString();
        return url;
    }
    static URL createUrl(){
        String stringUrl = createStringUrl();
        try {
            return new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("NetworkUtils", "Error creating URL: ", e);
            return null;
        }
    }
    private static String dateFormat(String dateData) {
        String jsonDate = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat jsonFormat = new SimpleDateFormat(jsonDate, Locale.US);
        try {
            Date parsedJsonDate = jsonFormat.parse(dateData);
            String parsedDateFormat = "MMM d, yyy";
            SimpleDateFormat parsedDate = new SimpleDateFormat(parsedDateFormat,Locale.US);
            return parsedDate.format(parsedJsonDate);
        }catch (ParseException e){
            Log.e("NetworkUtils", "ERROR parsing date", e);
            return "";
        }
    }
    static String makeHttpRequest(URL url)throws IOException{
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readStream(inputStream);
            }else {
               Log.e("MainActivity", "Error response code: ----------" + urlConnection.getResponseCode());
            }
        }catch (IOException e){
            Log.e("NetworkUtils", "ERROR in Http request:------------------------ ", e);
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readStream(InputStream inputStream)throws IOException {
       StringBuilder outputs = new StringBuilder();
       if (inputStream != null){
           InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
           BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
           String readerLine = bufferedReader.readLine();
           while (readerLine != null){
               outputs.append(readerLine);
               readerLine = bufferedReader.readLine();
           }
       }
       return outputs.toString();
    }

    static List<News> jsonParse(String response){
        ArrayList<News> listOfAllNews = new ArrayList<>();
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject jsonResults = jsonResponse.getJSONObject("response");
            JSONArray arrayResults = jsonResults.getJSONArray("results");

            for (int i = 0; i < arrayResults.length(); i++){
                JSONObject aResult = arrayResults.getJSONObject(i);
                String webTitle = aResult.getString("webTitle");
                String url = aResult.getString("webUrl");
                String section = aResult.getString("sectionName");
                String date = aResult.getString("webPublicationDate");
                date = dateFormat(date);
                JSONArray arrayTags = aResult.getJSONArray("tags");
                String author = "";
                if (arrayTags.length() == 0 ){
                    author = null;
                }else {
                    for (int b = 0; b < arrayTags.length(); b++){
                        JSONObject firstObject = arrayTags.getJSONObject(b);
                        author += firstObject.getString("webTitle") + ". ";

                    }
                }
                listOfAllNews.add(new News(webTitle, author, url, section, date));

            }

        }catch (JSONException e){
            Log.e("NetworkUtils", "Error parsing json: ------------------", e);
        }
        return listOfAllNews;

    }


}