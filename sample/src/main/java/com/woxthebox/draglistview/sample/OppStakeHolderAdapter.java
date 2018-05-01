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
 * Created by amit on 30/4/18.
 */


public class OppStakeHolderAdapter extends RecyclerView.Adapter<OppStakeHolderAdapter.MyHolder> {
        public  static String cName,cDesignation;
        private Deal d;

        Context context;

    public OppStakeHolderAdapter(Context context){
        this.context = context;


    }

    String uName[] = {"Samuel D. Pollock ", "Joyce A.Neal", "Wanda J. Aguirre"};
  String uDesg[] = {"Information systems manager","Information systems manager","Information systems manager"};
    int uImage[] = {R.drawable.img_avatar_card,R.drawable.img_avatar_card,R.drawable.img_avatar_card};


    @NonNull
    @Override
    public OppStakeHolderAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = layoutInflater.inflate(R.layout.opp_stakeholder_adapter, parent, false);

        OppStakeHolderAdapter.MyHolder myHolder = new OppStakeHolderAdapter.MyHolder(v);

        return myHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull OppStakeHolderAdapter.MyHolder holder, int position) {
            holder.holderName.setText(uName[position]);
            holder.holderDesgnation.setText(uDesg[position]);
            holder.imageView.setImageResource(uImage[position]);

        }

    @Override
    public int getItemCount() {
        return uName.length;
    }
        public class MyHolder extends RecyclerView.ViewHolder {

            TextView holderName,holderDesgnation;
            ImageView imageView;
            public MyHolder(View itemView) {
                super(itemView);

                holderName = itemView.findViewById(R.id.opp_holder_name);
                holderDesgnation = itemView.findViewById(R.id.opp_holder_designation);
                imageView = itemView.findViewById(R.id.opp_stakeholder_image);

            }
        }


    }
