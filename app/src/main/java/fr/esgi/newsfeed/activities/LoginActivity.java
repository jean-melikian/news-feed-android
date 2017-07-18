package fr.esgi.newsfeed.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.application.Session;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.Credentials;
import fr.esgi.newsfeed.models.SessionToken;
import fr.esgi.newsfeed.services.auth.AuthService;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mConnexion;
    private Button mSubscribe;

    private Context mContext;

    private AuthService mAuthService;
    private Credentials mCredentials;
    private Session session;
    private View.OnClickListener mConnexionOnClikListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!(mEmail.getText().toString().isEmpty() || mEmail == null) && !(mPassword.getText().toString().isEmpty() || mPassword == null)) {
                mCredentials = new Credentials(mEmail.getText().toString(), mPassword.getText().toString());
                LaunchLoginService(mCredentials);
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

        session = Session.start(this, null);

        if (session.getSessionToken() != null) {
            if (session.getSessionToken().validateToken()) {
                LaunchMainActivity();
            }
        } else {
            setContentView(R.layout.activity_login);

            mContext = this;

            //Init views

            mEmail = (EditText) findViewById(R.id.eT_email);
            mPassword = (EditText) findViewById(R.id.eT_password);
            mConnexion = (Button) findViewById(R.id.btn_connexion_login);
            mSubscribe = (Button) findViewById(R.id.btn_subscribe_inLogin);

            mConnexion.setOnClickListener(mConnexionOnClikListener);
            mSubscribe.setOnClickListener(mSubscribeOnClickListener);
        }
    }

    public void LaunchMainActivity() {
        // Start MainActivity
        Intent intentMainActivity = new Intent(this, MainActivity.class);
        startActivity(intentMainActivity);
        finish();
    }

    private void LaunchLoginService(Credentials credentials) {

        new AuthService().login(credentials, new IServiceResultListener<SessionToken>() {
            @Override
            public void onResult(ServiceResult<SessionToken> result) {
                try {
                    if (result.getError() != null)
                        Toast.makeText(mContext, result.getError().toString(), Toast.LENGTH_SHORT).show();
                    else {
                        session.setSessionToken(result.getData());

                        if (session.getSessionToken() != null) {
                            if (session.getSessionToken().validateToken()) {
                                LaunchMainActivity();
                            }
                        } else {
                            Toast.makeText(mContext, "Could not authenticate you, check your credentials !", Toast.LENGTH_LONG).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


