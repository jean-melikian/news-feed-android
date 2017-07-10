package fr.esgi.newsfeed.helpers.retrofit;

/**
 * Created by norbert on 30/06/2017.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Exclude {

    public boolean serialize();
    public boolean deserialize();
}