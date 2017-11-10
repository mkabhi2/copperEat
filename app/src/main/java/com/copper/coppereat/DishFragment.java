package com.copper.coppereat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.copper.coppereat.adapterClasses.DishActivityListViewAdapter;
import com.copper.coppereat.customObjects.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishFragment extends Fragment {

    static int currentTabPosition=0;
    private ListView dishFragmentListView;
    DishActivity dishActivity=new DishActivity();
    public DishFragment(){


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_dish,container,false);
        dishFragmentListView=(ListView) view.findViewById(R.id.dishFragmentListView);


        DishActivityListViewAdapter customAdapter=new DishActivityListViewAdapter(getContext(),getContentsForCurrentTab());
        dishFragmentListView.setAdapter(customAdapter);

        return view;
    }

    public ArrayList<Dish> getContentsForCurrentTab(){
        String tabName=DishActivity.getDishActivityTabLayout().getTabAt(currentTabPosition++).getText().toString();
        return extractDishForCurrentTabFromDishDetails(tabName);
    }
    public ArrayList<Dish> extractDishForCurrentTabFromDishDetails(String tabName){
        List<Dish> list=dishActivity.getDishDetailsList();
        ArrayList<Dish> arrayList=new ArrayList<>();
        for(Dish dish:list){
            if(dish.getDishType().equals(tabName)){
                arrayList.add(dish);
            }
        }
        return arrayList;
    }
}
