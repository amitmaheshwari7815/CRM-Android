package com.woxthebox.draglistview.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by amit on 4/4/18.
 */

public class ExternalStakeholderAdapter extends RecyclerView.Adapter<ExternalStakeholderAdapter.MyHolder> {
 public  static String cName,cDesignation;
    Context context;

//  String uName[] = {"Samuel D. Pollock ", "Samuel D. Pollock", "Samuel D. Pollock "};
//  String uDesg[] = {"Information systems manager","Information systems manager","Information systems manager"};
//    int uImage[] = {R.drawable.img_avatar_card,R.drawable.img_avatar_card,R.drawable.img_avatar_card};



    public ExternalStakeholderAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public ExternalStakeholderAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.layout_externalstakeholder_adapter, parent, false);

        ExternalStakeholderAdapter.MyHolder myHolder = new ExternalStakeholderAdapter.MyHolder(v);

        return myHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ExternalStakeholderAdapter.MyHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;

            String name = ActiveDealsActivity.namec;
            String designation = ActiveDealsActivity.designation;


            myHolder.holderName.setText(name);
            myHolder.holderDesgnation.setText(designation);
//            holder.imageView.setImageResource(uImage[position]);
        }
    }
    @Override
    public int getItemCount() {
        return ActiveDealsActivity.deal.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        TextView holderName,holderDesgnation;
        ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);

            holderName = itemView.findViewById(R.id.holder_name);
            holderDesgnation = itemView.findViewById(R.id.holder_designation);
            imageView = itemView.findViewById(R.id.stakeholder_image);

        }
    }
}
