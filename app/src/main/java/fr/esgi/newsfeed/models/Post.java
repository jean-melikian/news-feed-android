package fr.esgi.newsfeed.models;

/**
 * Created by antoinepelletier on 12/07/2017.
 */

public class Post {
    private String title;
    private String content;
    private String date;

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

    public Post(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}