package com.example.owner.practice;

import java.util.UUID;

/**
 * Created by Owner on 2016-11-11.
 */
public class Notice_List {
    private UUID mId;
    private String url;
    private String cat;
    private String date;
    private String title;

    public Notice_List(String string, String string2, String string3, String string4){
        this.mId=UUID.randomUUID();
        this.url=string;
        this.cat=string2;
        this.date=string3;
        this.title=string4;
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
}
