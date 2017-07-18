package fr.esgi.newsfeed.services.user;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.models.User;

/**
 * Created by norbert on 30/06/2017.
 */

public interface IUserService {

    void read(String userID, IServiceResultListener<User> resultListener);

    void updateUser(User user, IServiceResultListener<User> resultListener);

}