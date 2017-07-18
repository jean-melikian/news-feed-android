package fr.esgi.newsfeed.services.comment;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.models.Comment;
import fr.esgi.newsfeed.models.News;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public interface ICommentService {

    void createComment(Comment comment, IServiceResultListener<String> resultListener) throws ServiceException;

    void getCommentsList(IServiceResultListener<List<Comment>> resultListener) throws ServiceException;

    void getCommentById(String id, IServiceResultListener<Comment> resultListener) throws ServiceException;

    void deleteComment(String id, IServiceResultListener<String> resultListener) throws ServiceException;

    void updateComment(Comment comment, IServiceResultListener<Comment> resultListener) throws ServiceException;
}
