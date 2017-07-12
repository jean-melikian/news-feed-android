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
public class NewsViewHolder extends RecyclerView.ViewHolder {

    public TextView holder_title;
    public TextView holder_content;
    public ImageView holder_img;

    public NewsViewHolder(View view) {
        super(view);
        holder_img = (ImageView) view.findViewById(R.id.item_image);
        holder_title = (TextView) view.findViewById(R.id.item_title);
        holder_content = (TextView) view.findViewById(R.id.item_description);
    }
}