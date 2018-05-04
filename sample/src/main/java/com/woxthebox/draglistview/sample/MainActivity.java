package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.woxthebox.draglistview.sample.contacts.ContactsActivity;
import com.woxthebox.draglistview.sample.opportunities.OpportunitiesActivity;
import com.woxthebox.draglistview.sample.relationships.RelationshipActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonContacts(View v)
    {
        startActivity(new Intent(this,ContactsActivity.class));
    }

    public void buttonOpportunities(View v)
    {
        startActivity(new Intent(this,OpportunitiesActivity.class));
    }

    public void buttonRelationships(View v) {
        startActivity(new Intent(this,RelationshipActivity.class));
    }


}
