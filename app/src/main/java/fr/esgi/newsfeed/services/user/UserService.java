package fr.esgi.newsfeed.services.user;

import fr.esgi.newsfeed.activities.MyApplication;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by norbert on 30/06/2017.
 */

public class UserService implements IUserService {

    /**
     * Empty Constructor
     */
    public UserService() {

    }

    private IRFUserService mRfUserService;


    private IRFUserService getmRfUserService() {
        if (mRfUserService == null) {
            mRfUserService = MyApplication.getDefault().create(IRFUserService.class);
        }
        return mRfUserService;
    }

    @Override
    public void read(String userID, final IServiceResultListener<User> resultListener) {
        Call<User> call = getmRfUserService().read("/users/", userID);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ServiceResult<User> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());

                    if (resultListener != null)
                        resultListener.onResult(result);
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ServiceResult<User> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void updateUser(User user, final IServiceResultListener<User> resultListener) {
        Call<User> call = getmRfUserService().updateUser("/users", user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ServiceResult<User> result = new ServiceResult<>();
                if (response.code() == 200) {
                    if (response.body() != null) {
                        result.setData(response.body());
                    }
                } else
                    result.setError(new ServiceException(response.code()));
                if (resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                ServiceResult<User> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }
}