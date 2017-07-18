package fr.esgi.newsfeed.services.news;

import java.util.List;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.models.News;

/**
 * Created by antoinepelletier on 17/07/2017.
 */

public interface INewsService {

    void createNews(News news, IServiceResultListener<String> resultListener) throws ServiceException;

    void getNewsList(IServiceResultListener<List<News>> resultListener) throws ServiceException;

    void getNewsById(String id, IServiceResultListener<News> resultListener) throws ServiceException;

    void deleteNews(String id, IServiceResultListener<String> resultListener) throws ServiceException;

    void updateNews(News news, IServiceResultListener<News> resultListener) throws ServiceException;

}
