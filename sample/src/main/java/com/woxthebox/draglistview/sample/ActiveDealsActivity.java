package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by amit on 10/3/18.
 */

public class ActiveDealsActivity extends Activity {
    RecyclerView rv1;
    TextView companyname;
    public static ArrayList deal;
    public AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_deals);
        companyname = findViewById(R.id.comapny_name);

        client = new AsyncHttpClient();
        deal = new ArrayList();
        getDeal();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String company = bundle.getString("company_name");
            companyname.setText(company);
        }


        rv1 = findViewById(R.id.activedearl_recyclerView);
        rv1.setLayoutManager(new LinearLayoutManager(this));

        ActiveDealsAdapter activeDealsAdapter = new ActiveDealsAdapter(this);
        rv1.setAdapter(activeDealsAdapter);

        rv1.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Toast.makeText(ActiveDealsActivity.this, "" + position, Toast.LENGTH_SHORT).show();
//                        String itemPosition = (String) arrayList.get(position);

                        Intent intent = new Intent(ActiveDealsActivity.this, ActiveDealsDetailsActivity.class);
                        startActivity(intent);
                    }
                })
        );
    }

    protected void getDeal() {
        String serverURL = "http://10.0.2.2:8000/api/clientRelationships/deal/?format=json";
        client.get(serverURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject Obj = null;
                    try {
                        Obj = response.getJSONObject(i);
                        String pk = Obj.getString("pk");
                        String name = Obj.getString("name");
                        String value = Obj.getString("value");
                        String currency = Obj.getString("currency");
                        String internalUsers = Obj.getString("internalUsers");
                        String requirements = Obj.getString("requirements");
                        String probability = Obj.getString("probability");
                        String active = Obj.getString("active");
                        String result = Obj.getString("result");
                        String doc = Obj.getString("doc");
                        String state = Obj.getString("state");
                        String duePeriod = Obj.getString("duePeriod");
                        String duePenalty = Obj.getString("duePenalty");

                        JSONObject company = Obj.getJSONObject("company");
                        String cname = company.getString("name");
                        String mobile = company.getString("mobile");


                        JSONObject address = Obj.getJSONObject("address");
                        String pk1 = address.getString("pk");
                        String street = address.getString("street");
                        String city = address.getString("city");
                        String astate = address.getString("state");
                        String pincode = address.getString("pincode");
                        String lat = address.getString("lat");
                        String lon = address.getString("lon");
                        String country = address.getString("country");

                        JSONObject contacts = Obj.getJSONObject("contacts");
                        String pk2 = contacts.getString("pk");
                        String namec = contacts.getString("name");




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}