package com.woxthebox.draglistview.sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    Spinner typeSpinner;
    String types[] = {"Email", "Meeting", "Call"};
    ArrayAdapter arrayAdapter;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info, container, false);

//        typeSpinner = v.findViewById(R.id.type_spinner);
//
//        arrayAdapter = new ArrayAdapter(getActivity(), R.layout.layout_type_spinner, types);
//        typeSpinner.setAdapter(arrayAdapter);

        return v;
    }

}
