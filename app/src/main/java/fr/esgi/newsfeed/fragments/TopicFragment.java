package fr.esgi.newsfeed.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.adapters.TopicAdapter;
import fr.esgi.newsfeed.models.Topic;

/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class TopicFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Topic> mListTopics;
    private TopicAdapter mAdapter;
    private JSONObject jsonObject;
    private String title;
    private String content;
    private String date;

    public static TopicFragment newInstance() {
        TopicFragment fragment = new TopicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_topics, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_topics);

        return v;
    }

}