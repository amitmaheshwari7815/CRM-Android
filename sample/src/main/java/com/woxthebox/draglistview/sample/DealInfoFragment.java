package com.woxthebox.draglistview.sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DealInfoFragment extends Fragment {
    TextView companyname,address,web,cin,tin,mobile,telephone,about;


    public DealInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_deal_info, container, false);
        companyname = view.findViewById(R.id.info_comapny_name);
        web = view.findViewById(R.id.info_web);
        cin = view.findViewById(R.id.cin);
        tin = view.findViewById(R.id.tin);
        mobile = view.findViewById(R.id.info_mobile);
        telephone = view.findViewById(R.id.info_tele);
        about = view.findViewById(R.id.about);


        return view;
    }

}
