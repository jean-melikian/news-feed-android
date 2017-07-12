package fr.esgi.newsfeed.models;

import java.io.Serializable;

/**
 * Created by antoinepelletier on 12/07/2017.
 */

public class News implements Serializable {

    private String _id;
    private String title;
    private String content;
    private String date;

    public String get_id() {
        return _id;
    }

    public void set_id(String id) {
        this._id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public News(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }


}
