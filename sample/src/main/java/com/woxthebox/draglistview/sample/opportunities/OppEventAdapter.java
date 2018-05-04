package com.woxthebox.draglistview.sample.opportunities;

/**
 * Created by amit on 1/5/18.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.woxthebox.draglistview.sample.R;

public class OppEventAdapter extends RecyclerView.Adapter<OppEventAdapter.MyHolder> {

    public static String  dealName,contactName,value,id,closingDate;
    public static int holderImage[] = {R.drawable.img_avatar_card,R.drawable.img_avatar_card};
    public static int holderimage1[] = {R.drawable.img_avatar_card,R.drawable.img_avatar_card};
    String SName[] = {"Samuel D. Pollock ","Samuel D. Pollock"};
    String SName1[] = {"Joyce A.Neal", "Joyce A.Neal"};
    String venueName [] = {"Some Place","CDF Building"};
    String eventName [] = {"A brand new event","Another event"};


    Context context;

    public OppEventAdapter(Context context ) {
        this.context = context;

    }

    @NonNull
    @Override
    public OppEventAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.opp_event_adapter, parent, false);
        OppEventAdapter.MyHolder myHolder = new OppEventAdapter.MyHolder(v);
        return myHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull OppEventAdapter.MyHolder holder, int position) {

            holder.EventName.setText(eventName[position]);
            holder.stakeholderimage.setImageResource(holderImage[position]);
            holder.holderimage.setImageResource(holderimage1[position]);
            holder.holderName.setText(SName[position]);
            holder.holderName1.setText(SName1[position]);
            holder.venueName.setText(venueName[position]);

    }

    @Override
    public int getItemCount() {
        return SName.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView stakeholderimage,holderimage;
        TextView holderName, holderName1, venueName,EventName;

        public MyHolder(View itemView) {
            super(itemView);
            stakeholderimage = itemView.findViewById(R.id.opp_stakeholder_image);
            holderimage = itemView.findViewById(R.id.opp_holder_image);
            holderName = itemView.findViewById(R.id.opp_holder_name);
            holderName1 = itemView.findViewById(R.id.opp_event_holder);
            venueName = itemView.findViewById(R.id.opp_event_venu);
            EventName = itemView.findViewById(R.id.opp_event_name);




        }
    }
}