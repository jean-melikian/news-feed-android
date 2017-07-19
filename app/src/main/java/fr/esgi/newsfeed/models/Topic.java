package fr.esgi.newsfeed.models;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by antoinepelletier on 12/07/2017.
 */

public class Topic {
    private String author;
    private String title;
    private String content;
    @Nullable
    private String date;
    private List<Post> posts;

    public Topic(String author, String title, String content, String date, List<Post> posts) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.date = date;
        this.posts = posts;
        Log.d("Topic", toString());
    }

    public String get_id() {
        return "1";
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


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Topic topic = (Topic) o;

        if (!author.equals(topic.author)) return false;
        if (!title.equals(topic.title)) return false;
        if (!content.equals(topic.content)) return false;
        return date.equals(topic.date);

    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}