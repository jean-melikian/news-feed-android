package fr.esgi.newsfeed.services.User;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.models.SessionToken;
import okhttp3.ResponseBody;

/**
 * Created by antoinepelletier on 17/07/2017.
 */

public interface INewsService {

    void createNews(News news, IServiceResultListener<String> resultListener);

    void getNewsList(IServiceResultListener<List<News>> resultListener);

    void getNewsById(String id, IServiceResultListener<News> resultListener);

    void deleteNews(String id, IServiceResultListener<News> resultListener);

    void updateNews(News news, IServiceResultListener<News> resultListener);

}
