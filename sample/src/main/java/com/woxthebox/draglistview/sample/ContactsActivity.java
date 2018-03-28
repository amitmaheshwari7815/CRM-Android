package com.woxthebox.draglistview.sample;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Browser;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class ContactsActivity extends FragmentActivity {

    FloatingActionButton fab, fabImport, fabNew;
    RecyclerView browse_rv;
    private boolean fabExpanded = false;
    //    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabImport;
    private LinearLayout layoutFabNew;
    //    private LinearLayout layoutFabPhoto;
//    private String TAG = ContactsActivity.class.getSimpleName();
//    private ProgressDialog pDialog;
//    private ListView lv;
    public static ArrayList contactList;
    public AsyncHttpClient client;


    Animation rotate_forward, rotate_Backward, fab_open, fab_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

//        contactList = new ArrayList<>();
        client = new AsyncHttpClient();
        contactList = new ArrayList();
        getUser();

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
                if (fabExpanded == true) {
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
                startActivity(new Intent(ContactsActivity.this, ContactsListActivity.class));
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
    private void closeSubMenusFab() {
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
    private void openSubMenusFab() {
        layoutFabImport.setVisibility(View.VISIBLE);
        layoutFabNew.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
//        fab.setImageResource(R.drawable.ic_close);
        fabImport.startAnimation(fab_open);
        fabNew.setAnimation(fab_open);
        fab.startAnimation(rotate_forward);
        fabExpanded = true;
    }

    protected void getUser(){
        String serverURL = "http://10.0.2.2:8000/api/clientRelationships/contact/?format=json";
        client.get(serverURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int s = 0; s < response.length(); s++) {
                    JSONObject usrObj = null;

                    try {
                        usrObj = response.getJSONObject(s);
//                        String user = usrObj.getString("user");
                        String name = usrObj.getString("name");
                        String email = usrObj.getString("email");
                        String mobile = usrObj.getString("mobile");
                        String designation = usrObj.getString("designation");

                        JSONObject company = usrObj.getJSONObject("company");
                        String companyName = company.getString("name");
//
//                        JSONArray comapany = usrObj.getJSONArray("company");
//                        for (int j = 0; j < comapany.length(); j++) {
//                            JSONObject innerElem = comapany.getJSONObject(j);
//
//                            String user1 = innerElem.getString("name");
////                        String pk1 = innerElem.getString("pk");
//
//
//                            JSONArray address = usrObj.getJSONArray("address");
//                            for (int k = 0; k < address.length(); k++) {
//                                JSONObject a = address.getJSONObject(k);
//
//                                String street = a.getString("street");
//                                String city = a.getString("city");
//                                String state = a.getString("state");
//                                String pincode = a.getString("pincode");
//                                String g = String.valueOf(pincode);
//                                String country = a.getString("country");
//                                String telephone = a.getString("telephone");


                        // tmp hash map for single contact
                        HashMap pk = new HashMap();


                        // adding each child node to HashMap key => value
//                            pk.put("user", user);
                        pk.put("name", name);
                        pk.put("company",companyName);
                        pk.put("mobile", mobile);
                        pk.put("email", email);
                        pk.put("designation", designation);



                        // adding contact to contact list
                        contactList.add(pk);

//                            }

//                        }
                    } catch (org.json.JSONException e) {
                        e.printStackTrace();
                        Log.e("JSONException", "123456");
                    }
                }
                Log.e("caslnasnxnx", ""+contactList.size());
                browse_rv.setLayoutManager(new LinearLayoutManager(ContactsActivity.this));

                BrowseAdapter browseAdapter = new BrowseAdapter(ContactsActivity.this);
                browse_rv.setAdapter(browseAdapter);
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
//    private class GetContacts extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Showing progress dialog
//            pDialog = new ProgressDialog(ContactsActivity.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
//
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            HttpHandler sh = new HttpHandler();
//
//            // Making a request to url and getting response
//            String jsonStr = sh.makeServiceCall(url);
//            String list[] ={"0","1","2","3","4","5","6","7"};
//            Log.e(TAG, "Response from url: " + jsonStr);
//
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObj = new JSONObject(jsonStr);
//                    for (int s = 0; s< list.length; s++){
//
//                    // Getting JSON Array node
//                    JSONArray contacts = jsonObj.getJSONArray(list[s]);
//
//
//                    // looping through All Contacts
//
//                        String user = c.getString("user");
//                        String name = c.getString("name");
//                        String email = c.getString("email");
//                        String mobile = c.getString("mobile");
//                        String designation = c.getString("designation");
//
//                        JSONArray comapany = response.getJSONArray("company");
//
//                        for (int j = 0; j < comapany.length(); j++) {
//                            JSONObject innerElem = comapany.getJSONObject(j);
//
//                            String user1 = innerElem.getString("name");
//                            String pk1 = innerElem.getString("pk");
//
//
//                            JSONArray address = jsonObj.getJSONArray("address");
//                            for (int k = 0; k < address.length(); k++) {
//                                JSONObject a = address.getJSONObject(k);
//
//                                String street = a.getString("street");
//                                String city = a.getString("city");
//                                String state = a.getString("state");
//                                int pincode = a.getInt("pincode");
//                                String g = String.valueOf(pincode);
//                                String country = a.getString("country");
//                                int telephone = a.getInt("telephone");
//                                String t = String.valueOf(telephone);

//
//                                // tmp hash map for single contact
//                                HashMap<String, String> pk = new HashMap<>();
//
//
//                                // adding each child node to HashMap key => value
////                            pk.put("user", user);
//                                pk.put("name", name);
//                                pk.put("Cname",user1);
//                                pk.put("street",street);
//                                pk.put("city",city);
//                                pk.put("state",state);
//                                pk.put("pincode",g);
//                                pk.put("country",country);
//                                pk.put("mobile",mobile);
//                                pk.put("email",email);
//                                pk.put("designation",designation);
//
//
//                                // adding contact to contact list
//                                contactList.add(pk);
//
//
//                            }
//
//                        }
//                    }
//                    }
//                } catch (final JSONException e) {
//                    Log.e(TAG, "Json parsing error: " + e.getMessage());
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(),
//                                    "Json parsing error: " + e.getMessage(),
//                                    Toast.LENGTH_LONG)
//                                    .show();
//                        }
//                    });
//
//                }
//            } else {
//                Log.e(TAG, "Couldn't get json from server.");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(getApplicationContext(),
//                                "Couldn't get json from server. Check LogCat for possible errors!",
//                                Toast.LENGTH_LONG)
//                                .show();
//                    }
//                });
//
//            }
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            super.onPostExecute(result);
//            // Dismiss the progress dialog
////            if (pDialog.isShowing())
////                pDialog.dismiss();
////            ListAdapter adapter = new SimpleAdapter(
////                    MainActivity.this, contactList,
////                    R.layout.list_item, new String[]{"name", "email",
////                    "mobile"}, new int[]{R.id.name,
////                    R.id.email, R.id.mobile});
////
////            lv.setAdapter(adapter);
//
//
//        }
//
//    }


}