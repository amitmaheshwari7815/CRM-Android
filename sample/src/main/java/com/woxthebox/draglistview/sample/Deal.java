package com.woxthebox.draglistview.sample;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by amit on 14/4/18.
 */
public class Deal {
    public String pk;
    public String user;
    public String name;
    public String addressPk,street,city,add_state,pincode,lat,lon,country;
    public String companyPk,companyName,companyMobile;
    public String value;
    public String currency;
    public String state;
    public String internalUsers;
    public String requirements;
    public String probability;
    public String closeDate;
    public String active;
    public String result;
    public String doc;
    public String duePenalty;
    public String duePeriod;
    public String mobile;
    public ArrayList<String> contracts;
    public String contactPk,contactName,contactEmail,contactMobile,contactDesignation,contactDp;
    boolean contactMale;
    public JSONObject jsonObject;


    public Deal(){

    }

    public Deal(String pk, String user, String name, String addressPk, String street, String city, String add_state,
                String pincode, String lat, String lon, String country, String companyPk, String companyName,
                String companyMobile, String value, String currency, String state, String internalUsers,
                String requirements, String probability, String closeDate, String active, String result, String doc,
                String duePenalty, String duePeriod, String mobile, ArrayList<String> contacts, String contactPk,
                String contactName, String contactEmail, String contactMobile, String contactDesignation,
                String contactDp, boolean contactMale, ArrayList<String> contracts) {
        this.pk = pk;
        this.user = user;
        this.name = name;
        this.addressPk = addressPk;
        this.street = street;
        this.city = city;
        this.add_state = add_state;
        this.pincode = pincode;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
        this.companyPk = companyPk;
        this.companyName = companyName;
        this.companyMobile = companyMobile;
        this.value = value;
        this.currency = currency;
        this.state = state;
        this.internalUsers = internalUsers;
        this.requirements = requirements;
        this.probability = probability;
        this.closeDate = closeDate;
        this.active = active;
        this.result = result;
        this.doc = doc;
        this.duePenalty = duePenalty;
        this.duePeriod = duePeriod;
        this.mobile = mobile;
        this.contactPk = contactPk;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
        this.contactMobile = contactMobile;
        this.contactDesignation = contactDesignation;
        this.contactDp = contactDp;
        this.contactMale = contactMale;
        this.contracts = contracts;

    }


    public Deal(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        try {
            this.pk = jsonObject.getString("pk");
            this.user = jsonObject.getString("user");
            this.name = jsonObject.getString("name");
            this.value = jsonObject.getString("value");
            this.currency = jsonObject.getString("currency");
            this.probability = jsonObject.getString("probability");
            this.closeDate = jsonObject.getString("closeDate");
            this.active = jsonObject.getString("active");
            this.result = jsonObject.getString("result");
            this.doc = jsonObject.getString("doc");
            this.state = jsonObject.getString("state");
            this.internalUsers = jsonObject.getString("internalUsers");
            this.requirements = jsonObject.getString("requirements");
            this.duePeriod = jsonObject.getString("duePeriod");
            this.duePenalty = jsonObject.getString("duePenalty");

            JSONObject company = jsonObject.getJSONObject("company");
            this.companyPk = company.getString("pk");
            this.companyName = company.getString("name");
            this.companyMobile = company.getString("mobile");

            JSONObject address = company.getJSONObject("address");
            this.addressPk = address.getString("pk");
            this.street = address.getString("street");
            this.city = address.getString("city");
            this.add_state = address.getString("state");
            this.country = address.getString("country");

            JSONArray jsonArray = jsonObject.getJSONArray("contacts");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject contacts = jsonArray.getJSONObject(i);

                this.contactPk = contacts.getString("pk");
                this.contactName = contacts.getString("name");
                this.contactEmail = contacts.getString("email");
                this.contactDesignation = contacts.getString("designation");
                this.contactDp = contacts.getString("dp");
                this.contactMale = contacts.getBoolean("male");


                JSONArray jsonArray1 = jsonObject.getJSONArray("contracts");
                for (int j = 0; j < jsonArray1.length(); j++) {
////                    JSONObject contracts = jsonArray1.getJSONObject(j);
//                    this.contracts = jsonArray1.getString(j);
//
//
//
                }
            }

            } catch(JSONException e){
                e.printStackTrace();
            }

        }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAdd_state() {
        return add_state;
    }

    public void setAdd_state(String add_state) {
        this.add_state = add_state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public ArrayList<String> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<String> contracts) {
        this.contracts = contracts;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyPk() {
        return companyPk;
    }

    public void setCompanyPk(String companyPk) {
        this.companyPk = companyPk;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyMobile() {
        return companyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        this.companyMobile = companyMobile;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInternalUsers() {
        return internalUsers;
    }

    public void setInternalUsers(String internalUsers) {
        this.internalUsers = internalUsers;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getDuePenalty() {
        return duePenalty;
    }

    public void setDuePenalty(String duePenalty) {
        this.duePenalty = duePenalty;
    }

    public String getDuePeriod() {
        return duePeriod;
    }

    public void setDuePeriod(String duePeriod) {
        this.duePeriod = duePeriod;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContactPk() {
        return contactPk;
    }

    public void setContactPk(String contactPk) {
        this.contactPk = contactPk;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactDesignation() {
        return contactDesignation;
    }

    public void setContactDesignation(String contactDesignation) {
        this.contactDesignation = contactDesignation;
    }

    public String getContactDp() {
        return contactDp;
    }

    public void setContactDp(String contactDp) {
        this.contactDp = contactDp;
    }

    public boolean isContactMale() {
        return contactMale;
    }

    public void setContactMale(boolean contactMale) {
        this.contactMale = contactMale;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}

