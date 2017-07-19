package fr.esgi.newsfeed.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.models.User;
import fr.esgi.newsfeed.viewHolder.TopicViewHolder;

/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicViewHolder> {

    private Context mContext;
    private List<Topic> mListTopics = new ArrayList<>();

    private TopicAdapter.OnItemClickListener mOnItemClickListener;

    public static interface OnItemClickListener {
        public void onItemClicked(News news);
    }

    public TopicAdapter(List<Topic> listTopics, Context context) {
        mListTopics.clear();
        mListTopics.addAll(listTopics);
        this.notifyDataSetChanged();
        mContext = context;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_topics_item, parent, false);

        return new TopicViewHolder(inflatedView);
    }

    public void setOnItemClickListener(TopicAdapter.OnItemClickListener listener) {
        mOnItemClickListener = listener;
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
        return mListTopics != null ? mListTopics.size() : 0;
    }
}
