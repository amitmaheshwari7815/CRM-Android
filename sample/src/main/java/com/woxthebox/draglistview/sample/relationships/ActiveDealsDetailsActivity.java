package com.woxthebox.draglistview.sample.relationships;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woxthebox.draglistview.sample.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amit on 14/3/18.
 */

public class ActiveDealsDetailsActivity extends FragmentActivity {
    TabLayout tabLayout;
    TextView Dealname, Valuation, ClosingDate;
    ImageView imageView;
    Deal d;
    private String pk;
    private Integer contractPk;
    private ActiveDealsViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;


    private static final String TAG = ActiveDealsDetailsActivity.class.toString();

    public static String name, nameC, designation,value, closedate, web,cin,tin,about,telephone,mobile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_deals_details);


        final Bundle b = getIntent().getExtras();
        if (b != null) {
            name = b.getString("name");
            value = b.getString("value");
            closedate = b.getString("closeDate");
            pk = b.getString("pk");
            contractPk = b.getInt("contracts");

//         /*   web = b.getString("web");
//            cin = b.getString("cin");
//            tin = b.getString("tin");
//            about = b.getString("about");
//            telephone = b.getString("telephone");
//            mobile = b.getString("mobile");*/
            d = new Deal();
            d.name = name;
            d.value = value;
            d.contactName = nameC;
            d.contactDesignation = designation;
            d.closeDate = closedate;


        }


        Dealname = findViewById(R.id.deal_name);
        Valuation = findViewById(R.id.valuation_money);
        ClosingDate = findViewById(R.id.closing_date);


      String close = closedate;
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
      Date date1 = null;
      String string = null;
        try {
            date1 = simpleDateFormat.parse(close);
            string = dateFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("ParseException",""+e);
        }


        Dealname.setText(d.getName());
        Valuation.setText(d.getValue());
        ClosingDate.setText(string);

        viewPager = findViewById(R.id.deal_viewpager);

        tabLayout = findViewById(R.id.deal_view);
        viewPagerAdapter = new ActiveDealsViewPagerAdapter(getSupportFragmentManager(), pk,contractPk);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);

        createTabs();


        Bundle bundle = new Bundle();
        bundle.putString("requirements",d.getRequirements());
        RequirementFragment requirementFragment = new RequirementFragment();
        requirementFragment.setArguments(bundle);
    }


    private void createTabs() {

        LinearLayout tabOne = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.active_deal_tab, null);
        ImageView iv_tab1 = tabOne.findViewById(R.id.iv_tab);
        iv_tab1.setImageResource(R.drawable.ic_info);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        LinearLayout tabTwo = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.active_deal_tab, null);
        ImageView iv_tab2 = tabTwo.findViewById(R.id.iv_tab);
        iv_tab2.setImageResource(R.drawable.ic_stakeholder);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        LinearLayout tabThree = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.active_deal_tab, null);
        ImageView iv_tab3 = tabThree.findViewById(R.id.iv_tab);
        iv_tab3.setImageResource(R.drawable.ic_finances);
        tabLayout.getTabAt(2).setCustomView(tabThree);

        LinearLayout tabFour = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.active_deal_tab, null);
        ImageView iv_tab4 = tabFour.findViewById(R.id.iv_tab);
        iv_tab4.setImageResource(R.drawable.ic_requirements);
        tabLayout.getTabAt(3).setCustomView(tabFour);
    }

}