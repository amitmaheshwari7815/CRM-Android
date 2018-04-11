package com.woxthebox.draglistview.sample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinancesFragment extends Fragment {
    RecyclerView rv;

    public static ArrayList info;
    public AsyncHttpClient client;

    public FinancesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_finances, container, false);
        rv = v.findViewById(R.id.finances_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        FinancesAdapter financesAdapter = new FinancesAdapter(getContext());
        rv.setAdapter(financesAdapter);


        client = new AsyncHttpClient();
        info = new ArrayList();
        getFinances();


        return v;
    }
    protected void getFinances() {
        String serverURL = "http://10.0.2.2:8000/api/clientRelationships/contract/?format=json";
        client.get(serverURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject Obj = null;
                    try {
                        Obj = response.getJSONObject(i);
                        String contract_pk = Obj.getString("pk");
//                        String user = Obj.getString("user");
                        String name = Obj.getString("name");
                        String created = Obj.getString("created");
                        String updated = Obj.getString("updated");
                        String value = Obj.getString("value");
                        String status = Obj.getString("status");
                        String details = Obj.getString("details");
                        JSONObject data = Obj.getJSONObject("details");
                        String data1 = data.getString("details");
                        String dueDate = Obj.getString("dueDate");



//                      HashMap hashMap = new HashMap();

//                        hashMap.put("pk", company_pk);
//                        hashMap.put("name", name);
//                        hashMap.put("cin", cin);
//                        hashMap.put("tin", tin);
//                        hashMap.put("telephone", telephone);
//                        hashMap.put("about", about);
//                        hashMap.put("doc", doc);
//                        hashMap.put("mobile", mobile);
//                        hashMap.put("web", web);
//                        hashMap.put("street", street);
//                        hashMap.put("city", city);
//                        hashMap.put("state", state);
//                        hashMap.put("pincode", pincode);
//                        hashMap.put("country", country);
//                        info.add(hashMap);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

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
