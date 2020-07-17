package com.codestudioworks.clienteventmanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {

  static final String TAG = "DBHelper";
  static final String DB_NAME = "data.db";
  static final int DB_VERSION = 1;

  static final String T_CLIENTS = "Clients";
  static final String C_CLIENTS_ID = "id";
  static final String C_CLIENTS_CODE = "code";
  static final String C_CLIENTS_COMPANYNAME = "companyName";
  static final String C_CLIENTS_ADMINHEADQUARTERSADDRESS = "adminAddress";
  static final String C_CLIENTS_ADMINHEADQUARTERSPOSTALCODE = "adminPostalCode";
  static final String C_CLIENTS_ADMINHEADQUARTERSZONE = "adminZone";
  static final String C_CLIENTS_ADMINHEADQUARTERSCITY = "adminCity";
  static final String C_CLIENTS_ADMINHEADQUARTERSPROVINCE = "adminProvince";
  static final String C_CLIENTS_ADMINHEADQUARTERSREGION = "adminRegion";
  static final String C_CLIENTS_ADMINHEADQUARTERSAREA = "adminArea";
  static final String C_CLIENTS_ADMINHEADQUARTERSCOUNTRY = "adminCountry";
  static final String C_CLIENTS_ADMINHEADQUARTERSLATITUDE = "adminLatitude";
  static final String C_CLIENTS_ADMINHEADQUARTERSLONGITUDE = "adminLongitude";
  static final String C_CLIENTS_REGISTEREDOFFICEADDRESS = "officeAddress";
  static final String C_CLIENTS_REGISTEREDOFFICEPOSTALCODE = "officePostalCode";
  static final String C_CLIENTS_REGISTEREDOFFICEZONE = "officeZone";
  static final String C_CLIENTS_REGISTEREDOFFICECITY = "officeCity";
  static final String C_CLIENTS_REGISTEREDOFFICEPROVINCE = "officeProvince";
  static final String C_CLIENTS_REGISTEREDOFFICEREGION = "officeRegion";
  static final String C_CLIENTS_REGISTEREDOFFICEAREA = "officeArea";
  static final String C_CLIENTS_REGISTEREDOFFICECOUNTRY = "officeCountry";
  static final String C_CLIENTS_REGISTEREDOFFICELATITUDE = "officeLatitude";
  static final String C_CLIENTS_REGISTEREDOFFICELONGITUDE = "officeLongitude";
  static final String C_CLIENTS_REGISTEREDOFFICECERTIFIEDEMAIL = "certifiedEMail";
  static final String C_CLIENTS_REGISTEREDOFFICEDEFAULTCURRENCY = "defaultCurrency";
  static final String C_CLIENTS_REGISTEREDOFFICETAXPAYERNUMBER = "taxPayerNumber";
  static final String C_CLIENTS_REGISTEREDOFFICETAXREGISTRATIONNUMBER = "taxRegistrationNumber";
  static final String C_CLIENTS_MAINPERSONINCHARGENAME = "mainPersonInChargeName";
  static final String C_CLIENTS_MAINPERSONINCHARGEPHONE = "mainPersonInChargePhone";
  static final String C_CLIENTS_MAINPERSONINCHARGECELL = "mainPersonInChargeCell";
  static final String C_CLIENTS_MAINPERSONINCHARGEFAX = "mainPersonInChargeFax";
  static final String C_CLIENTS_MAINPERSONINCHARGEVOIP = "mainPersonInChargeVoIP";
  static final String C_CLIENTS_MAINPERSONINCHARGEEMAIL = "mainPersonInChargeEMail";
  static final String C_CLIENTS_MAINPERSONINCHARGEWEB = "mainPersonInChargeWeb";
  // id into business categories table
  static final String C_CLIENTS_BUSINESSDATABUSINESSCATEGORYID = "businessCategoryId";
  // id into invitation means table
  static final String C_CLIENTS_BUSINESSDATAINITIALCONTACTCHANNELID = "initialContactChannelId";
  // id into business advisors table
  static final String C_CLIENTS_BUSINESSDATABUSINESSCONSULTANTID = "businessConsultantId";
  // id into employees table
  static final String C_CLIENTS_BUSINESSDATAREPORTINGTECHNICIANEMPLOYEEID = "reportingTechnicianEmployeeId";
  // id into employees table
  static final String C_CLIENTS_BUSINESSDATACONTRACTISSUINGEMPLOYEEID = "contractIssuingEmployeeId";
  // code into payment types table
  static final String C_CLIENTS_ACCOUNTINGINFOPAYMENTTYPECODE = "paymentTypeCode";
  // id into bank accounts table
  static final String C_CLIENTS_ACCOUNTINGINFOID = "bankAccountId";
  // code into tax categories table
  static final String C_CLIENTS_ACCOUNTINGINFOTAXCATEGORYCODE = "taxCategoryCode";
  static final String C_CLIENTS_EFFECTIVEORPOTENTIAL = "effectiveOrPotential";
  static final String C_CLIENTS_NOTES = "notes";

  // business areas table

  static final String T_BUSINESSAREAS = "BusinessAreas";
  static final String C_BUSINESSAREAS_CODE = "code";
  static final String C_BUSINESSAREAS_DESCRIPTION = "description";

  // client business areas table

  static final String T_CLIENTBUSINESSAREAS = "ClientBusinessAreas";
  static final String C_CLIENTBUSINESSAREAS_CLIENTID = "clientId";
  static final String C_CLIENTBUSINESSAREAS_BUSINESSAREACODE = "businessAreaCode";
  static final String C_CLIENTBUSINESSAREAS_PRIMARYORSECONDARYCONCERN = "primaryOrSecondaryConcern";

  // contact roles table

  static final String T_PERSONINCHARGEROLES = "PersonInChargeRoles";
  static final String C_PERSONINCHARGEROLES_ID = "personInChargeRoleId";
  static final String C_PERSONINCHARGEROLES_NAME = "personInChargeRoleName";

  // client people in charge table

  static final String T_CLIENTPEOPLEINCHARGE = "ClientPeopleInCharge";
  static final String C_CLIENTPEOPLEINCHARGE_CLIENTID = "clientId";
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGESUBID = "personInChargeSubId";
  /*
   * following must be kept same as C_PERSONINCHARGEROLES_ID for natural join in
   * V_CLIENTPEOPLEINCHARGEWITHROLES view to work
   */
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEROLEID = "personInChargeRoleId";
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGENAME = "personInChargeName";
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEPHONE = "personInChargePhone";
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGECELL = "personInChargeCell";
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEFAX = "personInChargeFax";
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEVOIP = "personInChargeVoIP";
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEEMAIL = "personInChargeEMail";
  static final String C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEWEB = "personInChargeWeb";

  // business categories table

  static final String T_BUSINESSCATEGORIES = "BusinessCategories";
  static final String C_BUSINESSCATEGORIES_ID = "businessCategoryId";
  static final String C_BUSINESSCATEGORIES_NAME = "businessCategoryName";

  // initial contact means table

  static final String T_INITIALCONTACTCHANNELS = "InitialContactChannels";
  static final String C_INITIALCONTACTCHANNELS_ID = "initialContactChannelId";
  static final String C_INITIALCONTACTCHANNELS_DESCRIPTION = "initialContactChannelDescription";

  // business consultants table (AKA agents or sellers)

  static final String T_BUSINESSCONSULTANTS = "BusinessAdvisors";
  static final String C_BUSINESSCONSULTANTS_ID = "businessConsultantId";
  static final String C_BUSINESSCONSULTANTS_NAME = "businessConsultantName";
  static final String C_BUSINESSCONSULTANTS_ADDRESS = "businessConsultantAddress";
  static final String C_BUSINESSCONSULTANTS_POSTALCODE = "businessConsultantPostalCode";
  static final String C_BUSINESSCONSULTANTS_ZONE = "businessConsultantZone";
  static final String C_BUSINESSCONSULTANTS_CITY = "businessConsultantCity";
  static final String C_BUSINESSCONSULTANTS_PROVINCE = "businessConsultantProvince";
  static final String C_BUSINESSCONSULTANTS_REGION = "businessConsultantRegion";
  static final String C_BUSINESSCONSULTANTS_AREA = "businessConsultantArea";
  static final String C_BUSINESSCONSULTANTS_COUNTRY = "businessConsultantCountry";
  static final String C_BUSINESSCONSULTANTS_PHONE = "businessConsultantPhone";
  static final String C_BUSINESSCONSULTANTS_CELL = "businessConsultantCell";
  static final String C_BUSINESSCONSULTANTS_FAX = "businessConsultantFax";
  static final String C_BUSINESSCONSULTANTS_VOIP = "businessConsultantVoIP";
  static final String C_BUSINESSCONSULTANTS_EMAIL = "businessConsultantEMail";
  static final String C_BUSINESSCONSULTANTS_WEB = "businessConsultantWeb";
  static final String C_BUSINESSCONSULTANTS_TAXPAYERNUMBER = "businessConsultantTaxPayerNumber";
  static final String C_BUSINESSCONSULTANTS_TAXREGISTRATIONNUMBER = "businessConsultantTaxRegistrationNumber";

  // employees table

  static final String T_EMPLOYEES = "Employees";
  static final String C_EMPLOYEES_ID = "employeeId";
  static final String C_EMPLOYEES_NAME = "employeeName";
  static final String C_EMPLOYEES_ADDRESS = "employeeAddress";
  static final String C_EMPLOYEES_POSTALCODE = "employeePostalCode";
  static final String C_EMPLOYEES_ZONE = "employeeZone";
  static final String C_EMPLOYEES_CITY = "employeeCity";
  static final String C_EMPLOYEES_PROVINCE = "employeeProvince";
  static final String C_EMPLOYEES_REGION = "employeeRegion";
  static final String C_EMPLOYEES_AREA = "employeeArea";
  static final String C_EMPLOYEES_COUNTRY = "employeeCountry";
  static final String C_EMPLOYEES_PHONE = "employeePhone";
  static final String C_EMPLOYEES_CELL = "employeeCell";
  static final String C_EMPLOYEES_FAX = "employeeFax";
  static final String C_EMPLOYEES_VOIP = "employeeVoIP";
  static final String C_EMPLOYEES_EMAIL = "employeeEMail";
  static final String C_EMPLOYEES_WEB = "employeeWeb";
  static final String C_EMPLOYEES_TAXPAYERNUMBER = "employeeTaxPayerNumber";
  static final String C_EMPLOYEES_TAXREGISTRATIONNUMBER = "employeeTaxRegistrationNumber";
  static final String C_EMPLOYEES_STARTDATE = "employeeStartDate";
  static final String C_EMPLOYEES_ENDDATE = "employeeEndDate";

  // payment types table

  static final String T_PAYMENTTYPES = "PaymentTypes";
  static final String C_PAYMENTTYPES_CODE = "paymentTypeCode";
  static final String C_PAYMENTTYPES_DESCRIPTION = "paymentTypeName";

  // bank accounts table

  static final String T_BANKACCOUNTS = "BankAccounts";
  static final String C_BANKACCOUNTS_ID = "id";
  static final String C_BANKACCOUNTS_BANKACCOUNTNAME = "bankAccountName";
  static final String C_BANKACCOUNTS_INSTITUTIONNUMBER = "bankInstitutionCode";
  static final String C_BANKACCOUNTS_BRANCHNUMBER = "bankBranchNumber";
  static final String C_BANKACCOUNTS_ACCOUNTNUMBER = "bankAccountNumber";
  static final String C_BANKACCOUNTS_IBAN = "iban";

  // tax categories table

  static final String T_TAXCATEGORIES = "TaxCategories";
  static final String C_TAXCATEGORIES_TAXCATEGORYCODE = "taxCategoryCode";
  static final String C_TAXCATEGORIES_TAXCATEGORYRATE = "taxCategoryRate";
  static final String C_TAXCATEGORIES_TAXCATEGORYDESCRIPTION = "taxCategoryDescription";

  // client work order locations table

  static final String T_CLIENTWORKORDERLOCATIONS = "ClientWorkOrderLocations";
  static final String C_CLIENTWORKORDERLOCATIONS_CLIENTID = "clientId";
  static final String C_CLIENTWORKORDERLOCATIONS_LOCATIONSUBID = "workOrderLocationSubId";
  static final String C_CLIENTWORKORDERLOCATIONS_ADDRESS = "workOrderAdminAddress";
  static final String C_CLIENTWORKORDERLOCATIONS_POSTALCODE = "workOrderPostalCode";
  static final String C_CLIENTWORKORDERLOCATIONS_ZONE = "workOrderZone";
  static final String C_CLIENTWORKORDERLOCATIONS_CITY = "workOrderCity";
  static final String C_CLIENTWORKORDERLOCATIONS_PROVINCE = "workOrderProvince";
  static final String C_CLIENTWORKORDERLOCATIONS_REGION = "workOrderRegion";
  static final String C_CLIENTWORKORDERLOCATIONS_AREA = "workOrderArea";
  static final String C_CLIENTWORKORDERLOCATIONS_COUNTRY = "workOrderCountry";
  static final String C_CLIENTWORKORDERLOCATIONS_LATITUDE = "workOrderLatitude";
  static final String C_CLIENTWORKORDERLOCATIONS_LONGITUDE = "workOrderLongitude";

  // client phone calls table

  static final String T_CLIENTPHONECALLS = "ClientPhoneCalls";
  static final String C_CLIENTPHONECALLS_CLIENTID = "clientId";
  static final String C_CLIENTPHONECALLS_CALLSUBID = "callSubId";
  static final String C_CLIENTPHONECALLS_CALLCALLERNAME = "callerName";
  static final String C_CLIENTPHONECALLS_CALLDATE = "callDate";
  static final String C_CLIENTPHONECALLS_CALLCALLEENAME = "calleeName";
  static final String C_CLIENTPHONECALLS_CALLNOTES = "callNotes";

  // client visits table

  static final String T_CLIENTVISITS = "ClientVisits";
  static final String C_CLIENTVISITS_CLIENTID = "clientId";
  static final String C_CLIENTVISITS_VISITSUBID = "visitSubId";
  static final String C_CLIENTVISITS_VISITVISITORNAME = "visitorName";
  static final String C_CLIENTVISITS_VISITDATE = "visitDate";
  static final String C_CLIENTVISITS_VISITNOTES = "visitNotes";

  // client attachments table

  static final String T_CLIENTATTACHMENTS = "ClientAttachments";
  static final String C_CLIENTATTACHMENTS_CLIENTID = "clientId";
  static final String C_CLIENTATTACHMENTS_ATTACHMENTSUBID = "attachmentSubId";
  static final String C_CLIENTATTACHMENTS_ATTACHMENTNAME = "attachmentName";
  static final String C_CLIENTATTACHMENTS_ATTACHMENTCONTENTS = "attachmentContents";

  // client people in charge with roles view

  static final String V_CLIENTPEOPLEINCHARGEWITHROLES = "ClientPeopleInChargeWithRoles";

  // calendar event table

  static final String T_CALENDAREVENTS = "CalendarEvents";
  static final String C_CALENDAREVENTS_EMPLOYEEID = "employeeId";
  static final String C_CALENDAREVENTS_EVENTSUBID = "eventSubId";
  static final String C_CALENDAREVENTS_EVENTGOOGLEID = "eventGoogleId";
  static final String C_CALENDAREVENTS_EVENTDATE = "eventDate";
  static final String C_CALENDAREVENTS_EVENTSTARTTIME = "startTime";
  static final String C_CALENDAREVENTS_EVENTENDTIME = "endTime";
  static final String C_CALENDAREVENTS_EVENTDESCRIPTION = "description";
  static final String C_CALENDAREVENTS_EVENTCONTACTPERSON = "contactPerson";
  static final String C_CALENDAREVENTS_WORKORDERNAME = "workOrderName";
  static final String C_CALENDAREVENTS_CONSULTANTID = "consultantId";
  static final String C_CALENDAREVENTS_CLIENTID = "clientId";

  // calendar event sharing table

  static final String T_CALENDAREVENTSHARING = "CalendarEventSharing";
  static final String C_CALENDAREVENTS_OWNEREMPLOYEEID = "ownerEmployeeId";
  static final String C_CALENDAREVENTS_OWNEREVENTSUBID = "ownerEventSubId";
  static final String C_CALENDAREVENTS_SHAREEVENT = "viewerSubId";

  // calendar sharing preferences

  static final String T_CALENDARSHARINGPREFERENCES = "calendarSharingPreferences";
  static final String C_CALENDARSHARINGPREFERENCES_OWNEREMPLOYEEID = "ownerEmployeeId";
  static final String C_CALENDARSHARINGPREFERENCES_OTHEREMPLOYEEID = "otherEmployeeId";
  static final String C_CALENDARSHARINGPREFERENCES_CANVIEW = "canView";

  private Context ctx;

  public DBHelper(Context ctx) {

    super(ctx, DB_NAME, null, DB_VERSION);

    this.ctx = ctx;

  }

  @Override
  public void onCreate(SQLiteDatabase db) {

    Log.d(TAG, "Creating database tables...");
    String sql;
    sql = "create table " + T_BUSINESSCATEGORIES + " (" + C_BUSINESSCATEGORIES_ID + " integer not null primary key autoincrement, " + C_BUSINESSCATEGORIES_NAME + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_INITIALCONTACTCHANNELS + " (" + C_INITIALCONTACTCHANNELS_ID + " integer not null primary key autoincrement, " + C_INITIALCONTACTCHANNELS_DESCRIPTION
        + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_BUSINESSCONSULTANTS + " (" + C_BUSINESSCONSULTANTS_ID + " integer not null primary key autoincrement, " + C_BUSINESSCONSULTANTS_NAME + " text, "
        + C_BUSINESSCONSULTANTS_ADDRESS + " text, " + C_BUSINESSCONSULTANTS_POSTALCODE + " text, " + C_BUSINESSCONSULTANTS_ZONE + " text, " + C_BUSINESSCONSULTANTS_CITY
        + " text, " + C_BUSINESSCONSULTANTS_PROVINCE + " text, " + C_BUSINESSCONSULTANTS_REGION + " text, " + C_BUSINESSCONSULTANTS_AREA + " text, "
        + C_BUSINESSCONSULTANTS_COUNTRY + " text, " + C_BUSINESSCONSULTANTS_PHONE + " text, " + C_BUSINESSCONSULTANTS_CELL + " text, " + C_BUSINESSCONSULTANTS_FAX + " text, "
        + C_BUSINESSCONSULTANTS_VOIP + " text, " + C_BUSINESSCONSULTANTS_EMAIL + " text, " + C_BUSINESSCONSULTANTS_WEB + " text, " + C_BUSINESSCONSULTANTS_TAXPAYERNUMBER
        + " text, " + C_BUSINESSCONSULTANTS_TAXREGISTRATIONNUMBER + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_EMPLOYEES + " (" + C_EMPLOYEES_ID + " integer not null primary key autoincrement, " + C_EMPLOYEES_NAME + " text, " + C_EMPLOYEES_ADDRESS + " text, "
        + C_EMPLOYEES_POSTALCODE + " text, " + C_EMPLOYEES_ZONE + " text, " + C_EMPLOYEES_CITY + " text, " + C_EMPLOYEES_PROVINCE + " text, " + C_EMPLOYEES_REGION + " text, "
        + C_EMPLOYEES_AREA + " text, " + C_EMPLOYEES_COUNTRY + " text, " + C_EMPLOYEES_PHONE + " text, " + C_EMPLOYEES_CELL + " text, " + C_EMPLOYEES_FAX + " text, "
        + C_EMPLOYEES_VOIP + " text, " + C_EMPLOYEES_EMAIL + " text, " + C_EMPLOYEES_WEB + " text, " + C_EMPLOYEES_TAXPAYERNUMBER + " text, " + C_EMPLOYEES_TAXREGISTRATIONNUMBER
        + " text, " + C_EMPLOYEES_STARTDATE + " text, " + C_EMPLOYEES_ENDDATE + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_PAYMENTTYPES + " (" + C_PAYMENTTYPES_CODE + " text not null primary key, " + C_PAYMENTTYPES_DESCRIPTION + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_BANKACCOUNTS + " (" + C_BANKACCOUNTS_ID + " integer not null primary key autoincrement, " + C_BANKACCOUNTS_BANKACCOUNTNAME + " text, "
        + C_BANKACCOUNTS_INSTITUTIONNUMBER + " text, " + C_BANKACCOUNTS_BRANCHNUMBER + " text, " + C_BANKACCOUNTS_ACCOUNTNUMBER + " text, " + C_BANKACCOUNTS_IBAN + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_TAXCATEGORIES + " (" + C_TAXCATEGORIES_TAXCATEGORYCODE + " text not null primary key, " + C_TAXCATEGORIES_TAXCATEGORYRATE + " text,"
        + C_TAXCATEGORIES_TAXCATEGORYDESCRIPTION + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_CLIENTS + " (" + C_CLIENTS_ID + " integer not null primary key autoincrement, " + C_CLIENTS_CODE + " text, " + C_CLIENTS_COMPANYNAME + " text, "
        + C_CLIENTS_ADMINHEADQUARTERSADDRESS + " text, " + C_CLIENTS_ADMINHEADQUARTERSPOSTALCODE + " text, " + C_CLIENTS_ADMINHEADQUARTERSZONE + " text, "
        + C_CLIENTS_ADMINHEADQUARTERSCITY + " text, " + C_CLIENTS_ADMINHEADQUARTERSPROVINCE + " text, " + C_CLIENTS_ADMINHEADQUARTERSREGION + " text, "
        + C_CLIENTS_ADMINHEADQUARTERSAREA + " text, " + C_CLIENTS_ADMINHEADQUARTERSCOUNTRY + " text, " + C_CLIENTS_ADMINHEADQUARTERSLATITUDE + " float, "
        + C_CLIENTS_ADMINHEADQUARTERSLONGITUDE + " float, " + C_CLIENTS_REGISTEREDOFFICEADDRESS + " text, " + C_CLIENTS_REGISTEREDOFFICEPOSTALCODE + " text, "
        + C_CLIENTS_REGISTEREDOFFICEZONE + " text, " + C_CLIENTS_REGISTEREDOFFICECITY + " text, " + C_CLIENTS_REGISTEREDOFFICEPROVINCE + " text, "
        + C_CLIENTS_REGISTEREDOFFICEREGION + " text, " + C_CLIENTS_REGISTEREDOFFICEAREA + " text, " + C_CLIENTS_REGISTEREDOFFICECOUNTRY + " text, "
        + C_CLIENTS_REGISTEREDOFFICELATITUDE + " float, " + C_CLIENTS_REGISTEREDOFFICELONGITUDE + " float, " + C_CLIENTS_REGISTEREDOFFICECERTIFIEDEMAIL + " text, "
        + C_CLIENTS_REGISTEREDOFFICEDEFAULTCURRENCY + " text, " + C_CLIENTS_REGISTEREDOFFICETAXPAYERNUMBER + " text, " + C_CLIENTS_REGISTEREDOFFICETAXREGISTRATIONNUMBER
        + " text, " + C_CLIENTS_MAINPERSONINCHARGENAME + " text, " + C_CLIENTS_MAINPERSONINCHARGEPHONE + " text, " + C_CLIENTS_MAINPERSONINCHARGECELL + " text, "
        + C_CLIENTS_MAINPERSONINCHARGEFAX + " text, " + C_CLIENTS_MAINPERSONINCHARGEVOIP + " text, " + C_CLIENTS_MAINPERSONINCHARGEEMAIL + " text, "
        + C_CLIENTS_MAINPERSONINCHARGEWEB + " text, " + C_CLIENTS_BUSINESSDATABUSINESSCATEGORYID + " integer, " + C_CLIENTS_BUSINESSDATAINITIALCONTACTCHANNELID + " integer, "
        + C_CLIENTS_BUSINESSDATABUSINESSCONSULTANTID + " integer, " + C_CLIENTS_BUSINESSDATAREPORTINGTECHNICIANEMPLOYEEID + " integer, "
        + C_CLIENTS_BUSINESSDATACONTRACTISSUINGEMPLOYEEID + " integer, " + C_CLIENTS_ACCOUNTINGINFOPAYMENTTYPECODE + " text, " + C_CLIENTS_ACCOUNTINGINFOID + " integer, "
        + C_CLIENTS_ACCOUNTINGINFOTAXCATEGORYCODE + " text, " + C_CLIENTS_EFFECTIVEORPOTENTIAL + " integer, " + C_CLIENTS_NOTES + " text, " + "foreign key ("
        + C_CLIENTS_BUSINESSDATABUSINESSCATEGORYID + ") references " + T_BUSINESSCATEGORIES + " (" + C_BUSINESSCATEGORIES_ID + "), " + "foreign key ("
        + C_CLIENTS_BUSINESSDATAINITIALCONTACTCHANNELID + ") references " + T_INITIALCONTACTCHANNELS + " (" + C_INITIALCONTACTCHANNELS_ID + "), " + "foreign key ("
        + C_CLIENTS_BUSINESSDATABUSINESSCONSULTANTID + ") references " + T_BUSINESSCONSULTANTS + " (" + C_BUSINESSCONSULTANTS_ID + "), " + "foreign key ("
        + C_CLIENTS_BUSINESSDATAREPORTINGTECHNICIANEMPLOYEEID + ") references " + T_EMPLOYEES + " (" + C_EMPLOYEES_ID + "), " + "foreign key ("
        + C_CLIENTS_BUSINESSDATACONTRACTISSUINGEMPLOYEEID + ") references " + T_EMPLOYEES + " (" + C_EMPLOYEES_ID + "), " + "foreign key ("
        + C_CLIENTS_ACCOUNTINGINFOPAYMENTTYPECODE + ") references " + T_PAYMENTTYPES + " (" + C_PAYMENTTYPES_CODE + "), " + "foreign key (" + C_CLIENTS_ACCOUNTINGINFOID
        + ") references " + T_BANKACCOUNTS + " (" + C_BANKACCOUNTS_ID + "), " + "foreign key (" + C_CLIENTS_ACCOUNTINGINFOTAXCATEGORYCODE + ") references " + T_TAXCATEGORIES
        + " (" + C_TAXCATEGORIES_TAXCATEGORYCODE + "))";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_BUSINESSAREAS + " (" + C_BUSINESSAREAS_CODE + " text not null primary key, " + C_BUSINESSAREAS_DESCRIPTION + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_CLIENTBUSINESSAREAS + " (" + C_CLIENTBUSINESSAREAS_CLIENTID + " integer not null, " + C_CLIENTBUSINESSAREAS_BUSINESSAREACODE + " text not null, "
        + C_CLIENTBUSINESSAREAS_PRIMARYORSECONDARYCONCERN + " text, " + "primary key (" + C_CLIENTBUSINESSAREAS_CLIENTID + ", " + C_CLIENTBUSINESSAREAS_BUSINESSAREACODE + "), "
        + "foreign key (" + C_CLIENTBUSINESSAREAS_CLIENTID + ") references " + T_CLIENTS + " (" + C_CLIENTS_ID + "), " + "foreign key (" + C_CLIENTBUSINESSAREAS_BUSINESSAREACODE
        + ") references " + T_BUSINESSAREAS + " (" + C_BUSINESSAREAS_CODE + "))";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_PERSONINCHARGEROLES + " (" + C_PERSONINCHARGEROLES_ID + " integer not null primary key autoincrement, " + C_PERSONINCHARGEROLES_NAME + " text)";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_CLIENTPEOPLEINCHARGE + " (" + C_CLIENTPEOPLEINCHARGE_CLIENTID + " integer not null, " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGESUBID
        + " integer not null, " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEROLEID + " integer not null, " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGENAME + ", text, "
        + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEPHONE + " text, " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGECELL + " text, " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEFAX + " text, "
        + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEVOIP + " text, " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEEMAIL + " text, " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEWEB + " text, "
        + "primary key (" + C_CLIENTPEOPLEINCHARGE_CLIENTID + ", " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGESUBID + "), " + "foreign key (" + C_CLIENTPEOPLEINCHARGE_CLIENTID
        + ") references " + T_CLIENTS + " (" + C_CLIENTS_ID + "), " + "foreign key (" + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEROLEID + ") references " + T_PERSONINCHARGEROLES
        + " (" + C_PERSONINCHARGEROLES_ID + "))";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_CLIENTWORKORDERLOCATIONS + " (" + C_CLIENTWORKORDERLOCATIONS_CLIENTID + " integer not null, " + C_CLIENTWORKORDERLOCATIONS_LOCATIONSUBID
        + " integer not null, " + C_CLIENTWORKORDERLOCATIONS_ADDRESS + " text, " + C_CLIENTWORKORDERLOCATIONS_POSTALCODE + " text, " + C_CLIENTWORKORDERLOCATIONS_ZONE + " text, "
        + C_CLIENTWORKORDERLOCATIONS_CITY + " text, " + C_CLIENTWORKORDERLOCATIONS_PROVINCE + " text, " + C_CLIENTWORKORDERLOCATIONS_REGION + " text, "
        + C_CLIENTWORKORDERLOCATIONS_AREA + " text, " + C_CLIENTWORKORDERLOCATIONS_COUNTRY + " text, " + C_CLIENTWORKORDERLOCATIONS_LATITUDE + " text, "
        + C_CLIENTWORKORDERLOCATIONS_LONGITUDE + " text, " + "primary key (" + C_CLIENTWORKORDERLOCATIONS_CLIENTID + ", " + C_CLIENTWORKORDERLOCATIONS_LOCATIONSUBID + "), "
        + "foreign key (" + C_CLIENTPEOPLEINCHARGE_CLIENTID + ") references " + T_CLIENTS + " (" + C_CLIENTS_ID + "))";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_CLIENTPHONECALLS + " (" + C_CLIENTPHONECALLS_CLIENTID + " integer not null, " + C_CLIENTPHONECALLS_CALLSUBID + " integer not null, "
        + C_CLIENTPHONECALLS_CALLCALLERNAME + " text, " + C_CLIENTPHONECALLS_CALLDATE + " text, " + C_CLIENTPHONECALLS_CALLCALLEENAME + " text, " + C_CLIENTPHONECALLS_CALLNOTES
        + " text, " + "primary key (" + C_CLIENTPHONECALLS_CLIENTID + ", " + C_CLIENTPHONECALLS_CALLSUBID + "), " + "foreign key (" + C_CLIENTPEOPLEINCHARGE_CLIENTID
        + ") references " + T_CLIENTS + " (" + C_CLIENTS_ID + "))";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_CLIENTVISITS + " (" + C_CLIENTVISITS_CLIENTID + " integer not null, " + C_CLIENTVISITS_VISITSUBID + " integer not null, "
        + C_CLIENTVISITS_VISITVISITORNAME + " text, " + C_CLIENTVISITS_VISITDATE + " text, " + C_CLIENTVISITS_VISITNOTES + " text, " + "primary key (" + C_CLIENTVISITS_CLIENTID
        + ", " + C_CLIENTVISITS_VISITSUBID + "), " + "foreign key (" + C_CLIENTPEOPLEINCHARGE_CLIENTID + ") references " + T_CLIENTS + " (" + C_CLIENTS_ID + "))";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_CLIENTATTACHMENTS + " (" + C_CLIENTATTACHMENTS_CLIENTID + " integer not null, " + C_CLIENTATTACHMENTS_ATTACHMENTSUBID + " integer not null, "
        + C_CLIENTATTACHMENTS_ATTACHMENTNAME + " text, " + C_CLIENTATTACHMENTS_ATTACHMENTCONTENTS + " text, " + "foreign key (" + C_CLIENTPEOPLEINCHARGE_CLIENTID + ") references "
        + T_CLIENTS + " (" + C_CLIENTS_ID + "))";
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create view " + V_CLIENTPEOPLEINCHARGEWITHROLES + " as select " + C_CLIENTPEOPLEINCHARGE_CLIENTID + ", " + C_PERSONINCHARGEROLES_NAME + ", "
        + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEROLEID + ", " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGESUBID + ", " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGENAME + ", "
        + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEPHONE + ", " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGECELL + ", " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEFAX + ", "
        + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEVOIP + ", " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEEMAIL + ", " + C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEWEB + " from "
        + T_CLIENTPEOPLEINCHARGE + " natural join " + T_PERSONINCHARGEROLES + " order by " + C_PERSONINCHARGEROLES_NAME;
    Log.d(TAG, sql);
    db.execSQL(sql);
    sql = "create table " + T_CALENDAREVENTS + " (" + C_CALENDAREVENTS_EMPLOYEEID + " integer not null, " + C_CALENDAREVENTS_EVENTSUBID + " integer not null, "
        + C_CALENDAREVENTS_EVENTGOOGLEID + " text, "
        + C_CALENDAREVENTS_EVENTDATE + " text, " + C_CALENDAREVENTS_EVENTSTARTTIME + " text, " + C_CALENDAREVENTS_EVENTENDTIME + " text, " + C_CALENDAREVENTS_EVENTDESCRIPTION
        + " text, " + C_CALENDAREVENTS_EVENTCONTACTPERSON + " text, " + C_CALENDAREVENTS_WORKORDERNAME + " text, " + C_CALENDAREVENTS_CONSULTANTID + " integer, "
        + C_CALENDAREVENTS_CLIENTID + " integer, " + "primary key (" + C_CALENDAREVENTS_EMPLOYEEID + ", " + C_CALENDAREVENTS_EVENTSUBID + "), " + "foreign key ("
        + C_CALENDAREVENTS_CONSULTANTID + ") references " + T_BUSINESSCONSULTANTS + " (" + C_BUSINESSCONSULTANTS_ID + "), " + "foreign key (" + C_CALENDAREVENTS_CLIENTID
        + ") references " + T_CLIENTS + " (" + C_CLIENTS_ID + "))";
    Log.d(TAG, sql);
    db.execSQL(sql);
    Log.d(TAG, "Done.");

    // populate tables with default data

    populate(db);

  }

  private Document getDOMDocument(InputStream is) {

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    DocumentBuilder db = null;

    try {

      db = dbf.newDocumentBuilder();

    } catch (ParserConfigurationException e) {

      e.printStackTrace();

      return null;

    }

    Document doc = null;

    try {

      doc = db.parse(is);

    } catch (SAXException e) {

      System.out.println("XML file is not well formed.");

      e.printStackTrace();

    } catch (IOException e) {

      e.printStackTrace();

    }
    
    return doc;

  }
  
  private static final String EASECTORS_CODE = "code";
  private static final String EASECTORS_DESC = "desc";
  private static final String BUSINESSCATEGORIES_CATEGORY = "category";
  private static final String CONTACTPERSONROLES_ROLE = "role";
  private static final String INITIALCHANNELS_CHANNEL = "channel";

  private void populate(SQLiteDatabase db) {
    
    Log.d(TAG, "Populating databases..."); 
    
    Document doc;
    
    // populate business areas

    List<String> codes = new ArrayList<String>();
    List<String> descs = new ArrayList<String>();
    
    doc = getDOMDocument(ctx.getResources().openRawResource(R.raw.easectors));
    
    NodeList nlSectors = ((Element) doc.getChildNodes().item(0)).getChildNodes();
    
    for (int i = 0; i < nlSectors.getLength(); i++) {
      
      Node node = nlSectors.item(i);
      
      if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(EASECTORS_CODE))
        
        codes.add(((Element) node).getTextContent());
      
      else if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(EASECTORS_DESC))
        
        descs.add(((Element) node).getTextContent());
      
    }
    
    if (codes.size() != descs.size()) {
      System.out.println("Number of EA sector codes does not match number of EA sector descriptions.");
      System.exit(-1);
    }
    
    ContentValues values = new ContentValues();
    
    for (int i = 0; i < codes.size(); i++) {
      
      values.clear();
      values.put(DBHelper.C_BUSINESSAREAS_CODE, codes.get(i));
      values.put(DBHelper.C_BUSINESSAREAS_DESCRIPTION, descs.get(i));
      db.insertOrThrow(DBHelper.T_BUSINESSAREAS, null, values);
      
    }
    
    // populate business categories
    
    List<String> businessCategories = new ArrayList<String>();
    
    doc = getDOMDocument(ctx.getResources().openRawResource(R.raw.businesscategories));
    
    NodeList nlCategories = ((Element) doc.getChildNodes().item(0)).getChildNodes();
    
    for (int i = 0; i < nlCategories.getLength(); i++) {
      
      Node node = nlCategories.item(i);
      
      if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(BUSINESSCATEGORIES_CATEGORY))
        
        businessCategories.add(((Element) node).getTextContent());
      
    }
 
    for (int i = 0; i < businessCategories.size(); i++) {
      
      values.clear();
      values.put(DBHelper.C_BUSINESSCATEGORIES_NAME, businessCategories.get(i));
      db.insertOrThrow(DBHelper.T_BUSINESSCATEGORIES, null, values);
      
    }
     
    // populate contact person roles
    
    List<String> roles = new ArrayList<String>();
    
    doc = getDOMDocument(ctx.getResources().openRawResource(R.raw.contactpersonroles));
    
    NodeList nlRoles = ((Element) doc.getChildNodes().item(0)).getChildNodes();
    
    for (int i = 0; i < nlRoles.getLength(); i++) {
      
      Node node = nlRoles.item(i);
      
      if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(CONTACTPERSONROLES_ROLE))
        
        roles.add(((Element) node).getTextContent());
      
    }
 
    for (int i = 0; i < roles.size(); i++) {
      
      values.clear();
      values.put(DBHelper.C_PERSONINCHARGEROLES_NAME, roles.get(i));
      db.insertOrThrow(DBHelper.T_PERSONINCHARGEROLES, null, values);
      
    }
  
    // populate initial contact channels
    
    List<String> channels = new ArrayList<String>();
    
    doc = getDOMDocument(ctx.getResources().openRawResource(R.raw.initialcontactchannels));
    
    NodeList nlChannels = ((Element) doc.getChildNodes().item(0)).getChildNodes();
    
    for (int i = 0; i < nlChannels.getLength(); i++) {
      
      Node node = nlChannels.item(i);
      
      if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(INITIALCHANNELS_CHANNEL))
        
        channels.add(((Element) node).getTextContent());
      
    }
 
    for (int i = 0; i < channels.size(); i++) {
      
      values.clear();
      values.put(DBHelper.C_INITIALCONTACTCHANNELS_DESCRIPTION, channels.get(i));
      db.insertOrThrow(DBHelper.T_INITIALCONTACTCHANNELS, null, values);
      
    }
      
    Log.d(TAG, "Done.");
    
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    Log.d(TAG, "Recreating database tables...");
    db.execSQL("drop table if exists " + T_CALENDAREVENTS);
    db.execSQL("drop view if exists " + V_CLIENTPEOPLEINCHARGEWITHROLES);
    db.execSQL("drop table if exists " + T_CLIENTATTACHMENTS);
    db.execSQL("drop table if exists " + T_CLIENTVISITS);
    db.execSQL("drop table if exists " + T_CLIENTPHONECALLS);
    db.execSQL("drop table if exists " + T_CLIENTWORKORDERLOCATIONS);
    db.execSQL("drop table if exists " + T_CLIENTPEOPLEINCHARGE);
    db.execSQL("drop table if exists " + T_PERSONINCHARGEROLES);
    db.execSQL("drop table if exists " + T_CLIENTBUSINESSAREAS);
    db.execSQL("drop table if exists " + T_BUSINESSAREAS);
    db.execSQL("drop table if exists " + T_CLIENTS);
    db.execSQL("drop table if exists " + T_TAXCATEGORIES);
    db.execSQL("drop table if exists " + T_BANKACCOUNTS);
    db.execSQL("drop table if exists " + T_PAYMENTTYPES);
    db.execSQL("drop table if exists " + T_EMPLOYEES);
    db.execSQL("drop table if exists " + T_BUSINESSCONSULTANTS);
    db.execSQL("drop table if exists " + T_INITIALCONTACTCHANNELS);
    db.execSQL("drop table if exists " + T_BUSINESSCATEGORIES);
    onCreate(db);
    Log.d(TAG, "Done.");

  }

}

class DB {

  private DB(Context ctx) {

    dbHelper = new DBHelper(ctx);
    database = dbHelper.getWritableDatabase();

  }

  public static DB db(Context ctx) {
    if (db == null)
      db = new DB(ctx);
    return db;
  }

  // store and retrieve data

  public Data storeAndRetrieve(Data data) {

    putData(data);

    Data retData = getData();

    return retData;

  }

  public Data getData() {

    // retrieve everything form the database

    Data data = new Data();

    Cursor ci;
    ci = db.database.query(DBHelper.T_BUSINESSCATEGORIES, null, null, null, null, null, DBHelper.C_BUSINESSCATEGORIES_NAME);
    while (ci.moveToNext()) {
      BusinessCategory businessCategory = new BusinessCategory();
      businessCategory.id = ci.getLong(ci.getColumnIndex(DBHelper.C_BUSINESSCATEGORIES_ID));
      businessCategory.name = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCATEGORIES_NAME));
      data.businessCategories.add(businessCategory);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_INITIALCONTACTCHANNELS, null, null, null, null, null, DBHelper.C_INITIALCONTACTCHANNELS_DESCRIPTION);
    while (ci.moveToNext()) {
      InitialContactChannel initialContactChannel = new InitialContactChannel();
      initialContactChannel.id = ci.getLong(ci.getColumnIndex(DBHelper.C_INITIALCONTACTCHANNELS_ID));
      initialContactChannel.name = ci.getString(ci.getColumnIndex(DBHelper.C_INITIALCONTACTCHANNELS_DESCRIPTION));
      data.initialContactChannels.add(initialContactChannel);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_BUSINESSCONSULTANTS, null, null, null, null, null, DBHelper.C_BUSINESSCONSULTANTS_NAME);
    while (ci.moveToNext()) {
      BusinessConsultant businessConsultant = new BusinessConsultant();
      businessConsultant.id = ci.getLong(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_ID));
      businessConsultant.name = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_NAME));
      businessConsultant.address = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_ADDRESS));
      businessConsultant.postalCode = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_POSTALCODE));
      businessConsultant.zone = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_ZONE));
      businessConsultant.city = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_CITY));
      businessConsultant.province = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_PROVINCE));
      businessConsultant.region = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_REGION));
      businessConsultant.area = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_AREA));
      businessConsultant.country = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_COUNTRY));
      businessConsultant.phone = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_PHONE));
      businessConsultant.cell = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_CELL));
      businessConsultant.fax = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_FAX));
      businessConsultant.voip = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_VOIP));
      businessConsultant.email = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_EMAIL));
      businessConsultant.web = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_WEB));
      businessConsultant.taxPayerNumber = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_TAXPAYERNUMBER));
      businessConsultant.taxRegistrationNumber = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSCONSULTANTS_TAXREGISTRATIONNUMBER));
      data.businessConsultants.add(businessConsultant);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_EMPLOYEES, null, null, null, null, null, DBHelper.C_EMPLOYEES_NAME);
    while (ci.moveToNext()) {
      Employee employee = new Employee();
      employee.id = ci.getLong(ci.getColumnIndex(DBHelper.C_EMPLOYEES_ID));
      employee.name = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_NAME));
      employee.address = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_ADDRESS));
      employee.postalCode = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_POSTALCODE));
      employee.zone = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_ZONE));
      employee.city = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_CITY));
      employee.province = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_PROVINCE));
      employee.region = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_REGION));
      employee.area = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_AREA));
      employee.country = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_COUNTRY));
      employee.phone = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_PHONE));
      employee.cell = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_CELL));
      employee.fax = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_FAX));
      employee.voip = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_VOIP));
      employee.email = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_EMAIL));
      employee.web = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_WEB));
      employee.taxPayerNumber = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_TAXPAYERNUMBER));
      employee.taxRegistrationNumber = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_TAXREGISTRATIONNUMBER));
      employee.startDate = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_STARTDATE));
      employee.endDate = ci.getString(ci.getColumnIndex(DBHelper.C_EMPLOYEES_ENDDATE));
      data.employees.add(employee);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_PAYMENTTYPES, null, null, null, null, null, DBHelper.C_PAYMENTTYPES_DESCRIPTION);
    while (ci.moveToNext()) {
      PaymentType paymentType = new PaymentType();
      paymentType.code = ci.getString(ci.getColumnIndex(DBHelper.C_PAYMENTTYPES_CODE));
      paymentType.description = ci.getString(ci.getColumnIndex(DBHelper.C_PAYMENTTYPES_DESCRIPTION));
      data.paymentTypes.add(paymentType);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_BANKACCOUNTS, null, null, null, null, null, DBHelper.C_BANKACCOUNTS_BANKACCOUNTNAME);
    while (ci.moveToNext()) {
      BankAccount bankAccount = new BankAccount();
      bankAccount.id = ci.getLong(ci.getColumnIndex(DBHelper.C_BANKACCOUNTS_ID));
      bankAccount.bankAccountName = ci.getString(ci.getColumnIndex(DBHelper.C_BANKACCOUNTS_BANKACCOUNTNAME));
      bankAccount.institutionNumber = ci.getString(ci.getColumnIndex(DBHelper.C_BANKACCOUNTS_INSTITUTIONNUMBER));
      bankAccount.branchNumber = ci.getString(ci.getColumnIndex(DBHelper.C_BANKACCOUNTS_BRANCHNUMBER));
      bankAccount.accountNumber = ci.getString(ci.getColumnIndex(DBHelper.C_BANKACCOUNTS_ACCOUNTNUMBER));
      bankAccount.iban = ci.getString(ci.getColumnIndex(DBHelper.C_BANKACCOUNTS_IBAN));
      data.bankAccounts.add(bankAccount);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_TAXCATEGORIES, null, null, null, null, null, DBHelper.C_TAXCATEGORIES_TAXCATEGORYCODE);
    while (ci.moveToNext()) {
      TaxCategory taxCategory = new TaxCategory();
      taxCategory.code = ci.getString(ci.getColumnIndex(DBHelper.C_TAXCATEGORIES_TAXCATEGORYCODE));
      taxCategory.rate = ci.getString(ci.getColumnIndex(DBHelper.C_TAXCATEGORIES_TAXCATEGORYRATE));
      taxCategory.description = ci.getString(ci.getColumnIndex(DBHelper.C_TAXCATEGORIES_TAXCATEGORYDESCRIPTION));
      data.taxCategories.add(taxCategory);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_BUSINESSAREAS, null, null, null, null, null, DBHelper.C_BUSINESSAREAS_CODE);
    while (ci.moveToNext()) {
      BusinessArea businessArea = new BusinessArea();
      businessArea.code = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSAREAS_CODE));
      businessArea.description = ci.getString(ci.getColumnIndex(DBHelper.C_BUSINESSAREAS_DESCRIPTION));
      data.businessAreas.add(businessArea);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_PERSONINCHARGEROLES, null, null, null, null, null, DBHelper.C_PERSONINCHARGEROLES_NAME);
    while (ci.moveToNext()) {
      PersonInChargeRole personInChargeRole = new PersonInChargeRole();
      personInChargeRole.id = ci.getLong(ci.getColumnIndex(DBHelper.C_PERSONINCHARGEROLES_ID));
      personInChargeRole.name = ci.getString(ci.getColumnIndex(DBHelper.C_PERSONINCHARGEROLES_NAME));
      data.personInChargeRoles.add(personInChargeRole);
    }
    ci.close();
    ci = db.database.query(DBHelper.T_CLIENTS, null, null, null, null, null, DBHelper.C_CLIENTS_COMPANYNAME);
    while (ci.moveToNext()) {
      Client client = new Client();
      client.id = ci.getLong(ci.getColumnIndex(DBHelper.C_CLIENTS_ID));
      client.code = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_CODE));
      client.companyName = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_COMPANYNAME));
      client.adminAddress = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSADDRESS));
      client.adminPostalCode = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSPOSTALCODE));
      client.adminZone = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSZONE));
      client.adminCity = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSCITY));
      client.adminProvince = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSPROVINCE));
      client.adminRegion = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSREGION));
      client.adminArea = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSAREA));
      client.adminCountry = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSCOUNTRY));
      client.adminLatitude = ci.getDouble(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSLATITUDE));
      client.adminLongitude = ci.getDouble(ci.getColumnIndex(DBHelper.C_CLIENTS_ADMINHEADQUARTERSLONGITUDE));
      client.officeAddress = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICEADDRESS));
      client.officePostalCode = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICEPOSTALCODE));
      client.officeZone = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICEZONE));
      client.officeCity = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICECITY));
      client.officeProvince = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICEPROVINCE));
      client.officeRegion = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICEREGION));
      client.officeArea = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICEAREA));
      client.officeCountry = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICECOUNTRY));
      client.officeLatitude = ci.getDouble(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICELATITUDE));
      client.officeLongitude = ci.getDouble(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICELONGITUDE));
      client.certifiedEMail = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICECERTIFIEDEMAIL));
      client.defaultCurrency = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICEDEFAULTCURRENCY));
      client.taxPayerNumber = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICETAXPAYERNUMBER));
      client.taxRegistrationNumber = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_REGISTEREDOFFICETAXREGISTRATIONNUMBER));
      client.mainPersonInChargeName = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_MAINPERSONINCHARGENAME));
      client.mainPersonInChargePhone = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_MAINPERSONINCHARGEPHONE));
      client.mainPersonInChargeCell = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_MAINPERSONINCHARGECELL));
      client.mainPersonInChargeFax = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_MAINPERSONINCHARGEFAX));
      client.mainPersonInChargeVoIP = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_MAINPERSONINCHARGEVOIP));
      client.mainPersonInChargeEMail = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_MAINPERSONINCHARGEEMAIL));
      client.mainPersonInChargeWeb = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_MAINPERSONINCHARGEWEB));
      client.isEffectiveOrPotentialClient = ci.getInt(ci.getColumnIndex(DBHelper.C_CLIENTS_EFFECTIVEORPOTENTIAL));
      client.notes = ci.getString(ci.getColumnIndex(DBHelper.C_CLIENTS_NOTES));
      Cursor cj;
      cj = db.database.query(DBHelper.T_CLIENTBUSINESSAREAS, null, DBHelper.C_CLIENTBUSINESSAREAS_CLIENTID + " = ?", new String[] { Long.valueOf(client.id).toString() }, null,
          null, DBHelper.C_CLIENTBUSINESSAREAS_BUSINESSAREACODE);
      while (cj.moveToNext()) {
        ConcernedBusinessArea concernedBusinessArea = new ConcernedBusinessArea();
        String businessAreaCode = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTBUSINESSAREAS_BUSINESSAREACODE));
        for (BusinessArea businessArea : data.businessAreas)
          if (businessArea.code.equals(businessAreaCode))
            concernedBusinessArea.businessArea = businessArea;
        concernedBusinessArea.isPrimaryOrSecondaryConcern = cj.getInt(cj.getColumnIndex(DBHelper.C_CLIENTBUSINESSAREAS_PRIMARYORSECONDARYCONCERN));
        client.concernedBusinessAreas.add(concernedBusinessArea);
      }
      cj.close();
      cj = db.database.query(DBHelper.V_CLIENTPEOPLEINCHARGEWITHROLES, null, DBHelper.C_CLIENTBUSINESSAREAS_CLIENTID + " = ?", new String[] { Long.valueOf(client.id).toString() },
          null, null, null);
      while (cj.moveToNext()) {
        PersonInCharge personInCharge = new PersonInCharge();
        personInCharge.subId = cj.getLong(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGESUBID));
        long roleId = cj.getLong(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEROLEID));
        for (PersonInChargeRole personInChargeRole : data.personInChargeRoles)
          if (personInChargeRole.id == roleId)
            personInCharge.role = personInChargeRole;
        personInCharge.name = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGENAME));
        personInCharge.phone = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEPHONE));
        personInCharge.cell = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGECELL));
        personInCharge.fax = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEFAX));
        personInCharge.voip = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEVOIP));
        personInCharge.email = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEEMAIL));
        personInCharge.web = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEWEB));
        client.otherPeopleInCharge.add(personInCharge);
      }
      cj.close();
      cj = db.database.query(DBHelper.T_CLIENTWORKORDERLOCATIONS, null, DBHelper.C_CLIENTBUSINESSAREAS_CLIENTID + " = ?", new String[] { Long.valueOf(client.id).toString() },
          null, null, DBHelper.C_CLIENTWORKORDERLOCATIONS_COUNTRY + ", " + DBHelper.C_CLIENTWORKORDERLOCATIONS_AREA + ", " + DBHelper.C_CLIENTWORKORDERLOCATIONS_REGION + ", "
              + DBHelper.C_CLIENTWORKORDERLOCATIONS_PROVINCE + ", " + DBHelper.C_CLIENTWORKORDERLOCATIONS_CITY + ", " + DBHelper.C_CLIENTWORKORDERLOCATIONS_ZONE + ",  "
              + DBHelper.C_CLIENTWORKORDERLOCATIONS_POSTALCODE + ", " + DBHelper.C_CLIENTWORKORDERLOCATIONS_ADDRESS);
      while (cj.moveToNext()) {
        WorkOrderLocation workOrderLocation = new WorkOrderLocation();
        workOrderLocation.subId = cj.getLong(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_LOCATIONSUBID));
        workOrderLocation.address = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_ADDRESS));
        workOrderLocation.postalCode = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_POSTALCODE));
        workOrderLocation.zone = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_ZONE));
        workOrderLocation.city = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_CITY));
        workOrderLocation.province = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_PROVINCE));
        workOrderLocation.region = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_REGION));
        workOrderLocation.area = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_AREA));
        workOrderLocation.country = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_COUNTRY));
        workOrderLocation.latitude = cj.getDouble(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_LATITUDE));
        workOrderLocation.longitude = cj.getDouble(cj.getColumnIndex(DBHelper.C_CLIENTWORKORDERLOCATIONS_LONGITUDE));
        client.workOrderLocations.add(workOrderLocation);
      }
      cj.close();
      cj = db.database.query(DBHelper.T_CLIENTPHONECALLS, null, DBHelper.C_CLIENTBUSINESSAREAS_CLIENTID + " = ?", new String[] { Long.valueOf(client.id).toString() }, null, null,
          DBHelper.C_CLIENTPHONECALLS_CALLDATE);
      while (cj.moveToNext()) {
        ClientPhoneCall clientPhoneCall = new ClientPhoneCall();
        clientPhoneCall.callerName = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPHONECALLS_CALLCALLERNAME));
        clientPhoneCall.callDate = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPHONECALLS_CALLDATE));
        clientPhoneCall.calleeName = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPHONECALLS_CALLCALLEENAME));
        clientPhoneCall.callNotes = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTPHONECALLS_CALLNOTES));
        client.phoneCalls.add(clientPhoneCall);
      }
      cj.close();
      cj = db.database.query(DBHelper.T_CLIENTVISITS, null, DBHelper.C_CLIENTVISITS_CLIENTID + " = ?", new String[] { Long.valueOf(client.id).toString() }, null, null,
          DBHelper.C_CLIENTVISITS_VISITDATE);
      while (cj.moveToNext()) {
        ClientVisit clientVisit = new ClientVisit();
        clientVisit.visitorName = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTVISITS_VISITVISITORNAME));
        clientVisit.visitDate = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTVISITS_VISITDATE));
        clientVisit.visitNotes = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTVISITS_VISITNOTES));
        client.visits.add(clientVisit);
      }
      cj.close();
      cj = db.database.query(DBHelper.T_CLIENTATTACHMENTS, null, DBHelper.C_CLIENTVISITS_CLIENTID + " = ?", new String[] { Long.valueOf(client.id).toString() }, null, null,
          DBHelper.C_CLIENTATTACHMENTS_ATTACHMENTNAME + ", " + DBHelper.C_CLIENTATTACHMENTS_ATTACHMENTSUBID);
      while (cj.moveToNext()) {
        ClientAttachment clientAttachment = new ClientAttachment();
        clientAttachment.subId = cj.getLong(cj.getColumnIndex(DBHelper.C_CLIENTATTACHMENTS_ATTACHMENTSUBID));
        clientAttachment.name = cj.getString(cj.getColumnIndex(DBHelper.C_CLIENTATTACHMENTS_ATTACHMENTNAME));
        /*
         * C_CLIENTATTACHMENTS_ATTACHMENTCONTENTS intentionally not fetched
         * because would be impractical waste of memory resources
         */
        client.attachments.add(clientAttachment);
      }
      cj.close();
      data.clients.add(client);
    }
    ci.close();
    for (Employee employee : data.employees) {
      Cursor cj;
      // cj = db.database.query(DBHelper.T_CALENDAREVENTS, null,
      // DBHelper.C_CALENDAREVENTS_EMPLOYEEID + " = ?", new String[] {
      // Long.valueOf(employee.id).toString() },
      // null, null, DBHelper.C_CALENDAREVENTS_EVENTDATE);
      //
      String query = "select * from " + DBHelper.T_CALENDAREVENTS + " where " + DBHelper.C_CALENDAREVENTS_EMPLOYEEID + " = " + Long.valueOf(employee.id).toString() + " ORDER BY "
          + DBHelper.C_CALENDAREVENTS_EVENTDATE + ", " + DBHelper.C_CALENDAREVENTS_EVENTSTARTTIME + ", " + DBHelper.C_CALENDAREVENTS_EVENTENDTIME;
      cj = db.database.rawQuery(query, null);
      while (cj.moveToNext()) {
        Event event = new Event();
        event.eventSubId = cj.getLong(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_EVENTSUBID));
	      event.eventGoogleId = cj.getString(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_EVENTGOOGLEID));
        event.eventDate = cj.getString(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_EVENTDATE));
        event.startTime = cj.getString(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_EVENTSTARTTIME));
        event.endTime = cj.getString(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_EVENTENDTIME));
        event.description = cj.getString(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_EVENTDESCRIPTION));
        event.contactPerson = cj.getString(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_EVENTCONTACTPERSON));
        event.workOrderName = cj.getString(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_WORKORDERNAME));
        long consultantId = cj.getLong(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_CONSULTANTID));
        for (BusinessConsultant consultant : data.businessConsultants)
          if (consultant.id == consultantId)
            event.consultant = consultant;
        long clientId = cj.getLong(cj.getColumnIndex(DBHelper.C_CALENDAREVENTS_CLIENTID));
        for (Client client : data.clients)
          if (client.id == clientId)
            event.client = client;
        employee.events.add(event);
      }
      cj.close();
    }

    return data;

  }

  void deleteAllData() {

    db.database.delete(DBHelper.T_CALENDAREVENTS, null, null);
    db.database.delete(DBHelper.T_CLIENTATTACHMENTS, null, null);
    db.database.delete(DBHelper.T_CLIENTVISITS, null, null);
    db.database.delete(DBHelper.T_CLIENTPHONECALLS, null, null);
    db.database.delete(DBHelper.T_CLIENTWORKORDERLOCATIONS, null, null);
    db.database.delete(DBHelper.T_CLIENTPEOPLEINCHARGE, null, null);
    db.database.delete(DBHelper.T_PERSONINCHARGEROLES, null, null);
    db.database.delete(DBHelper.T_CLIENTBUSINESSAREAS, null, null);
    db.database.delete(DBHelper.T_CLIENTS, null, null);
    db.database.delete(DBHelper.T_BUSINESSAREAS, null, null);
    db.database.delete(DBHelper.T_TAXCATEGORIES, null, null);
    db.database.delete(DBHelper.T_BANKACCOUNTS, null, null);
    db.database.delete(DBHelper.T_PAYMENTTYPES, null, null);
    db.database.delete(DBHelper.T_EMPLOYEES, null, null);
    db.database.delete(DBHelper.T_BUSINESSCONSULTANTS, null, null);
    db.database.delete(DBHelper.T_INITIALCONTACTCHANNELS, null, null);
    db.database.delete(DBHelper.T_BUSINESSCATEGORIES, null, null);

  }

  public void putData(Data data) {

    // begin transaction

    db.database.beginTransaction();

    // delete everything from the database

    deleteAllData();

    // insert everything into the database

    ContentValues values = new ContentValues();
    for (BusinessCategory businessCategory : data.businessCategories) {
      values.clear();
      if (businessCategory.id != -1)
        values.put(DBHelper.C_BUSINESSCATEGORIES_ID, businessCategory.id);
      values.put(DBHelper.C_BUSINESSCATEGORIES_NAME, businessCategory.name);
      db.database.insertOrThrow(DBHelper.T_BUSINESSCATEGORIES, null, values);
    }
    for (InitialContactChannel initialContactChannel : data.initialContactChannels) {
      values.clear();
      if (initialContactChannel.id != -1)
        values.put(DBHelper.C_INITIALCONTACTCHANNELS_ID, initialContactChannel.id);
      values.put(DBHelper.C_INITIALCONTACTCHANNELS_DESCRIPTION, initialContactChannel.name);
      db.database.insertOrThrow(DBHelper.T_INITIALCONTACTCHANNELS, null, values);
    }
    for (BusinessConsultant businessConsultant : data.businessConsultants) {
      values.clear();
      if (businessConsultant.id != -1)
        values.put(DBHelper.C_BUSINESSCONSULTANTS_ID, businessConsultant.id);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_NAME, businessConsultant.name);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_ADDRESS, businessConsultant.address);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_POSTALCODE, businessConsultant.postalCode);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_ZONE, businessConsultant.zone);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_CITY, businessConsultant.city);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_PROVINCE, businessConsultant.province);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_REGION, businessConsultant.region);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_AREA, businessConsultant.area);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_COUNTRY, businessConsultant.country);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_PHONE, businessConsultant.phone);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_CELL, businessConsultant.cell);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_FAX, businessConsultant.fax);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_VOIP, businessConsultant.voip);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_EMAIL, businessConsultant.email);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_WEB, businessConsultant.web);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_TAXPAYERNUMBER, businessConsultant.taxPayerNumber);
      values.put(DBHelper.C_BUSINESSCONSULTANTS_TAXREGISTRATIONNUMBER, businessConsultant.taxRegistrationNumber);
      db.database.insertOrThrow(DBHelper.T_BUSINESSCONSULTANTS, null, values);
    }
    for (Employee employee : data.employees) {
      values.clear();
      if (employee.id != -1)
        values.put(DBHelper.C_EMPLOYEES_ID, employee.id);
      values.put(DBHelper.C_EMPLOYEES_NAME, employee.name);
      values.put(DBHelper.C_EMPLOYEES_ADDRESS, employee.address);
      values.put(DBHelper.C_EMPLOYEES_POSTALCODE, employee.postalCode);
      values.put(DBHelper.C_EMPLOYEES_ZONE, employee.zone);
      values.put(DBHelper.C_EMPLOYEES_CITY, employee.city);
      values.put(DBHelper.C_EMPLOYEES_PROVINCE, employee.province);
      values.put(DBHelper.C_EMPLOYEES_REGION, employee.region);
      values.put(DBHelper.C_EMPLOYEES_AREA, employee.area);
      values.put(DBHelper.C_EMPLOYEES_COUNTRY, employee.country);
      values.put(DBHelper.C_EMPLOYEES_PHONE, employee.phone);
      values.put(DBHelper.C_EMPLOYEES_CELL, employee.cell);
      values.put(DBHelper.C_EMPLOYEES_FAX, employee.fax);
      values.put(DBHelper.C_EMPLOYEES_VOIP, employee.voip);
      values.put(DBHelper.C_EMPLOYEES_EMAIL, employee.email);
      values.put(DBHelper.C_EMPLOYEES_WEB, employee.web);
      values.put(DBHelper.C_EMPLOYEES_TAXPAYERNUMBER, employee.taxPayerNumber);
      values.put(DBHelper.C_EMPLOYEES_TAXREGISTRATIONNUMBER, employee.taxRegistrationNumber);
      values.put(DBHelper.C_EMPLOYEES_STARTDATE, employee.startDate);
      values.put(DBHelper.C_EMPLOYEES_ENDDATE, employee.endDate);
      db.database.insertOrThrow(DBHelper.T_EMPLOYEES, null, values);
    }
    for (PaymentType paymentType : data.paymentTypes) {
      values.clear();
      values.put(DBHelper.C_PAYMENTTYPES_CODE, paymentType.code);
      values.put(DBHelper.C_PAYMENTTYPES_DESCRIPTION, paymentType.description);
      db.database.insertOrThrow(DBHelper.T_PAYMENTTYPES, null, values);
    }
    for (BankAccount bankAccount : data.bankAccounts) {
      values.clear();
      if (bankAccount.id != -1)
        values.put(DBHelper.C_BANKACCOUNTS_ID, bankAccount.id);
      values.put(DBHelper.C_BANKACCOUNTS_BANKACCOUNTNAME, bankAccount.bankAccountName);
      values.put(DBHelper.C_BANKACCOUNTS_INSTITUTIONNUMBER, bankAccount.institutionNumber);
      values.put(DBHelper.C_BANKACCOUNTS_BRANCHNUMBER, bankAccount.branchNumber);
      values.put(DBHelper.C_BANKACCOUNTS_ACCOUNTNUMBER, bankAccount.accountNumber);
      values.put(DBHelper.C_BANKACCOUNTS_IBAN, bankAccount.iban);
      db.database.insertOrThrow(DBHelper.T_BANKACCOUNTS, null, values);
    }
    for (TaxCategory taxCategory : data.taxCategories) {
      values.clear();
      values.put(DBHelper.C_TAXCATEGORIES_TAXCATEGORYCODE, taxCategory.code);
      values.put(DBHelper.C_TAXCATEGORIES_TAXCATEGORYRATE, taxCategory.rate);
      values.put(DBHelper.C_TAXCATEGORIES_TAXCATEGORYDESCRIPTION, taxCategory.description);
      db.database.insertOrThrow(DBHelper.T_TAXCATEGORIES, null, values);
    }
    for (BusinessArea businessArea : data.businessAreas) {
      values.clear();
      values.put(DBHelper.C_BUSINESSAREAS_CODE, businessArea.code);
      values.put(DBHelper.C_BUSINESSAREAS_DESCRIPTION, businessArea.description);
      db.database.insertOrThrow(DBHelper.T_BUSINESSAREAS, null, values);
    }
    for (PersonInChargeRole personInChargeRole : data.personInChargeRoles) {
      values.clear();
      if (personInChargeRole.id != -1)
        values.put(DBHelper.C_PERSONINCHARGEROLES_ID, personInChargeRole.id);
      values.put(DBHelper.C_PERSONINCHARGEROLES_NAME, personInChargeRole.name);
      db.database.insertOrThrow(DBHelper.T_PERSONINCHARGEROLES, null, values);
    }
    for (Client client : data.clients) {
      values.clear();
      long clientId = client.id;
      if (clientId != -1)
        values.put(DBHelper.C_CLIENTS_ID, clientId);
      else {
        String query = "select coalesce(max(" + DBHelper.C_CLIENTS_ID + "),  0) + 1 from " + DBHelper.T_CLIENTS;
        Cursor cq = db.database.rawQuery(query, null);
        cq.moveToNext();
        clientId = cq.getLong(0);
        values.put(DBHelper.C_CLIENTS_ID, clientId);
        cq.close();
      }
      values.put(DBHelper.C_CLIENTS_CODE, client.code);
      values.put(DBHelper.C_CLIENTS_COMPANYNAME, client.companyName);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSADDRESS, client.adminAddress);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSPOSTALCODE, client.adminPostalCode);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSZONE, client.adminZone);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSCITY, client.adminCity);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSPROVINCE, client.adminProvince);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSREGION, client.adminRegion);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSAREA, client.adminArea);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSCOUNTRY, client.adminCountry);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSLATITUDE, client.adminLatitude);
      values.put(DBHelper.C_CLIENTS_ADMINHEADQUARTERSLONGITUDE, client.adminLongitude);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICEADDRESS, client.officeAddress);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICEPOSTALCODE, client.officePostalCode);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICEZONE, client.officeZone);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICECITY, client.officeCity);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICEPROVINCE, client.officeProvince);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICEREGION, client.officeRegion);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICEAREA, client.officeArea);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICECOUNTRY, client.officeCountry);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICELATITUDE, client.officeLatitude);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICELONGITUDE, client.officeLongitude);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICECERTIFIEDEMAIL, client.certifiedEMail);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICEDEFAULTCURRENCY, client.defaultCurrency);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICETAXPAYERNUMBER, client.taxPayerNumber);
      values.put(DBHelper.C_CLIENTS_REGISTEREDOFFICETAXREGISTRATIONNUMBER, client.taxRegistrationNumber);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGENAME, client.mainPersonInChargeName);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGEPHONE, client.mainPersonInChargePhone);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGECELL, client.mainPersonInChargeCell);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGEFAX, client.mainPersonInChargeFax);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGEVOIP, client.mainPersonInChargeVoIP);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGEEMAIL, client.mainPersonInChargeEMail);
      values.put(DBHelper.C_CLIENTS_MAINPERSONINCHARGEWEB, client.mainPersonInChargeWeb);
      values.put(DBHelper.C_CLIENTS_EFFECTIVEORPOTENTIAL, client.isEffectiveOrPotentialClient);
      values.put(DBHelper.C_CLIENTS_NOTES, client.notes);
      db.database.insertOrThrow(DBHelper.T_CLIENTS, null, values);
      for (ConcernedBusinessArea concernedBusinessArea : client.concernedBusinessAreas) {
        values.clear();
        values.put(DBHelper.C_CLIENTBUSINESSAREAS_CLIENTID, clientId);
        values.put(DBHelper.C_CLIENTBUSINESSAREAS_BUSINESSAREACODE, concernedBusinessArea.businessArea.code);
        values.put(DBHelper.C_CLIENTBUSINESSAREAS_PRIMARYORSECONDARYCONCERN, concernedBusinessArea.isPrimaryOrSecondaryConcern);
        db.database.insertOrThrow(DBHelper.T_CLIENTBUSINESSAREAS, null, values);
      }
      for (PersonInCharge personInCharge : client.otherPeopleInCharge) {
        values.clear();
        long personInChargeSubId = personInCharge.subId;
        if (personInChargeSubId == -1) {
          String query = "select coalesce(max(" + DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGESUBID + "),  0) + 1 from " + DBHelper.T_CLIENTPEOPLEINCHARGE + " where "
              + DBHelper.C_CLIENTPEOPLEINCHARGE_CLIENTID + " = " + clientId;
          Cursor cq = db.database.rawQuery(query, null);
          cq.moveToNext();
          personInChargeSubId = cq.getLong(0);
          cq.close();
        }
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_CLIENTID, clientId);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGESUBID, personInChargeSubId);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEROLEID, personInCharge.role.id);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGENAME, personInCharge.name);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEPHONE, personInCharge.phone);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGECELL, personInCharge.cell);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEFAX, personInCharge.fax);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEVOIP, personInCharge.voip);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEEMAIL, personInCharge.email);
        values.put(DBHelper.C_CLIENTPEOPLEINCHARGE_PERSONINCHARGEWEB, personInCharge.web);
        db.database.insertOrThrow(DBHelper.T_CLIENTPEOPLEINCHARGE, null, values);
      }
      for (WorkOrderLocation workOrderLocation : client.workOrderLocations) {
        values.clear();
        long workOrderLocationSubId = workOrderLocation.subId;
        if (workOrderLocation.subId == -1) {
          String query = "select coalesce(max(" + DBHelper.C_CLIENTWORKORDERLOCATIONS_LOCATIONSUBID + "),  0) + 1 from " + DBHelper.T_CLIENTWORKORDERLOCATIONS + " where "
              + DBHelper.C_CLIENTWORKORDERLOCATIONS_CLIENTID + " = " + clientId;
          Cursor cq = db.database.rawQuery(query, null);
          cq.moveToNext();
          workOrderLocationSubId = cq.getLong(0);
          cq.close();
        }
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_CLIENTID, clientId);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_LOCATIONSUBID, workOrderLocationSubId);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_ADDRESS, workOrderLocation.address);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_POSTALCODE, workOrderLocation.postalCode);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_ZONE, workOrderLocation.zone);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_CITY, workOrderLocation.city);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_PROVINCE, workOrderLocation.province);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_REGION, workOrderLocation.region);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_AREA, workOrderLocation.area);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_COUNTRY, workOrderLocation.country);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_LATITUDE, workOrderLocation.latitude);
        values.put(DBHelper.C_CLIENTWORKORDERLOCATIONS_LONGITUDE, workOrderLocation.longitude);
        db.database.insertOrThrow(DBHelper.T_CLIENTWORKORDERLOCATIONS, null, values);
      }
      for (ClientPhoneCall clientPhoneCall : client.phoneCalls) {
        values.clear();
        long clientPhoneCallSubId = clientPhoneCall.subId;
        if (clientPhoneCall.subId == -1) {
          String query = "select coalesce(max(" + DBHelper.C_CLIENTPHONECALLS_CALLSUBID + "),  0) + 1 from " + DBHelper.T_CLIENTPHONECALLS + " where "
              + DBHelper.C_CLIENTPHONECALLS_CLIENTID + " = " + clientId;
          Cursor cq = db.database.rawQuery(query, null);
          cq.moveToNext();
          clientPhoneCallSubId = cq.getLong(0);
          cq.close();
        }
        values.put(DBHelper.C_CLIENTPHONECALLS_CLIENTID, clientId);
        values.put(DBHelper.C_CLIENTPHONECALLS_CALLSUBID, clientPhoneCallSubId);
        values.put(DBHelper.C_CLIENTPHONECALLS_CALLCALLERNAME, clientPhoneCall.callerName);
        values.put(DBHelper.C_CLIENTPHONECALLS_CALLDATE, clientPhoneCall.callDate);
        values.put(DBHelper.C_CLIENTPHONECALLS_CALLCALLEENAME, clientPhoneCall.calleeName);
        values.put(DBHelper.C_CLIENTPHONECALLS_CALLNOTES, clientPhoneCall.callNotes);
        db.database.insertOrThrow(DBHelper.T_CLIENTPHONECALLS, null, values);
      }
      for (ClientVisit clientVisit : client.visits) {
        values.clear();
        long clientVisitSubId = clientVisit.subId;
        if (clientVisit.subId == -1) {
          String query = "select coalesce(max(" + DBHelper.C_CLIENTVISITS_VISITSUBID + "),  0) + 1 from " + DBHelper.T_CLIENTVISITS + " where " + DBHelper.C_CLIENTVISITS_CLIENTID
              + " = " + clientId;
          Cursor cq = db.database.rawQuery(query, null);
          cq.moveToNext();
          clientVisitSubId = cq.getLong(0);
          cq.close();
        }
        values.put(DBHelper.C_CLIENTVISITS_CLIENTID, clientId);
        values.put(DBHelper.C_CLIENTVISITS_VISITSUBID, clientVisitSubId);
        values.put(DBHelper.C_CLIENTVISITS_VISITVISITORNAME, clientVisit.visitorName);
        values.put(DBHelper.C_CLIENTVISITS_VISITDATE, clientVisit.visitDate);
        values.put(DBHelper.C_CLIENTVISITS_VISITNOTES, clientVisit.visitNotes);
        db.database.insertOrThrow(DBHelper.T_CLIENTVISITS, null, values);
      }
      for (ClientAttachment clientAttachment : client.attachments) {
        values.clear();
        long clientAttachmentSubId = clientAttachment.subId;
        if (clientAttachment.subId == -1) {
          String query = "select coalesce(max(" + DBHelper.C_CLIENTATTACHMENTS_CLIENTID + "),  0) + 1 from " + DBHelper.T_CLIENTATTACHMENTS + " where "
              + DBHelper.C_CLIENTATTACHMENTS_ATTACHMENTSUBID + " = " + clientId;
          Cursor cq = db.database.rawQuery(query, null);
          cq.moveToNext();
          clientAttachmentSubId = cq.getLong(0);
          cq.close();
        }
        values.put(DBHelper.C_CLIENTATTACHMENTS_CLIENTID, clientId);
        values.put(DBHelper.C_CLIENTATTACHMENTS_ATTACHMENTSUBID, clientAttachmentSubId);
        values.put(DBHelper.C_CLIENTATTACHMENTS_ATTACHMENTNAME, clientAttachment.name);
        db.database.insertOrThrow(DBHelper.T_CLIENTATTACHMENTS, null, values);
      }
    }
    for (Employee employee : data.employees) {
      for (Event event : employee.events) {
        values.clear();
        long eventSubId = event.eventSubId;
        if (eventSubId == -1) {
          String query = "select coalesce(max(" + DBHelper.C_CALENDAREVENTS_EVENTSUBID + "),  0) + 1 from " + DBHelper.T_CALENDAREVENTS + " where "
              + DBHelper.C_CALENDAREVENTS_EMPLOYEEID + " = " + employee.id;
          Cursor cq = db.database.rawQuery(query, null);
          cq.moveToNext();
          eventSubId = cq.getLong(0);
          cq.close();
        }
        values.put(DBHelper.C_CALENDAREVENTS_EMPLOYEEID, employee.id);
        values.put(DBHelper.C_CALENDAREVENTS_EVENTSUBID, eventSubId);
        values.put(DBHelper.C_CALENDAREVENTS_EVENTGOOGLEID, event.eventGoogleId);
        values.put(DBHelper.C_CALENDAREVENTS_EVENTDATE, event.eventDate);
        values.put(DBHelper.C_CALENDAREVENTS_EVENTSTARTTIME, event.startTime);
        values.put(DBHelper.C_CALENDAREVENTS_EVENTENDTIME, event.endTime);
        values.put(DBHelper.C_CALENDAREVENTS_EVENTDESCRIPTION, event.description);
        values.put(DBHelper.C_CALENDAREVENTS_EVENTCONTACTPERSON, event.contactPerson);
        values.put(DBHelper.C_CALENDAREVENTS_WORKORDERNAME, event.workOrderName);
        values.put(DBHelper.C_CALENDAREVENTS_CONSULTANTID, event.consultant != null ? event.consultant.id : null);
        values.put(DBHelper.C_CALENDAREVENTS_CLIENTID, event.client != null ? event.client.id : null);
        db.database.insertOrThrow(DBHelper.T_CALENDAREVENTS, null, values);
      }
    }

    // set transaction to successful

    db.database.setTransactionSuccessful();

    // end transaction

    db.database.endTransaction();

  }

  private static DB db = null;
  private DBHelper dbHelper;
  SQLiteDatabase database;

}
