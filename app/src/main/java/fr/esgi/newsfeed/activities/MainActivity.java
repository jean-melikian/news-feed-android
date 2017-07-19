package fr.esgi.newsfeed.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.application.Session;
import fr.esgi.newsfeed.fragments.home.HomeFragment;
import fr.esgi.newsfeed.fragments.news.NewsFragment;
import fr.esgi.newsfeed.fragments.topics.TopicFragment;
import fr.esgi.newsfeed.helpers.Constants;
import fr.esgi.newsfeed.helpers.activity.FloatingButtonType;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.User;
import fr.esgi.newsfeed.services.user.UserService;

public class MainActivity extends BaseActivity {

    private FloatingActionButton fab;
    private Session session;
    private Fragment currentFragment;
    private BottomNavigationView mBottomNavigationView;


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

        initBottomNavigation();

        if (savedInstanceState == null) {
            currentFragment = new HomeFragment();
            getFragmentManager().beginTransaction().add(R.id.container, currentFragment).commit();
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initBottomNavigation();
        showBackButton(false);
    }

    @Override
    public String getTitleBarTitle() {
        return "Home";
    }

    @Override
    public String getHexActionbarColor() {
        return Constants.ACTION_BAR_COLOR;
    }

    @Override
    public int getTitleActionBarColor() {
        return getResources().getColor(R.color.black);
    }


    public void setFloatingButton(FloatingButtonType type, View.OnClickListener onClickListener) {

        fab.setVisibility(View.VISIBLE);
        switch (type) {
            case NONE:
                fab.setVisibility(View.GONE);
                break;
            case ADD:
                fab.setImageResource(R.drawable.ic_add_white_24dp);
                break;
            case EDIT:
                fab.setImageResource(R.drawable.ic_mode_edit_white_24dp);
                break;
            case SAVE:
                fab.setImageResource(R.drawable.ic_save_white_24dp);
                break;
        }

        fab.setOnClickListener(onClickListener);
    }


    /**
     * Function which initialize the Bottom Navigation View
     */
    private void initBottomNavigation() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();


                fab.setVisibility(View.GONE);

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        //Launch Home Fragment
                        currentFragment = new HomeFragment();
                        ft.replace(R.id.container, currentFragment);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack(null);
                        ft.commit();

                        break;
                    case R.id.menu_news:
                        // Launch News Fragment
                        currentFragment = new NewsFragment();
                        ft.replace(R.id.container, currentFragment);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack(null);
                        ft.commit();
                        break;

                    case R.id.menu_topics:
                        //Launch Topic Fragment
                        currentFragment = new TopicFragment();
                        ft.replace(R.id.container, currentFragment);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                }
                return true;
            }
        });
    }
}
