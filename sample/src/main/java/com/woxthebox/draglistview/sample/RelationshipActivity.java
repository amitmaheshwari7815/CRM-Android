package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by amit on 9/3/18.
 */

public class RelationshipActivity extends Activity {
    RecyclerView rv;
    public static ArrayList relationship;
    public AsyncHttpClient client;
    public static int pos;
    ServerUrl serverUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relationships);

        serverUrl = new ServerUrl();

        client = new AsyncHttpClient();
        relationship = new ArrayList();
        getData();

        rv = findViewById(R.id.realtionship_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));


        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(RelationshipActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//                        String itemPosition = (String) arrayList.get(position);
                        pos = position;
                        HashMap hm = (HashMap) relationship.get(position);
                        String companyName = (String) hm.get("name");
                        String companyPk = (String) hm.get("pk");
                        String web = (String) hm.get("web");
                        Intent intent = new Intent(RelationshipActivity.this,ActiveDealsActivity.class);
                        intent.putExtra("company_name",companyName);
                        intent.putExtra("pk",companyPk);
                        intent.putExtra("web",web);
                        startActivity(intent);
                    }
                })
        );
    }

    protected void getData() {
        String serverURL = serverUrl.url;
        client.get(serverURL+"api/clientRelationships/relationships/?format=json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject Obj = null;
                    try {
                        Obj = response.getJSONObject(i);
                        String company_pk = Obj.getString("pk");
                        String name = Obj.getString("name");
                        String logo = Obj.getString("logo");
                        String mobile = Obj.getString("mobile");
                        String web = Obj.getString("web");

                        JSONObject address = Obj.getJSONObject("address");

                        String pk1 = address.getString("pk");
                        String street = address.getString("street");
                        String city = address.getString("city");
                        String state = address.getString("state");
                        String pincode = address.getString("pincode");
                        String lat = address.getString("lat");
                        String lon = address.getString("lon");
                        String country = address.getString("country");

                        HashMap hashMap = new HashMap();

                        hashMap.put("pk", company_pk);
                        hashMap.put("name", name);
                        hashMap.put("logo", logo);
                        hashMap.put("mobile", mobile);
                        hashMap.put("web", web);
                        hashMap.put("street", street);
                        hashMap.put("city", city);
                        hashMap.put("state", state);
                        hashMap.put("pincode", pincode);
                        hashMap.put("country", country);
                        relationship.add(hashMap);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                RelationshipsAdapter relationshipsAdapter = new RelationshipsAdapter(RelationshipActivity.this);
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
}

