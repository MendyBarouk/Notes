package com.memory.mendybarouk.notes;

import java.io.Serializable;

/**
 * Created by mendybarouk on 08/12/2016.
 */

public class Note implements Serializable {
    private String title;
    private String data;

    public Note(String title, String data){
        this.title = title;
        this.data = data;
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

}
