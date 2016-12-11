package com.memory.mendybarouk.notes;

import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.io.Serializable;

/**
 * Created by mendybarouk on 08/12/2016.
 */

public class Note implements Serializable {
    private String title;
    private String data;
    Calendar time;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Note(String title, String data){
        this.title = title;
        this.data = data;
        time = Calendar.getInstance();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Calendar getTime() {
        return time;
    }
}
