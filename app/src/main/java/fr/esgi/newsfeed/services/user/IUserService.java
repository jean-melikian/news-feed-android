package fr.esgi.newsfeed.services.user;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.models.User;

/**
 * Created by norbert on 30/06/2017.
 */

public interface IUserService {

	void getCurrentUser(IServiceResultListener<User> resultListener) throws Exception;
	
	void updateUser(User user, IServiceResultListener<User> resultListener) throws ServiceException;
}