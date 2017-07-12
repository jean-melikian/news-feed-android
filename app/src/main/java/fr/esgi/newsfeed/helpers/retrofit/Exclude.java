package fr.esgi.newsfeed.helpers.retrofit;

/**
 * Created by norbert on 30/06/2017.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Exclude {
    /**
     * If {@code true}, the field marked with this annotation is not written out in the JSON while
     * serializing. Defaults to {@code true}.
     */
    public boolean serialize() default true;

    /**
     * If {@code true}, the field marked with this annotation is not deserialized from the JSON.
     * Defaults to {@code true}.
     */
    public boolean deserialize() default true;
}