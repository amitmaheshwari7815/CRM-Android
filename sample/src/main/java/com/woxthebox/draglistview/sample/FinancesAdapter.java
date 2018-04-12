package com.woxthebox.draglistview.sample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by amit on 4/4/18.
 */

public class FinancesAdapter extends RecyclerView.Adapter<FinancesAdapter.MyHolder> {
    public static String fid, fitem, fvalue, fupdated, fcreated, fstatus;

    Context context;
//    String dealId[] = {"1", "1", "1"};
//    String dealitem[] = {"1", "2", "3"};
//    String values[] = {"2000", "5000", "3000"};
//    String create[] = {"200 Days", "150 Days", "100 Days"};
//    String update[] = {"22 Days", "15 Days", "10 Days"};
//    String status[] = {"Received", "Approval", "Billed"};


    public FinancesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FinancesAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.layout_finances_adapter, parent, false);
        FinancesAdapter.MyHolder myHolder = new FinancesAdapter.MyHolder(v);

        return myHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final FinancesAdapter.MyHolder holder, int position) {
        if (holder instanceof MyHolder) {
            MyHolder myHolder = (MyHolder) holder;
            HashMap hm = (HashMap) FinancesFragment.finance.get(position);

            fid = (String) hm.get("pk");
            fcreated = (String)hm.get("created");
            String dtc = fcreated;
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.ENGLISH);
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM",Locale.ENGLISH);
            Date date = null;
            try{
                date = sdf1.parse(dtc);
                String newDate = sdf2.format(date);
                System.out.println(newDate);
                Log.e("Date",newDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar thatDay = Calendar.getInstance();
            thatDay.setTime(date);
            long today = System.currentTimeMillis();

            long diff = today - thatDay.getTimeInMillis();
            long days = diff/(24*60*60*1000);
            fupdated = (String)hm.get("updated");
            fvalue = (String)hm.get("value");
            fstatus = (String)hm.get("status");

            String dt = fupdated;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.ENGLISH);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM",Locale.ENGLISH);
            Date date1 = null;
            try{
                date1 = simpleDateFormat.parse(dt);
                String newDate1 = sdf.format(date);
                System.out.println(newDate1);
                Log.e("Date",newDate1);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar thatDay1 = Calendar.getInstance();
            thatDay.setTime(date1);
            long today1 = System.currentTimeMillis();

            long diff1 = today1 - thatDay1.getTimeInMillis();
            long days1 = diff1/(24*60*60*1000);




            myHolder.idDeal.setText(fid);
//            holder.items.setText(dealitem[position]);
            myHolder.value.setText(fvalue);
            myHolder.created.setText(days+ " Days");
            myHolder.update.setText(days1+ " Days");
            myHolder.status.setText(fstatus);

            holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.inflate(R.menu.card_manu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.quoted:
                                holder.status.setText("Quoted");
                                break;
                            case R.id.approved:
                                holder.status.setText("Approved");
                                break;
                            case R.id.billed:
                                holder.status.setText("Billed");
                                break;
                            case R.id.received:
                                holder.status.setText("Received");
                                break;
                            case R.id.cancelled:
                                holder.status.setText("Cancelled");
                                break;
                            case R.id.due_Elapsed:
                                holder.status.setText("Due Elapsed");
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

}



    @Override
    public int getItemCount() {
        return FinancesFragment.finance.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {

        TextView idDeal,items,value,created,update,status;
        ImageButton menu,download,notification;
        public MyHolder(View itemView) {
            super(itemView);

            idDeal = itemView.findViewById(R.id.id_deals);
            items = itemView.findViewById(R.id.items_id);
            value = itemView.findViewById(R.id.value_id);
            created = itemView.findViewById(R.id.created);
            update = itemView.findViewById(R.id.updated);
            status =itemView.findViewById(R.id.status);
            menu =itemView.findViewById(R.id.popup_menu);
            download =itemView.findViewById(R.id.download);
            notification =itemView.findViewById(R.id.notification);



        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Download", Toast.LENGTH_SHORT).show();

            }
        });
            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Notification", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
