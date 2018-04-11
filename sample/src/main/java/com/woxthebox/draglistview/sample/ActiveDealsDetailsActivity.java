package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by amit on 14/3/18.
 */

public class ActiveDealsDetailsActivity extends FragmentActivity {
    public int[] tabicon = {R.drawable.ic_info,R.drawable.ic_stakeholder,R.drawable.ic_finances,R.drawable.ic_requirements};
    TabLayout tabLayout;
    TextView Dealname, Valuation, ClosingDate;
    ImageView imageView;


    private static final String TAG = ActiveDealsDetailsActivity.class.toString();

    public static String name, value, closedate, web,cin,tin,about,telephone,mobile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_deals_details);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            name = b.getString("name");
            value = b.getString("value");
            closedate = b.getString("closeDate");

            web = b.getString("web");
            cin = b.getString("cin");
            tin = b.getString("tin");
            about = b.getString("about");
            telephone = b.getString("telephone");
            mobile = b.getString("mobile");
        }

        Dealname = findViewById(R.id.deal_name);
        Valuation = findViewById(R.id.valuation_money);
        ClosingDate = findViewById(R.id.closing_date);

        Dealname.setText(name);
        Valuation.setText(value);
        ClosingDate.setText(closedate);


        tabLayout = findViewById(R.id.deal_view);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {



            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(tabicon[tab.getPosition()]);
                int pos = tab.getPosition();

                switch (pos) {
                    case 0: {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.dealinfo_fragment, new DealInfoFragment(), "DealInfoFragment");
                        ft.commit();
                        break;
                    }
                    case 1: {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.dealinfo_fragment, new ExternalStakeholderFragment(), "ExternalStackHolderFragment");
                        ft.commit();
                        break;
                    }
                    case 2: {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.dealinfo_fragment, new FinancesFragment(), "FinancesFrangment");
                        ft.commit();
                        break;
                    }
                    case 3 : {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.dealinfo_fragment, new RequirementFragment(), "RequirementFrangment");
                        ft.commit();
                        break;
                    }

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}