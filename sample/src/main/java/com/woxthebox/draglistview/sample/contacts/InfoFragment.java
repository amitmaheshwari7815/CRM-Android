package com.woxthebox.draglistview.sample.contacts;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.woxthebox.draglistview.sample.R;
import com.woxthebox.draglistview.sample.ServerUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    ImageView searchLocImage;
    TextView searchLocTv, infoCompany,infoNetwork;
    public static List<ContacLite> contactLiteList;
    public AsyncHttpClient client;
    ServerUrl serverUrl;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        serverUrl = new ServerUrl();
        contactLiteList = new ArrayList<>();
        client = new AsyncHttpClient();
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        searchLocImage = v.findViewById(R.id.info_iv_address);
        infoCompany = v.findViewById(R.id.info_company);
        searchLocTv = v.findViewById(R.id.info_tv_address);
        infoNetwork = v.findViewById(R.id.info_frnd_name);

        infoCompany.setText(""+ ViewDetailsActivity.company);

        String address = ViewDetailsActivity.street+" "+ ViewDetailsActivity.city+" "+ ViewDetailsActivity.state+" "+ ViewDetailsActivity.pincode+" "+ ViewDetailsActivity.country;
        searchLocTv.setText(address);

        clickMethods();
        getUser();

        return v;
    }

    public void clickMethods(){

        final String loc = searchLocTv.getText().toString().trim();

        searchLocImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+loc);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        searchLocTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+loc);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }
    protected void getUser(){
        String serverURL = serverUrl.url;
        client.get(serverURL+"api/clientRelationships/contactLite/?company="+ViewDetailsActivity.cpk+"",new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject Obj = null;
                    try {
                        Obj = response.getJSONObject(i);

                        ContacLite contacLite = new ContacLite(Obj);

                        contactLiteList.add(contacLite);
//                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(contactLiteList.size()>0) {
                    ContacLite con = contactLiteList.get(1);
                    infoNetwork.setText(con.getName());
                } else if
                    (contactLiteList.size()==0){
                    infoNetwork.setText("");

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
