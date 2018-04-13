package com.woxthebox.draglistview.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;


public class ContactsActivity extends FragmentActivity {

    FloatingActionButton fab, fabImport, fabNew;
    public static RecyclerView browse_rv;
    BrowseAdapter browseAdapter;
    private boolean fabExpanded = false;
    //    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabImport;
    private LinearLayout layoutFabNew;
    //    private LinearLayout layoutFabPhoto;
//    private String TAG = ContactsActivity.class.getSimpleName();
//    private ProgressBar pDialog;
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
        browse_rv = findViewById(R.id.browse_recyclerView);

        getUser();

        fab = findViewById(R.id.fab);
        fabImport = findViewById(R.id.fab_import);
        fabNew = findViewById(R.id.fab_new);
//        pDialog = findViewById(R.id.progressBar1);

        layoutFabImport = (LinearLayout) this.findViewById(R.id.layoutFabImport);
        layoutFabNew = (LinearLayout) this.findViewById(R.id.layoutFabNew);
//        layoutFabPhoto = (LinearLayout) this.findViewById(R.id.layoutFabPhoto);

        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotate_Backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        String serverURL = "http://192.168.1.105:8000/api/clientRelationships/contact/?format=json";//192.168.43.87
        client.get(serverURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
//                for (int i =0; i < 10 ;i++) {
//                    pDialog.setVisibility(View.VISIBLE);

                final int res = response.length();
//                int a=10;
//                if (0<res)
                int s;
                for (s = 0; s < 9; s++) {

                        JSONObject usrObj = null;
                        try {
                            usrObj = response.getJSONObject(s);
//                        String user = usrObj.getString("user");
                            String name = usrObj.getString("name");
                            String email = usrObj.getString("email");
                            String emailSecondary = usrObj.getString("emailSecondary");
                            String mobile = usrObj.getString("mobile");
                            String mobileSecondary = usrObj.getString("mobileSecondary");
                            String designation = usrObj.getString("designation");
                            String notes = usrObj.getString("notes");
                            String linkedin = usrObj.getString("linkedin");
                            String facebook = usrObj.getString("facebook");
                            String dp = usrObj.getString("dp");

                            boolean gender = usrObj.getBoolean("male");

                            JSONObject company = usrObj.getJSONObject("company");
                            String companyName = company.getString("name");
                            String cin = company.getString("cin");
                            String tin = company.getString("tin");
                            String telephone = company.getString("telephone");
                            String cMobile = company.getString("mobile");
                            String about = company.getString("about");
                            String web = company.getString("web");
//                            String doc = company.getString("doc");

                            JSONObject a = company.getJSONObject("address");
//
                                String street = a.getString("street");
                                String city = a.getString("city");
                                String state = a.getString("state");
                                String pincode = a.getString("pincode");
                                String country = a.getString("country");

                            // tmp hash map for single contact
                            final HashMap hm = new HashMap();

                            // adding each child node to HashMap key => value
//                            pk.put("user", user);
                            hm.put("name", name);
                            hm.put("company", companyName);
                            hm.put("mobile", mobile);
                            hm.put("mobileSecondary", mobileSecondary);
                            hm.put("email", email);
                            hm.put("emailSecondary", emailSecondary);
                            hm.put("designation", designation);
                            hm.put("notes", notes);
                            hm.put("linkedin", linkedin);
                            hm.put("facebook", facebook);
                            hm.put("dp",dp);
                            hm.put("gender",gender);
                            hm.put("cin",cin);
                            hm.put("tin",tin);
                            hm.put("mob",cMobile);
                            hm.put("tel",telephone);
                            hm.put("about",about);
                            hm.put("web",web);
                            hm.put("street",street);
                            hm.put("city",city);
                            hm.put("state",state);
                            hm.put("pincode",pincode);
                            hm.put("country",country);
                            contactList.add(hm);
//                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
                Log.e("JSONExceptionresponse", ""+res);


                browse_rv.setLayoutManager(new LinearLayoutManager(ContactsActivity.this));
                browseAdapter = new BrowseAdapter(ContactsActivity.this);
                browse_rv.setAdapter(browseAdapter);

                browseAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {


                        Log.e("haint", "Load More");
                        contactList.add(null);
                        browseAdapter.notifyItemInserted(contactList.size() - 1);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("haint", "Load More 2");

                                //Remove loading item
                                contactList.remove(contactList.size() - 1);
                                browseAdapter.notifyItemRemoved(contactList.size());

                                //Load data
                                int index = contactList.size();
                                int in = index + 9;
//                                if (in < res) {
                                    for (int i = index; i < in; i++) {
//                                pDialog.setVisibility(View.GONE);
                                        JSONObject usrObj = null;
                                        try {
                                            usrObj = response.getJSONObject(i);
//                        String user = usrObj.getString("user");
                                            String name = usrObj.getString("name");
                                            String email = usrObj.getString("email");
                                            String emailSecondary = usrObj.getString("emailSecondary");
                                            String mobile = usrObj.getString("mobile");
                                            String mobileSecondary = usrObj.getString("mobileSecondary");
                                            String designation = usrObj.getString("designation");
                                            String notes = usrObj.getString("notes");
                                            String linkedin = usrObj.getString("linkedin");
                                            String facebook = usrObj.getString("facebook");
                                            String dp = usrObj.getString("dp");
                                            boolean gender = usrObj.getBoolean("male");

                                            JSONObject company = usrObj.getJSONObject("company");
                                            String companyName = company.getString("name");
                                            String cin = company.getString("cin");
                                            String tin = company.getString("tin");
                                            String telephone = company.getString("telephone");
                                            String cMobile = company.getString("mobile");
                                            String about = company.getString("about");
                                            String web = company.getString("web");
//                            String doc = company.getString("doc");

                                            JSONObject a = company.getJSONObject("address");
//
                                            String street = a.getString("street");
                                            String city = a.getString("city");
                                            String state = a.getString("state");
                                            String pincode = a.getString("pincode");
                                            String country = a.getString("country");
                                            // tmp hash map for single contact
                                            HashMap hm = new HashMap();


                                            // adding each child node to HashMap key => value
//                            pk.put("user", user);
                                            hm.put("name", name);
                                            hm.put("company", companyName);
                                            hm.put("mobile", mobile);
                                            hm.put("mobileSecondary", mobileSecondary);
                                            hm.put("email", email);
                                            hm.put("emailSecondary", emailSecondary);
                                            hm.put("designation", designation);
                                            hm.put("notes", notes);
                                            hm.put("linkedin", linkedin);
                                            hm.put("facebook", facebook);
                                            hm.put("dp",dp);
                                            hm.put("gender",gender);
                                            hm.put("cin",cin);
                                            hm.put("tin",tin);
                                            hm.put("mob",cMobile);
                                            hm.put("tel",telephone);
                                            hm.put("about",about);
                                            hm.put("web",web);
                                            hm.put("street",street);
                                            hm.put("city",city);
                                            hm.put("state",state);
                                            hm.put("pincode",pincode);
                                            hm.put("country",country);


                                            // adding contact to contact list
                                            contactList.add(hm);

//                            }

//                        }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Log.e("JSONException", "123456");
                                        }
                                    }
                                    browseAdapter.notifyDataSetChanged();
                                    browseAdapter.setLoaded();
//                                }else
//                                    Toast.makeText(ContactsActivity.this, "JSONException", Toast.LENGTH_SHORT).show();
                            }
                        }, 2000);
//
                    }
                });

                Log.e("caslnasnxnx", ""+contactList.size());

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