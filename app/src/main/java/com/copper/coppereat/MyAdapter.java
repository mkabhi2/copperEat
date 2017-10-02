package com.copper.coppereat;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vijaysingh on 10/2/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> values;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtFooter;
        public ImageView iconImage;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            iconImage=(ImageView) v.findViewById(R.id.icon);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }

    public void add(int position, String item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }


    public MyAdapter(List<String> myDataset) {
        values = myDataset;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final String name = values.get(position);
        int[] drawableIds = {R.drawable.ic_person_black_24dp,R.drawable.ic_sentiment_dissatisfied_black_24dp
                ,R.drawable.ic_sentiment_very_dissatisfied_black_24dp,R.drawable.ic_whatshot_black_24dp
        };
        String titlesForAccountPage[]={"Personal Information","Address Book","Payment Options", "Refer & Earn"};
        holder.iconImage.setImageResource(drawableIds[position]);
        holder.txtFooter.setText(titlesForAccountPage[position]);
        holder.txtFooter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}