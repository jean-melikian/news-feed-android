package fr.esgi.newsfeed.services.news;

import java.util.List;

import fr.esgi.newsfeed.models.News;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by antoinepelletier on 17/07/2017.
 */

public interface IRFNewsService {

    @POST("/news")
    Call<ResponseBody> createNews(@Body News news);

    @GET("/news")
    Call<List<News>> getNewsList();

    @GET("/news/{news_id}")
    Call<News> getNewsById(@Path("news_id") String id);

    @DELETE("/news/{news_id}")
    Call<ResponseBody> deleteNews(@Path("news_id") String id);

    @PUT("/news")
    Call<News> updateNews(@Body News news);


}
