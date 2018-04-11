package com.woxthebox.draglistview.sample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by amit on 10/3/18.
 */


public class ActiveDealsAdapter extends RecyclerView.Adapter<ActiveDealsAdapter.MyHolder> {

    public static String  dealName,contactName,value,id,closingDate;

    Context context;

    public ActiveDealsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ActiveDealsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.active_deals_style, parent, false);
        ActiveDealsAdapter.MyHolder myHolder = new ActiveDealsAdapter.MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveDealsAdapter.MyHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            HashMap hm = (HashMap) ActiveDealsActivity.deal.get(position);
            id = (String)hm.get("pk");
            dealName = (String)hm.get("name");
            contactName =(String)hm.get("name_con");
            value = (String)hm.get("value");
            closingDate = (String)hm.get("closeDate");

            myHolder.contactname.setText(contactName);
            myHolder.dealid.setText(id);
            myHolder.dealname.setText(dealName);
            myHolder.moneyvalue.setText(value);

        }
    }

    @Override
    public int getItemCount() {
        return ActiveDealsActivity.deal.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView ownerimage, contactphoto;
        TextView ownername, contactname, dealname, dealid,moneyvalue;

        public MyHolder(View itemView) {
            super(itemView);
            ownerimage = itemView.findViewById(R.id.owner_photo);
            dealid = itemView.findViewById(R.id.id_number);
            contactphoto = itemView.findViewById(R.id.contact_photo);
            ownername = itemView.findViewById(R.id.owner_name);
            contactname = itemView.findViewById(R.id.contact_name);
            dealname = itemView.findViewById(R.id.name_first);
            moneyvalue = itemView.findViewById(R.id.money_value);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ActiveDealsDetailsActivity.class);
                    intent.putExtra("name", dealName);
                    intent.putExtra("value", value);
                    intent.putExtra("closeDate",closingDate);
                }
            });
        }
    }
}

