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

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.MyHolder> {

//    private String TAG = BrowseAdapter.class.getSimpleName();
//    private ProgressDialog pDialog;
//    private CardView cardView;
//    private static String serverURL = "http://10.0.2.2:8000/api/clientRelationships/contact/?format=json";
//    private AsyncHttpClient client;
//    ArrayList<HashMap<String, String>> contactList;
//    private final int VIEW_TYPE_ITEM = 0;
//    private final int VIEW_TYPE_LOADING = 1;




//    public static int contact_images[] = {R.drawable.male, R.drawable.woman, R.drawable.male, R.drawable.woman, R.drawable.male,
//            R.drawable.woman,R.drawable.male, R.drawable.woman};
//    public static String contact_names[] = {"Samuel D. Pollock", "Rita Stith", "Ronald Allen", "Veronica Woods", "Robert Y. Griffin","Purity Supreme", "P. Madhawa", "Shweta Stith",
//            "Robert H. Peebles","Lorie T. Morales"};
//    public static String contact_companies[] = {"First Choice Yard Help", "Foxmoor", "First Choice Yard Help ", "Foxmoor", "First Choice Yard Help ","Purity Supreme", "Pomeroy's",
//            "First Choice Yard Help ","Purity Supreme","Pomeroy's"};
//    public static String contact_designations[] = {"Information systems manager", "Sales Manager", "General Manager", "Sales Manager", "Information systems manager","Sales Manager",
//            "Sales executive","Sales Manager","Sales executive","Sales Manager"};
//    public static String contact_cnos[] = {"7854211505", "8542120007", "8542450070", "854214512", "8542191345","95854218454", "8458542121","788542145","7215485421","8285421145"};
//    public static String contact_emails[] = {"SamuelDPollock@teleworm.us", "RitaOStith@jourrapide.com", "RonaldSAllen@jourrapide.com", "VeronicaJWoods@armyspy.com", "JamesMPacheco@teleworm.us",
//            "PPMadhawa@jourrapide.com", "RobertYGriffin@teleworm.us","ShwetaOStith@jourrapide.com","RobertHPeebles@armyspy.com","LorieTMorales@jourrapide.com"};

    Context context;

    String name,Cname,street,city,state,pincode,country,email,mobile,designation,company;

    public BrowseAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.browse_contacts_style, parent, false);


        BrowseAdapter.MyHolder myHolder = new BrowseAdapter.MyHolder(v);
        return myHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        HashMap hm = (HashMap) ContactsActivity.contactList.get(position);
         name  = (String) hm.get("name");
         company = (String) hm.get("company");
//         Cname  = (String) hm.get("Cname");
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
        holder.browseName.setText(name);
        holder.browseDesignation.setText(designation);
        holder.browseCompany.setText(company);
        holder.browseMob.setText(mobile);
        holder.browseEmail.setText(email);
    }

    @Override
    public int getItemCount() {
        Log.e("caslnasnxnx", "sdffs"+ContactsActivity.contactList.size());
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


}



