package com.androidangel.newsapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_main_list_item, parent, false);
        }

        News currentNews = getItem(position);
        TextView newsTitleTextView = listItemView.findViewById(R.id.tv_news_title);
        String title = currentNews.getmTitle();
        newsTitleTextView.setText(title);

        TextView newsCategoryTV = listItemView.findViewById(R.id.tv_news_section);
        String category = currentNews.getmCategory();
        newsCategoryTV.setText(category);

        TextView newsDateTV =  listItemView.findViewById(R.id.tv_news_date);
        String date = currentNews.getmDate();
        newsDateTV.setText(date);

        TextView newsAuthorTV = listItemView.findViewById(R.id.tv_news_author);
        String author = currentNews.getmAuthor();

        if(author.equals("")){
            author = getContext().getString(R.string.no_author);
        }

        newsAuthorTV.setText(author);

        return listItemView;
    }
}


