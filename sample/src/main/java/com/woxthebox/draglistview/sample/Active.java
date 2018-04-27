package com.woxthebox.draglistview.sample;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 16/4/18.
 */

public class Active {
    public  String pk,user,created,typ,data,deal,notes,doc,internalUsers;
    public ArrayList<String> contact = new ArrayList<>();
    public JSONObject jsonObject;


    public Active(){


    }

    public Active(String pk, String user, String created, String typ, String data, String deal, String notes, String doc, String internalUsers, ArrayList<String> contact) {
        this.pk = pk;
        this.user = user;
        this.created = created;
        this.typ = typ;
        this.data = data;
        this.deal = deal;
        this.notes = notes;
        this.doc = doc;
        this.internalUsers = internalUsers;
        this.contact = contact;
    }
public Active(JSONObject jsonObject){
        this.jsonObject = jsonObject;
        try {
            this.pk = jsonObject.getString("pk");
            this.user = jsonObject.getString("user");
            this.created = jsonObject.getString("created");
            this.typ = jsonObject.getString("typ");
            this.data = jsonObject.getString("data");
            this.deal = jsonObject.getString("deal");
            this.notes = jsonObject.getString("notes");
            this.doc = jsonObject.getString("doc");
            this.internalUsers = jsonObject.getString("internalUsers");


        } catch (JSONException e) {
            e.printStackTrace();
        }
}



    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getInternalUsers() {
        return internalUsers;
    }

    public void setInternalUsers(String internalUsers) {
        this.internalUsers = internalUsers;
    }

    public ArrayList<String> getContact() {
        return contact;
    }

    public void setContact(ArrayList<String> contact) {
        this.contact = contact;
    }

}
