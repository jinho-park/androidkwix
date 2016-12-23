package com.example.owner.practice;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class NoticeLab {
    private static NoticeLab sNoticeLab;
    private Vector<Notice_List> notices;

    public static NoticeLab get(Context context) {
        if(sNoticeLab == null) {
            sNoticeLab = new NoticeLab();
        }
        return sNoticeLab;
    }

    public Notice_List getNotice(UUID id) {
        for(Notice_List list : notices) {
            if(list.getId().equals(id)) {
                return list;
            }
        }
        return null;
    }
}
