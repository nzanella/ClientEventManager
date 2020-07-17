package com.codestudioworks.clienteventmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ClientActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(R.layout.client);
    
    // retrieve action parameter passed as global variable
    
    this.action = Globals.actions.peek().getType();
    
    this.client = (Client) Globals.actions.peek().getEntry();
    
    // retrieve and set screen title according to action
    
    ((TextView) findViewById(R.id.title)).setText(this.action == Action.ACTION_ENTRY_ADD ? R.string.client_title_add : R.string.client_title_modify);
    
    // retrieve simple entry fields

    this.codeEditText = (EditText) findViewById(R.id.code); 
    this.companyNameEditText = (EditText) findViewById(R.id.companyName);
    this.adminHeadquartersAddressEditText = (EditText) findViewById(R.id.adminHeadquartersAddress);
    this.adminHeadquartersZoneEditText = (EditText) findViewById(R.id.adminHeadquartersZone);
    this.adminHeadquartersCityEditText = (EditText) findViewById(R.id.adminHeadquartersCity);
    this.adminHeadquartersProvinceEditText = (EditText) findViewById(R.id.adminHeadquartersProvince);
    this.adminHeadquartersRegionEditText = (EditText) findViewById(R.id.adminHeadquartersRegion);
    this.adminHeadquartersAreaEditText = (EditText) findViewById(R.id.adminHeadquartersArea);
    this.adminHeadquartersCountryEditText = (EditText) findViewById(R.id.adminHeadquartersCountry);
    this.adminHeadquartersLatitudeEditText = (EditText) findViewById(R.id.adminHeadquartersLatitude);
    this.adminHeadquartersLongitudeEditText = (EditText) findViewById(R.id.adminHeadquartersLongitude);
    this.registeredOfficeAddressEditText = (EditText) findViewById(R.id.registeredOfficeAddress);
    this.registeredOfficeZoneEditText = (EditText) findViewById(R.id.registeredOfficeZone);
    this.registeredOfficeCityEditText = (EditText) findViewById(R.id.registeredOfficeCity);
    this.registeredOfficeProvinceEditText = (EditText) findViewById(R.id.registeredOfficeProvince);
    this.registeredOfficeRegionEditText = (EditText) findViewById(R.id.registeredOfficeRegion);
    this.registeredOfficeAreaEditText = (EditText) findViewById(R.id.registeredOfficeArea);
    this.registeredOfficeCountryEditText = (EditText) findViewById(R.id.registeredOfficeCountry);
    this.registeredOfficeLatitudeEditText = (EditText) findViewById(R.id.registeredOfficeLatitude);
    this.registeredOfficeLongitudeEditText = (EditText) findViewById(R.id.registeredOfficeLongitude);
    this.certifiedEMailEditText = (EditText) findViewById(R.id.registeredOfficeCertifiedEMail);
    this.defaultCurrencyEditText = (EditText) findViewById(R.id.defaultCurrency);
    this.taxPayerNumberEditText = (EditText) findViewById(R.id.taxPayerNumber);
    this.taxRegistrationNumberEditText = (EditText) findViewById(R.id.taxRegistrationNumber);
    this.mainPersonInChargeNameEditText = (EditText) findViewById(R.id.mainPersonInChargeName);
    this.mainPersonInChargePhoneEditText = (EditText) findViewById(R.id.mainPersonInChargePhone);
    this.mainPersonInChargeCellEditText = (EditText) findViewById(R.id.mainPersonInChargeCell);
    this.mainPersonInChargeFaxEditText = (EditText) findViewById(R.id.mainPersonInChargeFax);
    this.mainPersonInChargeVoIPEditText = (EditText) findViewById(R.id.mainPersonInChargeVoIP);
    this.mainPersonInChargeEMailEditText = (EditText) findViewById(R.id.mainPersonInChargeEMail);
    this.mainPersonInChargeWebEditText = (EditText) findViewById(R.id.mainPersonInChargeWeb);
    this.businessInformationBusinessCategoryNameEditText = (EditText) findViewById(R.id.businessDataBusinessCategory);
    this.businessInformationInitialContactMeansNameEditText = (EditText) findViewById(R.id.businessDataInitialContactMeans);
    this.businessInformationBusinessConsultantNameEditText = (EditText) findViewById(R.id.businessDataBusinessConsultant);
    this.businessInformationReportingTechnicianNameEditText = (EditText) findViewById(R.id.businessDataReportingTechnicianEmployee);
    this.businessInformationContractIssuerNameEditText = (EditText) findViewById(R.id.businessDataContractIssuingEmployee);
    this.accountingDataPaymentTypeCodeEditText = (EditText) findViewById(R.id.accountingDataPaymentTypeCode);
    this.accountingDataBankAccountNameEditText = (EditText) findViewById(R.id.accountingDataBankAccountName);
    this.accountingDataTaxCategoryCodeEditText = (EditText) findViewById(R.id.accountingDataTaxCategoryCode);
    this.effectiveClientRadioButton = (RadioButton) findViewById(R.id.effectiveClient);
    this.potentialClientRadioButton = (RadioButton) findViewById(R.id.potentialClient);
    this.notesEditText = (EditText) findViewById(R.id.notes);
    
    // retrieve composite entry fields and buttons

    this.clientBusinessAreasLinearLayout = (LinearLayout) findViewById(R.id.businessAreas);
    this.clientBusinessAreasButton = (Button) findViewById(R.id.setBusinessAreas);
    this.otherPeopleInChargeLinearLayout = (LinearLayout) findViewById(R.id.morePeopleInCharge);
    this.otherPeopleInChargeButton = (Button) findViewById(R.id.setMorePeopleInCharge);
    this.workOrderLocationsLinearLayout = (LinearLayout) findViewById(R.id.moreWorkOrderLocations);
    this.workOrderLocationsButton = (Button) findViewById(R.id.setWorkOrderLocations);
    this.phoneCallsButton = (Button) findViewById(R.id.phone);
    this.visitsButton = (Button) findViewById(R.id.visits);
    //this.attachmentsLinearLayout = (LinearLayout) findViewById(R.id.attachments);
    //this.attachmentsButton = (Button) findViewById(R.id.setAttachments);
    
    // retrieve set and clear buttons
    
    this.setBusinessDataBusinessCategoryButton = (Button) findViewById(R.id.setBusinessDataBusinessCategory);
    this.clearBusinessDataBusinessCategoryButton = (Button) findViewById(R.id.clearBusinessDataBusinessCategory);
    this.setBusinessDataInitialContactMeansButton = (Button) findViewById(R.id.setBusinessDataInitialContactMeans);
    this.clearBusinessDataInitialContactMeansButton = (Button) findViewById(R.id.clearBusinessDataInitialContactMeans);
    this.setBusinessDataBusinessConsultantButton = (Button) findViewById(R.id.setBusinessDataBusinessConsultant);
    this.clearBusinessDataBusinessConsultantButton = (Button) findViewById(R.id.clearBusinessDataBusinessConsultant);
    this.setBusinessDataReportingTechnicianEmployeeButton = (Button) findViewById(R.id.setBusinessDataReportingTechnicianEmployee);
    this.clearBusinessDataReportingTechnicianEmployeeButton = (Button) findViewById(R.id.clearBusinessDataReportingTechnicianEmployee);
    this.setBusinessDataContractIssuingEmployeeButton = (Button) findViewById(R.id.setBusinessDataContractIssuingEmployee);
    this.clearBusinessDataContactIssuingEmployeeButton = (Button) findViewById(R.id.clearBusinessDataContractIssuingEmployee);
    this.setAccountingDataPaymentTypeButton = (Button) findViewById(R.id.setAccountingDataPaymentType);
    this.clearAccountingDataPaymentTypeButton = (Button) findViewById(R.id.clearAccountingDataPaymentType);
    this.setAccountingDataBankAccountButton = (Button) findViewById(R.id.setAccountingDataBankAccount);
    this.clearAccountingDataBankAccountButton = (Button) findViewById(R.id.clearAccountingDataBankAccount);
    this.setAccountingDataTaxCategoryButton = (Button) findViewById(R.id.setAccountingDataTaxCategory);
    this.clearAccountingDataTaxCategoryButton = (Button) findViewById(R.id.clearAccountingDataTaxCategory);
    
    // retrieve further buttons
    
    this.phoneCallsButton = (Button) findViewById(R.id.phoneCalls);
    this.visitsButton = (Button) findViewById(R.id.visits);
    //this.attachmentsButton = (Button) findViewById(R.id.setAttachments);
    
    // update display

    updateDisplay();

    // setup button click listeners

    clientBusinessAreasButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        
        storeDisplay();

        Globals.actions.push(new Action(Action.ACTION_CONTENT_DEFINE, ClientActivity.this.client.concernedBusinessAreas));

        startActivityForResult(new Intent(ClientActivity.this, ClientBusinessAreasActivity.class), SELECT_CONCERNEDBUSINESSAREAS_REQUEST);

      }

    });

    otherPeopleInChargeButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(Action.ACTION_CONTENT_DEFINE, ClientActivity.this.client));

        startActivityForResult(new Intent(ClientActivity.this, PeopleInChargeActivity.class), DEFINE_OTHERPEOPLEINCHARGE_REQUEST);

      }

    });

    this.setBusinessDataBusinessCategoryButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            Client client = (Client) parentEntry;
        
            client.businessCategory = (BusinessCategory) childEntry;
            
          }
          
        }));

        startActivityForResult(new Intent(ClientActivity.this, BusinessCategoriesDoneActivity.class), SELECT_BUSINESSCATEGORIES_REQUEST);

      }
      
    });

    this.clearBusinessDataBusinessCategoryButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.businessCategory = null; 

      }
      
    });

    this.setBusinessDataInitialContactMeansButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            Client client = (Client) parentEntry;
        
            client.initialContactChannel = (InitialContactChannel) childEntry;
            
          }
          
        }));

        startActivityForResult(new Intent(ClientActivity.this, InitialContactChannelsDoneActivity.class), SELECT_INITIALCONTACTMEANS_REQUEST);

      }
      
    });

    this.clearBusinessDataInitialContactMeansButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.initialContactChannel = null; 

      }
      
    });
    
    this.setBusinessDataBusinessConsultantButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            Client client = (Client) parentEntry;
        
            client.businessConsultant = (BusinessConsultant) childEntry;
            
          }
          
        }));

        startActivityForResult(new Intent(ClientActivity.this, ConsultantsDoneActivity.class), SELECT_BUSINESSCONSULTANT_REQUEST);

      }
      
    });

    this.clearBusinessDataBusinessConsultantButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.businessConsultant = null; 

      }
      
    });
    
    this.setBusinessDataReportingTechnicianEmployeeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            Client client = (Client) parentEntry;
        
            client.reportingTechnician = (Employee) childEntry;
            
          }
          
        }));

        startActivityForResult(new Intent(ClientActivity.this, EmployeesDoneActivity.class), SELECT_REPORTINGTECHNICIAN_REQUEST);

      }
      
    });

    this.clearBusinessDataReportingTechnicianEmployeeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.reportingTechnician = null; 

      }
      
    });
    
    this.setBusinessDataContractIssuingEmployeeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            Client client = (Client) parentEntry;
        
            client.contractIssuer = (Employee) childEntry;
            
          }
          
        }));

        startActivityForResult(new Intent(ClientActivity.this, EmployeesDoneActivity.class), SELECT_CONTRACTISSUER_REQUEST);

      }
      
    });

    this.clearBusinessDataReportingTechnicianEmployeeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.reportingTechnician = null; 

      }
      
    });
    
    this.setBusinessDataContractIssuingEmployeeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            Client client = (Client) parentEntry;
        
            client.contractIssuer = (Employee) childEntry;
            
          }
          
        }));

        startActivityForResult(new Intent(ClientActivity.this, EmployeesDoneActivity.class), SELECT_CONTRACTISSUER_REQUEST);

      }
      
    });

    this.clearBusinessDataContactIssuingEmployeeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.contractIssuer = null; 

      }
      
    });
    
    this.setAccountingDataPaymentTypeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            Client client = (Client) parentEntry;
        
            client.paymentType = (PaymentType) childEntry;
            
          }
          
        }));

        startActivityForResult(new Intent(ClientActivity.this, PaymentTypesDoneActivity.class), SELECT_PAYMENTTYPE_REQUEST);

      }
      
    });
    
    this.clearAccountingDataPaymentTypeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.paymentType = null; 

      }
      
    });

    this.setAccountingDataBankAccountButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        storeDisplay();

        Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            Client client = (Client) parentEntry;
        
            client.bankAccount = (BankAccount) childEntry;
            
          }
          
        }));

        startActivityForResult(new Intent(ClientActivity.this, BankAccountsDoneActivity.class), SELECT_CONTRACTISSUER_REQUEST);

      }
      
    });
    
    this.clearAccountingDataBankAccountButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.bankAccount = null; 

      }
      
    });
    
    this.setAccountingDataTaxCategoryButton.setOnClickListener(new OnClickListener() {
        
        @Override
        public void onClick(View v) {

          storeDisplay();

          Globals.actions.push(new Action(ClientActivity.this.client, new PropertySetterFunctor() {
            
            public void setProperty(Object parentEntry, Object childEntry) {
                        
              Client client = (Client) parentEntry;
          
              client.taxCategory = (TaxCategory) childEntry;
              
            }
            
          }));

          startActivityForResult(new Intent(ClientActivity.this, TaxCategoriesDoneActivity.class), SELECT_TAXCATEGORY_REQUEST);

        }
        
      });
    
    this.clearAccountingDataTaxCategoryButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {

        ClientActivity.this.client.taxCategory = null; 

      }
      
    });

    workOrderLocationsButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {

        storeDisplay();
        
        //neil
        //Globals.actions.push(new Action(Action.ACTION_NONE, ClientActivity.this.client.workOrderLocations));
        Globals.actions.push(new Action(Action.ACTION_CONTENT_DEFINE, ClientActivity.this.client));

        startActivityForResult(new Intent(ClientActivity.this, WorkOrderLocationsActivity.class), DEFINE_WORKORDERLOCATIONS_REQUEST);

      }

    });
    
    phoneCallsButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        storeDisplay();
        
        //neil
        Globals.actions.push(new Action(Action.ACTION_CONTENT_DEFINE, ClientActivity.this.client));

        startActivityForResult(new Intent(ClientActivity.this, ClientPhoneCallsActivity.class), DEFINE_PHONECALLS_REQUEST);

      }
      
    });
    
    visitsButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {

        storeDisplay();
        
        //neil
        Globals.actions.push(new Action(Action.ACTION_CONTENT_DEFINE, ClientActivity.this.client));

        startActivityForResult(new Intent(ClientActivity.this, ClientVisitsActivity.class), DEFINE_VISITS_REQUEST);

      }
      
    });
    
    /*
    attachmentsButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {

        storeDisplay();
        
        //Globals.actions.push(new Action(Action.ACTION_NONE, ClientActivity.this.client.attachments));

        //startActivityForResult(new Intent(ClientActivity.this, ClientAttachmentsActivity.class), DEFINE_ATTACHMENTS_REQUEST);

      }
      
    });
    */

    // assume everything is going to work out

    setResult(RESULT_OK);

  }
  
  void updateDisplay() {

    // initialize simple entry fields
    
    this.codeEditText.setText(this.client.code);
    this.companyNameEditText.setText(this.client.companyName);
    this.adminHeadquartersAddressEditText.setText(this.client.adminAddress);
    this.adminHeadquartersZoneEditText.setText(this.client.adminZone);
    this.adminHeadquartersCityEditText.setText(this.client.adminCity);
    this.adminHeadquartersProvinceEditText.setText(this.client.adminProvince);
    this.adminHeadquartersRegionEditText.setText(this.client.adminRegion);
    this.adminHeadquartersAreaEditText.setText(this.client.adminArea);
    this.adminHeadquartersCountryEditText.setText(this.client.adminCountry);
    this.adminHeadquartersLatitudeEditText.setText(Double.valueOf(this.client.adminLatitude).toString());
    this.adminHeadquartersLongitudeEditText.setText(Double.valueOf(this.client.adminLongitude).toString());
    this.registeredOfficeAddressEditText.setText(this.client.officeAddress);
    this.registeredOfficeZoneEditText.setText(this.client.officeZone);
    this.registeredOfficeCityEditText.setText(this.client.officeCity);
    this.registeredOfficeProvinceEditText.setText(this.client.officeProvince);
    this.registeredOfficeRegionEditText.setText(this.client.officeRegion);
    this.registeredOfficeAreaEditText.setText(this.client.officeArea);
    this.registeredOfficeCountryEditText.setText(this.client.officeCountry);
    this.registeredOfficeLatitudeEditText.setText(Double.valueOf(this.client.officeLatitude).toString());
    this.registeredOfficeLongitudeEditText.setText(Double.valueOf(this.client.officeLongitude).toString());
    this.certifiedEMailEditText.setText(this.client.certifiedEMail);
    this.defaultCurrencyEditText.setText(this.client.defaultCurrency);
    this.taxPayerNumberEditText.setText(this.client.taxPayerNumber);
    this.taxRegistrationNumberEditText.setText(this.client.taxRegistrationNumber);
    this.mainPersonInChargeNameEditText.setText(this.client.mainPersonInChargeName);
    this.mainPersonInChargePhoneEditText.setText(this.client.mainPersonInChargePhone);
    this.mainPersonInChargeCellEditText.setText(this.client.mainPersonInChargeCell);
    this.mainPersonInChargeFaxEditText.setText(this.client.mainPersonInChargeFax);
    this.mainPersonInChargeVoIPEditText.setText(this.client.mainPersonInChargeVoIP);
    this.mainPersonInChargeEMailEditText.setText(this.client.mainPersonInChargeEMail);
    this.mainPersonInChargeWebEditText.setText(this.client.mainPersonInChargeWeb);
    this.businessInformationBusinessCategoryNameEditText.setText(this.client.businessCategory != null ? this.client.businessCategory.name : null);
    this.businessInformationInitialContactMeansNameEditText.setText(this.client.initialContactChannel != null ? this.client.initialContactChannel.name : null);
    this.businessInformationBusinessConsultantNameEditText.setText(this.client.businessConsultant != null ? this.client.businessConsultant.name : null);
    this.businessInformationReportingTechnicianNameEditText.setText(this.client.reportingTechnician != null ? this.client.reportingTechnician.name : null);
    this.businessInformationContractIssuerNameEditText.setText(this.client.contractIssuer  != null ? this.client.contractIssuer.name : null);
    this.accountingDataPaymentTypeCodeEditText.setText(this.client.paymentType != null ? this.client.paymentType.code : null);
    this.accountingDataBankAccountNameEditText.setText(this.client.bankAccount != null ? this.client.bankAccount.bankAccountName : null);
    this.accountingDataTaxCategoryCodeEditText.setText(this.client.taxCategory != null ? this.client.taxCategory.code : null);
    this.effectiveClientRadioButton.setChecked(this.client.isEffectiveOrPotentialClient == Client.CLIENT_TYPE_EFFECTIVE);
    this.potentialClientRadioButton.setChecked(this.client.isEffectiveOrPotentialClient == Client.CLIENT_TYPE_POTENTIAL);
    this.notesEditText.setText(this.client.notes);
 
    // initialize client business areas composite entry field

    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
      ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    
    clientBusinessAreasLinearLayout.removeAllViews();
    clientBusinessAreasLinearLayout.setOrientation(LinearLayout.VERTICAL);
    clientBusinessAreasLinearLayout.setLayoutParams(lp);

    for (ConcernedBusinessArea concernedBusinessArea : this.client.concernedBusinessAreas) {
      
      View row = getLayoutInflater().inflate(R.layout.client_businessareas_entry, null);

      TextView codeTextView = (TextView) row.findViewById(R.id.code);
      TextView descriptionTextView = (TextView) row.findViewById(R.id.description);
      TextView concernTextView = (TextView) row.findViewById(R.id.concern);

      codeTextView.setText(concernedBusinessArea.businessArea.code);
      descriptionTextView.setText(concernedBusinessArea.businessArea.description);
      concernTextView.setText(concernedBusinessArea.getConcern(this));
      
      clientBusinessAreasLinearLayout.addView(row);

    }
    
    clientBusinessAreasLinearLayout.invalidate(); 

    // initialize other composite entry field

    // ...

  }
  
  void storeDisplay() {
    
    /* client id intentionally left out here */

    this.client.code = this.codeEditText.getText().toString();
    this.client.companyName = this.companyNameEditText.getText().toString();
    this.client.adminAddress = this.adminHeadquartersAddressEditText.getText().toString();
    this.client.adminZone = this.adminHeadquartersZoneEditText.getText().toString();
    this.client.adminCity = this.adminHeadquartersCityEditText.getText().toString();
    this.client.adminProvince = this.adminHeadquartersProvinceEditText.getText().toString();
    this.client.adminRegion = this.adminHeadquartersRegionEditText.getText().toString();
    this.client.adminArea = this.adminHeadquartersAreaEditText.getText().toString();
    this.client.adminCountry = this.adminHeadquartersCountryEditText.getText().toString();
    this.client.adminLatitude = Double.valueOf(this.adminHeadquartersLatitudeEditText.getText().toString());
    this.client.adminLongitude = Double.valueOf(this.adminHeadquartersLongitudeEditText.getText().toString());
    this.client.officeAddress = this.registeredOfficeAddressEditText.getText().toString();
    this.client.officeZone = this.registeredOfficeZoneEditText.getText().toString();
    this.client.officeCity = this.registeredOfficeCityEditText.getText().toString();
    this.client.officeProvince = this.registeredOfficeProvinceEditText.getText().toString();
    this.client.officeRegion = this.registeredOfficeRegionEditText.getText().toString();
    this.client.officeArea = this.registeredOfficeAreaEditText.getText().toString();
    this.client.officeCountry = this.registeredOfficeCountryEditText.getText().toString();
    this.client.officeLatitude = Double.valueOf(this.registeredOfficeLatitudeEditText.getText().toString());
    this.client.officeLongitude = Double.valueOf(this.registeredOfficeLongitudeEditText.getText().toString());
    this.client.certifiedEMail = this.certifiedEMailEditText.getText().toString();
    this.client.defaultCurrency = this.defaultCurrencyEditText.getText().toString();
    this.client.taxPayerNumber = this.taxPayerNumberEditText.getText().toString();
    this.client.taxRegistrationNumber = this.taxRegistrationNumberEditText.getText().toString();
    this.client.mainPersonInChargeName = this.mainPersonInChargeNameEditText.getText().toString();
    this.client.mainPersonInChargePhone = this.mainPersonInChargePhoneEditText.getText().toString();
    this.client.mainPersonInChargeCell = this.mainPersonInChargeCellEditText.getText().toString();
    this.client.mainPersonInChargeFax = this.mainPersonInChargeFaxEditText.getText().toString();
    this.client.mainPersonInChargeVoIP = this.mainPersonInChargeVoIPEditText.getText().toString();
    this.client.mainPersonInChargeEMail = this.mainPersonInChargeEMailEditText.getText().toString();
    this.client.mainPersonInChargeWeb = this.mainPersonInChargeWebEditText.getText().toString();
    this.client.isEffectiveOrPotentialClient = this.effectiveClientRadioButton.isChecked() ? Client.CLIENT_TYPE_EFFECTIVE : Client.CLIENT_TYPE_POTENTIAL;
    this.client.notes = this.notesEditText.getText().toString();

    /* other entries already represented as other client object fields */

  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    
    switch (requestCode) {
      
      case DEFINE_OTHERPEOPLEINCHARGE_REQUEST:
        
        Globals.actions.pop();
        
        break;
      
      case DEFINE_WORKORDERLOCATIONS_REQUEST:
      
        Globals.actions.pop();
        
        break;
 
      case DEFINE_PHONECALLS_REQUEST:
        
        Globals.actions.pop();
        
        break;
        
      case DEFINE_VISITS_REQUEST:
    
        Globals.actions.pop();
        
        break;
        
    }
    
    if (resultCode == RESULT_OK) {
            
      // refresh data
      
      updateDisplay();

    }
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    MenuInflater inflater = getMenuInflater();

    inflater.inflate(R.menu.client_editable_menu, menu);

    return true;

  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {

    MenuItem saveItem = menu.findItem(R.id.save);

    saveItem.setEnabled(true);

    return super.onPrepareOptionsMenu(menu);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {

      case R.id.save:
        
        storeDisplay();
      
        switch (this.action) {

          case Action.ACTION_ENTRY_ADD:

            Globals.data.clients.add(client);
            
            Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

            break;
            
          case Action.ACTION_ENTRY_MODIFY:
              
            Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

            break;
        
        }
        
        finish();
        
        // check server response code and finish in case operation was successful else display an error message
        
/*        
        if (Globals.cp.responseCode.equals(ResultCode.OK))

          finish();
            
        else
            
         Toast.makeText(this, Globals.cp.responseCode, Toast.LENGTH_SHORT).show();
*/
        break;

    }

    return true;

  }
  
  // action to be performed
  
  int action;
  
  // request codes associated with started activities

  public static final int SELECT_CONCERNEDBUSINESSAREAS_REQUEST = 0;
  public static final int DEFINE_OTHERPEOPLEINCHARGE_REQUEST = 1;
  
  public static final int DEFINE_WORKORDERLOCATIONS_REQUEST = 2;
  public static final int DEFINE_PHONECALLS_REQUEST = 3;
  public static final int DEFINE_VISITS_REQUEST = 4;
  public static final int DEFINE_ATTACHMENTS_REQUEST = 5;
  
  public static final int SELECT_BUSINESSCATEGORIES_REQUEST = 6;
  public static final int SELECT_INITIALCONTACTMEANS_REQUEST = 7;
  public static final int SELECT_BUSINESSCONSULTANT_REQUEST = 8;
  public static final int SELECT_REPORTINGTECHNICIAN_REQUEST = 9;
  public static final int SELECT_CONTRACTISSUER_REQUEST = 10;
  public static final int SELECT_PAYMENTTYPE_REQUEST = 11;
  public static final int SELECT_BANKACCOUNT_REQUEST = 12;
  public static final int SELECT_TAXCATEGORY_REQUEST = 12;

  // entry to be manipulated
    
  Client client;

  EditText codeEditText;
  EditText companyNameEditText;
  EditText adminHeadquartersAddressEditText;
  EditText adminHeadquartersZoneEditText;
  EditText adminHeadquartersCityEditText;
  EditText adminHeadquartersProvinceEditText;
  EditText adminHeadquartersRegionEditText;
  EditText adminHeadquartersAreaEditText;
  EditText adminHeadquartersCountryEditText;
  EditText adminHeadquartersLatitudeEditText;
  EditText adminHeadquartersLongitudeEditText;
  EditText registeredOfficeAddressEditText;
  EditText registeredOfficeZoneEditText;
  EditText registeredOfficeCityEditText;
  EditText registeredOfficeProvinceEditText;
  EditText registeredOfficeRegionEditText;
  EditText registeredOfficeAreaEditText;
  EditText registeredOfficeCountryEditText;
  EditText registeredOfficeLatitudeEditText;
  EditText registeredOfficeLongitudeEditText;
  EditText certifiedEMailEditText;
  EditText defaultCurrencyEditText;
  EditText taxPayerNumberEditText;
  EditText taxRegistrationNumberEditText;
  EditText mainPersonInChargeNameEditText;
  EditText mainPersonInChargePhoneEditText;
  EditText mainPersonInChargeCellEditText;
  EditText mainPersonInChargeFaxEditText;
  EditText mainPersonInChargeVoIPEditText;
  EditText mainPersonInChargeEMailEditText;
  EditText mainPersonInChargeWebEditText;
  EditText businessInformationBusinessCategoryNameEditText;
  EditText businessInformationInitialContactMeansNameEditText;
  EditText businessInformationBusinessConsultantNameEditText;
  EditText businessInformationReportingTechnicianNameEditText;
  EditText businessInformationContractIssuerNameEditText;
  EditText accountingDataPaymentTypeCodeEditText;
  EditText accountingDataBankAccountNameEditText;
  EditText accountingDataTaxCategoryCodeEditText;
  
  Button setBusinessDataBusinessCategoryButton;
  Button clearBusinessDataBusinessCategoryButton;
  Button setBusinessDataInitialContactMeansButton;
  Button clearBusinessDataInitialContactMeansButton;
  Button setBusinessDataBusinessConsultantButton;
  Button clearBusinessDataBusinessConsultantButton;
  Button setBusinessDataReportingTechnicianEmployeeButton;
  Button clearBusinessDataReportingTechnicianEmployeeButton;
  Button setBusinessDataContractIssuingEmployeeButton;
  Button clearBusinessDataContactIssuingEmployeeButton;
  Button setAccountingDataPaymentTypeButton;
  Button clearAccountingDataPaymentTypeButton;
  Button setAccountingDataBankAccountButton;
  Button clearAccountingDataBankAccountButton;
  Button setAccountingDataTaxCategoryButton;
  Button clearAccountingDataTaxCategoryButton;

  RadioButton effectiveClientRadioButton;
  RadioButton potentialClientRadioButton;
  EditText notesEditText;
 
  LinearLayout clientBusinessAreasLinearLayout;
  Button clientBusinessAreasButton;
  
  LinearLayout otherPeopleInChargeLinearLayout;
  Button otherPeopleInChargeButton;
  
  LinearLayout workOrderLocationsLinearLayout;
  Button workOrderLocationsButton;
  
  Button phoneCallsButton;
  Button visitsButton;
  
  LinearLayout attachmentsLinearLayout;
  Button attachmentsButton;
   
}
