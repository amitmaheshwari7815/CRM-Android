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
    String keys[] = {"k1", "k2", "k3", "k4"};
    int ids[] = {R.id.contact_name, R.id.contact_company_name, R.id.contact_number, R.id.contact_email};
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

//        button = (Button)findViewById(R.id.button1);

        storeContacts = new ArrayList<String>();

        EnableRuntimePermission();
        GetContactsIntoArrayList();

        simpleAdapter = new SimpleAdapter(ContactsListActivity.this, storeContacts, R.layout.contact_items_listview, keys, ids);

        listView.setAdapter(simpleAdapter);


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap hm = (HashMap) storeContacts.get(position);
                String name = (String) hm.get(keys[0]);
                final String cno = (String) hm.get(keys[2]);

                ImageView phoneCall, textMessage;
                View v = getLayoutInflater().inflate(R.layout.layout_meeting_style, null, false);
                phoneCall = v.findViewById(R.id.phone_call);
                textMessage = v.findViewById(R.id.text_message);
                phoneCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ContactsListActivity.this, "call" + cno, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Intent.ACTION_DIAL);
                        i.setData(Uri.parse("tel:" + cno));
//                        if (ActivityCompat.checkSelfPermission(ContactsListActivity.this,
//                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            return;
//                        }
//                        if (ActivityCompat.checkSelfPermission(ContactsListActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            // TODO: Consider calling
//                            //    ActivityCompat#requestPermissions
//                            // here to request the missing permissions, and then overriding
//                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                            //                                          int[] grantResults)
//                            // to handle the case where the user grants the permission. See the documentation
//                            // for ActivityCompat#requestPermissions for more details.
//                            return;
//                        }
//                        if (i.resolveActivity(getPackageManager()) != null) {
                            startActivity(i);
//                        }
                    }
                });

                textMessage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ContactsListActivity.this, "SMS"+cno, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("text/plain");
                        i.putExtra(Intent.EXTRA_TEXT,"Hii whats happened");
                        startActivity(i);
                    }
                });
                AlertDialog.Builder adb = new AlertDialog.Builder(ContactsListActivity.this);
                adb.setView(v);
                adb.create().show();

//                Intent i = new Intent(ContactsListActivity.this, NewContactActivity.class);
//                i.putExtra("name", name);
//                i.putExtra("cno", cno);
//                startActivity(i);
            }
        });

    }
    int digits = 10;
    int plus_sign_pos = 0;
    public void GetContactsIntoArrayList(){
//        String[] PROJECTION = new String[] {
//                ContactsContract.Contacts._ID,
//                ContactsContract.Contacts.DISPLAY_NAME,
//                ContactsContract.Contacts.HAS_PHONE_NUMBER,
//        };
//        String SELECTION = ContactsContract.Contacts.HAS_PHONE_NUMBER + "='1'";
//        Cursor contacts = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, null, null);
//        String contactId = contacts.getString(contacts.getColumnIndex(ContactsContract.Contacts._ID));
//        String rawContactId = getRawContactId(contactId);
//        String company_Name = getCompanyName(rawContactId);
        String order = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, order);
        String temp_name="";
        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_PRIMARY));
            if (name.equals(temp_name))
                continue;
            temp_name = name;
//            companyName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY));
//            String rawContactId = getRawContactId(contactId);
//            String company_Name = getCompanyName(rawContactId);
            phonenumber = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
            if (hasCountryCode(phonenumber)) {
                // +52 for MEX +526441122345, 13-10 = 3, so we need to remove 3 characters
                String country_digits = phonenumber.replace("+91","");
                phonenumber = country_digits;
            }

            HashMap hm = new HashMap();
            hm.put(keys[0],name);
//            hm.put(keys[1],company_Name);
            hm.put(keys[2],phonenumber);
//            hm.put(keys[3],email);

            storeContacts.add(hm);
        }

        cursor.close();

    }

    private boolean hasCountryCode(String number) {
        return number.charAt(plus_sign_pos) == '+'; // Didn't String had contains() method?...
    }

    private String getRawContactId(String contactId) {


        String[] projection = new String[]{ContactsContract.RawContacts._ID};
        String selection = ContactsContract.RawContacts.CONTACT_ID + "=?";
        String[] selectionArgs = new String[]{contactId};
        Cursor c = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI, projection, selection, selectionArgs, null);
        if (c == null) return null;
        int rawContactId = -1;
        if (c.moveToFirst()) {
            rawContactId = c.getInt(c.getColumnIndex(ContactsContract.RawContacts._ID));
        }
        c.close();
        return String.valueOf(rawContactId);

    }

    private String getCompanyName(String rawContactId) {
        try {
            String orgWhere = ContactsContract.Data.RAW_CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
            String[] orgWhereParams = new String[]{rawContactId, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
            Cursor cursor = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, orgWhere, orgWhereParams, null);
            if (cursor == null) return null;
            String name = null;
            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY));
                HashMap hm = new HashMap();
                hm.put(keys[1],name);
                storeContacts.add(hm);
            }
            cursor.close();
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                ContactsListActivity.this,
                Manifest.permission.READ_CONTACTS))
        {

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
