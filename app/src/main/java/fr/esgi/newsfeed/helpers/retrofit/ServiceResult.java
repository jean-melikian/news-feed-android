package fr.esgi.newsfeed.helpers.retrofit;

/**
 * Created by norbert on 30/06/2017.
 */

public class ServiceResult<T> {
    T mData;
    ServiceException mError;

    public T getData() {
        return mData;
    }

    public void setData(T mData) {
        this.mData = mData;
    }

    public ServiceException getError() {
        return mError;
    }

    public void setError(ServiceException mError) {
        this.mError = mError;
    }
}
