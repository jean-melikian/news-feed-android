package fr.esgi.newsfeed.services.post;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.models.Post;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public interface IPostService {

    void createPost(Post post, IServiceResultListener<String> resultListener) throws ServiceException;

    void getPostsList(IServiceResultListener<List<Post>> resultListener) throws ServiceException;

    void getPostById(String id, IServiceResultListener<Post> resultListener) throws ServiceException;

    void deletePost(String id, IServiceResultListener<String> resultListener) throws ServiceException;

    void updatePost(Post post, IServiceResultListener<Post> resultListener) throws ServiceException;
}
