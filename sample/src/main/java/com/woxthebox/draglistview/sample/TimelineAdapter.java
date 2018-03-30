package com.woxthebox.draglistview.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cioc on 29/3/18.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.MyHolder>{

    Context context;

    String note[] = {"Using CardView you can represent the information in a card manner with a drop shadow (elevation) and corner radius which", ""
            ,"Using CardView you can represent the information in a card manner with a drop shadow (elevation) and corner radius which",""
            ,"You can achieve good looking UI when CardView is combined with RecyclerView. In this article we are going " +
            "to learn how to integrate CardView with RecyclerView by creating a beautiful music app that displays music albums with a cover image and title."};
    int img[] = {R.drawable.map_marker,R.drawable.map_marker,R.drawable.map_marker,};



    public TimelineAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public TimelineAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.layout_timeline_adapter, parent, false);

        TimelineAdapter.MyHolder myHolder = new TimelineAdapter.MyHolder(v);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineAdapter.MyHolder holder, int position) {
        holder.textView.setText(note[position]);
        holder.imageView.setImageResource(img[position]);

    }

    @Override
    public int getItemCount() {
        return note.length;
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.timeline_textView);
            imageView = itemView.findViewById(R.id.timeline_imageView);

        }
    }
}
