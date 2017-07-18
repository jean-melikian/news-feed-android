package fr.esgi.newsfeed.services.auth;

import fr.esgi.newsfeed.models.Credentials;
import fr.esgi.newsfeed.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by antoinepelletier on 13/07/2017.
 */

public interface IRFAuthService {

    @POST("/auth/subscribe")
    Call<ResponseBody> create(@Body User user);

    @POST("/auth/login")
    Call<String> login(@Body Credentials credentials);
}
