package fr.esgi.newsfeed.fragments.news;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.activities.MainActivity;
import fr.esgi.newsfeed.helpers.activity.FloatingButtonType;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.services.news.NewsService;

/**
 * Created by antoinepelletier on 19/07/2017.
 */

public class NewsAddFragment extends Fragment {

    private EditText mTitle;
    private EditText mContent;

    private News mCurrentNews;

    private NewsService mNewsService;

    public static NewsAddFragment newInstance() {
        NewsAddFragment fragment = new NewsAddFragment();
        return fragment;
    }



    @Override
    public void onResume() {
        super.onResume();

        MainActivity main = ((MainActivity) getActivity()) ;

        if (main != null) {
            main.setTitleBar(getActivity().getResources().getString(R.string.add_news));
            main.setCountSubFragment(1);
            main.setFloatingButton(FloatingButtonType.SAVE, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkFields(new EditText[]{mTitle, mContent})) {
                        // Launch Service Subscription
                        Date d = new Date();
                        LaunchCreateNewsService(new News(mTitle.getText().toString(), mContent.getText().toString(), d.toString()));
                    }
                }
            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_news, container, false);

        mTitle = (EditText) v.findViewById(R.id.editTexT_title_news);
        mContent = (EditText) v.findViewById(R.id.editTexT_content_news);

        return v;
    }

    /**
     * Function to launch the service for create a News
     *
     * @param news
     */
    private void LaunchCreateNewsService(News news) {
        try {
            mNewsService = new NewsService();
            mNewsService.createNews(news, new IServiceResultListener<String>() {
                @Override
                public void onResult(ServiceResult<String> result) {
                    if (result.getData() != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Toast.makeText(getContext(), "Your news has been created", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
        }
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
