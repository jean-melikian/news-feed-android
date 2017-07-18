package fr.esgi.newsfeed.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.esgi.newsfeed.R;

import static android.view.View.GONE;


/**
 * Created by Antoine Pelletier on 11/07/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivityHolderInterface {

    private Context mContext;

    private ImageView mIco_back;
    private ImageView mIco_account;
    private TextView mTxtTitleBar;

    private View actionBarView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
        this.setContentView(getContentViewId());

        initActionBar(getTitleBarTitle());
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initActionBar(getTitleBarTitle());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        finish();
    }

    private void initActionBar(String titleBarTitle) {
        ActionBar activityActionBar = getSupportActionBar();
        activityActionBar.setDisplayShowHomeEnabled(false);
        activityActionBar.setDisplayShowTitleEnabled(false);
        activityActionBar.setDisplayShowCustomEnabled(true);

        actionBarView = (View) LayoutInflater.from(mContext).inflate(R.layout.custom_action_bar, null);
        activityActionBar.setCustomView(actionBarView);

        Toolbar parent = (Toolbar) actionBarView.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);

        mIco_back = (ImageView) actionBarView.findViewById(R.id.actionbar_ic_back);
        mIco_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTxtTitleBar = (TextView) actionBarView.findViewById(R.id.txt_action_title);
        if (!TextUtils.isEmpty(titleBarTitle)) {
            mTxtTitleBar.setText(titleBarTitle.replaceAll("\n", ""));
        }
        mTxtTitleBar.setTextColor(getTitleActionBarColor());

        mIco_account = (ImageView) actionBarView.findViewById(R.id.actionbar_ic_account);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            mIco_account.setImageDrawable(getResources().getDrawable(R.drawable.ico_user_black, getTheme()));
        } else {
            mIco_account.setImageDrawable(getResources().getDrawable(R.drawable.ico_user_black));
        }
        mIco_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountIntent = new Intent(view.getContext(), AccountActivity.class);
                startActivity(accountIntent);
            }
        });

    }

    public void setBackBtnInvisible() {
        mIco_back.setVisibility(GONE);
    }

    public void setBackBtnVisible() {
        mIco_back.setVisibility(View.VISIBLE);
    }

    public void setTitleBar(String title) {
        mTxtTitleBar.setText(title);
    }

    @Override
    public abstract int getContentViewId();

    @Override
    public abstract void initView();

    @Override
    public abstract String getTitleBarTitle();

    @Override
    public abstract String getHexActionbarColor();

    @Override
    public abstract int getTitleActionBarColor();

}
