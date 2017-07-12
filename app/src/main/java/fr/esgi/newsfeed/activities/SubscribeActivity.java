package fr.esgi.newsfeed.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.User;
import fr.esgi.newsfeed.services.User.IRFUserService;
import fr.esgi.newsfeed.services.User.IUserService;
import fr.esgi.newsfeed.services.User.UserService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by antoinepelletier on 12/07/2017.
 */

public class SubscribeActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mFirstname;
    private EditText mLastname;

    private Button mButtonSubscribe;
    private View.OnClickListener mButtonSubscribeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (checkFields(new EditText[]{mEmail, mPassword, mFirstname, mLastname})) {
                // Launch Service Subscription
                LaunchSubrscriptionService(new User(mEmail.getText().toString(), mPassword.getText().toString(), mFirstname.getText().toString(), mLastname.getText().toString()));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_activity);

        // Init views

        mEmail = (EditText) findViewById(R.id.et_subscribe_email);
        mPassword = (EditText) findViewById(R.id.et_subscribe_password);
        mFirstname = (EditText) findViewById(R.id.et_firstname);
        mLastname = (EditText) findViewById(R.id.et_lastname);
        mButtonSubscribe = (Button) findViewById(R.id.btn_subscribe);


        mButtonSubscribe.setOnClickListener(mButtonSubscribeListener);

    }

    private void LaunchSubrscriptionService(User user) {


    }

    public boolean checkEditText(EditText editText) {
        return !(editText.getText().toString().isEmpty() || editText == null);
    }

    public boolean checkFields(EditText[] editTexts) {
        for (int i = 0; i < editTexts.length; i++) {
            if (!checkEditText(editTexts[i]))
                return false;
        }
        return true;
    }
}