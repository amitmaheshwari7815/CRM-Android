package com.woxthebox.draglistview.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by amit on 30/4/18.
 */

public class OppViewPagerAdpater extends FragmentPagerAdapter {
    public OppViewPagerAdpater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {


            case 0:
                fragment = new OppStakeHolderFragment();

                break;
            case 1:
                fragment = new OppTimelineFragment();
//              fragment = new OppStakeHolderFragment();
                break;
            case 2:
                fragment = new OppEventFragment();
//                 fragment = new OppStakeHolderFragment();
                break;

        }
        return fragment;
    }

        @Override
        public int getCount () {
            return 3;
        }
    }

