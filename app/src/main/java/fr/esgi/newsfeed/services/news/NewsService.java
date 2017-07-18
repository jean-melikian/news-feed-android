package fr.esgi.newsfeed.services.news;

import java.util.List;

import fr.esgi.newsfeed.application.Session;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceGenerator;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.models.SessionToken;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 17/07/2017.
 */

public class NewsService implements INewsService {

    private IRFNewsService mRfNewsService;
    private SessionToken currentToken;

    /**
     * Empty Constructor
     */
    public NewsService() {

    }

    private IRFNewsService getmRfNewsService() {
        if (mRfNewsService == null) {
            mRfNewsService = ServiceGenerator.createService(IRFNewsService.class);
        }
        return mRfNewsService;
    }

    @Override
    public void createNews(News news, final IServiceResultListener<String> resultListener) {

        Call<ResponseBody> call = getmRfNewsService().createNews(news);

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
    public void getNewsList(final IServiceResultListener<List<News>> resultListener) {
        Call<List<News>> call = getmRfNewsService().getNewsList();

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                ServiceResult<List<News>> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                ServiceResult<List<News>> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void getNewsById(String id, final IServiceResultListener<News> resultListener) {
        Call<News> call = getmRfNewsService().getNewsById(id);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                ServiceResult<News> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());

                    if (resultListener != null)
                        resultListener.onResult(result);
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                ServiceResult<News> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void deleteNews(String id, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfNewsService().deleteNews(id);

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
    public void updateNews(News news, final IServiceResultListener<News> resultListener) {
        Call<News> call = getmRfNewsService().updateNews(news);

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                ServiceResult<News> result = new ServiceResult<>();
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
            public void onFailure(Call<News> call, Throwable t) {
                ServiceResult<News> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }
}
