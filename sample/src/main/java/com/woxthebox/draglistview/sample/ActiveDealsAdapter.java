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
 * Created by amit on 10/3/18.
 */


public class ActiveDealsAdapter extends RecyclerView.Adapter<ActiveDealsAdapter.MyHolder> {

    public static int owner_photo[] = {R.drawable.ic_owner, R.drawable.ic_owner};
    public static int contact_photo[] = {R.drawable.ic_owner, R.drawable.ic_owner};
    public static String deal_name[] = {"First", "Second"};
    public static String deal_id_number [] = {"1","2"};
    public static String money_value [] = {"5000","2000"};
    public static String owner_name[] = {"Admin CIOC", "Admin CIOC"};
    public static String contact_name[] = {"Samuel D. Pollock ", "Daniel Strack"};
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
        holder.ownerimage.setImageResource(owner_photo[position]);
        holder.contactphoto.setImageResource(contact_photo[position]);
        holder.ownername.setText(owner_name[position]);
        holder.contactname.setText(contact_name[position]);
        holder.dealname.setText(deal_name[position]);
        holder.dealidnumber.setText(deal_id_number[position]);
        holder.moneyvalue.setText(money_value[position]);

    }


    @Override
    public int getItemCount() {
        return deal_name.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView ownerimage, contactphoto;
        TextView ownername, contactname, dealname, dealid,dealidnumber,moneyvalue;

        public MyHolder(View itemView) {
            super(itemView);
            ownerimage = itemView.findViewById(R.id.owner_photo);
            contactphoto = itemView.findViewById(R.id.contact_photo);
            ownername = itemView.findViewById(R.id.owner_name);
            contactname = itemView.findViewById(R.id.contact_name);
            dealname = itemView.findViewById(R.id.name_first);
            dealidnumber = itemView.findViewById(R.id.id_number);
            moneyvalue = itemView.findViewById(R.id.money_value);
        }
    }
}

