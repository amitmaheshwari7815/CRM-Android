package com.woxthebox.draglistview.sample.relationships;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by amit on 16/4/18.
 */

public class Service implements Serializable{

    public String pk,created,companyName,user,cin,tin,address_pk,street,city,state,pincode,country,mobile,telepone,logo,about,doc,web;
    public JSONObject jsonObject;

    public Service(){

    }

    public Service(String pk, String created, String companyName, String user, String cin, String tin, String address_pk, String street,
                   String city, String state, String pincode, String mobile, String telepone, String logo,
                   String about, String  doc, String web ){
        this.pk = pk;
        this.created = created;
        this.user = user;
        this.companyName = companyName;
        this.cin = cin;
        this.tin = tin;
        this.address_pk = address_pk;
        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.mobile = mobile;
        this.telepone = telepone;
        this.logo = logo;
        this.about = about;
        this.doc = doc;
        this.web = web;
    }

    public Service(JSONObject jsonObject){
        this.jsonObject = jsonObject;
        try {
            this.pk = jsonObject.getString("pk");
            this.created = jsonObject.getString("created");
            this.companyName = jsonObject.getString("name");
            this.cin = jsonObject.getString("cin");
            this.tin = jsonObject.getString("tin");
            this.mobile = jsonObject.getString("mobile");
            this.telepone = jsonObject.getString("telephone");
            this.logo = jsonObject.getString("logo");
            this.about = jsonObject.getString("about");
            this.doc = jsonObject.getString("doc");
            this.web = jsonObject.getString("web");

            JSONObject address = jsonObject.getJSONObject("address");
            this.address_pk = address.getString("pk");
            this.street = address.getString("street");
            this.city = address.getString("city");
            this.state = address.getString("state");
            this.pincode = address.getString("pincode");
            this.country = address.getString("country");



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getPk() {
        return pk;
    }

    public String getCreated() {
        return created;
    }

    public String getUser() {
        return user;
    }

    public String getCin() {
        return cin;
    }

    public String getTin() {
        return tin;
    }

    public String getMobile() {
        return mobile;
    }

    public String getTelepone() {
        return telepone;
    }

    public String getLogo() {
        return logo;
    }

    public String getAbout() {
        return about;
    }

    public String getDoc() {
        return doc;
    }

    public String getWeb() {
        return web;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getAddress_pk() {
        return address_pk;
    }

    public void setAddress_pk(String address_pk) {
        this.address_pk = address_pk;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setTelepone(String telepone) {
        this.telepone = telepone;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

}
