package com.example.edavi019.carpaint;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<ResultLists> values;
    public Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ColorImage;
        public TextView ColorName;
        public TextView ColorCode;
        public View layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            ColorImage = itemView.findViewById(R.id.ColorImage);
            ColorName = itemView.findViewById(R.id.ColorName);
            ColorCode = itemView.findViewById(R.id.ColorCode);
            //ParentLayout = itemView.findViewById(R.id.Parentlayout);
        }
    }

        public RecyclerViewAdapter(List<ResultLists> myDataSet,Context context) {
        values = myDataSet;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.resultsrowlayout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultsrowlayout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;*/
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        final ResultLists resultLists = values.get(position);
        holder.ColorName.setText(resultLists.getColorName());
        holder.ColorCode.setText(resultLists.getColorCode());
        holder.ColorImage.setBackgroundColor(Color.parseColor(resultLists.getHex()));

        }
    @Override
    public int getItemCount() {
        return values.size();
    }

}
