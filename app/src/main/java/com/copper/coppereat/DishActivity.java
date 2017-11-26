package com.copper.coppereat;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.copper.coppereat.adapterClasses.DishTabLayoutPagerAdapter;
import com.copper.coppereat.customObjects.Carts;
import com.copper.coppereat.customObjects.Dish;
import com.copper.coppereat.retrofitClient.DishClient;
import com.copper.coppereat.retrofitServiceGenerator.DishServiceGeneratorRetrofit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishActivity extends AppCompatActivity {

    public static int flag=-1;
    String restrauntID="101";
    private static ArrayList<Dish> vegDishDetailsList=new ArrayList<Dish>();
    private static ArrayList<String> dihsCategoryList=new ArrayList<String>();
    private static List<Dish> dishDetailsList=null;
    SwitchCompat vegDishFilter;
    static boolean isVegItemsSelected=false;
    TextView b;
    ViewPager dishFragmentViewPager;
    static TabLayout tabLayout;


    private TextView dishActivityOfferTextView;
    private static Button checkoutButton;
    private static TextView cartSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        checkoutButton=(Button)findViewById(R.id.dishActivityCheckoutButton);
        cartSummary=(TextView)findViewById(R.id.dishActivityCartSummaryTextView);

        tabLayout = (TabLayout) findViewById(R.id.dishActivityTabLayout);
        vegDishFilter=(SwitchCompat)findViewById(R.id.dishActivityswitchButton);
        dishActivityOfferTextView=(TextView)findViewById(R.id.dishActivityOfferTextView);

        checkoutButton.setVisibility(View.INVISIBLE);
        cartSummary.setVisibility(View.INVISIBLE);

        vegDishFilter.setChecked(false);
        getDishDetailsForSelectedRestraunt(restrauntID);

        vegDishFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                               if(isChecked){
                                   isVegItemsSelected=true;
                                   setUpTabs();
                                    }else {
                                   isVegItemsSelected=false;
                                   setUpTabs();
                                    }
                           }

        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DishActivity.this,CheckoutActivity.class);
                startActivity(intent);
            }
        });

        setOfferToDishActivityOfferTextView();

    }

    public void setOfferToDishActivityOfferTextView(){
        String offer="10 % Off. Use Code BC10 for 10% Discount. Max Discount is Rs 40";
        SpannableString spannableString=  new SpannableString(offer);
        spannableString.setSpan(new RelativeSizeSpan(1f), 0,offer.indexOf(". "), 0); // set size
        spannableString.setSpan(new ForegroundColorSpan(Color.GREEN), 0, offer.indexOf(". "), 0);// set color
        dishActivityOfferTextView.setText(spannableString);
        dishActivityOfferTextView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL);

    }

    public void createNewDishListForOnlyVegItems(List<Dish> dishDetailsList){

        for (Dish d:dishDetailsList) {
        if(d.getDishCategory().equals("veg")){
           vegDishDetailsList.add(d);
        }
        }

    }
    public ArrayList<Dish> getVegDishDetailsList(){
        return vegDishDetailsList;
    }

    public void setUpTabs(){

        dishFragmentViewPager = (ViewPager) findViewById(R.id.dishActivityViewPager);
        DishTabLayoutPagerAdapter dishTabLayoutPagerAdapter = new DishTabLayoutPagerAdapter(getSupportFragmentManager());
        dishFragmentViewPager.setAdapter(dishTabLayoutPagerAdapter);
        tabLayout.setupWithViewPager(dishFragmentViewPager);
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

    public ArrayList<String > getDishCategoryList(){
        return dihsCategoryList;
    }

    public void setDishCategoryList(List<Dish> dishDetailsList){
        for (Dish d:dishDetailsList) {
            if(!dihsCategoryList.contains(d.getDishType()))
            dihsCategoryList.add(d.getDishType());
        }
    }

    public void setDishDetailsList(List<Dish> dishDetailsList){
        this.dishDetailsList=dishDetailsList;
        setDishCategoryList(dishDetailsList);
        createNewDishListForOnlyVegItems(dishDetailsList);
        addDishCategoryAsTabsToTabLayout(tabLayout);
        setUpTabs();

    }

    public List<Dish> getDishDetailsList(){
        return dishDetailsList;
    }

    public static TabLayout getDishActivityTabLayout(){
        return tabLayout;
    }

    public static void showCartDetailsSummary(){


        HashMap<Dish,Integer> cart= Carts.getCartList();
        int totalQuantity=0;
        double price=0;
        double totalCost=0;
        if(cart.size()==0){
         cartSummary.setVisibility(View.INVISIBLE);
         checkoutButton.setVisibility(View.INVISIBLE);
        }else {
            for (Dish d : cart.keySet()) {
                price = Double.parseDouble(d.getDishPrice());
                Integer value = cart.get(d);
                totalCost = totalCost + (price * value);
                totalQuantity += value;
            }

            String message = totalQuantity + "items in your cart" + "\n" + "Rs " + totalCost;
            cartSummary.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cart_icon, 0, 0, 0);
            cartSummary.setText(message);
            cartSummary.setGravity(Gravity.CENTER_VERTICAL);

            cartSummary.setVisibility(View.VISIBLE);
            checkoutButton.setVisibility(View.VISIBLE);


        }
    }

}
