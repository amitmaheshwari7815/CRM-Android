package com.woxthebox.draglistview.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by amit on 9/3/18.
 */

/**
 * Created by amit on 9/3/18.
 */

public class RelationshipActivity extends AppCompatActivity {
    RecyclerView rv;
    RelationshipsAdapter relationshipsAdapter;
    public static List<Relationships> relationship;
    public AsyncHttpClient client;
    public static int pos;
    ServerUrl serverUrl;
    private SearchView searchView;
    Relationships rel;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationships);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        serverUrl = new ServerUrl();
        client = new AsyncHttpClient();
        relationship = new ArrayList<>();
        getData();

        rv = findViewById(R.id.realtionship_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(RelationshipActivity.this));
        rv.setItemAnimator(new DefaultItemAnimator());


        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
//                        Toast.makeText(RelationshipActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        pos = position;
                        Relationships rel = relationship.get(position);
//                        d.getPk();
//                        d.getName();
//                        d.getContactName();
                        Intent intent = new Intent(RelationshipActivity.this, ActiveDealsActivity.class);
                        intent.putExtra("company_name", rel.getCompanyName());
                        intent.putExtra("pk", rel.getPk());
                        intent.putExtra("web", rel.getWeb());
                        startActivity(intent);
                    }
                })
        );
    }


    protected void getData() {
        String serverURL = serverUrl.url;
        client.get(serverURL + "api/clientRelationships/relationships/?&name__contains=&limit=&offset=0", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject Obj = null;

                    try {
                        Obj = response.getJSONObject(i);

                        Relationships relationships = new Relationships(Obj);
                        relationship.add(relationships);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                RelationshipsAdapter relationshipsAdapter = new RelationshipsAdapter(RelationshipActivity.this, relationship);
                rv.setAdapter(relationshipsAdapter);

            }


            @Override
            public void onFinish() {
                System.out.println("finished 001");

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                System.out.println("finished failed 001");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (relationshipsAdapter != null) relationshipsAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }
}