package fr.esgi.newsfeed.services.post;

import java.util.List;

import fr.esgi.newsfeed.models.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public interface IRFPostService {

    @POST("/posts")
    Call<ResponseBody> createPost(@Body Post post);

    @GET("/posts")
    Call<List<Post>> getPostsList();

    @GET("/posts/{post_id}")
    Call<Post> getPostById(@Path("post_id") String id);

    @DELETE("/posts/{post_id}")
    Call<ResponseBody> deletePost(@Path("post_id") String id);

    @PUT("/posts")
    Call<Post> updatePost(@Body Post post);
}
