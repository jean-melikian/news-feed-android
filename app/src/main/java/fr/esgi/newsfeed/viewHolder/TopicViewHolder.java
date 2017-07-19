package fr.esgi.newsfeed.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.esgi.newsfeed.R;

/**
 * Created by antoinepelletier on 13/07/2017.
 */

public class TopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView textTopicsTitle;

    public TopicViewHolder(View itemView) {
        super(itemView);

        textTopicsTitle = (TextView) itemView.findViewById(R.id.tv_topics_title);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Log.d("TopicViewHolder", textTopicsTitle.getText().toString());
    }

    public TextView getTextTopicsTitle() {
        return textTopicsTitle;
    }

}
