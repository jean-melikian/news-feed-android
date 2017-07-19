package fr.esgi.newsfeed.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.application.Session;
import fr.esgi.newsfeed.fragments.home.HomeFragment;
import fr.esgi.newsfeed.fragments.news.NewsFragment;
import fr.esgi.newsfeed.fragments.topics.TopicFragment;
import fr.esgi.newsfeed.helpers.Constants;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.User;
import fr.esgi.newsfeed.services.user.UserService;

public class MainActivity extends BaseActivity {

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

    /**
     * Function which initialize the Bottom Navigation View
     */
    private void initBottomNavigation() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        //Launch Home Fragment
                        currentFragment = new HomeFragment();
                        break;
                    case R.id.menu_news:
                        // Launch News Fragment
                        currentFragment = new NewsFragment();
                        break;

                    case R.id.menu_topics:
                        //Launch Topic Fragment
                        currentFragment = new TopicFragment();
                        break;
                }
                ft.replace(R.id.container, currentFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
                return true;
            }
        });
    }
}
