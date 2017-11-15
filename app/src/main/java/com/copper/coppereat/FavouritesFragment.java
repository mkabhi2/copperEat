package com.copper.coppereat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.copper.coppereat.adapterClasses.RecyclerViewAdapter;
import com.copper.coppereat.utilityClasses.RVItemClickListener;
import com.copper.coppereat.utilityClasses.RVItemDecoration;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Context context;
    RecyclerView recyclerView;
    ArrayList<Object> favoritesList = new ArrayList<Object>();

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    public static FavouritesFragment newInstance(String param1, String param2) {
        FavouritesFragment fragment = new FavouritesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        context = view.getContext();

        setUpRecyclerView(recyclerView);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void setUpRecyclerView(RecyclerView recyclerView){

        if(HomeScreenActivity.favouritesList!=null && favoritesList.isEmpty()){
            favoritesList=HomeScreenActivity.favouritesList;
        }
        recyclerView.setAdapter(new RecyclerViewAdapter(favoritesList, getContext()));
        recyclerView.addItemDecoration(new RVItemDecoration() );

        //False enables to let it scroll with whole activity
        recyclerView.setNestedScrollingEnabled(false);

        //Adding touch listener to recycler view
        recyclerView.addOnItemTouchListener(
                new RVItemClickListener(context, new RVItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        //TODO REPLACE WITH ORIGINAL RESTAURANT DETAILS ACTIVITY
                        Intent intent = new Intent();
                        intent.setClass(context,TempTestingActivity.class);
                        startActivity(intent);
                    }
                })
        );
    }
}
