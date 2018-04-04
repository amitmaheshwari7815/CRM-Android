package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by amit on 14/3/18.
 */

public class ActiveDealsDetailsActivity extends FragmentActivity {
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_deals_details);

        tabLayout = findViewById(R.id.deal_view);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch (pos){
                    case 0:
                    {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.dealinfo_fragment, new DealInfoFragment(), "DealInfoFragment");
                        ft.commit();
                        break;
                    }
                    case 1:
                    {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.dealinfo_fragment, new ExternalStakeholderFragment(), "ExternalStackHolderFragment");
                        ft.commit();
                        break;
                    }
                    case 2:
                    {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.dealinfo_fragment, new FinancesFragment(),"FinancesFrangment");
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