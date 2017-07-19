package fr.esgi.newsfeed.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.application.Session;
import fr.esgi.newsfeed.helpers.Constants;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.User;
import fr.esgi.newsfeed.services.user.UserService;

public class AccountActivity extends BaseActivity {

    private TextView mTv_email;
    private TextView mTv_firstname;
    private TextView mTv_lastname;

    private UserService mUserService;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_account;
    }

    @Override
    public void initView() {
        mTv_email = (TextView) findViewById(R.id.tv_email_account);
        mTv_firstname = (TextView) findViewById(R.id.tv_firstname_account);
        mTv_lastname = (TextView) findViewById(R.id.tv_lastname_account);

	    try {
		    if (Session.get().getUser() == null) {
			    GetUserInformations();
		    } else {
			    currentUser = Session.get().getUser();
			    setFields();
		    }
	    } catch (ServiceException e) {
		    e.printStackTrace();
	    }

    }

	public void setFields() {
		if (currentUser != null) {
			mTv_email.setText(currentUser.getEmail());
			mTv_firstname.setText(currentUser.getFirstname());
			mTv_lastname.setText(currentUser.getLastname());
		}
	}

    /**
     * Function which returns the current informations for the user connected
     *
     * @return
     */
    private User GetUserInformations() {
        // TODO : Launch the webService to get a user by the given token
        mUserService = new UserService();
        try {
            mUserService.getCurrentUser(new IServiceResultListener<User>() {
                @Override
                public void onResult(ServiceResult<User> result) {


	                if (result.getError() == null) {
		                try {
			                Session.get().setUser((currentUser = result.getData()));
		                } catch (ServiceException e) {

			                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
			                finish();
		                }
		                Log.d("AccountActivity", currentUser.toString());
		                setFields();
	                } else
		                Log.e("AccountActivity", result.getError().toString());
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
	        Toast.makeText(this, "Could not fetch user info...", Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    public String getTitleBarTitle() {
        return getResources().getString(R.string.your_account);
    }

    @Override
    public String getHexActionbarColor() {
        return Constants.ACTION_BAR_COLOR;
    }

    @Override
    public int getTitleActionBarColor() {
        return R.color.black;
    }
}
