package com.woxthebox.draglistview.sample;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    ImageView searchLocImage;
    TextView searchLocTv, infoCompany;



    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_info, container, false);

        searchLocImage = v.findViewById(R.id.info_iv_address);
        infoCompany = v.findViewById(R.id.info_company);
        searchLocTv = v.findViewById(R.id.info_tv_address);

        infoCompany.setText(""+ViewDetailsActivity.company);

        String address = ViewDetailsActivity.street+" "+ViewDetailsActivity.city+" "+ViewDetailsActivity.state+" "+ViewDetailsActivity.pincode+" "+ViewDetailsActivity.country;
        searchLocTv.setText(address);

        clickMethods();

        return v;
    }

    public void clickMethods(){

        final String loc = searchLocTv.getText().toString().trim();

        searchLocImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+loc);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        searchLocTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+loc);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

}
