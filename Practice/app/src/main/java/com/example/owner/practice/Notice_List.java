package com.example.owner.practice;

import java.io.Serializable;
import java.util.UUID;

public class Notice_List implements Serializable{
    static final long serialVersionUID=-6618469841127325812L;

    private UUID mId;
    private String url;
    private String cat;
    private String date;
    private String title;

    public Notice_List(){
        this.mId=UUID.randomUUID();
    }

    public int getlistsize(){
        return url.length();
    }

    public String getUrls(){
        return url;
    }

    public String getCats(){
        return cat;
    }

    public String getDates(){
        return date;
    }

    public String getTitles(){
        return title;
    }

    public UUID getId(){
        return mId;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setUrls(String url){
        this.url = url;
    }

    public void setCat(String cat){
        this.cat = cat;
    }

    public void setDate(String date){
        this.date = date;
    }
}
