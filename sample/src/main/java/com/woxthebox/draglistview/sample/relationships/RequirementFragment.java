package com.woxthebox.draglistview.sample.relationships;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.woxthebox.draglistview.sample.R;


public class RequirementFragment extends Fragment {

//    TextView requirements;

    public RequirementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_requirement, container, false);


//        String requirements = ActiveDealsActivity.requirements;

//        String htmlAsString = getString(R.string.hello_world);

            String requirements = this.getArguments().getString("requirements");
            Spanned htmlAsSpanned = Html.fromHtml(String.valueOf(requirements));

//
            TextView textView = (TextView) v.findViewById(R.id.requirements_tv);
            textView.setText(htmlAsSpanned);


            return v;
        }


    }
