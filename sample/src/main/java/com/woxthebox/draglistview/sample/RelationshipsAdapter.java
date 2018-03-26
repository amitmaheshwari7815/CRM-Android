package com.woxthebox.draglistview.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RelationshipsAdapter extends RecyclerView.Adapter<RelationshipsAdapter.MyHolder> {

    public static int account_images[] = {R.drawable.ic_account, R.drawable.ic_account, R.drawable.ic_account, R.drawable.ic_account,
            R.drawable.ic_account, R.drawable.ic_account};
    public static String account_names[] = {"Account", "Account", "Account", "Account", "Account"};
    public static String company_names[] = {"CIOC FMCG Pvt Ltd", "ABC pvt Ltd", "XYZ solution", "Apple Corp.", "Google"};

    Context context;

    public RelationshipsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RelationshipsAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.relationship_style, parent, false);
        RelationshipsAdapter.MyHolder myHolder = new RelationshipsAdapter.MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RelationshipsAdapter.MyHolder holder, int position) {
        holder.accountimage.setImageResource(account_images[position]);
        holder.accountname.setText(account_names[position]);
        holder.companyname.setText(company_names[position]);

    }

    @Override
    public int getItemCount() {
        return account_names.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView accountimage, viewDetails;
        TextView accountname, companyname;

        public MyHolder(View itemView) {
            super(itemView);
            accountimage = itemView.findViewById(R.id.Account_photo);
            accountname = itemView.findViewById(R.id.account);
            companyname = itemView.findViewById(R.id.company_name);
        }

    }
}