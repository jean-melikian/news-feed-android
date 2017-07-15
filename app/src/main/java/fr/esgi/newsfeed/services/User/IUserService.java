package fr.esgi.newsfeed.services.User;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.User;

/**
 * Created by norbert on 30/06/2017.
 */

public interface IUserService {

	void getCurrentUser(IServiceResultListener<User> resultListener) throws Exception;
}