package fr.esgi.newsfeed.services.User;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.User;

/**
 * Created by norbert on 30/06/2017.
 */

public interface IUserService {

    void create(User user, IServiceResultListener<String> resultListener);

    void read(String userID, IServiceResultListener<User> resultListener);
}