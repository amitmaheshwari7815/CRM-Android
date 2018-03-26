package com.woxthebox.draglistview.sample;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ashish on 3/7/2018.
 */

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.MyHolder> {

    public static int contact_images[] = {R.drawable.male, R.drawable.woman, R.drawable.male, R.drawable.woman, R.drawable.male,
            R.drawable.woman,R.drawable.male, R.drawable.woman, R.drawable.male, R.drawable.woman};
    public static String contact_names[] = {"Samuel D. Pollock", "Rita Stith", "Ronald Allen", "Veronica Woods", "Robert Y. Griffin","Purity Supreme", "P. Madhawa", "Shweta Stith",
            "Robert H. Peebles","Lorie T. Morales"};
    public static String contact_companies[] = {"First Choice Yard Help", "Foxmoor", "First Choice Yard Help ", "Foxmoor", "First Choice Yard Help ","Purity Supreme", "Pomeroy's",
            "First Choice Yard Help ","Purity Supreme","Pomeroy's"};
    public static String contact_designations[] = {"Information systems manager", "Sales Manager", "General Manager", "Sales Manager", "Information systems manager","Sales Manager",
            "Sales executive","Sales Manager","Sales executive","Sales Manager"};
    public static String contact_cnos[] = {"7854211505", "8542120007", "8542450070", "854214512", "8542191345","95854218454", "8458542121","788542145","7215485421","8285421145"};
    public static String contact_emails[] = {"SamuelDPollock@teleworm.us", "RitaOStith@jourrapide.com", "RonaldSAllen@jourrapide.com", "VeronicaJWoods@armyspy.com", "JamesMPacheco@teleworm.us",
            "PPMadhawa@jourrapide.com", "RobertYGriffin@teleworm.us","ShwetaOStith@jourrapide.com","RobertHPeebles@armyspy.com","LorieTMorales@jourrapide.com"};

    Context context;

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
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.browseImage.setImageResource(contact_images[position]);
        holder.browseName.setText(contact_names[position]);
        holder.browseDesignation.setText(contact_designations[position]);
        holder.browseCompany.setText(contact_companies[position]);
        holder.browseMob.setText(contact_cnos[position]);
        holder.browseEmail.setText(contact_emails[position]);
    }

    @Override
    public int getItemCount() {
        return contact_names.length;
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView browseImage, editProfile, viewDetails;
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
                    Intent intent = new Intent(context, EditContactActivity.class);
                    intent.putExtra("image", contact_images[getLayoutPosition()]);
                    intent.putExtra("name", contact_names[getLayoutPosition()]);
                    intent.putExtra("designation", contact_designations[getLayoutPosition()]);
                    intent.putExtra("company", contact_companies[getLayoutPosition()]);
                    intent.putExtra("cno", contact_cnos[getLayoutPosition()]);
                    intent.putExtra("email", contact_emails[getLayoutPosition()]);
                    context.startActivity(intent);
                }
            });

            viewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, ViewDetailsActivity.class);
                    intent.putExtra("image", contact_images[getLayoutPosition()]);
                    intent.putExtra("name", contact_names[getLayoutPosition()]);
                    intent.putExtra("designation", contact_designations[getLayoutPosition()]);
                    intent.putExtra("company", contact_companies[getLayoutPosition()]);
                    intent.putExtra("cno", contact_cnos[getLayoutPosition()]);
                    intent.putExtra("email", contact_emails[getLayoutPosition()]);
                    context.startActivity(intent);
                }
            });
        }
    }
}

