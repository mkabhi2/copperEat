package com.copper.coppereat.adapterClasses;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.copper.coppereat.DishActivity;
import com.copper.coppereat.DishFragment;
import com.copper.coppereat.customObjects.Dish;

/**
 * Created by vijaysingh on 11/7/2017.
 */

public class DishTabLayoutPagerAdapter extends FragmentPagerAdapter {

    DishActivity dishActivity=new DishActivity();

    public DishTabLayoutPagerAdapter(FragmentManager fm){//}, int NumOfTabs){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        DishFragment dishFragment=DishFragment.newInstance(position);

        return dishFragment;
    }

    @Override
    public int getCount() {
        return dishActivity.getDishCategoryList().size();
    }


    public CharSequence getPageTitle(int position) {

        if(position<getCount()){
            return dishActivity.getDishCategoryList().get(position);
        }else {
            return null;
        }
    }
}
