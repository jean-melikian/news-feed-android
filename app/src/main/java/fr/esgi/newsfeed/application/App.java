package fr.esgi.newsfeed.application;

import android.app.Application;

/**
 * Created by norbert on 28/06/2017.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Session.start(getApplicationContext(), null);
    }
}