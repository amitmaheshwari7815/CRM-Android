package com.woxthebox.draglistview.sample;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by amit on 20/4/18.
 */

public class Relationships implements Serializable{
    public String pk,companyName,mobile,logo,web;
    public String addressPk,street,city,state,pincode,country;
    public JSONObject jsonObject;


    public Relationships(String pk, String companyName, String mobile, String logo, String web, String addressPk, String street, String city, String state, String pincode, String country) {
        this.pk = pk;
        this.companyName = companyName;
        this.mobile = mobile;
        this.logo = logo;
        this.web = web;
        this.addressPk = addressPk;

        this.street = street;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
    }

    public Relationships(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        try {
            this.pk = jsonObject.getString("pk");
            this.companyName = jsonObject.getString("name");
            this.mobile = jsonObject.getString("mobile");
            this.logo = jsonObject.getString("logo");
            this.web = jsonObject.getString("web");

            JSONObject address = jsonObject.getJSONObject("address");
            this.addressPk = address.getString("pk");
            this.street = address.getString("street");
            this.city = address.getString("city");
            this.state = address.getString("state");
            this.pincode = address.getString("pincode");
            this.country = address.getString("country");




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

    public String getCompanyName() {

        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getAddressPk() {
        return addressPk;
    }

    public void setAddressPk(String addressPk) {
        this.addressPk = addressPk;
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




}

