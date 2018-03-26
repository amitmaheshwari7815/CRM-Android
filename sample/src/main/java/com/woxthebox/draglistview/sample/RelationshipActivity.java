package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by amit on 9/3/18.
 */

public class RelationshipActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationships);

        rv = findViewById(R.id.realtionship_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));

        RelationshipsAdapter relationshipsAdapter= new RelationshipsAdapter(this);
        rv.setAdapter(relationshipsAdapter);
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(RelationshipActivity.this, ""+position, Toast.LENGTH_SHORT).show();
//                        String itemPosition = (String) arrayList.get(position);

                        String companyName = RelationshipsAdapter.company_names[position];
                        Intent intent = new Intent(RelationshipActivity.this,ActiveDealsActivity.class);
                        intent.putExtra("company_name",companyName);
                        startActivity(intent);
                    }
                })
        );
    }


}
