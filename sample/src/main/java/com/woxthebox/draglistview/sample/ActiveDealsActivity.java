package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by amit on 10/3/18.
 */

public class ActiveDealsActivity extends AppCompatActivity {
    RecyclerView rv1;
    TextView companyname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_deals);
        companyname = findViewById(R.id.comapny_name);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String company = bundle.getString("company_name");
            companyname.setText(company);
        }


        rv1 = findViewById(R.id.activedearl_recyclerView);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        ActiveDealsAdapter activeDealsAdapter= new ActiveDealsAdapter(this);
        rv1.setAdapter(activeDealsAdapter);

        rv1.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(ActiveDealsActivity.this, ""+position, Toast.LENGTH_SHORT).show();
//                        String itemPosition = (String) arrayList.get(position);

                        Intent intent = new Intent(ActiveDealsActivity.this,ActiveDealsDetailsActivity.class);
                        startActivity(intent);
                    }
                })
        );
    }

}