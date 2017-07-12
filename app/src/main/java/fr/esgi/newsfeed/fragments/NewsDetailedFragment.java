package fr.esgi.newsfeed.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.adapters.CommentsAdapter;
import fr.esgi.newsfeed.adapters.NewsAdapter;
import fr.esgi.newsfeed.models.Comment;
import fr.esgi.newsfeed.models.News;


/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class NewsDetailedFragment extends Fragment implements CommentsAdapter.OnItemClickListener {

    private TextView mTitle;
    private TextView mContent;
    private TextView mDate;
    private RecyclerView mRecyclerViewComments;
    private CommentsAdapter mCommentAdapter;

    private News currentNews;
    private List<Comment> mListComments;


    private View.OnClickListener mBtnEditClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    private View.OnClickListener mBtnDeleteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); //get position which is swipe

            if (direction == ItemTouchHelper.RIGHT) {    //if swipe left

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); //alert for confirm to delete
                builder.setMessage("SUPPRIMER");    //set message

                builder.setPositiveButton("Etes vous sûr de vouloir supprimer ?", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCommentAdapter.notifyItemRemoved(position);    //item removed from recylcerview
                        // TODO Launch Delete Service
                        mListComments.remove(position);  //then remove item
                        return;
                    }
                }).setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mCommentAdapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        mCommentAdapter.notifyItemRangeChanged(position, mCommentAdapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                        return;
                    }
                }).show();  //show alert dialog
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_detailed, container, false);

        mTitle = (TextView) v.findViewById(R.id.tv_news_title);
        mContent = (TextView) v.findViewById(R.id.tv_news_content);
        mDate = (TextView) v.findViewById(R.id.tv_news_date);
        mRecyclerViewComments = (RecyclerView) v.findViewById(R.id.rv_comments);

        mListComments = getCommentsByNews();
        mCommentAdapter = new CommentsAdapter(mListComments, getContext());
        mRecyclerViewComments.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewComments.setAdapter(mCommentAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerViewComments);

        return v;
    }

    @Override
    public void onItemClicked(Comment comment) {
        // TODO : Launch CommentDetailedFragment
    }

    public List<Comment> getCommentsByNews() {
        // TODO : Launch Service to get the comments

        return null;
    }
}
