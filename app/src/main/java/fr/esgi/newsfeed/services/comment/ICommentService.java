package fr.esgi.newsfeed.services.comment;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.Comment;
import fr.esgi.newsfeed.models.News;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public interface ICommentService {

    void createComment(Comment comment, IServiceResultListener<String> resultListener);

    void getCommentsList(IServiceResultListener<List<Comment>> resultListener);

    void getCommentById(String id, IServiceResultListener<Comment> resultListener);

    void deleteComment(String id, IServiceResultListener<String> resultListener);

    void updateComment(Comment comment, IServiceResultListener<News> resultListener);
}
