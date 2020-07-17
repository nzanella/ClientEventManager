package com.codestudioworks.clienteventmanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

class Data {

  static final String PREF_ELATOSACCOUNT = "elatos";
  static final String PREF_ELATOSACCOUNT_BUSINESSNAME = "elatosBusinessName";
  static final String PREF_ELATOSACCOUNT_USERNAME = "elatosUserName";
  static final String PREF_ELATOSACCOUNT_PASSWORD = "elatosPassword";

  List<BusinessCategory> businessCategories = new ArrayList<BusinessCategory>();
  List<InitialContactChannel> initialContactChannels = new ArrayList<InitialContactChannel>();
  List<BusinessConsultant> businessConsultants = new ArrayList<BusinessConsultant>();
  List<Employee> employees = new ArrayList<Employee>();
  List<PaymentType> paymentTypes = new ArrayList<PaymentType>();
  List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
  List<TaxCategory> taxCategories = new ArrayList<TaxCategory>();
  List<BusinessArea> businessAreas = new ArrayList<BusinessArea>();
  List<PersonInChargeRole> personInChargeRoles = new ArrayList<PersonInChargeRole>();
  List<Client> clients = new ArrayList<Client>();
  //List<Event> events = new ArrayList<Event>(); this is inside each employee instance

}

class BusinessCategory {
  long id = -1;
  String name = null;
}

class InitialContactChannel {
  long id = -1;
  String name = null;
}

class BusinessConsultant {
  long id = -1;
  String name = null;
  String address = null;
  String postalCode = null;
  String zone = null;
  String city = null;
  String province = null;
  String region = null;
  String area = null;
  String country = null;
  String phone = null;
  String cell = null;
  String fax = null;
  String voip = null;
  String email = null;
  String web = null;
  String taxPayerNumber = null;
  String taxRegistrationNumber = null;
}

class Employee {
  long id = -1;
  String name = null;
  String address = null;
  String zone = null;
  String postalCode = null;
  String city = null;
  String province = null;
  String region = null;
  String area = null;
  String country = null;
  String phone = null;
  String cell = null;
  String fax = null;
  String voip = null;
  String email = null;
  String web = null;
  String taxPayerNumber = null;
  String taxRegistrationNumber = null;
  String startDate = null;
  String endDate = null;
  List<Event> events = new ArrayList<Event>();
}

class PaymentType {
	
  public static boolean isCodeUsed(List<PaymentType> paymentTypes, String code) {

    for (PaymentType paymentType : paymentTypes)

      if (paymentType.code.equals(code))

        return true;

    return false;

  }
  
  String code = null;
  String description = null;
}

class BankAccount {
  long id = -1;
  String bankAccountName = null;
  String institutionNumber = null;
  String branchNumber = null;
  String accountNumber = null;
  String iban = null;
}

class TaxCategory {
	
  public static boolean isCodeUsed(List<TaxCategory> taxCategorys, String code) {

    for (TaxCategory taxCategory : taxCategorys)

      if (taxCategory.code.equals(code))

        return true;

    return false;

  }
  
  String code = null;
  String rate = null;
  String description = null;
}

class BusinessArea {
  
  public static boolean isCodeUsed(List<BusinessArea> businessAreas, String code) {

    for (BusinessArea businessArea : businessAreas)

      if (businessArea.code.equals(code))

        return true;

    return false;

  }
  
  String code = null;
  String description = null;
  
}

class PersonInChargeRole {
  long id = -1;
  String name = null;
}

//

class ConcernedBusinessArea {
  
  String getConcern(Context context) {
    return isPrimaryOrSecondaryConcern == CONCERN_PRIMARY ? context.getString(R.string.businessArea_label_primaryConcern)
      : isPrimaryOrSecondaryConcern == CONCERN_SECONDARY ? context.getString(R.string.businessArea_label_secondaryConcern)
      : null;
  }
  
  BusinessArea businessArea = null;
  int isPrimaryOrSecondaryConcern = CONCERN_NONE;
  static final int CONCERN_NONE = 0; // an invalid value for isPrimaryOrSecondaryConcern used elsewhere
  static final int CONCERN_PRIMARY = 1;
  static final int CONCERN_SECONDARY = 2;
}

class PersonInCharge {
  long subId = -1;
  PersonInChargeRole role = null;
  String name = null;
  String phone = null;
  String cell = null;
  String fax = null;
  String voip = null;
  String email = null;
  String web = null;
}

class WorkOrderLocation {
  long subId = -1;
  String address = null;
  String postalCode = null;
  String zone = null;
  String city = null;
  String province = null;
  String region = null;
  String area = null;
  String country = null;
  double latitude = 0.0;
  double longitude = 0.0;
}

class ClientPhoneCall {
  long subId = -1;
  String callerName = null;
  String callDate = null;
  String calleeName = null;
  String callNotes = null;
}

class ClientVisit {
 long subId = -1;
 String visitorName = null;
 String visitDate = null;
 String visitNotes = null;
}

class ClientAttachment {
  long subId = -1;
  String name = null;
  String data = null;//for now. set to byte array or something
}

class Client {

  public static long getNextId(List<Client> clients) {

    long nextId = 0;

    for (Client client : clients)

      if (nextId <= client.id)

        nextId = client.id + 1;

    return nextId;

  }
  
  static final int CLIENT_TYPE_POTENTIAL = 0;
  static final int CLIENT_TYPE_EFFECTIVE = 1;

  long id = -1;
  String code = null;
  String companyName = null;
  String adminAddress = null;
  String adminPostalCode = null;
  String adminZone = null;
  String adminCity = null;
  String adminProvince = null;
  String adminRegion = null;
  String adminArea = null;
  String adminCountry = null;
  double adminLatitude = 0.0;
  double adminLongitude = 0.0;
  String officeAddress = null;
  String officePostalCode = null;
  String officeZone = null;
  String officeCity = null;
  String officeProvince = null;
  String officeRegion = null;
  String officeArea = null;
  String officeCountry = null;
  double officeLatitude = 0.0;
  double officeLongitude = 0.0;
  String certifiedEMail = null;
  String defaultCurrency = null;
  String taxPayerNumber = null;
  String taxRegistrationNumber = null;
  String mainPersonInChargeName = null;
  String mainPersonInChargePhone = null;
  String mainPersonInChargeCell = null;
  String mainPersonInChargeFax = null;
  String mainPersonInChargeVoIP = null;
  String mainPersonInChargeEMail = null;
  String mainPersonInChargeWeb = null;
  // business information
  BusinessCategory businessCategory = null;
  InitialContactChannel initialContactChannel = null;
  BusinessConsultant businessConsultant = null;
  Employee reportingTechnician = null;
  Employee contractIssuer = null;
  // financial information
  PaymentType paymentType = null;
  BankAccount bankAccount = null;
  TaxCategory taxCategory = null;
  // other information
  int isEffectiveOrPotentialClient = CLIENT_TYPE_POTENTIAL;
  String notes = null;

  List<ConcernedBusinessArea> concernedBusinessAreas = new ArrayList<ConcernedBusinessArea>();
  List<PersonInCharge> otherPeopleInCharge = new ArrayList<PersonInCharge>();
  List<WorkOrderLocation> workOrderLocations = new ArrayList<WorkOrderLocation>();
  List<ClientPhoneCall> phoneCalls = new ArrayList<ClientPhoneCall>();
  List<ClientVisit> visits = new ArrayList<ClientVisit>();
  List<ClientAttachment> attachments = new ArrayList<ClientAttachment>();

}

class Event {
  long eventSubId = -1;
  String eventGoogleId = null;
  String eventDate = null; // date in YYYY-MM-DD format
  String startTime = null; // start time in HH:MM format
  String endTime = null; // end time in HH:MM format
  String description = null;
  String contactPerson = null;
  String workOrderName = null;
  BusinessConsultant consultant = null;
  Client client = null;
  String notes = null;

  int getYear() {
    
    return Integer.parseInt(eventDate.substring(0, 4));

  }

  int getMonth() {

    return Integer.parseInt(eventDate.substring(5, 7)) - 1;

  }

  int getDay() {

    return Integer.parseInt(eventDate.substring(8, 10));

  }

  int getStartHour() {

    return Integer.parseInt(startTime.substring(0, 2));

  }

  int getStartMinute() {


    return Integer.parseInt(startTime.substring(3, 5));

  }

  int getEndHour() {

    return Integer.parseInt(endTime.substring(0, 2));

  }

  int getEndMinute() {

    return Integer.parseInt(endTime.substring(3, 5));

  }

}
