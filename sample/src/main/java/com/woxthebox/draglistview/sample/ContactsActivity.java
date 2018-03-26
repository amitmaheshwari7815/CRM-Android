package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ContactsActivity extends FragmentActivity {

    FloatingActionButton fab, fabImport, fabNew;
    RecyclerView browse_rv;
    private boolean fabExpanded = false;
//    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabImport;
    private LinearLayout layoutFabNew;
//    private LinearLayout layoutFabPhoto;

    Animation rotate_forward, rotate_Backward, fab_open, fab_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        fab = findViewById(R.id.fab);
        fabImport = findViewById(R.id.fab_import);
        fabNew = findViewById(R.id.fab_new);

        layoutFabImport = (LinearLayout) this.findViewById(R.id.layoutFabImport);
        layoutFabNew = (LinearLayout) this.findViewById(R.id.layoutFabNew);
//        layoutFabPhoto = (LinearLayout) this.findViewById(R.id.layoutFabPhoto);

        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotate_Backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        browse_rv = findViewById(R.id.browse_recyclerView);
        browse_rv.setLayoutManager(new LinearLayoutManager(this));

        BrowseAdapter browseAdapter = new BrowseAdapter(this);
        browse_rv.setAdapter(browseAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (importFab.getVisibility() == View.VISIBLE && newFab.getVisibility() == View.VISIBLE) {
//                    importFab.setVisibility(View.GONE);
//                    newFab.setVisibility(View.GONE);
//                    importFab.startAnimation(fab_close);
//                    newFab.setAnimation(fab_close);
//                    fab.startAnimation(rotate_Backward);
//                    fab.startAnimation(fab_open);
//                } else {
//                    importFab.setVisibility(View.VISIBLE);
//                    newFab.setVisibility(View.VISIBLE);
//                    importFab.startAnimation(fab_open);
//                    newFab.setAnimation(fab_open);
//                    fab.startAnimation(rotate_forward);
//                }
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });

        closeSubMenusFab();

        fabImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ContactsActivity.this, "Ok", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri data = Uri.parse("content://contacts/people/");
//                intent.setData(data);
                startActivity(new Intent(ContactsActivity.this,ContactsListActivity.class));
            }
        });

        fabNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactsActivity.this, NewContactActivity.class));
            }
        });
    }

    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabImport.setVisibility(View.INVISIBLE);
        layoutFabNew.setVisibility(View.INVISIBLE);
//        fab.setImageResource(R.drawable.ic_add);
        fabImport.startAnimation(fab_close);
        fabNew.setAnimation(fab_close);
        fab.startAnimation(rotate_Backward);
        fab.startAnimation(fab_open);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabImport.setVisibility(View.VISIBLE);
        layoutFabNew.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
//        fab.setImageResource(R.drawable.ic_close);
        fabImport.startAnimation(fab_open);
        fabNew.setAnimation(fab_open);
        fab.startAnimation(rotate_forward);
        fabExpanded = true;
    }
}
