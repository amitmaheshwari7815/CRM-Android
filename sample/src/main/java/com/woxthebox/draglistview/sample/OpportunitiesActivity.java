/**
 * Copyright 2014 Magnus Woxblom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.woxthebox.draglistview.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

public class OpportunitiesActivity extends AppCompatActivity {
    public static ArrayList opportunities;
    public AsyncHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunities);

        client = new AsyncHttpClient();
        opportunities = new ArrayList();
        getOpprtunities();


        if (savedInstanceState == null) {
            showFragment(BoardFragment.newInstance());
        }

        getSupportActionBar().hide();
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, "fragment").commit();
    }

    //    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        boolean listFragment = getSupportFragmentManager().findFragmentByTag("fragment") instanceof ListFragment;
//        menu.findItem(R.id.action_lists).setVisible(!listFragment);
//        menu.findItem(R.id.action_board).setVisible(listFragment);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_lists:
//                showFragment(ListFragment.newInstance());
//                return true;
//            case R.id.action_board:
//                showFragment(BoardFragment.newInstance());
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
    protected void getOpprtunities() {
        String serverURL = "http://10.0.2.2:8000/api/clientRelationships/deal/?format=json";
         client.get(serverURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");

                        JSONObject contacts = jsonObject.getJSONObject("contacts");
                        Log.e("contactslength",""+contacts.length());
                            String Cname = contacts.getString("name");
                            String company = contacts.getString("company");
                            String email = contacts.getString("email");
                            String cmobile = contacts.getString("mobile");
                            String designation = contacts.getString("designation");
                            boolean male = contacts.getBoolean("male");
                        } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    HashMap hashMap = new HashMap();

//                        hashMap.put("name", name);
//                        hashMap.put("logo", logo);
//                        hashMap.put("mobile", mobile);
//                        hashMap.put("web", web);
////                        pk.put(address, "address");
//                        hashMap.put("street", street);
//                        hashMap.put("name",Cname);
//                        hashMap.put("company",company);
//                        hashMap.put("email",email);
//                        hashMap.put("mobile",cmobile);
//                        hashMap.put("contacts",contacts);
//                        hashMap.put("designation",designation);
//                        hashMap.put("male",male);


//                        pk.put(city, "city");
//                        pk.put(state, "state");
//                        pk.put(pincode, "pincode");
//                        pk.put(country, "country");
                        opportunities.add(hashMap);

                }
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
