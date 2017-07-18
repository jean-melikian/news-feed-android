package fr.esgi.newsfeed.services.comment;

import java.util.List;

import fr.esgi.newsfeed.models.Comment;
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

public interface IRFCommentService {

    @POST("/comments")
    Call<ResponseBody> createComment(@Body Comment comment);

    @GET("/comments")
    Call<List<Comment>> getCommentsList();

    @GET("/comments/{comment_id}")
    Call<Comment> getCommentById(@Path("news_id") String id);

    @DELETE("/comments/{comment_id}")
    Call<ResponseBody> deleteComment(@Path("comment_id") String id);

    @PUT("/comments")
    Call<Comment> updateComment(@Body Comment comment);
}
