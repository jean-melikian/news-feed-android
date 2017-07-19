package fr.esgi.newsfeed.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.models.User;
import fr.esgi.newsfeed.viewHolder.TopicViewHolder;

/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {

    private Context mContext;
    private List<Topic> mListTopics;

    public TopicAdapter(Context mContext, List<Topic> mListTopics) {
        this.mContext = mContext;
        this.mListTopics = mListTopics;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_topics_item, parent, false);

        return new TopicViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        holder.getTextTopicsTitle().setText(mListTopics.get(position).getTitle());
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
