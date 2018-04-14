package com.woxthebox.draglistview.sample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.woxthebox.draglistview.sample.app.AppController;

import java.util.HashMap;

/**
 * Created by Ashish on 3/7/2018.
 */

public class BrowseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int pos ;
    private int lastVisibleItem, totalItemCount;

    Context context;

    String name,street,city,state,pincode,country,email,mobile,designation,dp,company,telephone, cMobile, cin, tin, about, web;
    boolean gender;

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

    @Override
    public int getItemViewType(int position) {
        return ContactsActivity.contactList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_browse_adapter, parent, false);
            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
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
            String name,street,city,state,pincode,country,email,mobile,designation,company, dp, telephone, cMobile, cin, tin, about, web;
            boolean gender;
            MyHolder myHolder = (MyHolder) holder;
                    HashMap hm = (HashMap) ContactsActivity.contactList.get(position);
                    name = (String) hm.get("name");
                    company = (String) hm.get("company");
                    email = (String) hm.get("email");
                    mobile = (String) hm.get("mobile");
                    designation = (String) hm.get("designation");
                    dp = (String) hm.get("dp");
                    street = (String) hm.get("street");
                    city = (String) hm.get("city");
                    state = (String) hm.get("state");
                    pincode = (String) hm.get("pincode");
                    country = (String) hm.get("country");
                    telephone = (String) hm.get("tel");
                    gender = (Boolean) hm.get("gender");
                    cin = (String) hm.get("cin");
                    tin = (String) hm.get("tin");
                    cMobile = (String) hm.get("mob");
                    about = (String) hm.get("about");
                    web = (String) hm.get("web");

                    if (dp.equals("null")) {
                        if (gender)
                            myHolder.browseImage.setImageResource(R.drawable.male);
                        else
                            myHolder.browseImage.setImageResource(R.drawable.female);
                    } else {
                        myHolder.browseImage.setImageUrl(dp, imageLoader);
                    }
                    myHolder.browseName.setText(name);
                    myHolder.browseDesignation.setText(designation);
                    myHolder.browseCompany.setText(company);
                    myHolder.browseMob.setText(mobile);
                    myHolder.browseEmail.setText(email);

                    final String finalMobile = mobile;
                    myHolder.browseMob.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Intent.ACTION_DIAL);
                            i.setData(Uri.parse("tel:" + finalMobile));
                            context.startActivity(i);
                        }
                    });

                    final String finalEmail = email;
                    myHolder.browseEmail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                            emailIntent.setData(Uri.parse("mailto:" + finalEmail));
                            context.startActivity(emailIntent);
                        }
                    });

        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return  ContactsActivity.contactList == null ? 0 : ContactsActivity.contactList.size();//
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        NetworkImageView browseImage;
        ImageView editProfile, viewDetails;
        TextView browseName,browseDesignation, browseCompany, browseMob, browseEmail;

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
                    pos = getLayoutPosition();
                    hashmapMethod();
                    Intent intent = new Intent(context, EditContactActivity.class);
                    intent.putExtra("image",dp);
                    intent.putExtra("name", name);
                    intent.putExtra("designation", designation);
                    intent.putExtra("company", company);
                    intent.putExtra("cno", mobile);
                    intent.putExtra("email", email);
                    intent.putExtra("gender",gender);
                    intent.putExtra("cin",cin);
                    intent.putExtra("tin",tin);
                    intent.putExtra("mob",cMobile);
                    intent.putExtra("tel",telephone);
                    intent.putExtra("about",about);
                    intent.putExtra("web",web);
                    intent.putExtra("street",street);
                    intent.putExtra("city",city);
                    intent.putExtra("state",state);
                    intent.putExtra("pincode", pincode);
                    intent.putExtra("country",country);
                    context.startActivity(intent);
                }
            });

            viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos = getLayoutPosition();
                    hashmapMethod();
                    Toast.makeText(context, ""+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ViewDetailsActivity.class);
                    intent.putExtra("image", dp);
                    intent.putExtra("name", name);
                    intent.putExtra("designation", designation);
                    intent.putExtra("company", company);
                    intent.putExtra("cno", mobile);
                    intent.putExtra("email", email);
                    intent.putExtra("gender",gender);
                    intent.putExtra("cin",cin);
                    intent.putExtra("tin",tin);
                    intent.putExtra("mob",cMobile);
                    intent.putExtra("tel",telephone);
                    intent.putExtra("about",about);
                    intent.putExtra("web",web);
                    intent.putExtra("street",street);
                    intent.putExtra("city",city);
                    intent.putExtra("state",state);
                    intent.putExtra("pincode", pincode);
                    intent.putExtra("country",country);
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

    void hashmapMethod(){
        HashMap hm = (HashMap) ContactsActivity.contactList.get(pos);
        name = (String) hm.get("name");
        company = (String) hm.get("company");
        email = (String) hm.get("email");
        mobile = (String) hm.get("mobile");
        designation = (String) hm.get("designation");
        dp = (String) hm.get("dp");
        street = (String) hm.get("street");
        city = (String) hm.get("city");
        state = (String) hm.get("state");
        pincode = (String) hm.get("pincode");
        country = (String) hm.get("country");
        telephone = (String) hm.get("tel");
        gender = (Boolean)hm.get("gender");
        cin = (String)hm.get("cin");
        tin = (String) hm.get("tin");
        cMobile = (String)hm.get("mob");
        about = (String)hm.get("about");
        web = (String)hm.get("web");
    }
}



