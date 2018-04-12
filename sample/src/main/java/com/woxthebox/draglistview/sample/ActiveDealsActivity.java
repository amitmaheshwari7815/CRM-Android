package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
 * Created by amit on 10/3/18.
 */

public class ActiveDealsActivity extends Activity {
    RecyclerView rv1;
    TextView companyname,web;
    public static ArrayList deal;
    public AsyncHttpClient client;
    public static String c_pk,company,street,city,astate,pincode,country,pkc,requirements;
    public static int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_deals);
        companyname = findViewById(R.id.comapny_name);
        web = findViewById(R.id.web_text);
        rv1 = findViewById(R.id.activedearl_recyclerView);
        client = new AsyncHttpClient();
        deal = new ArrayList();
        getDeal();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            company = bundle.getString("company_name");
            c_pk = bundle.getString("pk");
            String web1 = bundle.getString("web");
            companyname.setText(company);
            web.setText(web1);

        }

        rv1.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(ActiveDealsActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                        pos=position;
                        HashMap hm = (HashMap) deal.get(position);
                        String dealName = (String) hm.get("name");
                        String value = (String) hm.get("value");
                        String closingDate = (String) hm.get("closeDate");
                        Intent intent = new Intent(ActiveDealsActivity.this, ActiveDealsDetailsActivity.class);
                        intent.putExtra("name",dealName);
                        intent.putExtra("value",value);
                        intent.putExtra("closeDate",closingDate);
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
                        String pk = Obj.getString("pk");// id

                            String name = Obj.getString("name");//name
                            String value = Obj.getString("value"); //value
                            String currency = Obj.getString("currency");


                            String probability = Obj.getString("probability");
                            String closingDate = Obj.getString("closeDate");
                            String active = Obj.getString("active");
                            String result = Obj.getString("result");
                            String doc = Obj.getString("doc");
                            String state = Obj.getString("state");
                            String duePeriod = Obj.getString("duePeriod");
                            String duePenalty = Obj.getString("duePenalty");

                        JSONObject company = Obj.getJSONObject("company");

                            String cname = company.getString("name");// company name
                            String mobile = company.getString("mobile");


                            JSONObject address = company.getJSONObject("address");
                            String pk1 = address.getString("pk");


                            JSONArray jsonArray = Obj.getJSONArray("contacts");
                            for (int j = 0; j < jsonArray.length(); j++) {
                                JSONObject contacts = jsonArray.getJSONObject(j);

                                String pk2 = contacts.getString("pk");
                                String namec = contacts.getString("name");// contact name
                                String companyc = contacts.getString("company");
                                if (c_pk.equals(companyc)) {
                                    pkc = company.getString("pk");// id

                                    requirements = Obj.getString("requirements");


                                    String email = contacts.getString("email");
                                    String mobilec = contacts.getString("mobile");
                                    String designation = contacts.getString("designation");
                                    String dp = contacts.getString("dp");
                                    boolean gender = contacts.getBoolean("male");
                                    street = address.getString("street");
                                    city = address.getString("city");
                                    astate = address.getString("state");
                                    pincode = address.getString("pincode");
                                    String lat = address.getString("lat");
                                    String lon = address.getString("lon");
                                    country = address.getString("country");

//                                JSONArray jsonArray1 = Obj.getJSONArray("contracts");
//                                for (int k = 0; k < jsonArray1.length(); k++) {
//                                    JSONObject contracts = jsonArray1.getJSONObject(k);
//                                    JSONArray internalUsers = Obj.getJSONArray("internalUsers");
//                                    for (int k=0; k < jsonArray.length(); j++) {
//                                        JSONObject internal  = jsonArray.getJSONObject(k);


                                    HashMap hashMap = new HashMap();
                                    hashMap.put("pk", pk);
                                    hashMap.put("name", name);
                                    hashMap.put("value", value);
                                    hashMap.put("currency", currency);
//                                    hashMap.put("internalUsers", internalUsers);
                                    hashMap.put("requirements", requirements);
                                    hashMap.put("probability", probability);
                                    hashMap.put("closeDate", closingDate);
                                    hashMap.put("active", active);
                                    hashMap.put("doc", doc);
                                    hashMap.put("result", result);
                                    hashMap.put("state", state);
                                    hashMap.put("duePeriod", duePeriod);
                                    hashMap.put("duePenalty", duePenalty);
                                    hashMap.put("company", cname);
                                    hashMap.put("mobile", mobile);
                                    hashMap.put("street", street);
                                    hashMap.put("add_state", astate);
                                    hashMap.put("city", city);
                                    hashMap.put("pincode", pincode);
                                    hashMap.put("country", country);
                                    hashMap.put("name_con", namec);
                                    hashMap.put("company_con", companyc);
                                    hashMap.put("email", email);
                                    hashMap.put("mobile_con", mobilec);
                                    hashMap.put("designation", designation);
                                    hashMap.put("male", gender);

                                    deal.add(hashMap);
                                    Log.d("deal", deal.size() + "");
//                                }
                                }else {
                                    Log.d("pk"," - not matching");
//                            }
                        }
                        }

                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                rv1.setLayoutManager(new LinearLayoutManager(ActiveDealsActivity.this));
                ActiveDealsAdapter activeDealsAdapter = new ActiveDealsAdapter(ActiveDealsActivity.this);
                rv1.setAdapter(activeDealsAdapter);
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


