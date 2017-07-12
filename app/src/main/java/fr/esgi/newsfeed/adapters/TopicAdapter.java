package fr.esgi.newsfeed.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.viewHolder.TopicViewHolder;

/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {

    private Context mContext;
    private List<Topic> mListTopics;

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {

    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return 0;
    }
}
