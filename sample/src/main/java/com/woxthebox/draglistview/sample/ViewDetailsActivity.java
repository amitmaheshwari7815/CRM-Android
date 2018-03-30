package com.woxthebox.draglistview.sample;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.pchmn.materialchips.ChipsInput;
import com.pchmn.materialchips.model.ChipInterface;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.woxthebox.draglistview.sample.edittag.ContactChip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewDetailsActivity extends FragmentActivity {
    ImageView contactImage;
    TextView nameTv, companyTv, designationTv, cnoTv, emailTv;

    TabLayout tl;
    FloatingActionButton fabView, fabSchedule, fabTask, fabMeeting, fabNotes;
    private boolean fabExpanded = false;
    private LinearLayout layoutFabSchedule;
    private LinearLayout layoutFabTask;
    private LinearLayout layoutFabMeeting;
    private LinearLayout layoutFabNotes;
    Animation rotate_forward, rotate_Backward, fab_open, fab_close;

    int c_yr, c_month, c_day, c_hr, c_min;

    private static final String TAG = ViewDetailsActivity.class.toString();
    private List<ContactChip> mContactList;
    ChipsInput scheduleInternalPeople, scheduleOS, taskOtherStake;

    ArrayList noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        Bundle b = getIntent().getExtras();
        int image = b.getInt("image");
        final String name = b.getString("name");
        String company = b.getString("company");
        String designation = b.getString("designation");
        String cno = b.getString("cno");
        final String email = b.getString("email");

        nameTv = findViewById(R.id.view_d_name);
        companyTv = findViewById(R.id.view_d_comapany);
        designationTv = findViewById(R.id.view_d_designation);
        cnoTv = findViewById(R.id.view_mob_no);
        emailTv = findViewById(R.id.view_email);

        fabView = findViewById(R.id.fab_view);
        fabSchedule = findViewById(R.id.fab_schedule);
        fabTask = findViewById(R.id.fab_task);
        fabMeeting = findViewById(R.id.fab_meeting);
        fabNotes = findViewById(R.id.fab_notes);

        layoutFabSchedule = (LinearLayout) this.findViewById(R.id.layoutFabSchedule);
        layoutFabTask = (LinearLayout) this.findViewById(R.id.layoutFabTask);
        layoutFabMeeting = (LinearLayout) this.findViewById(R.id.layoutFabMeeting);
        layoutFabNotes = (LinearLayout) this.findViewById(R.id.layoutFabNotes);

        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotate_Backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        nameTv.setText(name);
        companyTv.setText(company);
        designationTv.setText(designation);
        cnoTv.setText(cno);
        emailTv.setText(email);

        mContactList = new ArrayList<>();
        noteList = new ArrayList<>();

        tl = findViewById(R.id.tl_view);
        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch (pos){
                    case 0:
                    {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.view_fg, new TimelineFragment(), "TimelineFragment");
                        ft.commit();
                        break;
                    }
                    case 1:
                    {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.view_fg, new InfoFragment(), "ActiveFragment");
                        ft.commit();
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Calendar c = Calendar.getInstance();
        c_yr = c.get(Calendar.YEAR);
        c_month = c.get(Calendar.MONTH);
        c_day = c.get(Calendar.DAY_OF_MONTH);
        c_hr = c.get(Calendar.HOUR_OF_DAY);
        c_min = c.get(Calendar.MINUTE);

        fabView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });
        closeSubMenusFab();

        fabSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mContactList = new ArrayList<>();
                final EditText scheduleDate, scheduleTime, scheduleLocation, scheduleEventDetails;
                Button scheduleCancel, scheduleSave;
                View v = getLayoutInflater().inflate(R.layout.layout_schedule_style, null, false);

                scheduleDate = v.findViewById(R.id.schedule_date);
                scheduleTime = v.findViewById(R.id.schedule_time);
                scheduleOS = v.findViewById(R.id.chips_input_os_schedule);
                scheduleInternalPeople = v.findViewById(R.id.chips_input_ip_schedule);
                scheduleLocation = v.findViewById(R.id.schedule_loction);
                scheduleEventDetails = v.findViewById(R.id.schedule_event_details);

                scheduleCancel = v.findViewById(R.id.schedule_cancel);
                scheduleSave = v.findViewById(R.id.schedule_save);

                scheduleDate.setFocusableInTouchMode(false);
                scheduleTime.setFocusableInTouchMode(false);
                scheduleDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dpd = new DatePickerDialog(ViewDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                scheduleDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                            }
                        },c_yr,c_month,c_day);
                        DatePicker dp = dpd.getDatePicker();
//                dp.setMinDate(System.currentTimeMillis()-10*24*60*60*1000);
//                dp.setMaxDate(System.currentTimeMillis());
                        dpd.show();
                    }
                });

                scheduleTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog tpd = new TimePickerDialog(ViewDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (hourOfDay > 12) {
                                    scheduleTime.setText((hourOfDay-12) + ":" + minute+" PM");
                                } else {
                                    scheduleTime.setText(hourOfDay + ":" + minute+" AM");
                                }
                            }
                        }, c_hr, c_min,false);
                        tpd.show();
                    }
                });

                // get contact list
                new RxPermissions(ViewDetailsActivity.this).request(Manifest.permission.READ_CONTACTS).subscribe();
                getContactList();

                // chips listener
                scheduleInternalPeople.addChipsListener(new ChipsInput.ChipsListener() {
                    @Override
                    public void onChipAdded(ChipInterface chip, int newSize) {
                        Log.e(TAG, "chip added, " + newSize);
                    }

                    @Override
                    public void onChipRemoved(ChipInterface chip, int newSize) {
                        Log.e(TAG, "chip removed, " + newSize);
                    }

                    @Override
                    public void onTextChanged(CharSequence text) {
                        Log.e(TAG, "text changed: " + text.toString());
                    }
                });
                // chips listener
                scheduleOS.addChipsListener(new ChipsInput.ChipsListener() {
                    @Override
                    public void onChipAdded(ChipInterface chip, int newSize) {
                        Log.e(TAG, "chip added, " + newSize);
                    }

                    @Override
                    public void onChipRemoved(ChipInterface chip, int newSize) {
                        Log.e(TAG, "chip removed, " + newSize);
                    }

                    @Override
                    public void onTextChanged(CharSequence text) {
                        Log.e(TAG, "text changed: " + text.toString());
                    }
                });

                AlertDialog.Builder adb = new AlertDialog.Builder(ViewDetailsActivity.this);
                adb.setView(v);
                adb.setCancelable(false);
                final AlertDialog ad = adb.create();
                scheduleCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ad.dismiss();
                    }
                });
                ad.show();
            }
        });

        fabTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText taskDate, taskDetails;
                Button taskCancel, scheduleSave;
                View v = getLayoutInflater().inflate(R.layout.layout_task_style, null, false);
                taskDate = v.findViewById(R.id.task_date);
                taskOtherStake = v.findViewById(R.id.chips_input_os_task);
                taskDetails = v.findViewById(R.id.task_details);

                taskCancel= v.findViewById(R.id.task_cancel);
                scheduleSave = v.findViewById(R.id.task_save);

                taskDate.setFocusableInTouchMode(false);
                taskDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dpd = new DatePickerDialog(ViewDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                taskDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                            }
                        },c_yr,c_month,c_day);
                        DatePicker dp = dpd.getDatePicker();
//                dp.setMinDate(System.currentTimeMillis()-10*24*60*60*1000);
//                dp.setMaxDate(System.currentTimeMillis());
                        dpd.show();
                    }
                });

                // get contact list
                new RxPermissions(ViewDetailsActivity.this).request(Manifest.permission.READ_CONTACTS).subscribe();
                getContactList();

                // chips listener
                taskOtherStake.addChipsListener(new ChipsInput.ChipsListener() {
                    @Override
                    public void onChipAdded(ChipInterface chip, int newSize) {
                        Log.e(TAG, "chip added, " + newSize);
                    }

                    @Override
                    public void onChipRemoved(ChipInterface chip, int newSize) {
                        Log.e(TAG, "chip removed, " + newSize);
                    }

                    @Override
                    public void onTextChanged(CharSequence text) {
                        Log.e(TAG, "text changed: " + text.toString());
                    }
                });

                AlertDialog.Builder adb = new AlertDialog.Builder(ViewDetailsActivity.this);
                adb.setView(v);
                adb.setCancelable(false);
                final AlertDialog ad = adb.create();
                taskCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ad.dismiss();
                    }
                });
                ad.show();
//                taskOtherStake.setFilterableList(mContactList);
            }
        });

        fabMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewDetailsActivity.this, MeetingActivity.class));
            }
        });

        fabNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView attachFile;
                final EditText noteDetails;
                Button noteCancel, noteSend;
                View v = getLayoutInflater().inflate(R.layout.layout_note_style, null, false);
                attachFile = v.findViewById(R.id.note_attach_file);
                noteDetails = v.findViewById(R.id.note_details);
                noteCancel = v.findViewById(R.id.note_cancel);
                noteSend = v.findViewById(R.id.note_send);

                attachFile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        startActivityForResult(intent, 101);
                    }
                });

                AlertDialog.Builder adb = new AlertDialog.Builder(ViewDetailsActivity.this);
                adb.setView(v);
                adb.setCancelable(false);
                final AlertDialog ad = adb.create();
                noteCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String details = noteDetails.getText().toString().trim();
                        ad.dismiss();
                    }
                });
                ad.show();
            }
        });
    }

    //closes FAB submenus
    private void closeSubMenusFab(){
        layoutFabSchedule.setVisibility(View.GONE);
        layoutFabTask.setVisibility(View.GONE);
        layoutFabMeeting.setVisibility(View.GONE);
        layoutFabNotes.setVisibility(View.GONE);
        fabView.setImageResource(R.drawable.ic_add);
        fabSchedule.startAnimation(fab_close);
        fabTask.setAnimation(fab_close);
        fabMeeting.setAnimation(fab_close);
        fabNotes.setAnimation(fab_close);
        fabView.startAnimation(rotate_Backward);
//        fabView.startAnimation(fab_open);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabSchedule.setVisibility(View.VISIBLE);
        layoutFabTask.setVisibility(View.VISIBLE);
        layoutFabMeeting.setVisibility(View.VISIBLE);
        layoutFabNotes.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
//        fab.setImageResource(R.drawable.ic_close);
        fabSchedule.startAnimation(fab_open);
        fabTask.setAnimation(fab_open);
        fabMeeting.setAnimation(fab_open);
        fabNotes.setAnimation(fab_open);
        fabView.startAnimation(rotate_forward);
        fabExpanded = true;
    }

    private void getContactList() {
        Cursor phones = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null,null,null, null);

        // loop over all contacts
        if(phones != null) {
            while (phones.moveToNext()) {
                // get contact info
                String phoneNumber = null;
                String id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
                String name = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String avatarUriString = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
                Uri avatarUri = null;
                if(avatarUriString != null)
                    avatarUri = Uri.parse(avatarUriString);

                // get phone number
                if (Integer.parseInt(phones.getString(phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[] { id }, null);

                    while (pCur != null && pCur.moveToNext()) {
                        phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }

                    pCur.close();

                }

                ContactChip contactChip = new ContactChip(id, avatarUri, name, phoneNumber);
                // add contact to the list
                mContactList.add(contactChip);
            }
            phones.close();
        }

        // pass contact list to chips input


        if (fabSchedule.isClickable()) {
            scheduleOS.setFilterableList(mContactList);
            scheduleInternalPeople.setFilterableList(mContactList);
        } else if (fabTask.isClickable()){
            taskOtherStake.setFilterableList(mContactList);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101){
            if (resultCode == RESULT_OK){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

