package com.woxthebox.draglistview.sample;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
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


        String requirements = ActiveDealsActivity.requirements;
        Toast.makeText(getContext(), ""+requirements, Toast.LENGTH_SHORT).show();

//        String htmlAsString = getString(R.string.hello_world);
        Spanned htmlAsSpanned = Html.fromHtml(String.valueOf(requirements));
//
        TextView textView = (TextView) v.findViewById(R.id.requirements_tv);
        textView.setText(htmlAsSpanned);




      return v;
    }

}
