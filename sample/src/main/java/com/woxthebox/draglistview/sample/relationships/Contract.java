package com.woxthebox.draglistview.sample.relationships;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by amit on 16/4/18.
 */

public class Contract {
    public String pk, user,created,updated,value,deal,status,dueDate,details,data,billedDate,
            recivedDate, archivedDate,grandTotal;
    public JSONObject jsonObject;

    public Contract(){

    }

    public Contract(String pk, String user, String created, String updated, String value, String deal,
                    String status, String dueDate, String details, String data, String billedDate, String recivedDate,
                    String archivedDate, String grandTotal) {
        this.pk = pk;
        this.user = user;
        this.created = created;
        this.updated = updated;
        this.value = value;
        this.deal = deal;
        this.status = status;
        this.dueDate = dueDate;
        this.details = details;
        this.data = data;
        this.billedDate = billedDate;
        this.recivedDate = recivedDate;
        this.archivedDate = archivedDate;
        this.grandTotal = grandTotal;

    }

    public Contract(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        try {
            this.pk = jsonObject.getString("pk");
            this.user = jsonObject.getString("user");
            this.created = jsonObject.getString("created");
            this.updated = jsonObject.getString("updated");
            this.value = jsonObject.getString("value");
            this.status = jsonObject.getString("status");
            this.details = jsonObject.getString("details");
            this.dueDate = jsonObject.getString("dueDate");


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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBilledDate() {
        return billedDate;
    }

    public void setBilledDate(String billedDate) {
        this.billedDate = billedDate;
    }

    public String getRecivedDate() {
        return recivedDate;
    }

    public void setRecivedDate(String recivedDate) {
        this.recivedDate = recivedDate;
    }

    public String getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(String archivedDate) {
        this.archivedDate = archivedDate;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }
    public JSONObject getJsonObject(){
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }
}
