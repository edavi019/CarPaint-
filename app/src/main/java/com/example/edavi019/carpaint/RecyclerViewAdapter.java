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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mColorImage = new ArrayList<>();
    private ArrayList<String> mColorName = new ArrayList<>();
    private ArrayList<String> mColorCode = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mColorImage, ArrayList<String> mColorName, ArrayList<String> mColorCode) {
        this.mColorImage = mColorImage;
        this.mColorName = mColorName;
        this.mColorCode = mColorCode;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resultsrowlayout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.ColorName.setText(mColorName.get(position));
        holder.ColorCode.setText(mColorCode.get(position));
        holder.ColorImage.setBackgroundColor(Color.parseColor(mColorImage.get(position)));

        holder.ParentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "onClick: clicked on: " + mColorName.get(position));

                Toast.makeText(mContext, mColorName.get(position), Toast.LENGTH_SHORT).show();
             }
        } );
           
    }

    @Override
    public int getItemCount() {
        return mColorName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView ColorImage;
        TextView ColorName;
        TextView ColorCode;
        RelativeLayout ParentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ColorImage = itemView.findViewById(R.id.ColorImage);
            ColorName = itemView.findViewById(R.id.ColorName);
            ColorCode = itemView.findViewById(R.id.ColorCode);
            ParentLayout = itemView.findViewById(R.id.Parentlayout);
        }
    }
}
