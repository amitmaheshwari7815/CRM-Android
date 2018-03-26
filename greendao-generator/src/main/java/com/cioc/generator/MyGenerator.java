package com.cioc.generator;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.cioc.crm.db"); // Your app package name and the (.db) is the folder where the DAO files will be generated into.
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"../app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addContactEntities(schema);
        addDealEntities(schema);
        addContractEntities(schema);
        addActivityEntities(schema);
    }

    // This is use to describe the colums of your table
    private static Entity addContactEntities(final Schema schema) {
        Entity contact = schema.addEntity("Contact");
        contact.addIdProperty().primaryKey().autoincrement();
        contact.addIntProperty("user_id").notNull();
        contact.addStringProperty("contactName");
        contact.addStringProperty("contactCreated");
        contact.addStringProperty("contactUpdated");
        contact.addStringProperty("contactCompany");
        contact.addStringProperty("contactDesignation");
        contact.addStringProperty("contactEmail");
        contact.addStringProperty("contactEmailSecondary");
        contact.addIntProperty("contactMobile");
        contact.addIntProperty("contactMobileSecondary");
        contact.addStringProperty("contactLinkedin");
        contact.addStringProperty("contactFacebook");
        contact.addStringProperty("contactDP");
        contact.addBooleanProperty("contactGender");
        return contact;
    }

    private static Entity addDealEntities(final Schema schema) {
        Entity deal = schema.addEntity("Deal");
        deal.addIdProperty().primaryKey().autoincrement();
        deal.addIntProperty("user_id").notNull();
        deal.addStringProperty("dealName");
        deal.addStringProperty("dealCompany");
        deal.addStringProperty("dealValue");
        deal.addStringProperty("dealCreate");
        deal.addStringProperty("dealUpdated");
        deal.addStringProperty("dealCurrency");
        deal.addStringProperty("dealState");
        deal.addStringProperty("dealContacts");
        deal.addStringProperty("dealInternalUsers");
        deal.addStringProperty("dealRequirements");
        deal.addStringProperty("dealProbability");
        deal.addStringProperty("dealCloseDate");
        deal.addStringProperty("dealActivity");
        deal.addStringProperty("dealResult");
        deal.addStringProperty("dealDoc");
        deal.addStringProperty("dealRate");
        deal.addStringProperty("dealDuePenalty");
        deal.addStringProperty("dealDuaPeriod");
        return deal;
    }

    private static Entity addContractEntities(final Schema schema) {
        Entity contract = schema.addEntity("Contract");
        contract.addIdProperty().primaryKey().autoincrement();
        contract.addIntProperty("user_id").notNull();
        contract.addStringProperty("contractCreated");
        contract.addStringProperty("contractUpdated");
        contract.addStringProperty("contractValue");
        contract.addStringProperty("contractDeal");
        contract.addStringProperty("contractStatus");
        contract.addStringProperty("contractDueDate");
        contract.addStringProperty("contractDetails");
        contract.addStringProperty("contractData");
        contract.addStringProperty("contractBilledDate");
        contract.addStringProperty("contractReceivedDate");
        contract.addStringProperty("contractArchivedDate");
        contract.addStringProperty("contractGrandTotal");
        return contract;
    }

    private static Entity addActivityEntities(final Schema schema) {
        Entity activity = schema.addEntity("Activity");
        activity.addIdProperty().primaryKey().autoincrement();
        activity.addIntProperty("user_id").notNull();
        activity.addStringProperty("number");
        activity.addStringProperty("activityCreated");
        activity.addStringProperty("activityData");
        activity.addStringProperty("activityDeal");
        activity.addStringProperty("activityContact");
        activity.addStringProperty("activityNote");
        activity.addStringProperty("activityDoc");
        activity.addStringProperty("activityContacts");
        activity.addStringProperty("activityInternalUsers");
        return activity;
    }

}
