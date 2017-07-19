package fr.esgi.newsfeed.fragments.news;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.adapters.CommentsAdapter;
import fr.esgi.newsfeed.adapters.NewsAdapter;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.Comment;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.services.news.NewsService;


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
    private static String CURRENT_NEWS = "CURRENT_NEWS";
    private List<Comment> mListComments;

    private Button btn_delete_news;
    private Button btn_update_news;

    private NewsService mNewsService;


    private View.OnClickListener mBtnEditClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO : Launch Fragment EditNews
        }
    };
    private View.OnClickListener mBtnDeleteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO : Launch Service Delete News
            mNewsService = new NewsService();
            try {
                mNewsService.deleteNews(currentNews.get_id(), new IServiceResultListener<String>() {
                    @Override
                    public void onResult(ServiceResult<String> result) {
                        if (result.getData() != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                Toast.makeText(getContext(), "Your news has been deleted.", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "A problem occured during the suppresion, please try again.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } catch (ServiceException e) {
                e.printStackTrace();
            }

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

        Bundle bundle = getArguments();
        currentNews = (News) bundle.getSerializable(CURRENT_NEWS);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_detailed, container, false);

        mTitle = (TextView) v.findViewById(R.id.tv_news_title);
        mTitle.setText(currentNews.getTitle());

        mContent = (TextView) v.findViewById(R.id.tv_news_content);
        mContent.setText(currentNews.getContent());

        mDate = (TextView) v.findViewById(R.id.tv_news_date);
        mDate.setText(currentNews.getDate());

        mRecyclerViewComments = (RecyclerView) v.findViewById(R.id.rv_comments);

        mListComments = getCommentsByNews();
        mCommentAdapter = new CommentsAdapter(mListComments, getContext());
        mRecyclerViewComments.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewComments.setAdapter(mCommentAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerViewComments);

        btn_delete_news = (Button) v.findViewById(R.id.btn_delete_news);
        btn_delete_news.setOnClickListener(mBtnDeleteClickListener);
        btn_update_news = (Button) v.findViewById(R.id.btn_update_news);
        btn_update_news.setOnClickListener(mBtnEditClickListener);

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
