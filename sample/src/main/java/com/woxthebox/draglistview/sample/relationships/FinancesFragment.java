package com.woxthebox.draglistview.sample.relationships;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.woxthebox.draglistview.sample.R;
import com.woxthebox.draglistview.sample.ServerUrl;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class FinancesFragment extends Fragment {
    RecyclerView rv;
    Context context;
    public static ArrayList<Contract> finance;
    public AsyncHttpClient client;
    ServerUrl serverUrl;
    public Integer contractPk;

    Activity activity;
    @SuppressLint("ValidFragment")
    public FinancesFragment(Context context){
        this.context = context;
    }

    public FinancesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contractPk = getArguments().getInt("contracts");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_finances, container, false);

        rv = v.findViewById(R.id.finances_rv);
        serverUrl = new ServerUrl();
        client = new AsyncHttpClient();
        finance = new ArrayList<>();


        getFinances();


        return v;
    }
    protected void getFinances() {
        String serverURL = serverUrl.url;
        client.get(serverURL+ "api/clientRelationships/contract/"+contractPk+"/?format=json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, final JSONObject response) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject Obj = null;

                    Obj = response;
                    Contract r = new Contract(Obj);

//
                    finance.add(r);


                    rv.setLayoutManager(new LinearLayoutManager(getContext()));
                    FinancesAdapter financesAdapter = new FinancesAdapter(getContext(),finance);
                    rv.setAdapter(financesAdapter);
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
