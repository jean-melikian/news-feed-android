package fr.esgi.newsfeed.viewHolder;

/**
 * Created by antoinepelletier on 12/07/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.esgi.newsfeed.R;

/**
 * View holder class
 */
public class CommentsViewHolder extends RecyclerView.ViewHolder {

    public TextView holder_title;
    public TextView holder_content;
    public TextView holder_date;

    public CommentsViewHolder(View view) {
        super(view);
        holder_date = (TextView) view.findViewById(R.id.title_date_comment);
        holder_title = (TextView) view.findViewById(R.id.title_item_comment);
        holder_content = (TextView) view.findViewById(R.id.item_description);
    }
}