package fr.esgi.newsfeed.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antoinepelletier.mynewsproject.R;
import com.example.antoinepelletier.mynewsproject.models.News;

import java.util.List;

/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private Context mContext;
    private List<News> mNews;
    private int mLayout_resource;
    OnNewsClickListener mItemClickListener;

    public TopicAdapter(Context mContext, List<News> mNews, int layout_resource, OnNewsClickListener itemClickListener) {
        this.mContext = mContext;
        this.mNews = mNews;
        this.mLayout_resource = layout_resource;
        mItemClickListener = itemClickListener;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_row, parent, false);
        return new TopicViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        holder.holder_img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_menu_send));
        holder.holder_title.setText(mNews.get(position).getTitle());
        holder.holder_content.setText(mNews.get(position).getContent());
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
        return mNews.size();
    }

    /**
     * Remove the news from the adapter and the list
     *
     * @param position
     */
    public void remove(int position) {
        mNews.remove(position);
    }

    /**
     * View holder class
     */
    public class TopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView holder_title;
        public TextView holder_content;
        public ImageView holder_img;

        public TopicViewHolder(View view) {
            super(view);
            holder_img = (ImageView) view.findViewById(R.id.item_image);
            holder_title = (TextView) view.findViewById(R.id.item_title);
            holder_content = (TextView) view.findViewById(R.id.item_description);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getPosition());
            }
        }
    }
}
