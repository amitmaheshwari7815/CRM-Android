package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 28/4/18.
 */

public class StepView extends FragmentActivity {
    TabLayout tab;
    HorizontalStepView horizontalStepView;
    TextView textView;
    Button button;
    private OppViewPagerAdpater oppViewPagerAdpater;
    private ViewPager viewPager;

String listName[] = {"Contacting","Contacting","Contacting","Contacting","Contacting"};



@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.step_view);
    textView = (TextView)findViewById(R.id.listName);
    button =(Button)findViewById(R.id.mark_complete);
    horizontalStepView = (HorizontalStepView)findViewById(R.id.horizontalStepView);
    viewPager = findViewById(R.id.opp_viewpager);
    tab = findViewById(R.id.opp_deal_view);
    oppViewPagerAdpater = new OppViewPagerAdpater(getSupportFragmentManager());
    viewPager.setAdapter(oppViewPagerAdpater);
    tab.setupWithViewPager(viewPager);
    viewPager.setOffscreenPageLimit(3);

    Tabs();

    List<StepBean> stepsBeanList = new ArrayList<>();
    StepBean stepBean0 = new StepBean("",1);
    StepBean stepBean1 = new StepBean("",1);
    StepBean stepBean2 = new StepBean("",0);
    StepBean stepBean3 = new StepBean("",-1);
    StepBean stepBean4 = new StepBean("",-1);
    StepBean stepBean5 = new StepBean("",-1);

    stepsBeanList.add(stepBean0);
    stepsBeanList.add(stepBean1);
    stepsBeanList.add(stepBean2);
    stepsBeanList.add(stepBean3);
    stepsBeanList.add(stepBean4);
    stepsBeanList.add(stepBean5);


    horizontalStepView
            .setStepViewTexts(stepsBeanList)
            .setTextSize(20)
            .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getApplication(), android.R.color.holo_green_dark))
            .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getApplication(), R.color.uncompleted_text_color))
            .setStepViewComplectedTextColor(ContextCompat.getColor(getApplication(), android.R.color.holo_green_dark))
            .setStepViewUnComplectedTextColor(ContextCompat.getColor(getApplication(), R.color.uncompleted_text_color))
            .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getApplication(), R.drawable.ic_completed))
            .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getApplication(), R.drawable.default_icon))
            .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getApplication(), R.drawable.ic_attention));


}

    private void Tabs() {

        LinearLayout tabOne = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.opp_deal_tab, null);
        ImageView iv_tab1 = tabOne.findViewById(R.id.opp_tab);
        iv_tab1.setImageResource(R.drawable.ic_stakeholder);
        tab.getTabAt(0).setCustomView(tabOne);

        LinearLayout tabTwo = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.opp_deal_tab, null);
        ImageView iv_tab2 = tabTwo.findViewById(R.id.opp_tab);
        iv_tab2.setImageResource(R.drawable.ic_timeline);
        tab.getTabAt(1).setCustomView(tabTwo);

        LinearLayout tabThree = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.opp_deal_tab, null);
        ImageView iv_tab3 = tabThree.findViewById(R.id.opp_tab);
        iv_tab3.setImageResource(R.drawable.ic_event);
        tab.getTabAt(2).setCustomView(tabThree);

    }



}



