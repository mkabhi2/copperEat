package com.copper.coppereat.utilityClasses;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by abhmishr on 11/4/17.
 */

public class RestaurantListItemDecoration extends RecyclerView.ItemDecoration {
    //TODO ADD PROPER STYLING OR DECORATION HERE
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = 4;
    }
}
