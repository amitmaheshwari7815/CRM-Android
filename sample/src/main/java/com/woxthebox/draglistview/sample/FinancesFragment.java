package com.woxthebox.draglistview.sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinancesFragment extends Fragment {
    RecyclerView rv;

    public FinancesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_finances, container, false);
        rv = v.findViewById(R.id.finances_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        FinancesAdapter financesAdapter = new FinancesAdapter(getContext());
        rv.setAdapter(financesAdapter);


        return v;
    }

}
