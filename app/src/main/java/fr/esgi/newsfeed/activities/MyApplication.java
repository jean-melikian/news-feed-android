package fr.esgi.newsfeed.activities;
import android.app.Application;

import fr.esgi.newsfeed.helpers.Constants;
import retrofit2.Retrofit;

/**
 * Created by norbert on 28/06/2017.
 */

public class MyApplication extends Application {

    private static Retrofit retrofit;
    public static Retrofit getDefault() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.getBaseURL())
                    .build();
        }
        return retrofit;
    }
}
