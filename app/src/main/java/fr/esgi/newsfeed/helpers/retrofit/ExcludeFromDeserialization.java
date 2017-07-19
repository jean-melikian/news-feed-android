package fr.esgi.newsfeed.helpers.retrofit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Ozone on 19/07/2017.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcludeFromDeserialization {
	/**
	 * If {@code true}, the field marked with this annotation is not deserialized from the JSON.
	 * Defaults to {@code true}.
	 */
	boolean deserialize() default true;
}
