package com.rk.bottled;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    // provide a reference to the view for each data item
    // complex data item may need more than one view per item
    // you provide access to all the iews for a data item in a view holder
    private ArrayList<ConversationFragment> fragments;
    public VerticalViewPager viewPager;

    public static class CustomViewHolder extends RecyclerView.ViewHolder {


        // provide a reference to the view for each data item
        // complex data item may need more than one view per item
        // you provide access to all the iews for a data item in a view holder

        // specify data items
        public Button button;

        public CustomViewHolder(Button v) {
            super(v);
            button = v;
        }
    }

    // Specify appropriate constructor for dataset
    public RecyclerViewAdapter(ArrayList<ConversationFragment> data) {
        fragments = data;
    }

    // Create new views (invoked by the layout manager)
    public RecyclerViewAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        Button v = (Button) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_conversation, parent, false);

        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        // - get element from your dataset at this position
        // -replace the contents of the view with that element

        View button = (Button) holder.button;
        button.setBackgroundColor(randomColor());

        // set button listeners
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(4);
            }
        });

        // set button text
        ((Button) button).setText(fragments.get(position).getPreview());
    //    holder.textView.setText(mDataset[position]);
    }

    // get size of dataset
    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public int randomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    // set view pager for conversations buttons to change to chat fragment
    public void setViewPager(VerticalViewPager vp) {
        viewPager = vp;
    };
}
