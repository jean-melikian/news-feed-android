package fr.esgi.newsfeed.services.post;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceExceptionType;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public class PostService implements IPostService {

    private IRFPostService mRfPostService;
    private SessionToken currentToken;

    /**
     * Empty Constructor
     */
    public PostService() {

    }

    private IRFPostService getmRfPostService() {
        if (mRfPostService == null) {
            mRfPostService = ServiceGenerator.createService(IRFPostService.class);
        }
        return mRfPostService;
    }

    @Override
    public void createPost(Post post, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfPostService().createPost(post);

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
    public void getPostsList(final IServiceResultListener<List<Post>> resultListener) {
        Call<List<Post>> call = getmRfPostService().getPostsList();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                ServiceResult<List<Post>> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                ServiceResult<List<Post>> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void getPostById(String id, final IServiceResultListener<Post> resultListener) {
        Call<Post> call = getmRfPostService().getPostById(id);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                ServiceResult<Post> result = new ServiceResult<>();
                if (response.code() == 200) {
                    result.setData(response.body());

                    if (resultListener != null)
                        resultListener.onResult(result);
                } else {
                    result.setError(new ServiceException(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                ServiceResult<Post> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);

            }
        });
    }

    @Override
    public void deletePost(String id, final IServiceResultListener<String> resultListener) {
        Call<ResponseBody> call = getmRfPostService().deletePost(id);

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
    public void updatePost(Post post, final IServiceResultListener<Post> resultListener) {
        Call<Post> call = getmRfPostService().updatePost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                ServiceResult<Post> result = new ServiceResult<>();
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
            public void onFailure(Call<Post> call, Throwable t) {
                ServiceResult<Post> result = new ServiceResult<>();
                result.setError(new ServiceException(t, ServiceExceptionType.UNKNOWN));
                if (resultListener != null)
                    resultListener.onResult(result);
            }
        });
    }
}
