package fr.esgi.newsfeed.services.topic;

import android.util.Log;

import java.util.List;

import fr.esgi.newsfeed.application.Session;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceGenerator;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.SessionToken;
import fr.esgi.newsfeed.models.Topic;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public class TopicService implements ITopicService {

    private IRFTopicService mRfTopicService;
    private SessionToken currentToken;

    /**
     * Empty Constructor
     */
    public TopicService() {

    }

    private IRFTopicService getTokenMRfTopicService() throws ServiceException {
        if (mRfTopicService == null) {

            mRfTopicService = ServiceGenerator.createAuthService(IRFTopicService.class, Session.get().getSessionToken());
        }
        return mRfTopicService;
    }

    @Override
    public void createTopic(Topic topic, final IServiceResultListener<String> resultListener) throws ServiceException {
        Call<ResponseBody> call = getTokenMRfTopicService().createTopic(topic);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
    public void getTopicsList(final IServiceResultListener<List<Topic>> resultListener) throws ServiceException {
        Call<List<Topic>> call = getTokenMRfTopicService().getTopicsList();

        call.enqueue(new Callback<List<Topic>>() {
            @Override
            public void onResponse(Call<List<Topic>> call, Response<List<Topic>> response) {
                Log.d("TopicService", "Code: " + String.valueOf(response.code()) + "\n Body:" + response.body().toString());
                ServiceResult<List<Topic>> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());
                } else {
                    result.setError(new ServiceException(response.code()));
                }

                if (resultListener != null)
                    resultListener.onResult(result);
            }

            @Override
            public void onFailure(Call<List<Topic>> call, Throwable t) {
                t.printStackTrace();
                ServiceResult<List<Topic>> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void getTopicById(String id, final IServiceResultListener<Topic> resultListener) throws ServiceException {
        Call<Topic> call = getTokenMRfTopicService().getTopicById(id);

        call.enqueue(new Callback<Topic>() {
            @Override
            public void onResponse(Call<Topic> call, Response<Topic> response) {
                ServiceResult<Topic> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());

                    if (resultListener != null)
                        resultListener.onResult(result);
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Topic> call, Throwable t) {
                ServiceResult<Topic> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void deleteTopic(String id, final IServiceResultListener<String> resultListener) throws ServiceException {
        Call<ResponseBody> call = getTokenMRfTopicService().deleteTopic(id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ServiceResult<String> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body().toString());

                    if (resultListener != null)
                        resultListener.onResult(result);
                } else {
                    result.setError(new ServiceException(response.code()));
                }
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
    public void updateTopic(Topic topic, final IServiceResultListener<Topic> resultListener) throws ServiceException {
        Call<Topic> call = getTokenMRfTopicService().updateTopic(topic);

        call.enqueue(new Callback<Topic>() {
            @Override
            public void onResponse(Call<Topic> call, Response<Topic> response) {
                ServiceResult<Topic> result = new ServiceResult<>();
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
            public void onFailure(Call<Topic> call, Throwable t) {
                ServiceResult<Topic> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }
}
