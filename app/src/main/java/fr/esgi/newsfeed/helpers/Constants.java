package fr.esgi.newsfeed.helpers;

import android.support.v7.appcompat.BuildConfig;

/**
 * Created by norbert on 28/06/2017.
 */

public class Constants {

    private static final String BASE_URL_DEV = "";
    private static final String BASE_URL_PROD = "";

    public static String getBaseURL() {
        if (BuildConfig.DEBUG) {
            return BASE_URL_DEV;
        } else {
            return BASE_URL_PROD;
        }
    }
}