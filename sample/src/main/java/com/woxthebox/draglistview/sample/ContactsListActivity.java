package com.woxthebox.draglistview.sample;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactsListActivity extends Activity {
    ListView listView;
    EditText searchContact;
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

        searchContact = findViewById(R.id.search_contact);
        listView = findViewById(R.id.listview1);

        storeContacts = new ArrayList<String>();

        EnableRuntimePermission();

        allContacts();

        textChange();

    }

    public void allContacts(){
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

    void textChange(){
        searchContact.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchName = s.toString().trim();
                listView.setVisibility(View.GONE);
                if(searchName.equals("")){
                    listView.setVisibility(View.VISIBLE);
                    allContacts();
                } else {
//                    Toast.makeText(ContactsListActivity.this, "wait", Toast.LENGTH_SHORT).show();
                    ContentResolver cr = getContentResolver();
                    String[] result = null;
                    // Find a contact using a partial name match
//                    String searchName = "specify serach name here";
                    Uri lookupUri = Uri.withAppendedPath(
                            ContactsContract.Contacts.CONTENT_FILTER_URI, searchName);
                    // Create a projection of the required column names.
                    String[] projection = new String[] { ContactsContract.Contacts._ID };
                    // Get a Cursor that will return the ID(s) of the matched name.
                    Cursor idCursor = cr.query(lookupUri, projection, null, null, null);
                    // Extract the first matching ID if it exists.
                    String id = null;
                    if (idCursor.moveToFirst()) {
                        int idIdx = idCursor
                                .getColumnIndexOrThrow(ContactsContract.Contacts._ID);
                        id = idCursor.getString(idIdx);
                    }
                    // Close that Cursor.
                    idCursor.close();
                    // Create a new Cursor searching for the data associated with the
                    // returned Contact ID.
                    if (id != null) {
                        // Return all the PHONE data for the contact.
                        String where = ContactsContract.Data.CONTACT_ID + " = " + id
                                + "AND" + ContactsContract.Data.MIMETYPE + " = ‘"
                                + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                                + "’";
                        projection = new String[] { ContactsContract.Data.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER };
                        Cursor dataCursor = getContentResolver().query(
                                ContactsContract.Data.CONTENT_URI, projection, where, null,
                                null);
                        // Get the indexes of the required columns.
                        int nameIdx = dataCursor
                                .getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
                        int phoneIdx = dataCursor
                                .getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        result = new String[dataCursor.getCount()];
                        while (dataCursor.moveToNext()) {
                            // Extract the name.
                            String name = dataCursor.getString(nameIdx);
                            // Extract the phone number.
                            String number = dataCursor.getString(phoneIdx);
                            result[dataCursor.getPosition()] = name + "(" + number + ")";
                            Toast.makeText(ContactsListActivity.this, name + "(" + number + ")", Toast.LENGTH_SHORT).show();

                        }
                        dataCursor.close();
                    }
                }
            }
        });
    }

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
