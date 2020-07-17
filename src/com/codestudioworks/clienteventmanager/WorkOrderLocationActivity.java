package com.codestudioworks.clienteventmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class WorkOrderLocationActivity extends Activity {

  int getLayout() {

    return R.layout.workorderlocation;

  }
  
  int getAddTitle() {
    
    return R.string.workOrderLocation_title_add;

  }
  
  int getModifyTitle() {

    return R.string.workOrderLocation_title_modify;

  }
  
  int getMenu() {

    return R.menu.workorderlocation_editable_menu;

  }
  
  int getSaveMenuItem() {

    return R.id.save;

  }
  
  void retrieveFields() {
	  
    this.workOrderLocationAddressEditText = (EditText) findViewById(R.id.address);
    this.workOrderLocationZoneEditText = (EditText) findViewById(R.id.zone);
    this.workOrderLocationCityEditText = (EditText) findViewById(R.id.city);
    this.workOrderLocationProvinceEditText = (EditText) findViewById(R.id.province);
    this.workOrderLocationRegionEditText = (EditText) findViewById(R.id.region);
    this.workOrderLocationAreaEditText = (EditText) findViewById(R.id.area);
    this.workOrderLocationCountryEditText = (EditText) findViewById(R.id.country);
    this.workOrderLocationLatitudeEditText = (EditText) findViewById(R.id.latitude);
    this.workOrderLocationLongitudeEditText = (EditText) findViewById(R.id.longitude);
  }
  
  void loadEntry() {
	  
    this.workOrderLocationAddressEditText.setText(workOrderLocation.address);
    this.workOrderLocationZoneEditText.setText(workOrderLocation.zone);
    this.workOrderLocationCityEditText.setText(workOrderLocation.city);
    this.workOrderLocationProvinceEditText.setText(workOrderLocation.province);
    this.workOrderLocationRegionEditText.setText(workOrderLocation.region);
    this.workOrderLocationAreaEditText.setText(workOrderLocation.area);
    this.workOrderLocationCountryEditText.setText(workOrderLocation.country);
    this.workOrderLocationLatitudeEditText.setText(Double.valueOf(workOrderLocation.latitude).toString());
    this.workOrderLocationLongitudeEditText.setText(Double.valueOf(workOrderLocation.longitude).toString());

  }
  
  void storeEntry() {
    
    this.workOrderLocation.address = this.workOrderLocationAddressEditText.getText().toString();
    this.workOrderLocation.zone = this.workOrderLocationZoneEditText.getText().toString();
    this.workOrderLocation.city = this.workOrderLocationCityEditText.getText().toString();
    this.workOrderLocation.province = this.workOrderLocationProvinceEditText.getText().toString();
    this.workOrderLocation.region = this.workOrderLocationRegionEditText.getText().toString();
    this.workOrderLocation.area = this.workOrderLocationAreaEditText.getText().toString();
    this.workOrderLocation.country = this.workOrderLocationCountryEditText.getText().toString();
    this.workOrderLocation.latitude = Double.valueOf(this.workOrderLocationLatitudeEditText.getText().toString());
    this.workOrderLocation.longitude = Double.valueOf(this.workOrderLocationLongitudeEditText.getText().toString());
    
  }
  
  void preAdd() {
  
    //Globals.data.clients.get(Globals.selectedClientIndex).workOrderLocations.add(workOrderLocation);
    
    ((ArrayList<WorkOrderLocation>) Globals.actions.peek().getParent()).add(workOrderLocation);

  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(getLayout());
    
    // retrieve action parameter passed as global variable
    
    this.action = Globals.actions.peek().getType();
    
    this.workOrderLocation = (WorkOrderLocation) Globals.actions.peek().getEntry();
    
    // retrieve and set screen title according to action
    
    ((TextView) findViewById(R.id.title)).setText(this.action == Action.ACTION_ENTRY_ADD ? getAddTitle() : getModifyTitle());
    
    // retrieve entry fields
    
    retrieveFields();

    // initialize entry fields
    
    loadEntry();

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

        storeEntry();            
        
        switch (this.action) {

          case Action.ACTION_ENTRY_ADD:

            preAdd();
            
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
    
  WorkOrderLocation workOrderLocation;

  EditText workOrderLocationAddressEditText;
  EditText workOrderLocationZoneEditText;
  EditText workOrderLocationCityEditText;
  EditText workOrderLocationProvinceEditText;
  EditText workOrderLocationRegionEditText;
  EditText workOrderLocationAreaEditText;
  EditText workOrderLocationCountryEditText;
  EditText workOrderLocationLatitudeEditText;
  EditText workOrderLocationLongitudeEditText;

}
