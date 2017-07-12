package fr.esgi.newsfeed.models;

/**
 * Created by antoinepelletier on 12/07/2017.
 */

public class Comment {

    private String _id;
    private String title;
    private String content;
    private String date;

    public Comment(String _id, String title, String content, String date, String topic_id) {
        this._id = _id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.topic_id = topic_id;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    private String topic_id;

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

}
