package fr.esgi.newsfeed.helpers.retrofit;

/**
 * Created by norbert on 30/06/2017.
 */

public interface IServiceResultListener<T> {
    void onResult(ServiceResult<T> result);
}
