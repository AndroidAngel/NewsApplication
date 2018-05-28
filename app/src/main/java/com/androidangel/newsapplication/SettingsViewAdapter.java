package com.androidangel.newsapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingsViewAdapter extends ArrayAdapter<News> {

    public SettingsViewAdapter(Context context) {
        super(context, 0, new ArrayList<News>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.settings_list_item, parent, false);
        }
        TextView section = convertView.findViewById(R.id.set_section);

        News currentNews = getItem(position);
        section.setText(currentNews.getSection());

        return convertView;


    }
}



