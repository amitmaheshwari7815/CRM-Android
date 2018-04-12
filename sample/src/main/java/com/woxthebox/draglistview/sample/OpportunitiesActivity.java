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
                String jsonString = "[{\"pk\":5,\"user\":1,\"created\":\"2017-09-14T07:33:36.729045Z\",\"updated\":\"2018-04-03T05:09:18.733648Z\",\"company\":{\"pk\":8,\"name\":\"Purity Supreme\",\"address\":{\"pk\":8,\"street\":\"2247 Poco Mas Drive\",\"city\":\"Dallas\",\"state\":\"TX\",\"pincode\":75219,\"lat\":null,\"lon\":null,\"country\":\"US\"},\"mobile\":null},\"value\":65300,\"currency\":\"INR\",\"state\":\"demo\",\"contacts\":[{\"pk\":10,\"user\":1,\"name\":\"Joyce A. Neal\",\"company\":8,\"email\":\"JoyceANeal@rhyta.com\",\"mobile\":\"9702438730\",\"designation\":\"Sales executive\",\"dp\":null,\"male\":true},{\"pk\":11,\"user\":1,\"name\":\"Matthew Green\",\"company\":8,\"email\":\"MatthewLGreen@dayrep.com\",\"mobile\":\"9731991435\",\"designation\":\"CTO\",\"dp\":null,\"male\":true}],\"internalUsers\":[8,10,13],\"requirements\":null,\"probability\":51,\"closeDate\":\"2017-09-14T18:29:59Z\",\"active\":true,\"name\":\"Blandit insolens pri ad\",\"result\":\"na\",\"contracts\":[11],\"doc\":null,\"duePeriod\":7,\"duePenalty\":0},{\"pk\":7,\"user\":1,\"created\":\"2017-09-14T07:35:29.067699Z\",\"updated\":\"2018-04-02T13:09:25.008770Z\",\"company\":{\"pk\":10,\"name\":\"Team Electronics\",\"address\":{\"pk\":10,\"street\":\"3298 Franklin Avenue\",\"city\":\"Daytona Beach\",\"state\":\"FL\",\"pincode\":32114,\"lat\":null,\"lon\":null,\"country\":\"US\"},\"mobile\":null},\"value\":15000,\"currency\":\"USD\",\"state\":\"conclusion\",\"contacts\":[{\"pk\":9,\"user\":1,\"name\":\"Wanda J. Aguirre\",\"company\":11,\"email\":\"pradeep.yadav@uipath.com\",\"mobile\":\"7840850111\",\"designation\":\"CMO\",\"dp\":null,\"male\":true},{\"pk\":15,\"user\":1,\"name\":\"Katherine J. Kilgore\",\"company\":10,\"email\":\"KatherineJKilgore@rhyta.com\",\"mobile\":\"386-248-9909\",\"designation\":\"Director\",\"dp\":null,\"male\":true}],\"internalUsers\":[6,7,12],\"requirements\":null,\"probability\":61,\"closeDate\":\"2017-09-14T18:29:59Z\",\"active\":true,\"name\":\"Duo in dolorum detracto\",\"result\":\"na\",\"contracts\":[10],\"doc\":null,\"duePeriod\":7,\"duePenalty\":0}]";
                JSONArray result;
                try {
                    result = new JSONArray(jsonString);
                    JSONObject jsonObject;
                    for (int i = 0; i < result.length(); i++) {
                        jsonObject = new JSONObject(result.getJSONObject(i).toString());

                    }
                } catch(JSONException e){
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

