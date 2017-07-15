package fr.esgi.newsfeed.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.application.Session;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.User;
import fr.esgi.newsfeed.services.User.UserService;

public class MainActivity extends AppCompatActivity {

	private Session session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		UserService userService = new UserService();

		try {
			session = Session.get();

			userService.getCurrentUser(new IServiceResultListener<User>() {
				@Override
				public void onResult(ServiceResult<User> result) {
					if (result.getError() == null) {
						session.setUser(result.getData());
						Log.d("User", session.getUser().toString());
					} else
						Log.e("UserService", result.getError().toString());
				}
			});
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
