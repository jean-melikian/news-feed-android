package fr.esgi.newsfeed.fragments.topics;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.activities.MainActivity;
import fr.esgi.newsfeed.adapters.TopicAdapter;
import fr.esgi.newsfeed.helpers.activity.FloatingButtonType;
import fr.esgi.newsfeed.helpers.retrofit.IServiceResultListener;
import fr.esgi.newsfeed.helpers.retrofit.ServiceException;
import fr.esgi.newsfeed.helpers.retrofit.ServiceResult;
import fr.esgi.newsfeed.models.News;
import fr.esgi.newsfeed.models.Topic;
import fr.esgi.newsfeed.services.topic.TopicService;

/**
 * Created by antoinepelletier on 20/06/2017.
 */

public class TopicsFragment extends Fragment implements TopicAdapter.OnItemClickListener  {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private List<Topic> mListTopics;
    private TopicAdapter mAdapter;
    private JSONObject jsonObject;
    private String title;
    private String content;
    private String date;

    private TopicAdapter.OnItemClickListener onItemclick;

    private TopicService mTopicService;

    public static TopicsFragment newInstance() {
        TopicsFragment fragment = new TopicsFragment();
        return fragment;
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); //get position which is swipe

            if (direction == ItemTouchHelper.RIGHT) {    //if swipe on right

                AlertDialog.Builder builder = null; //alert for confirm to delete
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    builder = new AlertDialog.Builder(getContext());
                }
                builder.setMessage("SUPPRIMER");    //set message

                builder.setPositiveButton("Etes vous sûr de vouloir supprimer ?", new DialogInterface.OnClickListener() { //when click on DELETE
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.notifyItemRemoved(position);    //item removed from recylcerview
                        mTopicService = new TopicService();
                        try {
                            mTopicService.deleteTopic(mListTopics.get(position).get_id(), new IServiceResultListener<String>() {
                                @Override
                                public void onResult(ServiceResult<String> result) {
                                    if (result.getData() != null) {
                                        mListTopics.remove(position);  //then remove item
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            Toast.makeText(getContext(), "Your news has been deleted.", Toast.LENGTH_LONG).show();
                                        } else {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                Toast.makeText(getContext(), "A problem occured during the suppresion, please try again.", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                }
                            });
                        } catch (ServiceException e) {
                            e.printStackTrace();
                        }

                        return;
                    }
                }).setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.notifyItemRemoved(position + 1);    //notifies the RecyclerView Adapter that data in adapter has been removed at a particular position.
                        mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());   //notifies the RecyclerView Adapter that positions of element in adapter has been changed from position(removed element index to end of list), please update it.
                        return;
                    }
                }).show();  //show alert dialog
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTopicService = new TopicService();

        mListTopics = getTopics();

    }

    @Override
    public void onResume() {
        super.onResume();

        //onItemclick = this;

        MainActivity main = ((MainActivity) getActivity());
        if (main != null) {
            main.setTitleBar(getActivity().getResources().getString(R.string.topics));
            main.setFloatingButton(FloatingButtonType.ADD, new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    TopicsAddFragment topicsAddFragment = new TopicsAddFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.container, topicsAddFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_topics, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView_topics);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        launchTopicService();
        return v;
    }

    protected void launchTopicService() {
        try {
            mTopicService.getTopicsList(new IServiceResultListener<List<Topic>>() {
                @Override
                public void onResult(ServiceResult<List<Topic>> result) {
                    if(result.getError() == null) {
                        Log.d("TopicService", result.toString());
                        mListTopics = result.getData();
                        mAdapter = new TopicAdapter(mListTopics, getActivity());
                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        Log.e("TopicService", result.toString());
                    }
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public List<Topic> getTopics() {

        mTopicService = new TopicService();
        try {

            mTopicService.getTopicsList(new IServiceResultListener<List<Topic>>() {
                @Override
                public void onResult(ServiceResult<List<Topic>> result) {
                    mListTopics = result.getData();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mAdapter = new TopicAdapter(mListTopics, getContext());
                        mAdapter.setOnItemClickListener(onItemclick);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                    mRecyclerView.setAdapter(mAdapter);
                    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
                    itemTouchHelper.attachToRecyclerView(mRecyclerView);
                }
            });
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onItemClicked(News news) {

    }
}