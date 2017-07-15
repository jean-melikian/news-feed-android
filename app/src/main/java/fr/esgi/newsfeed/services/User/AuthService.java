package fr.esgi.newsfeed.services.User;

import android.util.Log;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceGenerator;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.Credentials;
import fr.esgi.newsfeed.models.SessionToken;
import fr.esgi.newsfeed.models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 13/07/2017.
 */

public class AuthService implements IAuthService {

    private IRFAuthService mRfAuthService;

    /**
     * Empty Constructor
     */
    public AuthService() {

    }

    private IRFAuthService getmRfAuthService() {
        if (mRfAuthService == null) {
            mRfAuthService = ServiceGenerator.createService(IRFAuthService.class);
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
    public void login(Credentials credentials, final IServiceResultListener<SessionToken> resultListener) {
        Call<String> call = getmRfAuthService().login(credentials);

        Log.d("Auth", "debug");
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                ServiceResult<SessionToken> result = new ServiceResult<>();
                Log.e("Auth", String.valueOf(response.code()));
                Log.e("Auth", response.body());
                if (response.isSuccessful()) {

                    Log.d("Auth", "Token: " + response.body());
                    if (response.body() != null) {
                        result.setData(new SessionToken(response.body()));

                    }
                } else
                    result.setError(new ServiceException(response.code()));
                if (resultListener != null)
                    resultListener.onResult(result);
            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                ServiceResult<SessionToken> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }


}
