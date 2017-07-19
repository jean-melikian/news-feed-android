package fr.esgi.newsfeed.fragments.topics;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.activities.MainActivity;
import fr.esgi.newsfeed.helpers.activity.FloatingButtonType;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.services.topic.TopicService;

/**
 * Created by antoinepelletier on 19/07/2017.
 */

public class TopicsAddFragment extends Fragment {

    private EditText mTitle;
    private EditText mContent;

    private Topic mCurrentTopic;

    private TopicService mTopicsService;

    public static TopicsAddFragment newInstance() {
        TopicsAddFragment fragment = new TopicsAddFragment();
        return fragment;
    }



    @Override
    public void onResume() {
        super.onResume();

        MainActivity main = ((MainActivity) getActivity()) ;

        if (main != null) {
            main.setTitleBar(getActivity().getResources().getString(R.string.add_topics));
            main.setCountSubFragment(1);
            main.setFloatingButton(FloatingButtonType.SAVE, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkFields(new EditText[]{mTitle, mContent})) {
                        // Launch Service Subscription
                        Date d = new Date();
                        LaunchCreateTopicsService(new Topic( "Me",
                                mTitle.getText().toString(),
                                mContent.getText().toString(), d.toString(), null));
                    }
                }
            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_topics, container, false);

        mTitle = (EditText) v.findViewById(R.id.editTexT_title_topic);
        mContent = (EditText) v.findViewById(R.id.editTexT_content_topics);

        return v;
    }

    /**
     * Function to launch the service for create a News
     *
     * @param topic
     */
    private void LaunchCreateTopicsService(Topic topic) {
        try {
            mTopicsService = new TopicService();
            mTopicsService.createTopic(topic, new IServiceResultListener<String>() {
                @Override
                public void onResult(ServiceResult<String> result) {
                    if (result.getData() != null) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            Toast.makeText(getContext(), "Your topics has been created", Toast.LENGTH_LONG).show();
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
