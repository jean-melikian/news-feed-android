package fr.esgi.newsfeed.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.esgi.newsfeed.R;
import fr.esgi.newsfeed.activities.MainActivity;

/**
 * Created by antoinepelletier on 18/07/2017.
 */

public class HomeFragment extends Fragment {


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setTitleBar(getActivity().getResources().getString(R.string.home));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        return v;
    }

}