package com.woxthebox.draglistview.sample.opportunities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.woxthebox.draglistview.sample.MainActivity;
import com.woxthebox.draglistview.sample.R;
import com.woxthebox.draglistview.sample.ServerUrl;
import com.woxthebox.draglistview.sample.app.AppController;
import com.woxthebox.draglistview.sample.contacts.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OppTimelineFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerViewTimeline;
    OppTimelineAdapter oppTimelineAdapter;
    ServerUrl serverUrl;

    private List<FeedItem> feedItems;
    private String URL_FEED = "https://api.androidhive.info/feed/feed.json";
    ArrayList<String> companiesList;
    public AsyncHttpClient client;

    public OppTimelineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_opp_timeline, container, false);

//        getContentValue();
        serverUrl = new ServerUrl();
        feedItems = new ArrayList<FeedItem>();
        recyclerViewTimeline = v.findViewById(R.id.timeline_recycler);
        recyclerViewTimeline.setLayoutManager(new LinearLayoutManager(getActivity()));
        oppTimelineAdapter = new OppTimelineAdapter(getActivity(), feedItems);
        recyclerViewTimeline.setAdapter(oppTimelineAdapter);


        // We first check for cached request
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(URL_FEED);
        if (entry != null) {
            // fetch the data from cache
            try {
                String data = new String(entry.data, "UTF-8");
                try {
                    parseJsonFeed(new JSONObject(data));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        } else {
            // making fresh volley request and getting json
            JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                    URL_FEED, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    VolleyLog.d(TAG, "Response: " + response.toString());
                    if (response != null) {
                        parseJsonFeed(response);
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                }
            });

            // Adding request to volley request queue
            AppController.getInstance().addToRequestQueue(jsonReq);
        }

        return v;
    }

//    protected void getContentValue(){
//        String serverURL = serverUrl.url;
//        client.get(serverURL+"api/clientRelationships/deal/?format=json",new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
////                super.onSuccess(statusCode, headers, response);
//                for(int i=0; i<response.length(); i++){
//                    try {
//                        JSONObject json = response.getJSONObject(i);
//                        String time = json.getString("created");
//                        String status = json.getString("data");
//                        String doc = json.getString("doc");
////                        String time = json.getString("created");
//
////                        companiesList.add(companyName);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                System.out.println("finished EditContact");
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                System.out.println("finished failed EditContact");
//            }
//        });
//    }

    /**
     * Parsing json response and passing the data to feed view list adapter
     * */
    private void parseJsonFeed(JSONObject response) {
        try {
            JSONArray feedArray = response.getJSONArray("feed");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject feedObj = (JSONObject) feedArray.get(i);

                FeedItem item = new FeedItem();
                item.setId(feedObj.getInt("id"));
                item.setName(feedObj.getString("name"));

                // Image might be null sometimes
                String image = feedObj.isNull("image") ? null : feedObj
                        .getString("image");
                item.setImge(image);
                item.setStatus(feedObj.getString("status"));
                item.setProfilePic(feedObj.getString("profilePic"));
                item.setTimeStamp(feedObj.getString("timeStamp"));

                // url might be null sometimes
                String feedUrl = feedObj.isNull("url") ? null : feedObj
                        .getString("url");
                item.setUrl(feedUrl);

                feedItems.add(item);
                Log.e("feedItems", ""+feedItems.size());
            }

            // notify data changes to list adapater
//            timelineAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
