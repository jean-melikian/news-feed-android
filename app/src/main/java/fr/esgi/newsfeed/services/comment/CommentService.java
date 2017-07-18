package fr.esgi.newsfeed.services.comment;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.Comment;
import fr.esgi.newsfeed.services.news.IRFNewsService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public class CommentService implements ICommentService {

    private IRFCommentService mRfCommentService;
    private SessionToken currentToken;

    /**
     * Empty Constructor
     */
    public CommentService() {

    }

    private IRFCommentService getmRfCommentService() {
        if (mRfCommentService == null) {
            mRfCommentService = ServiceGenerator.createService(IRFNewsService.class);
        }
        return mRfCommentService;
    }

    @Override
    public void createComment(Comment comment, final IServiceResultListener<String> resultListener) {

        Call<ResponseBody> call = getmRfCommentService().createComment(comment);

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
    public void getCommentsList(final IServiceResultListener<List<Comment>> resultListener) {
        Call<List<Comment>> call = getmRfCommentService().getCommentsList();

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                ServiceResult<List<Comment>> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                ServiceResult<List<Comment>> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void getCommentById(String id, final IServiceResultListener<Comment> resultListener) {
        Call<Comment> call = getmRfCommentService().getCommentById(id);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                ServiceResult<Comment> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());

                    if (resultListener != null)
                        resultListener.onResult(result);
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                ServiceResult<Comment> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void deleteComment(String id, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfCommentService().deleteComment(id);

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
    public void updateComment(Comment comment, final IServiceResultListener<Comment> resultListener) {
        Call<Comment> call = getmRfCommentService().updateComment(comment);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                ServiceResult<Comment> result = new ServiceResult<>();
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
            public void onFailure(Call<Comment> call, Throwable t) {
                ServiceResult<Comment> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }
}
