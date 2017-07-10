package fr.esgi.newsfeed.services.User;

import fr.esgi.newsfeed.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by norbert on 30/06/2017.
 */

public interface IRFUserService {
    @POST("users/")
    Call<ResponseBody> create(@Body User user);

    @GET("{path}")
    Call<User> read(@Path(value = "path", encoded = true) String path);
}