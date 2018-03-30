package com.woxthebox.draglistview.sample;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactsListActivity extends Activity {
    ListView listView;
    String keys[] = {"k1", "k2"};
    int ids[] = {R.id.contact_name, R.id.contact_number};
    ArrayList storeContacts;
    SimpleAdapter simpleAdapter;
    Cursor cursor;
    String name, phonenumber, companyName, email;
    public static final int RequestPermissionCode = 1;
//    ContentResolver mContentResolver = getContentResolver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contacts_list);

        listView = (ListView) findViewById(R.id.listview1);

        storeContacts = new ArrayList<String>();

        EnableRuntimePermission();
        GetContactsIntoArrayList();

        simpleAdapter = new SimpleAdapter(ContactsListActivity.this, storeContacts, R.layout.contact_items_listview, keys, ids);

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap hm = (HashMap) storeContacts.get(position);
                String name = (String) hm.get(keys[0]);
                final String cno = (String) hm.get(keys[1]);
                Intent i = new Intent(ContactsListActivity.this, NewContactActivity.class);
                i.putExtra("name", name);
                i.putExtra("cno", cno);
                startActivity(i);
            }
        });

    }
    int digits = 10;
    int plus_sign_pos = 0;
    public void GetContactsIntoArrayList(){
        String order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, order);
        String temp_name="";
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY));
            if (name.equals(temp_name))
                continue;
            temp_name = name;
//            companyName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY));
            phonenumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
            if (hasCountryCode(phonenumber)) {
                String country_digits = phonenumber.replace("+91","");
                phonenumber = country_digits;
            }

            HashMap hm = new HashMap();
            hm.put(keys[0],name);
//            hm.put(keys[1],company_Name);
            hm.put(keys[1],phonenumber);
//            hm.put(keys[3],email);

            storeContacts.add(hm);
        }

        cursor.close();

    }

    private boolean hasCountryCode(String number) {
        return number.charAt(plus_sign_pos) == '+'; // Didn't String had contains() method?...
    }


    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                ContactsListActivity.this,
                Manifest.permission.READ_CONTACTS)) {
//            Toast.makeText(this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {
        switch (RC) {
            case RequestPermissionCode:
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(ContactsListActivity.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {
//                    Toast.makeText(ContactsListActivity.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}
