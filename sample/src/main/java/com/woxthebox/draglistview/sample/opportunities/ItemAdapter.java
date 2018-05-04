/*
 * Copyright 2014 Magnus Woxblom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.woxthebox.draglistview.sample.opportunities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItemAdapter;
import com.woxthebox.draglistview.sample.R;

import java.util.ArrayList;
import java.util.List;

class ItemAdapter extends DragItemAdapter<Pair<Long, String>, ItemAdapter.MyHolder> {
    Context context;
    private int mLayoutId;
    private int mGrabHandleId;
    private boolean mDragOnLongPress;
    String text[] = {"Joyce A. Neal","Wanda J. Aguirre"};
    String cName[] = {"Purity Supreme","Team Electronics"};
    String dName[] = {"Blandit insolens pri ad","Duo in dolorum detracto"};

    private List<Opportunities> opportunities;

    public ItemAdapter(Context context,List<Opportunities> opportunitiesList){
        this.context = context;
        this.opportunities = opportunitiesList;
    }

    ItemAdapter(ArrayList<Pair<Long, String>> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
        mLayoutId = layoutId;
        mGrabHandleId = grabHandleId;
        mDragOnLongPress = dragOnLongPress;
        setItemList(list);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        ItemAdapter.MyHolder myHolder = new ItemAdapter.MyHolder(view);
        return  myHolder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.MyHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String text1 = mItemList.get(position).second;
//        Opportunities o = opportunities.get(position);
//
        holder.mText.setText(text[position]);
//        holder.mText.setText(o.getContactName());
        holder.dealName.setText(dName[position]);
//        holder.dealName.setText(o.getName());
        holder.companyName.setText(cName[position]);
//        holder.companyName.setText(o.getCompanyName());
//        holder.itemView.setTag(mItemList.get(position));

    }

    @Override
    public long getUniqueItemId(int position) {
        return mItemList.get(position).first;
    }

    class MyHolder extends DragItemAdapter.ViewHolder {
        TextView mText, companyName,dealName;

        MyHolder(final View itemView) {
            super(itemView, mGrabHandleId, mDragOnLongPress);
            mText = (TextView) itemView.findViewById(R.id.text);
            companyName =(TextView) itemView.findViewById(R.id.opp_card_company);
            dealName = (TextView) itemView.findViewById(R.id.opp_card_deal);
        }

        @Override
        public void onItemClicked(View view) {
            Intent intent = new Intent(view.getContext(),StepView.class);
            view.getContext().startActivity(intent);
            Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
}
