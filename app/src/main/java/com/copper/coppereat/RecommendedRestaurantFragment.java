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
import com.copper.coppereat.customObjects.Restaurant;
import com.copper.coppereat.utilityClasses.RVItemClickListener;
import com.copper.coppereat.utilityClasses.RVItemDecoration;

import java.util.ArrayList;

public class RecommendedRestaurantFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RecyclerView orderAgainRV, expressRV, topRatedRV, popularRV, bestOfferRV;
    ArrayList<Object> orderAgainRestaurantsList = new ArrayList<Object>();
    ArrayList<Object> expressRestaurantsList = new ArrayList<Object>();
    ArrayList<Object> topRatedRestaurantsList = new ArrayList<Object>();
    ArrayList<Object> popularRestaurantsList = new ArrayList<Object>();
    ArrayList<Object> bestOfferRestaurantsList = new ArrayList<Object>();
    Context context;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecommendedRestaurantFragment() {
        // Required empty public constructor
    }

    public static RecommendedRestaurantFragment newInstance(String param1, String param2) {
        RecommendedRestaurantFragment fragment = new RecommendedRestaurantFragment();
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

        View view = inflater.inflate(R.layout.fragment_recommended_restaurant, container, false);
        context = view.getContext();

        orderAgainRV = (RecyclerView) view.findViewById(R.id.order_again_recycler_view);
        expressRV = (RecyclerView) view.findViewById(R.id.express_recycler_view);
        topRatedRV = (RecyclerView) view.findViewById(R.id.top_rated_recycler_view);
        popularRV = (RecyclerView) view.findViewById(R.id.popular_recycler_view);
        bestOfferRV = (RecyclerView) view.findViewById(R.id.best_offer_recycler_view);

        setUpRVList(HomeScreenActivity.alreadyOrderedFromRestaurants, orderAgainRestaurantsList);
        setUpRVList(HomeScreenActivity.expressRestaurants, expressRestaurantsList);
        setUpRVList(HomeScreenActivity.highlyRatedRestaurants, topRatedRestaurantsList);
        setUpRVList(HomeScreenActivity.popularRestaurants, popularRestaurantsList);
        setUpRVList(HomeScreenActivity.inOfferRestaurants, bestOfferRestaurantsList);

        setUpRV(orderAgainRV, orderAgainRestaurantsList);
        setUpRV(expressRV, expressRestaurantsList);
        setUpRV(topRatedRV, topRatedRestaurantsList);
        setUpRV(popularRV, popularRestaurantsList);
        setUpRV(bestOfferRV, bestOfferRestaurantsList);

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

    private void setUpRVList(ArrayList<Restaurant> sourceList, ArrayList<Object> destinationList) {
        if(sourceList!=null && destinationList.isEmpty()){
            int endIndex = sourceList.size()>10 ? 10 : sourceList.size();
            destinationList.addAll(sourceList.subList(0,endIndex));
        }
    }

    private void setUpRV(RecyclerView recyclerView, ArrayList<Object> list){

        recyclerView.setAdapter(new RecyclerViewAdapter(list, getContext()));
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
