package fr.esgi.newsfeed.services.post;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.Post;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public interface IPostService {

    void createPost(Post post, IServiceResultListener<String> resultListener);

    void getPostsList(IServiceResultListener<List<Post>> resultListener);

    void getPostById(String id, IServiceResultListener<Post> resultListener);

    void deletePost(String id, IServiceResultListener<String> resultListener);

    void updatePost(Post post, IServiceResultListener<Post> resultListener);
}
