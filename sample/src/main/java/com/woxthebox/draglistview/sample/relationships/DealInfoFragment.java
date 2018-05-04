package com.woxthebox.draglistview.sample.relationships;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.woxthebox.draglistview.sample.R;
import com.woxthebox.draglistview.sample.ServerUrl;
import com.woxthebox.draglistview.sample.contacts.Contact;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class DealInfoFragment extends Fragment {
    public static String comanyName,infoAddress,infoWeb,ciN,tiN,infoMobile,infoTele,About;
    TextView companyname,address,web,cin,tin,mobile,telephone,about;
    Context context;
    ServerUrl serverUrl;
     private Deal d;
     private Contact c;
     private Service s;
    public static ArrayList info;
    public AsyncHttpClient client;
    public String pk;


    @SuppressLint("ValidFragment")
    public DealInfoFragment(Context context){
        this.context = context;
    }

    public DealInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pk = getArguments().getString("pk");
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
        serverUrl = new ServerUrl();
        client = new AsyncHttpClient();
        info = new ArrayList();


        /*pk = getArguments().getString("pk");*/

        getinfo();

//        HashMap hm = (HashMap) info.get(ActiveDealsActivity.pos);
//        infoWeb = (String)hm.get("web");
//        ciN = (String)hm.get("cin");
//        tiN = (String)hm.get("tin");
//        infoMobile = (String)hm.get("mobile");
//        infoTele = (String)hm.get("telephone");


        return view;

    }




    protected void getinfo() {
        String serverURL = serverUrl.url;

        client.get(serverURL+"api/ERP/service/"+pk+"/?format=json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                JSONObject Obj = null;

                    Obj  = response;
                    s = new Service(Obj);

                    info.add(s);
                    setData();

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

    private  void setData(){
        companyname.setText(s.getCompanyName());
        String address1 = s.getStreet()+" \n"+s.getCity()+" \n"+s.getState()+" \n"+s.getPincode()+" \n"+s.getCountry();
        address.setText(address1);
        web.setText(s.getWeb());
        cin.setText(s.getCin());
        tin.setText(s.getTin());
        mobile.setText(s.getMobile());
        telephone.setText(s.getTelepone());
        about.setText(s.getAbout());

    }

}
