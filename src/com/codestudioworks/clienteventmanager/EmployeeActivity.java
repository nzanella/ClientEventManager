package com.codestudioworks.clienteventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class EmployeeActivity extends Activity {

  int getLayout() {

    return R.layout.employee;

  }
  
  int getAddTitle() {
    
    return R.string.employee_title_add;

  }
  
  int getModifyTitle() {

    return R.string.employee_title_modify;

  }
  
  int getMenu() {

    return R.menu.employee_editable_menu;

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
    
    this.employee = (Employee) Globals.actions.peek().getEntry();
    
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
    this.startDateEditText = (EditText) findViewById(R.id.startDate);
    this.endDateEditText = (EditText) findViewById(R.id.endDate);

    // initialize entry fields

    this.nameEditText.setText(this.employee.name);
    this.addressEditText.setText(this.employee.address);
    this.zoneEditText.setText(this.employee.zone);
    this.cityEditText.setText(this.employee.city);
    this.provinceEditText.setText(this.employee.province);
    this.regionEditText.setText(this.employee.region);
    this.areaEditText.setText(this.employee.area);
    this.countryEditText.setText(this.employee.country);
    this.phoneEditText.setText(this.employee.phone);
    this.cellEditText.setText(this.employee.cell);
    this.faxEditText.setText(this.employee.fax);
    this.voipEditText.setText(this.employee.voip);
    this.emailEditText.setText(this.employee.email);
    this.webEditText.setText(this.employee.web);
    this.taxPayerNumberEditText.setText(this.employee.taxPayerNumber);
    this.taxRegistrationNumberEditText.setText(this.employee.taxRegistrationNumber);
    this.startDateEditText.setText(this.employee.startDate);
    this.endDateEditText.setText(this.employee.endDate);

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
      
        this.employee.name = this.nameEditText.getText().toString();
        this.employee.address = this.addressEditText.getText().toString();
        this.employee.zone = this.zoneEditText.getText().toString();
        this.employee.city = this.cityEditText.getText().toString();
        this.employee.province = this.provinceEditText.getText().toString();
        this.employee.region = this.regionEditText.getText().toString();
        this.employee.area = this.areaEditText.getText().toString();
        this.employee.country = this.countryEditText.getText().toString();
        this.employee.phone = this.phoneEditText.getText().toString();
        this.employee.cell = this.cellEditText.getText().toString();
        this.employee.fax = this.faxEditText.getText().toString();
        this.employee.voip = this.voipEditText.getText().toString();
        this.employee.email = this.emailEditText.getText().toString();
        this.employee.web = this.webEditText.getText().toString();
        this.employee.taxPayerNumber = this.taxPayerNumberEditText.getText().toString();
        this.employee.taxRegistrationNumber = this.taxRegistrationNumberEditText.getText().toString();
        this.employee.startDate = this.startDateEditText.getText().toString();
        this.employee.endDate = this.endDateEditText.getText().toString();
        
        switch (this.action) {

          case Action.ACTION_ENTRY_ADD:

            Globals.data.employees.add(employee);
            
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
    
  Employee employee;

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
  EditText startDateEditText;
  EditText endDateEditText;

}