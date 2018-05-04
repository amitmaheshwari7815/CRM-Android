package com.woxthebox.draglistview.sample.opportunities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.woxthebox.draglistview.sample.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OppStakeHolderFragment extends Fragment {
    RecyclerView recyclerView;


    public OppStakeHolderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opp_stake_holder, container, false);

        recyclerView = view.findViewById(R.id.stakeholder_recycler);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        OppStakeHolderAdapter oppStakeHolderAdapter = new OppStakeHolderAdapter(getContext());
        recyclerView.setAdapter(oppStakeHolderAdapter);

        return view;
    }

}
