package com.copper.coppereat;

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

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DishActivity extends AppCompatActivity {

    String restrauntID="101";
    private List<Dish> dihsCategoryList=null;
    private List<Dish> dishDetailsList=null;
    private static TabLayout dishCategoryTabLayout;
    SwitchCompat vegDishFilter;
    boolean isVegItemsSelected=false;
    TextView b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);
        dishCategoryTabLayout = (TabLayout) findViewById(R.id.dishActivityTabLayout);
        vegDishFilter=(SwitchCompat)findViewById(R.id.dishActivityswitchButton);
        vegDishFilter.setChecked(false);
        b=(TextView) findViewById(R.id.tex);
        final ViewPager dishFragmentViewPager = (ViewPager) findViewById(R.id.dishActivityViewPager);

        getDishCategoryForSelectedRestraunt(restrauntID);
        getDishDetailsForSelectedRestraunt(restrauntID);
        addDishCategoryAsTabsToTabLayout(dishCategoryTabLayout);
        DishTabLayoutPagerAdapter dishTabLayoutPagerAdapter=new DishTabLayoutPagerAdapter(getSupportFragmentManager());
        dishFragmentViewPager.setAdapter(dishTabLayoutPagerAdapter);


        dishFragmentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dishCategoryTabLayout));
        dishCategoryTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dishFragmentViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        vegDishFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    b.setText("checked");
                }else {
                    b.setText("not checed");
                }
            }
        });
    }

    public void addDishCategoryAsTabsToTabLayout(TabLayout tabLayout){
        if(dihsCategoryList!=null && tabLayout!=null){
            for (Dish dish:dihsCategoryList) {
                tabLayout.addTab(tabLayout.newTab().setText(dish.getDishType()));
            }
        }
    }

    public void getDishCategoryForSelectedRestraunt(String restrauntID){




//            DishClient dishClient = DishServiceGeneratorRetrofit.createService(DishClient.class);
//            Dish dish = new Dish(restrauntID);
//            Call<List<Dish>> call = dishClient.getDishCategory(dish);
//            try{
//            call.enqueue(new Callback<List<Dish>>() {
//                @Override
//                public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
//                    setDishCategoryList(response.body());
//                    addDishCategoryAsTabsToTabLayout(dishCategoryTabLayout);
//                }
//
//                @Override
//                public void onFailure(Call<List<Dish>> call, Throwable t) {
//
//                }
//            });
//        }catch (Exception e){
//
//        }
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

    public List<Dish> getDishCategoryList(){
        return dishDetailsList;
    }

    public void setDishCategoryList(List<Dish> dihsCategoryList){
        this.dihsCategoryList=dihsCategoryList;
    }

    public void setDishDetailsList(List<Dish> dishDetailsList){
        this.dishDetailsList=dishDetailsList;
    }

    public List<Dish> getDishDetailsList(){
        return dishDetailsList;
    }

    public static TabLayout getDishActivityTabLayout(){
        return dishCategoryTabLayout;
    }

}
