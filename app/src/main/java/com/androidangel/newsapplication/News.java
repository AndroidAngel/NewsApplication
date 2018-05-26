package com.androidangel.newsapplication;

import java.io.Serializable;

public class News extends Object implements Serializable {

    public String title;
    public String url;
    public String date;
    public String section;
    public String author;

    public News(String title,String author, String url, String section, String date ){
        this.title = title;
        this.author = author;
        this.url = url;
        this.section = section;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getSection() {
        return section;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString(){
        return "News{"+
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", section='" + section+ '\'' +
                ", date='" + date + '\'' +
                '}';

    }
}
