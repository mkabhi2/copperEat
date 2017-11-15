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

import com.copper.coppereat.adapterClasses.RecyclerViewAdapter;
import com.copper.coppereat.customObjects.Restaurant;
import com.copper.coppereat.utilityClasses.RVItemClickListener;
import com.copper.coppereat.utilityClasses.RVItemDecoration;

import java.util.ArrayList;

/**
 * Created by abhmishr on 11/3/17.
 */

public class RestaurantListFragment extends Fragment {

    ArrayList<Object> restaurantsList = new ArrayList<Object>();
    public RecyclerView recyclerView;
    private Context context;

    private OnListFragmentInteractionListener mListener;


    public RestaurantListFragment() {
        //mandatory empty constructor
    }

    public static RestaurantListFragment newInstance(int columnCount, ArrayList<Restaurant> restaurants) {
        RestaurantListFragment fragment = new RestaurantListFragment();
        Bundle args = new Bundle();
        args.putInt("column-count", columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            int mColumnCount = getArguments().getInt("column-count");
            if(getArguments().getParcelableArrayList("restraunt-list")!=null){
                restaurantsList.addAll(getArguments().getParcelableArrayList("restraunt-list"));
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        context = view.getContext();

        setUpRecyclerView(recyclerView);

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
        void onListFragmentInteraction(Restaurant item);
    }

    private void setUpRecyclerView(RecyclerView recyclerView){

        if(HomeScreenActivity.allRestaurants!=null && restaurantsList.isEmpty()){
            restaurantsList.addAll(HomeScreenActivity.allRestaurants);
        }
        recyclerView.setAdapter(new RecyclerViewAdapter(restaurantsList, getContext()));
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
