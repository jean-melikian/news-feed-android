package fr.esgi.newsfeed.fragments.topics;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.activities.MainActivity;
import fr.esgi.newsfeed.adapters.TopicAdapter;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.services.topic.TopicService;

/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class TopicFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private List<Topic> mListTopics;
    private TopicAdapter mAdapter;
    private JSONObject jsonObject;
    private String title;
    private String content;
    private String date;

    private TopicService mTopicService;

    public static TopicFragment newInstance() {
        TopicFragment fragment = new TopicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setTitleBar(getActivity().getResources().getString(R.string.topics));
        }

         mTopicService = new TopicService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_topics, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_topics);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        launchTopicService();
        return v;
    }

    protected void launchTopicService() {
        try {
            mTopicService.getTopicsList(new IServiceResultListener<List<Topic>>() {
                @Override
                public void onResult(ServiceResult<List<Topic>> result) {
                    if(result.getError() == null) {
                        Log.d("TopicService", result.toString());
                        mListTopics = result.getData();
                        mAdapter = new TopicAdapter(getActivity(), mListTopics);
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Log.e("TopicService", result.toString());
                    }
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}