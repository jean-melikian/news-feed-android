package fr.esgi.newsfeed.fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.activities.MainActivity;
import fr.esgi.newsfeed.adapters.NewsAdapter;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.services.news.NewsService;

public class NewsFragment extends Fragment implements NewsAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private List<News> mLstNews;

    private NewsService mNewsService;


    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); //get position which is swipe

            if (direction == ItemTouchHelper.RIGHT) {    //if swipe left

                AlertDialog.Builder builder = null; //alert for confirm to delete
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setMessage("SUPPRIMER");    //set message

                builder.setPositiveButton("Etes vous sûr de vouloir supprimer ?", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mNewsAdapter.notifyItemRemoved(position);    //item removed from recylcerview
                        // TODO Launch Delete Service
                        mLstNews.remove(position);  //then remove item
                        return;
                    }
                }).setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mNewsAdapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        mNewsAdapter.notifyItemRangeChanged(position, mNewsAdapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                        return;
                    }
                }).show();  //show alert dialog
            }
        }
    };

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setTitleBar(getActivity().getResources().getString(R.string.news));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_news);

        mLstNews = getNews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mNewsAdapter = new NewsAdapter(mLstNews, getContext());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        mRecyclerView.setAdapter(mNewsAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        return v;
    }

    @Override
    public void onItemClicked(News news) {
        // TODO : Launch fragment NewsDetailedFragment
    }

    /**
     * Function to launch the service and get all the News
     *
     * @return
     */
    public List<News> getNews() {
        // TODO : implement the service
        mNewsService = new NewsService();
        try {
            mNewsService.getNewsList(new IServiceResultListener<List<News>>() {

                @Override
                public void onResult(ServiceResult<List<News>> result) {
                    mLstNews = result.getData();
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        return null;
    }
}
