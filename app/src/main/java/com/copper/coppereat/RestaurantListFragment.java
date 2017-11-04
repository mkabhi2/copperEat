package com.copper.coppereat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.copper.coppereat.adapterClasses.MyRestaurantRecyclerViewAdapter;
import com.copper.coppereat.customObjects.Restaurants;
import com.copper.coppereat.utilityClasses.RecyclerItemClickListener;
import com.copper.coppereat.utilityClasses.RestaurantListItemDecoration;

import java.util.ArrayList;

/**
 * Created by abhmishr on 11/3/17.
 */

public class RestaurantListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String RESTRAUNT_LIST = "restraunt-list";
    private static final int FILTER_REQUEST = 1;

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private int expressDelivery;
    private String sortValue;
    ArrayList<String> priceValues;
    ArrayList<String> filterValues;
    ArrayList<Restaurants> restaurantsList;
    public RecyclerView recyclerView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RestaurantListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RestaurantListFragment newInstance(int columnCount, ArrayList<Restaurants> restaurants) {
        RestaurantListFragment fragment = new RestaurantListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putParcelableArrayList(RESTRAUNT_LIST, restaurants);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            restaurantsList = getArguments().getParcelableArrayList(RESTRAUNT_LIST);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);

        // Set the adapter
        if (true) {
            final Context context = view.getContext();
            recyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
            /*
            Dummy Data
            restaurantsList = new ArrayList<Restaurants>();
            for(int i=0;i<20;i++){
                 restaurantsList.add(new Restaurants(1,"Bubbly","Bub@gg.com","123","1234","1234","1234","1234","Halwa puri khao","rrr . No min order . 45 mins","15% Off","3.9",12960));
            }
            */

            recyclerView.setAdapter(new MyRestaurantRecyclerViewAdapter(restaurantsList, getContext()));
            recyclerView.addItemDecoration(new RestaurantListItemDecoration() );
            recyclerView.setNestedScrollingEnabled(false); //False enables to let it scroll with whole activity


            //Adding touch listener to recycler view
            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {

                            //TODO REPLACE WITH ORIGINAL RESTAURANT DETAILS ACTIVITY
                            Intent intent = new Intent();
                            intent.setClass(context,TempTestingActivity.class);
                            startActivity(intent);
                        }
                    })
            );


        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Restaurants item);
    }
}
