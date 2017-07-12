package fr.esgi.newsfeed.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.models.Comment;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.viewHolder.CommentsViewHolder;


/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    private Context mContext;
    private List<Comment> mComments;

    private OnItemClickListener mOnItemClickListener;

    public static interface OnItemClickListener {
        public void onItemClicked(Comment comment);
    }

    public CommentsAdapter(List<Comment> mDataList, Context context) {
        if (mComments != null) {
            mComments.clear();
            mComments.addAll(mDataList);
            this.notifyDataSetChanged();
            mContext = context;
        }
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        holder.holder_date.setText(mComments.get(position).getDate());
        holder.holder_title.setText(mComments.get(position).getTitle());
        holder.holder_content.setText(mComments.get(position).getContent());

        holder.itemView.setOnClickListener(new CommentsAdapter.OnClick(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mComments != null ? mComments.size() : 0;
    }

    /**
     * Remove the news from the adapter and the list
     *
     * @param position
     */
    public void remove(int position) {
        mComments.remove(position);
    }


    private class OnClick implements View.OnClickListener {

        private int position;

        public OnClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClicked(mComments.get(position));
        }
    }
}
