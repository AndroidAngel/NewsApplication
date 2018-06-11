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

    private List<News> mData;


    public NewsAdapter(Context context) {
        super(context, -1, new ArrayList<News>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_list_item, parent, false);
        }
        TextView title = convertView.findViewById(R.id.tv_news_title);
        TextView author = convertView.findViewById(R.id.tv_news_author);
        TextView section = convertView.findViewById(R.id.tv_news_section);
        TextView date = convertView.findViewById(R.id.tv_news_date);

        News currentNews = getItem(position);
        title.setText(currentNews.getTitle());
        author.setText(currentNews.getAuthor());
        section.setText(currentNews.getSection());
        date.setText(currentNews.getDate());

        return convertView;
    }

    public void setData(List<News> data) {
        mData = data;
        notifyDataSetChanged();
    }
}


