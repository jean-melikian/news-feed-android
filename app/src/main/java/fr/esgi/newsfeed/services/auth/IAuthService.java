package fr.esgi.newsfeed.services.auth;

import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.models.LoginInformations;
import fr.esgi.newsfeed.models.User;

/**
 * Created by antoinepelletier on 13/07/2017.
 */

public interface IAuthService {

    void create(User user, IServiceResultListener<String> resultListener);

    void login(LoginInformations infos, IServiceResultListener<String> resultListener);
}
