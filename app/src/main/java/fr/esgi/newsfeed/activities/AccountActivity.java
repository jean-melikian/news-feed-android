package fr.esgi.newsfeed.activities;

import android.os.Bundle;
import android.widget.TextView;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.helpers.Constants;
import fr.esgi.newsfeed.models.User;

public class AccountActivity extends BaseActivity {

    private TextView mTv_email;
    private TextView mTv_firstname;
    private TextView mTv_lastname;

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
        mTv_firstname = (TextView) findViewById(R.id.tv_lastname_account);

        GetUserInformations();
    }

    /**
     * Function which returns the current informations for the user connected
     *
     * @return
     */
    private User GetUserInformations() {
        // TODO : Launch the webService to get a user by the given token
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
