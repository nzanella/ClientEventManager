package com.codestudioworks.clienteventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ConsultantActivity extends Activity {
  
  int getLayout() {

    return R.layout.consultant;

  }
  
  int getAddTitle() {
    
    return R.string.consultant_title_add;

  }
  
  int getModifyTitle() {

    return R.string.consultant_title_modify;

  }
  
  int getMenu() {

    return R.menu.consultant_editable_menu;

  }
  
  int getSaveMenuItem() {

    return R.id.save;

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(getLayout());
    
    // retrieve action parameter passed as global variable
    
    this.action = Globals.actions.peek().getType();
    
    this.advisor = (BusinessConsultant) Globals.actions.peek().getEntry();
    
    // retrieve and set screen title according to action
    
    ((TextView) findViewById(R.id.title)).setText(this.action == Action.ACTION_ENTRY_ADD ? getAddTitle() : getModifyTitle());
    
    // retrieve entry fields

    this.nameEditText = (EditText) findViewById(R.id.name);
    this.addressEditText = (EditText) findViewById(R.id.address);
    this.zoneEditText = (EditText) findViewById(R.id.zone);
    this.cityEditText = (EditText) findViewById(R.id.city);
    this.provinceEditText = (EditText) findViewById(R.id.province);
    this.regionEditText = (EditText) findViewById(R.id.region);
    this.areaEditText = (EditText) findViewById(R.id.area);
    this.countryEditText = (EditText) findViewById(R.id.country);
    this.phoneEditText = (EditText) findViewById(R.id.phone);
    this.cellEditText = (EditText) findViewById(R.id.cell);
    this.faxEditText = (EditText) findViewById(R.id.fax);
    this.voipEditText = (EditText) findViewById(R.id.voip);
    this.emailEditText = (EditText) findViewById(R.id.email);
    this.webEditText = (EditText) findViewById(R.id.web);
    this.taxPayerNumberEditText = (EditText) findViewById(R.id.taxPayerNumber);
    this.taxRegistrationNumberEditText = (EditText) findViewById(R.id.taxRegistrationNumber);

    // initialize entry fields

    this.nameEditText.setText(this.advisor.name);
    this.addressEditText.setText(this.advisor.address);
    this.zoneEditText.setText(this.advisor.zone);
    this.cityEditText.setText(this.advisor.city);
    this.provinceEditText.setText(this.advisor.province);
    this.regionEditText.setText(this.advisor.region);
    this.areaEditText.setText(this.advisor.area);
    this.countryEditText.setText(this.advisor.country);
    this.phoneEditText.setText(this.advisor.phone);
    this.cellEditText.setText(this.advisor.cell);
    this.faxEditText.setText(this.advisor.fax);
    this.voipEditText.setText(this.advisor.voip);
    this.emailEditText.setText(this.advisor.email);
    this.webEditText.setText(this.advisor.web);
    this.taxPayerNumberEditText.setText(this.advisor.taxPayerNumber);
    this.taxRegistrationNumberEditText.setText(this.advisor.taxRegistrationNumber);

    // assume everything is going to work out

    setResult(RESULT_OK);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    MenuInflater inflater = getMenuInflater();

    inflater.inflate(getMenu(), menu);

    return true;

  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {

    MenuItem saveItem = menu.findItem(getSaveMenuItem());

    saveItem.setEnabled(true);

    return super.onPrepareOptionsMenu(menu);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {

      case R.id.save:
      
        this.advisor.name = this.nameEditText.getText().toString();
        this.advisor.address = this.addressEditText.getText().toString();
        this.advisor.zone = this.zoneEditText.getText().toString();
        this.advisor.city = this.cityEditText.getText().toString();
        this.advisor.province = this.provinceEditText.getText().toString();
        this.advisor.region = this.regionEditText.getText().toString();
        this.advisor.area = this.areaEditText.getText().toString();
        this.advisor.country = this.countryEditText.getText().toString();
        this.advisor.phone = this.phoneEditText.getText().toString();
        this.advisor.cell = this.cellEditText.getText().toString();
        this.advisor.fax = this.faxEditText.getText().toString();
        this.advisor.voip = this.voipEditText.getText().toString();
        this.advisor.email = this.emailEditText.getText().toString();
        this.advisor.web = this.webEditText.getText().toString();
        this.advisor.taxPayerNumber = this.taxPayerNumberEditText.getText().toString();
        this.advisor.taxRegistrationNumber = this.taxRegistrationNumberEditText.getText().toString();
        
        switch (this.action) {

          case Action.ACTION_ENTRY_ADD:

            Globals.data.businessConsultants.add(advisor);
            
            Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

            break;

          case Action.ACTION_ENTRY_MODIFY:
              
            Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

            break;
        
        }
        
        finish();
        
        break;

    }

    return true;

  }
  
  // action to be performed
  
  int action;
  
  // entry to be manipulated
    
  BusinessConsultant advisor;

  // user interface fields
  
  EditText nameEditText;
  EditText addressEditText;
  EditText zoneEditText;
  EditText cityEditText;
  EditText provinceEditText;
  EditText regionEditText;
  EditText areaEditText;
  EditText countryEditText;
  EditText phoneEditText;
  EditText cellEditText;
  EditText faxEditText;
  EditText voipEditText;
  EditText emailEditText;
  EditText webEditText;
  EditText taxPayerNumberEditText;
  EditText taxRegistrationNumberEditText;

}
