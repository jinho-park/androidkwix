package com.example.owner.practice;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * Created by Owner on 2016-11-11.
 */
public class NoticeLab {
    private static NoticeLab sNoticeLab;
    private Vector<Notice_List> mVector;

    public static NoticeLab get(Context context, Vector<Notice_List> vec) {
        if(sNoticeLab == null) {
            sNoticeLab = new NoticeLab(context, vec);
        }
        return sNoticeLab;
    }

    private NoticeLab(Context context, Vector<Notice_List> vec) {
        this.mVector = vec;
    }

    public Vector<Notice_List> getNotices() {
        return mVector;
    }

    public Notice_List getNotice(UUID id) {
        for(Notice_List list : mVector) {
            if(list.getId().equals(id)) {
                return list;
            }
        }
        return null;
    }
}
