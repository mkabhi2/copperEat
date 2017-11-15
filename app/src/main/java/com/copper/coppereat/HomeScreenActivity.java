package com.copper.coppereat;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.copper.coppereat.adapterClasses.RecyclerViewAdapter;
import com.copper.coppereat.adapterClasses.TabAdapterHomeScreen;
import com.copper.coppereat.customObjects.Dish;
import com.copper.coppereat.customObjects.Restaurant;
import com.copper.coppereat.utilityClasses.ArrayListComparator;
import com.copper.coppereat.utilityClasses.RetroFitNetworkClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeScreenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , RestaurantListFragment.OnListFragmentInteractionListener, RecommendedRestaurantFragment.OnFragmentInteractionListener, FavouritesFragment.OnFragmentInteractionListener{

    static ArrayList<Restaurant> allRestaurants;
    //TODO IMPLEMENT THESE ALL THROUGH RETROFIT
    static ArrayList<Restaurant> expressRestaurants = new ArrayList<Restaurant>();
    static ArrayList<Restaurant> highlyRatedRestaurants = new ArrayList<Restaurant>();
    static ArrayList<Restaurant> popularRestaurants = new ArrayList<Restaurant>();
    static ArrayList<Restaurant> inOfferRestaurants = new ArrayList<Restaurant>();
    static ArrayList<Restaurant> favouritesRestaurants = new ArrayList<Restaurant>();
    static ArrayList<Restaurant> alreadyOrderedFromRestaurants = new ArrayList<Restaurant>();
    //TODO IMPLEMENT WHEN SURE TO USE
    static ArrayList<Dish> highlyRatedDishes = new ArrayList<Dish>();
    static ArrayList<Dish> popularDishes = new ArrayList<Dish>();
    static ArrayList<Dish> favouriteDishes = new ArrayList<Dish>();

    static ArrayList<Object> favouritesList = new ArrayList<Object>();
    
    ViewPager viewPager;
    TabAdapterHomeScreen tabAdapterHomeScreen;
    RecyclerView recyclerViewOfRestaurantListFragment;

    //Variables Declared for Dialogs
    String sortValue;
    int expressDelivery;
    ArrayList<String> priceValues=new ArrayList<String>();
    ArrayList<String> filterValues=new ArrayList<String>();
    Button sortButton1, sortButton2, expressDeliveryButton, priceValueButton1, priceValueButton2, priceValueButton3, priceValueButton4;
    Button dishTypeButton1, submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Setting up toolbar as action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get restaurants from the network
        getAllRestaurantsListFromNetwork();

        //set up view
        setUpTabs();
        setUpFloatingButton();
        setUpNavigationDrawer(toolbar);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_sceen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void getAllRestaurantsListFromNetwork(){

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://18.220.28.118:8080")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        RetroFitNetworkClient retroFitNetworkClient = retrofit.create(RetroFitNetworkClient.class);
        Call<List<Restaurant>> call = retroFitNetworkClient.getRestrauntListForLocation("560037");

        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                allRestaurants = (ArrayList<Restaurant>) response.body();

                RecyclerView recyclerViewAllRestaurantListFragment = tabAdapterHomeScreen.restaurantListFragment.recyclerView;
                RecyclerView bestOfferRV = tabAdapterHomeScreen.recommendedFragment.bestOfferRV;
                RecyclerView expressRV = tabAdapterHomeScreen.recommendedFragment.expressRV;
                RecyclerView orderAgainRV = tabAdapterHomeScreen.recommendedFragment.orderAgainRV;
                RecyclerView popularRV = tabAdapterHomeScreen.recommendedFragment.popularRV;
                RecyclerView topRatedRV = tabAdapterHomeScreen.recommendedFragment.topRatedRV;

                updateAllRestaurantListAdapter(recyclerViewAllRestaurantListFragment, new ArrayList<Object>(allRestaurants));

                popularRestaurants.addAll(allRestaurants);
                highlyRatedRestaurants.addAll(allRestaurants);

                Collections.sort(popularRestaurants, new ArrayListComparator("Popularity"));
                List<Restaurant> tempPopularRestaurants = popularRestaurants.subList(0,(popularRestaurants.size()>10 ? 10 : popularRestaurants.size()));
                updateAllRestaurantListAdapter(popularRV, new ArrayList<Object>(tempPopularRestaurants));
                tempPopularRestaurants.clear();

                Collections.sort(highlyRatedRestaurants, new ArrayListComparator("Ratings"));
                List<Restaurant> tempHighlyRatedRestaurants = highlyRatedRestaurants.subList(0,(highlyRatedRestaurants.size()>10 ? 10 : highlyRatedRestaurants.size()));
                updateAllRestaurantListAdapter(topRatedRV, new ArrayList<Object>(tempHighlyRatedRestaurants));
                tempHighlyRatedRestaurants.clear();

                for(Restaurant restaurant : allRestaurants){
                    //TODO IMPLEMENT TO POPULATE OTHER LISTS this one is just a template
                    if(restaurant.getOffer()!=null && !restaurant.getOffer().equals("0")) {
                        inOfferRestaurants.add(restaurant);
                    }
                }

                Collections.sort(inOfferRestaurants, new ArrayListComparator("all"));
                List<Restaurant> tempInOfferRestaurants = inOfferRestaurants.subList(0,(inOfferRestaurants.size()>10 ? 10 : inOfferRestaurants.size()));
                updateAllRestaurantListAdapter(bestOfferRV, new ArrayList<Object>(tempInOfferRestaurants));
                tempInOfferRestaurants.clear();
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(HomeScreenActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateAllRestaurantListAdapter(RecyclerView recyclerView, ArrayList<Object> newRestaurantsList){
        recyclerView.setAdapter(new RecyclerViewAdapter(newRestaurantsList, HomeScreenActivity.this));
        recyclerView.invalidate();
    }

    private void setUpNavigationDrawer(Toolbar toolbar){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setUpFloatingButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO IMPLEMENT ALL DIALOGS
                if(viewPager.getCurrentItem()==0){
                    createRestaurantListFilterDialog();
                    updateRestrauntListBasedOnFilters();
                }
                if(viewPager.getCurrentItem()==1){}
                if(viewPager.getCurrentItem()==2){}
            }
        });
    }

    public void setUpTabs(){

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabAdapterHomeScreen = new TabAdapterHomeScreen(this, getSupportFragmentManager());
        viewPager.setAdapter(tabAdapterHomeScreen);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onListFragmentInteraction(Restaurant item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    //TODO IMPLEMENT ALL DIALOGS

    public void createRestaurantListFilterDialog(){

        final Dialog restaurantListFilterDialog = new Dialog(this);
        restaurantListFilterDialog.setContentView(R.layout.dialog_restaurant_list_filter);
        restaurantListFilterDialog.show();
        sortButton1 = restaurantListFilterDialog.findViewById(R.id.button);
        sortButton2 = restaurantListFilterDialog.findViewById(R.id.button6);
        submitButton= restaurantListFilterDialog.findViewById(R.id.button2);
        expressDeliveryButton = restaurantListFilterDialog.findViewById(R.id.button3);
        priceValueButton1 = restaurantListFilterDialog.findViewById(R.id.button4);
        dishTypeButton1 = restaurantListFilterDialog.findViewById(R.id.button5);

        sortButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sortValue!=null || !sortValue.equals("rating")){
                    sortButton1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    sortValue="Rating";
                    sortButton2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                else{
                    sortButton1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }

            }
        });
        sortButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sortValue!=null || !sortValue.equals("rating")){
                    sortButton2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    sortValue="Rating";
                    sortButton1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                else{
                    sortButton2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });

        expressDeliveryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expressDelivery!=1){
                    expressDelivery=1;
                    expressDeliveryButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                }
                else{
                    expressDelivery=0;
                    expressDeliveryButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }
        });
        priceValueButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(priceValues.contains("1")){
                    filterValues.remove("1");
                    priceValueButton1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                else{
                    priceValues.add("1");
                    priceValueButton1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }
        });
        dishTypeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filterValues.contains("Indian")){
                    filterValues.remove("Indian");
                    dishTypeButton1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                else{
                    filterValues.add("Indian");
                    dishTypeButton1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantListFilterDialog.dismiss();
            }
        });

    }
    public void createRecommendedListFilterDialog(){}
    public void createFavoritesFilterDialog(){}

    public void updateRestrauntListBasedOnFilters(){
        ArrayList<Object> tempRestaurantsList =new ArrayList<Object>(allRestaurants);

        //Updating list based on user filter selection
        if(!filterValues.isEmpty() || !priceValues.isEmpty() || expressDelivery==1){

            for(int itemCount = 0; itemCount< tempRestaurantsList.size(); itemCount++){

                //TODO COMPARE RESTRAUNT PINCODE WITH USER PINCODE INSTEAD OF FOLLOWING CONDITION FOR EXPRESS DELIVERY
                if(expressDelivery==1){
                    tempRestaurantsList.remove(itemCount);
                    continue;
                }

                Restaurant r = (Restaurant) tempRestaurantsList.get(itemCount);

                if(!priceValues.isEmpty() && !priceValues.contains(r.getBudget())){
                    tempRestaurantsList.remove(itemCount);
                    continue;
                }

                for(int filterCount=0;filterCount<filterValues.size();filterCount++){
                    if(!filterValues.isEmpty()
                            && !r.getDishTypes()
                            .contains(filterValues.get(filterCount))){
                        tempRestaurantsList.remove(itemCount);
                        break;
                    }
                }
            }
        }


        //SORT RESTRAUNT LIST BASED ON THE SORT CRITERIA SELECTED BY THE USER
        if(sortValue!=null){
            Collections.sort(tempRestaurantsList, new ArrayListComparator(sortValue));
        }
        recyclerViewOfRestaurantListFragment = tabAdapterHomeScreen.restaurantListFragment.recyclerView;
        recyclerViewOfRestaurantListFragment.setAdapter(new RecyclerViewAdapter(tempRestaurantsList, HomeScreenActivity.this));
        recyclerViewOfRestaurantListFragment.invalidate();
    }


}
