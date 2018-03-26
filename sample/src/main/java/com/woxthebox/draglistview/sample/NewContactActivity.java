package com.woxthebox.draglistview.sample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class NewContactActivity extends Activity {

    LinearLayout showAdvanceDetails;
    EditText newFullName, newEmail, newMobNo, newEmailDuplicate, newMobNoDuplicate, newDesignation, newNotes, newLinkedin, newFb;
    AutoCompleteTextView newCompany;
    Button addNewCompany, updateCompany;
    String items[] = {"CIOC FMCG Pvt Ltd","First Choice Yard Help","Muscle Factory","ABC Pvt Ltd","DXC Technology"};
    TextView newDp, newDpAttach;
    Button saveNewContact;
    Switch genderSwitch;
    ImageView switchProfile;
    TextView arrowUp, arrowDown;

    EditText dialogTel, dialogAbout, dialogMob, dialogStreet, dialogCity, dialogState, dialogPincode, dialogCountry, dialogCIN, dialogTIN, dialogLogo, dialogWeb;
    Button saveDialogDetails;
    TextView dialog_arrowUp, dialog_arrowDown;
    LinearLayout dialog_showAdvanceDetails;
    String name, cno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        Bundle b = getIntent().getExtras();
        if (b != null){
            name = b.getString("name");
            cno = b.getString("cno");
        }

        findAllIds();

        genderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    switchProfile.setImageResource(R.drawable.male);
                }
                else switchProfile.setImageResource(R.drawable.woman);
            }
        });


        addCompany();
//        editUpdateCompony();

        arrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowUp.setVisibility(View.VISIBLE);
                arrowDown.setVisibility(View.GONE);
                showAdvanceDetails.setVisibility(View.VISIBLE);
            }
        });

        arrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrowDown.setVisibility(View.VISIBLE);
                arrowUp.setVisibility(View.GONE);
                showAdvanceDetails.setVisibility(View.GONE);
            }
        });



        newDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 111);
            }
        });

    }

    public void findAllIds(){
        genderSwitch = findViewById(R.id.gender_sw);
        switchProfile = findViewById(R.id.switch_profile);

        newFullName = findViewById(R.id.contacts_full_name);
        newEmail = findViewById(R.id.contacts_email);
        newMobNo = findViewById(R.id.contacts_mobile);
        newCompany = findViewById(R.id.contacts_company);
        addNewCompany = findViewById(R.id.add_new_company);
        updateCompany = findViewById(R.id.update_company);

        arrowUp = findViewById(R.id.arrow_drop_up);
        arrowDown = findViewById(R.id.arrow_drop_down);
        showAdvanceDetails = findViewById(R.id.show_advance_ll);

        newEmailDuplicate = findViewById(R.id.contacts_email_secondary);
        newMobNoDuplicate = findViewById(R.id.contacts_mobile_secondary);
        newDesignation = findViewById(R.id.contacts_designation);
        newNotes = findViewById(R.id.contacts_notes);
        newLinkedin = findViewById(R.id.contacts_linkedin);
        newFb = findViewById(R.id.contacts_facebook);
        newDp = findViewById(R.id.contact_profile_photo);
        newDpAttach = findViewById(R.id.dp_attached);
        saveNewContact = findViewById(R.id.save_newContacts);

        newFullName.setText(name);
        newMobNo.setText(cno);
    }

    public void addCompany(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(NewContactActivity.this, android.R.layout.simple_dropdown_item_1line, items);
        newCompany.setAdapter(arrayAdapter);

        newCompany.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addNewCompany.setVisibility(View.VISIBLE);
                updateCompany.setVisibility(View.GONE);
                for (int i=0; i<items.length; i++){
                    if (s.toString().equals(items[i])){
                        addNewCompany.setVisibility(View.GONE);
                        updateCompany.setVisibility(View.VISIBLE);
                    }
                }
                if (s.toString().equals("")){
                    addNewCompany.setVisibility(View.GONE);
                    updateCompany.setVisibility(View.GONE);
                }
            }
        });
    }

    public void updateNewCompany(View view){

        View v = getLayoutInflater().inflate(R.layout.dialog_edit_update_company, null, false);
        dialogTel = v.findViewById(R.id.dialog_new_telephone);
        dialogAbout = v.findViewById(R.id.dialog_new_about);
        dialog_arrowUp = v.findViewById(R.id.dialog_arrow_drop_up);
        dialog_arrowDown = v.findViewById(R.id.dialog_arrow_drop_down);
        dialog_showAdvanceDetails = v.findViewById(R.id.dialog_show_advance_ll);

        dialogMob = v.findViewById(R.id.dialog_new_mob);
        dialogStreet = v.findViewById(R.id.dialog_new_street);
        dialogCity = v.findViewById(R.id.dialog_new_city);
        dialogState = v.findViewById(R.id.dialog_new_state);
        dialogPincode = v.findViewById(R.id.dialog_new_pincode);
        dialogCountry = v.findViewById(R.id.dialog_new_country);
        dialogCIN = v.findViewById(R.id.dialog_new_cin);
        dialogTIN = v.findViewById(R.id.dialog_new_tin);
        dialogLogo = v.findViewById(R.id.dialog_new_logo);
        dialogWeb = v.findViewById(R.id.dialog_new_web);
        saveDialogDetails = v.findViewById(R.id.dialog_new_save);

        dialog_arrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_arrowUp.setVisibility(View.VISIBLE);
                dialog_arrowDown.setVisibility(View.GONE);
                dialog_showAdvanceDetails.setVisibility(View.VISIBLE);
            }
        });

        dialog_arrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_arrowDown.setVisibility(View.VISIBLE);
                dialog_arrowUp.setVisibility(View.GONE);
                dialog_showAdvanceDetails.setVisibility(View.GONE);
            }
        });

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(v);
        adb.setCancelable(false);
        final AlertDialog ad = adb.create();
        saveDialogDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
            }
        });
        ad.show();
    }

    public void saveDetails(View v) {
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111){
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                try {
                    Bitmap bit = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    newDpAttach.setVisibility(View.VISIBLE);
                    newDpAttach.setText("Attached");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
