package fr.esgi.newsfeed.services.User;

import fr.esgi.newsfeed.activities.MyApplication;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.User;
import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by norbert on 30/06/2017.
 */

public class UserService implements IUserService {

    private IRFUserService mRfUserService;


    private IRFUserService getmRfUserService() {
        if (mRfUserService == null) {
            mRfUserService = MyApplication.getDefault().create(IRFUserService.class);
        }
        return  mRfUserService;
    }

    @Override
    public void create(User user, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfUserService().create(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody>
                    response) {
                ServiceResult<String> result = new ServiceResult<>();
                if(response.code() == 201)
                    result.setData(response.headers().get("Resourceuri"));
                else
                    result.setError(new ServiceException(response.code()));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ServiceResult<String> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if(resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }

    @Override
    public void read(String userID, IServiceResultListener<User> resultListener) {
        Call<User> call = getmRfUserService().read("/users/id");

    }
}