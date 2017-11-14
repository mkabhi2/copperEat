package com.copper.coppereat;

import android.app.IntentService;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.copper.coppereat.adapterClasses.DishTabLayoutPagerAdapter;
import com.copper.coppereat.customObjects.Dish;
import com.copper.coppereat.retrofitClient.DishClient;
import com.copper.coppereat.retrofitServiceGenerator.DishServiceGeneratorRetrofit;
import com.copper.coppereat.retrofitServiceGenerator.DishServiceRetrofitGeneratorSynchronus;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DishActivity extends AppCompatActivity {

    public static int flag=-1;
    String restrauntID="101";
    private static TreeSet<String> dihsCategoryList=new TreeSet<String>();
    private static List<Dish> dishDetailsList=null;
    private static TabLayout dishCategoryTabLayout;
    SwitchCompat vegDishFilter;
    boolean isVegItemsSelected=false;
    TextView b;
    ViewPager dishFragmentViewPager;
    static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        dishCategoryTabLayout = (TabLayout) findViewById(R.id.dishActivityTabLayout);
        vegDishFilter=(SwitchCompat)findViewById(R.id.dishActivityswitchButton);
        vegDishFilter.setChecked(false);
        b=(TextView) findViewById(R.id.tex);

        dishFragmentViewPager= (ViewPager) findViewById(R.id.dishActivityViewPager);
        //dishFragmentViewPager.setOffscreenPageLimit(6);
        getDishDetailsForSelectedRestraunt(restrauntID);

//        DishTabLayoutPagerAdapter dishTabLayoutPagerAdapter=new DishTabLayoutPagerAdapter(getSupportFragmentManager());
//        dishFragmentViewPager.setAdapter(dishTabLayoutPagerAdapter);


//        dishFragmentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dishCategoryTabLayout));
//        dishCategoryTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                dishFragmentViewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        vegDishFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    b.setText("checked");
                }else {
                    b.setText("not checked");
                }
            }
        });
    }

    public void addDishCategoryAsTabsToTabLayout(TabLayout tabLayout){
        if(dihsCategoryList!=null && tabLayout!=null){
            for (String string:dihsCategoryList) {
                tabLayout.addTab(tabLayout.newTab().setText(string));
            }
        }
    }

    public void getDishDetailsForSelectedRestraunt(String restrauntID) {
        DishClient dishClient = DishServiceGeneratorRetrofit.createService(DishClient.class);
        Dish dish = new Dish(restrauntID);
        Call<List<Dish>> call = dishClient.getDishDetails(dish);
        try {
            call.enqueue(new Callback<List<Dish>>() {
                @Override
                public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                    setDishDetailsList(response.body());
                }
                @Override
                public void onFailure(Call<List<Dish>> call, Throwable t) {
                    Log.d("", "");
                }
            });
        } catch (Exception e) {
            Log.d("", e.toString());
        }
    }

    public TreeSet<String > getDishCategoryList(){
        return dihsCategoryList;
    }

    public void setDishCategoryList(List<Dish> dishDetailsList){
        for (Dish d:dishDetailsList) {
            dihsCategoryList.add(d.getDishType());
        }

        addDishCategoryAsTabsToTabLayout(dishCategoryTabLayout);
    }

    public void setDishDetailsList(List<Dish> dishDetailsList){
        this.dishDetailsList=dishDetailsList;
        setDishCategoryList(dishDetailsList);
        performRest();
    }

    public void performRest(){

        DishTabLayoutPagerAdapter dishTabLayoutPagerAdapter=new DishTabLayoutPagerAdapter(getSupportFragmentManager());
        dishFragmentViewPager.setAdapter(dishTabLayoutPagerAdapter);
        dishFragmentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dishCategoryTabLayout));
//        dishCategoryTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                position=tab.getPosition();
//                //DishFragment dishFragment=new DishFragment();
//                //dishCategoryTabLayout.getTabAt(tab.getPosition()).select();
//                dishFragmentViewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


    }

    public List<Dish> getDishDetailsList(){
        return dishDetailsList;
    }

    public static TabLayout getDishActivityTabLayout(){
        return dishCategoryTabLayout;
    }



}
