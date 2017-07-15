package fr.esgi.newsfeed.services.User;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.Credentials;
import fr.esgi.newsfeed.models.SessionToken;
import fr.esgi.newsfeed.models.User;

/**
 * Created by antoinepelletier on 13/07/2017.
 */

public interface IAuthService {

    void create(User user, IServiceResultListener<String> resultListener);

	void login(Credentials infos, IServiceResultListener<SessionToken> resultListener);
}
