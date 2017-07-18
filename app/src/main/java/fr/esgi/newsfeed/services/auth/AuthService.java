package fr.esgi.newsfeed.services.auth;

import fr.esgi.newsfeed.activities.MyApplication;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.LoginInformations;
import fr.esgi.newsfeed.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 13/07/2017.
 */

public class AuthService implements IAuthService {

    /**
     * Empty Constructor
     */
    public AuthService() {

    }

    private IRFAuthService mRfAuthService;


    private IRFAuthService getmRfAuthService() {
        if (mRfAuthService == null) {
            mRfAuthService = MyApplication.getDefault().create(IRFAuthService.class);
        }
        return mRfAuthService;
    }

    @Override
    public void create(User user, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfAuthService().create(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody>
                    response) {
                ServiceResult<String> result = new ServiceResult<>();
                if (response.code() == 201) {
                    if (response.body() != null) {
                        result.setData(response.body().toString());
                    }
                } else
                    result.setError(new ServiceException(response.code()));
                if (resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }

    @Override
    public void login(LoginInformations infos, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfAuthService().login(infos);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if (response.code() == 200) {
                    if (response.body() != null) {
                        result.setData(response.body().toString());
                    }
                } else
                    result.setError(new ServiceException(response.code()));
                if (resultListener != null)
                    resultListener.onResult(result);
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }


}
