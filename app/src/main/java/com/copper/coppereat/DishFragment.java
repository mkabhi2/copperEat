package com.copper.coppereat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.copper.coppereat.adapterClasses.DishActivityListViewAdapter;
import com.copper.coppereat.adapterClasses.MyAdapter;
import com.copper.coppereat.customObjects.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishFragment extends Fragment {

    int tabNumber;

    //private ListView dishFragmentListView;
    private RecyclerView dishFragmentListView;

    DishActivity dishActivity=new DishActivity();
    //DishActivityListViewAdapter customAdapter;

    MyAdapter customAdapter;
    public DishFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            if(getArguments() != null) {
                tabNumber = getArguments().getInt("NUM");
            }

       //     View view = inflater.inflate(R.layout.fragment_dish, container, false);
       //     dishFragmentListView = (ListView) view.findViewById(R.id.dishFragmentListView);

        View view = inflater.inflate(R.layout.fragment_dishes, container, false);
        dishFragmentListView = (RecyclerView) view.findViewById(R.id.rv_recycler_view);



        //customAdapter = new DishActivityListViewAdapter(getContext(), getContentsForCurrentTab());
          customAdapter=new MyAdapter(getContext(),getContentsForCurrentTab());
            dishFragmentListView.setLayoutManager(new LinearLayoutManager(getContext()));
          dishFragmentListView.setAdapter(customAdapter);

            return view;
    }

    public ArrayList<Dish> getContentsForCurrentTab(){

        return extractDishForCurrentTabFromDishDetails(tabNumber);
    }

    public ArrayList<Dish> extractDishForCurrentTabFromDishDetails(int position){
        String tabName;
        List<Dish> list=null;

        if(position<dishActivity.getDishCategoryList().size()){
            tabName = dishActivity.getDishCategoryList().get(position);
        }else {
            return null;
        }

        if(DishActivity.isVegItemsSelected){
            list=dishActivity.getVegDishDetailsList();
        }else {
            list = dishActivity.getDishDetailsList();
        }
        ArrayList<Dish> arrayList=new ArrayList<>();
        for(Dish dish:list){
            if(dish.getDishType().equals(tabName)){
                arrayList.add(dish);
            }
        }
        return arrayList;
    }

    public static DishFragment newInstance(int tabNum) {

            DishFragment dishFragment = new DishFragment();
            Bundle args = new Bundle();
            args.putInt("NUM",tabNum);
            dishFragment.setArguments(args);
            return dishFragment;

    }

    @Override
    public void onResume() {
        super.onResume();
        customAdapter.notifyDataSetChanged();
    }
}
