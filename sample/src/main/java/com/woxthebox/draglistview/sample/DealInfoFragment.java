package com.woxthebox.draglistview.sample;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;
import io.reactivex.annotations.NonNull;


/**
 * A simple {@link Fragment} subclass.
 */
public class DealInfoFragment extends Fragment {
    public static String comanyName,infoAddress,infoWeb,ciN,tiN,infoMobile,infoTele,About;
    TextView companyname,address,web,cin,tin,mobile,telephone,about;
    Context context;


    public static ArrayList info;
    public AsyncHttpClient client;


    @SuppressLint("ValidFragment")
    public DealInfoFragment(Context context){
        this.context = context;
    }

    public DealInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_deal_info, container, false);

        companyname = view.findViewById(R.id.info_comapny_name);
        address = view.findViewById(R.id.info_address);
        web = view.findViewById(R.id.info_web);
        cin = view.findViewById(R.id.cin);
        tin = view.findViewById(R.id.tin);
        mobile = view.findViewById(R.id.info_mobile);
        telephone = view.findViewById(R.id.info_tele);
        about = view.findViewById(R.id.about);

        client = new AsyncHttpClient();
        info = new ArrayList();
        getinfo();

        companyname.setText(ActiveDealsActivity.company);
        String address1 = ActiveDealsActivity.street+" \n"+ActiveDealsActivity.city+" \n"+ActiveDealsActivity.astate+" \n"+ActiveDealsActivity.pincode+" \n"+ActiveDealsActivity.country;
        address.setText(address1);
        web.setText(ActiveDealsDetailsActivity.web);
        cin.setText(ActiveDealsDetailsActivity.cin);
        tin.setText(ActiveDealsDetailsActivity.tin);
        mobile.setText(ActiveDealsDetailsActivity.mobile);
        telephone.setText(ActiveDealsDetailsActivity.telephone);
        about.setText(ActiveDealsDetailsActivity.about);

//        HashMap hm = (HashMap) info.get(ActiveDealsActivity.pos);
//        infoWeb = (String)hm.get("web");
//        ciN = (String)hm.get("cin");
//        tiN = (String)hm.get("tin");
//        infoMobile = (String)hm.get("mobile");
//        infoTele = (String)hm.get("telephone");


        return view;

    }

    protected void getinfo() {
        String n_pk = ActiveDealsActivity.pkc;
        String serverURL = "http://10.0.2.2:8000/api/ERP/service/"+n_pk+"/?format=json";
        client.get(serverURL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
                    JSONObject Obj = null;
                    try {
                        Obj = response.getJSONObject(0);
                        String company_pk = Obj.getString("pk");
                        String name = Obj.getString("name");
                        String logo = Obj.getString("logo");
                        String cin1 = Obj.getString("cin");
                        cin.setText(cin1);
                        String tin1 = Obj.getString("tin");
                        tin.setText(tin1);
                        String mobile1 = Obj.getString("mobile");
                        mobile.setText(mobile1);
                        String telephone1 = Obj.getString("telephone");
                        telephone.setText(telephone1);
                        String about1 = Obj.getString("about");
                        about.setText(about1);
                        String doc = Obj.getString("doc");
                        String web1 = Obj.getString("web");
                        web.setText(web1);

                        JSONObject address = Obj.getJSONObject("address");

                        String pk1 = address.getString("pk");
                        String street = address.getString("street");
                        String city = address.getString("city");
                        String state = address.getString("state");
                        String pincode = address.getString("pincode");
                        String lat = address.getString("lat");
                        String lon = address.getString("lon");
                        String country = address.getString("country");

//                        HashMap hashMap = new HashMap();

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
//                }
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
