package fr.esgi.newsfeed.services.user;

import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by norbert on 30/06/2017.
 */

public interface IRFUserService {

    @GET("{path}")
    Call<User> read(@Path(value = "path", encoded = true) String path);

    @PUT("/users")
    Call<User> updateUser(@Body User user);
}