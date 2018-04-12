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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class EditContactActivity extends Activity {

    LinearLayout showAdvanceDetails;
    EditText editFullName, editEmail, editMobNo, editEmailDuplicate, editMobNoDuplicate, editDesignation, editNotes, editLinkedin, editFb;
    AutoCompleteTextView editCompany;
    Button addNewCompany, updateCompany;
    //    String items[] = {"CIOC FMCG Pvt Ltd","First Choice Yard Help","Muscle Factory","ABC Pvt Ltd","DXC Technology"};
    ArrayList<String> companiesList;
    public AsyncHttpClient client;
    TextView editDp, editDpAttach;
    Button saveEditContact;
    TextView arrowUp, arrowDown;
    ServerUrl serverUrl;
    EditText dialogTel, dialogAbout, dialogMob, dialogStreet, dialogCity, dialogState, dialogPincode, dialogCountry, dialogCIN, dialogTIN, dialogLogo, dialogWeb;
    Button saveDialogDetails;
    TextView dialog_arrowUp, dialog_arrowDown;
    LinearLayout dialog_showAdvanceDetails;

    public static String name,street,city,state,pincode,country,email,mobile,designation,company,telephone, cMobile, cin, tin, about, web;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        companiesList = new ArrayList<String>();
        client = new AsyncHttpClient();
        serverUrl = new ServerUrl();

        Bundle b = getIntent().getExtras();
        int image = b.getInt("image");
        name = b.getString("name");
        company = b.getString("company");
        designation = b.getString("designation");
        mobile = b.getString("cno");
        email = b.getString("email");
        final boolean gender = b.getBoolean("gender");
        street = b.getString("street");
        city = b.getString("city");
        pincode = b.getString("pincode");
        state = b.getString("state");
        country = b.getString("country");
        telephone = b.getString("tel");
        cMobile = b.getString("mob");
        cin = b.getString("cin");
        tin = b.getString("tin");
        about = b.getString("about");
        web = b.getString("web");

        findAllIds();
        addCompany();

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

        editDp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 112);
            }
        });

        editFullName.setText(name);
        editCompany.setText(company);
        editDesignation.setText(designation);
        editMobNo.setText(mobile);
        editEmail.setText(email);
    }

    public void findAllIds(){
        editFullName = findViewById(R.id.edit_full_name);
        editEmail = findViewById(R.id.edit_email);
        editMobNo = findViewById(R.id.edit_mobile);
        editCompany = findViewById(R.id.edit_company);
        addNewCompany = findViewById(R.id.edit_add_new_company);
        updateCompany = findViewById(R.id.edit_update_company);

        arrowUp = findViewById(R.id.arrow_drop_up);
        arrowDown = findViewById(R.id.arrow_drop_down);
        showAdvanceDetails = findViewById(R.id.show_advance_ll);

        editEmailDuplicate = findViewById(R.id.edit_email_secondary);
        editMobNoDuplicate = findViewById(R.id.edit_mobile_secondary);
        editDesignation = findViewById(R.id.edit_designation);
        editNotes = findViewById(R.id.edit_notes);
        editLinkedin = findViewById(R.id.edit_linkedin);
        editFb = findViewById(R.id.edit_facebook);
        editDp = findViewById(R.id.edit_profile_photo);
        editDpAttach = findViewById(R.id.edit_dp_attached);
        saveEditContact = findViewById(R.id.edit_save_newContacts);
    }

    public void addCompany(){
        String serverURL = serverUrl.url;
        client.get(serverURL+"api/ERP/service/?format=json", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject json = response.getJSONObject(i);
                        String companyName = json.getString("name");

                        companiesList.add(companyName);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                editcomanyname();
            }

            @Override
            public void onFinish() {
                System.out.println("finished EditContact");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println("finished failed EditContact");
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter(EditContactActivity.this, android.R.layout.simple_dropdown_item_1line, companiesList);
        editCompany.setAdapter(arrayAdapter);

        editCompany.addTextChangedListener(new TextWatcher() {
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
                for (int i=0; i<companiesList.size(); i++){
                    if (s.toString().equals(companiesList.get(i))){
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
    public void editcomanyname (){

    }

    public void editUpdateNewCompany(View view){

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

        dialogTel.setText(telephone);
        dialogAbout.setText(about);
        dialogMob.setText(cMobile);
        dialogStreet.setText(street);
        dialogCity.setText(city);
        dialogState.setText(state);
        dialogPincode.setText(pincode);
        dialogCountry.setText(country);
        dialogCIN.setText(cin);
        dialogTIN.setText(tin);
        dialogWeb.setText(web);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 112){
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                try {
                    Bitmap bit = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    editDpAttach.setVisibility(View.VISIBLE);
                    editDpAttach.setText("Attached");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}