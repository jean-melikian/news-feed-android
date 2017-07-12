package fr.esgi.newsfeed.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.esgi.newsfeed.R;

public class LoginActivity extends AppCompatActivity {

    private static String USER_INFORMATIONS = "USER_INFORMATIONS";
    private static String TOKEN_USER = "TOKEN_USER";

    private EditText mEmail;
    private EditText mPassword;
    private Button mConnexion;
    private Button mSubscribe;

    private SharedPreferences mSharedPreferences;

    private View.OnClickListener mConnexionOnClikListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!(mEmail.getText().toString().isEmpty() || mEmail == null) && !(mPassword.getText().toString().isEmpty() || mPassword == null)) {
                // Launch Service to make the connection

            }
        }
    };
    private View.OnClickListener mSubscribeOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent subscribeIntent = new Intent(view.getContext(), SubscribeActivity.class);
            startActivity(subscribeIntent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Init views

        mEmail = (EditText) findViewById(R.id.eT_email);
        mPassword = (EditText) findViewById(R.id.eT_password);
        mConnexion = (Button) findViewById(R.id.btn_connexion_login);
        mSubscribe = (Button) findViewById(R.id.btn_subscribe_inLogin);

        mConnexion.setOnClickListener(mConnexionOnClikListener);
        mSubscribe.setOnClickListener(mSubscribeOnClickListener);

        if (!getCurrentUserToken().isEmpty() || getCurrentUserToken() == null) {
            LaunchMainActivity();
        }
    }

    /**
     * Function to get Token stored into SharedPreferences
     *
     * @return string token
     */
    public String getCurrentUserToken() {
        mSharedPreferences = getSharedPreferences(USER_INFORMATIONS, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(TOKEN_USER, "");
    }

    public boolean isTokenInvalid(String token) {
        return getCurrentUserToken().isEmpty() || getCurrentUserToken() == null;
    }


    public void LaunchMainActivity() {
        // Start MainActivity
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentMainActivity);
    }
}


