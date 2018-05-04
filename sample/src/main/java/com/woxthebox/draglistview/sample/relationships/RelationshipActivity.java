package com.woxthebox.draglistview.sample.relationships;

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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.woxthebox.draglistview.sample.R;
import com.woxthebox.draglistview.sample.ServerUrl;
import com.woxthebox.draglistview.sample.app.AppController;

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


        rv = findViewById(R.id.realtionship_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(RelationshipActivity.this));
        rv.setItemAnimator(new DefaultItemAnimator());
        RelationshipsAdapter relationshipsAdapter = new RelationshipsAdapter(RelationshipActivity.this, relationship);
        rv.setAdapter(relationshipsAdapter);
        getData();

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
                        List<Relationships> items = new Gson().fromJson(response.toString(), new TypeToken<List<Relationships>>() {
                        }.getType());
                        relationship.clear();
                        relationship.addAll(items);
//                        relationshipsAdapter.notifyDataSetChanged();

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
                String serverURL = serverUrl.url + "api/clientRelationships/relationships/?&name__contains=" + newText + "&limit=&offset=0";
                JsonArrayRequest request = new JsonArrayRequest(serverURL,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject Obj = null;
                                    try {
                                        Obj = response.getJSONObject(i);


                                        if (response == null) {
                                            Toast.makeText(getApplicationContext(), "Couldn't fetch the contacts! Pleas try again.", Toast.LENGTH_LONG).show();
                                            return;
                                        }

                                        List<Relationships> items = new Gson().fromJson(response.toString(), new TypeToken<List<Relationships>>() {
                                        }.getType());

                                        // adding contacts to contacts list
                                        relationship.clear();
                                        relationship.addAll(items);

                                        // refreshing recycler view
//                                                                  relationshipsAdapter.notifyDataSetChanged();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error in getting json
//                                                          Log.e(TAG, "Error: " + error.getMessage());
//                                                          Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });

                AppController.getInstance().addToRequestQueue(request);
                return true;

//
//            @Override
//                    public void onFinish() {
//                        System.out.println("finished 001");
//
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
//                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                        System.out.println("finished failed 001");
//                    }
//                });

//                if (relationshipsAdapter != null) relationshipsAdapter.getFilter().filter(newText);

            }


        });
    }
}