package fr.esgi.newsfeed.helpers;

import android.support.v7.appcompat.BuildConfig;

/**
 * Created by norbert on 28/06/2017.
 */

public class Constants {

    private static final String BASE_URL_DEV = "https://esgi-2017.herokuapp.com/";
    private static final String BASE_URL_PROD = "https://esgi-2017.herokuapp.com/";

    /**
     * app action bar hex code: dark yellow
     */
    public static final String ACTION_BAR_COLOR = "#ffffff";

    public static String getBaseURL() {
        if (BuildConfig.DEBUG) {
            return BASE_URL_DEV;
        } else {
            return BASE_URL_PROD;
        }
    }
}