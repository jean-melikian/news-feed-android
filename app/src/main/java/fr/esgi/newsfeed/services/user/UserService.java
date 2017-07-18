package fr.esgi.newsfeed.services.user;

import fr.esgi.newsfeed.application.Session;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceGenerator;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by norbert on 30/06/2017.
 */

public class UserService implements IUserService {

    private IRFUserService mRfUserService;

    /**
     * Empty Constructor
     */
    public UserService() {

    }

    private IRFUserService getTokenMRfUserService() throws ServiceException {
        if (mRfUserService == null) {

            mRfUserService = ServiceGenerator.createAuthService(IRFUserService.class, Session.get().getSessionToken());
        }
        return mRfUserService;
    }


    @Override
    public void getCurrentUser(final IServiceResultListener<User> resultListener) throws ServiceException {
        Call<User> call = getTokenMRfUserService().read("/users/me");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                ServiceResult<User> result = new ServiceResult<User>();

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
                ServiceResult<User> result = new ServiceResult<User>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));

                if (resultListener != null) {
                    resultListener.onResult(result);
                }
            }
        });
    }

    @Override
    public void updateUser(User user, final IServiceResultListener<User> resultListener) throws ServiceException {
        Call<User> call = getTokenMRfUserService().updateUser(user);

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