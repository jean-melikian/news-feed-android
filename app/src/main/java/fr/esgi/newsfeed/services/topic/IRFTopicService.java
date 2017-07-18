package fr.esgi.newsfeed.services.topic;

import java.util.List;

import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.models.Topic;
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

public interface IRFTopicService {

    @POST("/topics")
    Call<ResponseBody> createTopic(@Body Topic topic);

    @GET("/topics")
    Call<List<Topic>> getTopicsList();

    @GET("/topics/{topic_id}")
    Call<Topic> getTopicById(@Path("topic_id") String id);

    @DELETE("/topics/{topic_id}")
    Call<ResponseBody> deleteTopic(@Path("topic_id") String id);

    @PUT("/topics")
    Call<Topic> updateTopic(@Body Topic topic);
}
