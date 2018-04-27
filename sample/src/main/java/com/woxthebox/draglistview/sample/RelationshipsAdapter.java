package com.woxthebox.draglistview.sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RelationshipsAdapter extends RecyclerView.Adapter<RelationshipsAdapter.MyHolder> implements Filterable{


    Context context;
    private List<Relationships> relationshipsList;
    private List<Relationships> mFilteredList;




    public RelationshipsAdapter(Context context, List<Relationships> relationshipList) {
        this.context = context;
        this.relationshipsList = relationshipList;
        this.mFilteredList = relationshipList;

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
        if (holder instanceof MyHolder) {
            MyHolder myHolder =(MyHolder) holder;
            Relationships d = relationshipsList.get(position);

            myHolder.companyname.setText(d.getCompanyName());
            myHolder.accountdeal.setText("Active Deals \n"+1);

        }
    }
    @Override
    public int getItemCount() {
        return RelationshipActivity.relationship.size();
    }

    @Override
    public  Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = relationshipsList;
                } else {

                    List<Relationships> filteredList = new ArrayList<>();

                    for (Relationships rel : relationshipsList) {

                        if (rel.getCompanyName().toLowerCase().contains(charString)) {

                            filteredList.add(rel);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                mFilteredList = (List<Relationships>) filterResults.values;
                notifyDataSetChanged();

            }
        };
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView accountimage, viewDetails;
        TextView accountdeal, companyname;

        public MyHolder(View itemView) {
            super(itemView);
//            accountimage = itemView.findViewById(R.id.Account_photo);
            companyname = itemView.findViewById(R.id.company_name);
            accountdeal = itemView.findViewById(R.id.active_deal);


        }

    }

}