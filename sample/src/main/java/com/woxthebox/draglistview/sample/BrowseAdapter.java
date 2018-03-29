package com.woxthebox.draglistview.sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Ashish on 3/7/2018.
 */

public class BrowseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private String TAG = BrowseAdapter.class.getSimpleName();
//    private ProgressDialog pDialog;
//    private CardView cardView;
//    private static String serverURL = "http://10.0.2.2:8000/api/clientRelationships/contact/?format=json";
//    private AsyncHttpClient client;
//    ArrayList<HashMap<String, String>> contactList;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    Context context;

    String name,Cname,street,city,state,pincode,country,email,mobile,designation,company;

    public BrowseAdapter(Context context){
        this.context = context;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ContactsActivity.browse_rv.getLayoutManager();
        ContactsActivity.browse_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.browse_contacts_style, parent, false);
            return new MyHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
//            User user = mUsers.get(position);
            MyHolder myHolder = (MyHolder) holder;
            HashMap hm = (HashMap) ContactsActivity.contactList.get(position);
            name  = (String) hm.get("name");
            company = (String) hm.get("company");
//         street  = (String) hm.get("street");
//         city = (String) hm.get("city");
//         state = (String) hm.get("state");
//         pincode = (String) hm.get("pincode");
//         country = (String) hm.get("country");
//         telephone = (String) hm.get("telephone");
            email = (String) hm.get("email");
            mobile = (String) hm.get("mobile");
            designation = (String) hm.get("designation");

//        holder.browseImage.setImageResource(contact_images[position]);
            myHolder.browseName.setText(name);
            myHolder.browseDesignation.setText(designation);
            myHolder.browseCompany.setText(company);
            myHolder.browseMob.setText(mobile);
            myHolder.browseEmail.setText(email);

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

//    @Override
//    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
//        HashMap hm = (HashMap) ContactsActivity.contactList.get(position);
//         name  = (String) hm.get("name");
//         company = (String) hm.get("company");
////         Cname  = (String) hm.get("Cname");
////         street  = (String) hm.get("street");
////         city = (String) hm.get("city");
////         state = (String) hm.get("state");
////         pincode = (String) hm.get("pincode");
////         country = (String) hm.get("country");
////         telephone = (String) hm.get("telephone");
//         email = (String) hm.get("email");
//         mobile = (String) hm.get("mobile");
//         designation = (String) hm.get("designation");
//
////        holder.browseImage.setImageResource(contact_images[position]);
//        holder.browseName.setText(name);
//        holder.browseDesignation.setText(designation);
//        holder.browseCompany.setText(company);
//        holder.browseMob.setText(mobile);
//        holder.browseEmail.setText(email);
//    }

    @Override
    public int getItemCount() {
//        Log.e("caslnasnxnx", "sdffs"+ContactsActivity.contactList.size());
        return  ContactsActivity.contactList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView browseImage, editProfile, viewDetails;
        TextView browseName,browseDesignation, browseCompany, browseMob, browseEmail;
//        ProgressBar  progressBar  = (ProgressBar) itemView.findViewById(R.id.progressBar1);

        public MyHolder(View itemView) {
            super(itemView);
            browseImage = itemView.findViewById(R.id.contacts_image_browse);
            browseName = itemView.findViewById(R.id.contacts_name_browse);
            browseDesignation = itemView.findViewById(R.id.contacts_designation_browse);
            browseCompany = itemView.findViewById(R.id.contacts_company_browse);
            browseMob = itemView.findViewById(R.id.contacts_no_browse);
            editProfile = itemView.findViewById(R.id.edit_profile);
            browseEmail = itemView.findViewById(R.id.contacts_email_browse);
            viewDetails = itemView.findViewById(R.id.view_details);

            editProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EditContactActivity.class);
//                    intent.putExtra("image", contact_images[getLayoutPosition()]);
                    intent.putExtra("name", name);
                    intent.putExtra("designation", designation);
                    intent.putExtra("company", company);
                    intent.putExtra("cno", mobile);
                    intent.putExtra("email", email);
                    context.startActivity(intent);
                }
            });

            viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ViewDetailsActivity.class);
//                    intent.putExtra("image", contact_images[getLayoutPosition()]);
                    intent.putExtra("name", name);
                    intent.putExtra("designation", designation);
                    intent.putExtra("company", company);
                    intent.putExtra("cno", mobile);
                    intent.putExtra("email", email);
                    context.startActivity(intent);
                }
            });
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }

    public void setLoaded() {
        isLoading = false;
    }
}



