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

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.woxthebox.draglistview.sample.app.AppController;

import java.util.List;

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
    List<Contact> contactList;

    String name,street,city,state,pincode,country,email,mobile,designation,dp,company,telephone, cMobile, cin, tin, about, web;
    boolean gender;

    public BrowseAdapter(Context context, List<Contact> contactList){
        this.context = context;
        this.contactList = contactList;
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
            final Contact c = contactList.get(position);
            if (c.dp.equals("null")) {
                if (c.male)
                    myHolder.browseImage.setImageResource(R.drawable.male);
                else
                    myHolder.browseImage.setImageResource(R.drawable.female);
            } else {
                myHolder.browseImage.setImageUrl(c.dp, imageLoader);
            }
            myHolder.browseName.setText(c.getName());
            myHolder.browseDesignation.setText(c.getDesignation());
            myHolder.browseCompany.setText(c.getCompanyName());
            myHolder.browseMob.setText(c.getMobile());
            myHolder.browseEmail.setText(c.getEmail());

            final String finalMobile = c.mobile;
            myHolder.browseMob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:" + finalMobile));
                    context.startActivity(i);
                }
            });

            final String finalEmail = c.email;
            myHolder.browseEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + finalEmail));
                    context.startActivity(emailIntent);
                }
            });
            myHolder.editProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    pos = getLayoutPosition();
//                    hashmapMethod();
                    Intent intent = new Intent(context, EditContactActivity.class);
                    intent.putExtra("image",c.getDp());
                    intent.putExtra("name", c.getName());
                    intent.putExtra("designation",c.getDesignation());
                    intent.putExtra("company", c.getCompanyName());
                    intent.putExtra("cno", c.getCompanyMobile());
                    intent.putExtra("email",c.getEmail());
                    intent.putExtra("gender",c.getMale());
                    intent.putExtra("cin",c.getCin());
                    intent.putExtra("tin",c.getTin());
                    intent.putExtra("mob",c.getMobile());
                    intent.putExtra("tel",c.getTelephone());
                    intent.putExtra("about",c.getAbout());
                    intent.putExtra("web",c.getWeb());
                    intent.putExtra("street",c.getStreet());
                    intent.putExtra("city",c.getCity());
                    intent.putExtra("state",c.getState());
                    intent.putExtra("pincode",c.getPincode());
                    intent.putExtra("country",c.getCountry());
                    context.startActivity(intent);
                }
            });

            myHolder.viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    pos = getLayoutPosition();
//                    hashmapMethod();
//                    Toast.makeText(context, ""+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ViewDetailsActivity.class);
                    intent.putExtra("image",c.getDp());
                    intent.putExtra("name", c.getName());
                    intent.putExtra("designation",c.getDesignation());
                    intent.putExtra("company", c.getCompanyName());
                    intent.putExtra("cno", c.getCompanyMobile());
                    intent.putExtra("email",c.getEmail());
                    intent.putExtra("gender",c.getMale());
                    intent.putExtra("cin",c.getCin());
                    intent.putExtra("tin",c.getTin());
                    intent.putExtra("mob",c.getMobile());
                    intent.putExtra("tel",c.getTelephone());
                    intent.putExtra("about",c.getAbout());
                    intent.putExtra("web",c.getWeb());
                    intent.putExtra("street",c.getStreet());
                    intent.putExtra("city",c.getCity());
                    intent.putExtra("state",c.getState());
                    intent.putExtra("pincode",c.getPincode());
                    intent.putExtra("country",c.getCountry());
                    context.startActivity(intent);
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

//    void hashmapMethod(){
//        HashMap hm = (HashMap) ContactsActivity.contactList.get(pos);
//        name = (String) hm.get("name");
//        company = (String) hm.get("company");
//        email = (String) hm.get("email");
//        mobile = (String) hm.get("mobile");
//        designation = (String) hm.get("designation");
//        dp = (String) hm.get("dp");
//        street = (String) hm.get("street");
//        city = (String) hm.get("city");
//        state = (String) hm.get("state");
//        pincode = (String) hm.get("pincode");
//        country = (String) hm.get("country");
//        telephone = (String) hm.get("tel");
//        gender = (Boolean)hm.get("gender");
//        cin = (String)hm.get("cin");
//        tin = (String) hm.get("tin");
//        cMobile = (String)hm.get("mob");
//        about = (String)hm.get("about");
//        web = (String)hm.get("web");
//    }
}



