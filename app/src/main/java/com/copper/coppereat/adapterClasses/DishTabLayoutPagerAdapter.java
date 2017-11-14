package com.copper.coppereat.adapterClasses;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.copper.coppereat.DishActivity;
import com.copper.coppereat.DishFragment;

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
        DishFragment dishFragment;
//        if (position <= getCount()) {
//            dishFragment = new DishFragment();
//            return dishFragment;
//        } else {
//            return null;
//        }
        switch (position) {
            case 0:
                dishFragment=new DishFragment();
            break;

            case 1:
                if(DishActivity.flag==-1){
                    DishActivity.flag=-2;
                    dishFragment=null;
                }else {
                    dishFragment=new DishFragment();
                }
            case 2:
            case 3:
            case 4:
            case 5:
                dishFragment=new DishFragment();
                //return dishFragment;
                break;
            default:
                dishFragment= null;

        }
        return dishFragment;
    }

    @Override
    public int getCount() {
        return 6;//dishActivity.getDishCategoryList().size();
    }
}
