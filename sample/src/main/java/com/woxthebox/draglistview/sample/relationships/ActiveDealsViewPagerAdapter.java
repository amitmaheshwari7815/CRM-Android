package com.woxthebox.draglistview.sample.relationships;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by amit on 21/4/18.
 */

public class ActiveDealsViewPagerAdapter extends FragmentPagerAdapter {

    private String pk;
    private int contractPk;

    public ActiveDealsViewPagerAdapter(FragmentManager fm, String pk, int contractPk) {
        super(fm);
        this.pk = pk;
        this.contractPk = contractPk;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){


            case 0:
                fragment = new DealInfoFragment();

                break;
            case 1:
                fragment = new ExternalStakeholderFragment();
//              fragment = new DealInfoFragment();
                break;
            case 2:
                fragment = new FinancesFragment();
//                fragment = new DealInfoFragment();
                break;
            case 3:
                fragment = new RequirementFragment();
//                fragment = new DealInfoFragment();

                break;

        }

        Bundle bundle = new Bundle();
        bundle.putString("pk", pk);
        bundle.putInt("contracts",contractPk);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
