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
import fr.esgi.newsfeed.viewHolder.NewsViewHolder;


/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private Context mContext;
    private List<News> mNews = new ArrayList<>();
    private int mLayout_resource;

    private OnItemClickListener mOnItemClickListener;

    public static interface OnItemClickListener {
        public void onItemClicked(News news);
    }

    public NewsAdapter(List<News> mDataList, Context context) {
        mNews.clear();
        mNews.addAll(mDataList);
        this.notifyDataSetChanged();
        mContext = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_row, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.holder_img.setImageDrawable(mContext.getResources().getDrawable(R.drawable.met_ic_clear));
        holder.holder_title.setText(mNews.get(position).getTitle());
        holder.holder_content.setText(mNews.get(position).getContent());

        holder.itemView.setOnClickListener(new NewsAdapter.OnClick(position));
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
        return mNews != null ? mNews.size() : 0;
    }

    /**
     * Remove the news from the adapter and the list
     *
     * @param position
     */
    public void remove(int position) {
        mNews.remove(position);
    }


    private class OnClick implements View.OnClickListener {

        private int position;

        public OnClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClicked(mNews.get(position));
        }
    }
}