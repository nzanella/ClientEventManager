package com.codestudioworks.clienteventmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonInChargeActivity extends Activity {

  int getLayout() {

    return R.layout.personincharge;

  }

  int getAddTitle() {

    return R.string.personInCharge_title_add;

  }

  int getModifyTitle() {

    return R.string.personInCharge_title_modify;

  }

  int getMenu() {

    return R.menu.personincharge_editable_menu;

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

    this.personInCharge = (PersonInCharge) Globals.actions.peek().getEntry();

    // retrieve and set screen title according to action

    ((TextView) findViewById(R.id.title)).setText(this.action == Action.ACTION_ENTRY_ADD ? getAddTitle() : getModifyTitle());

    // retrieve entry fields

    this.roleNameEditText = (EditText) findViewById(R.id.roleName);
    this.nameEditText = (EditText) findViewById(R.id.name);
    this.phoneEditText = (EditText) findViewById(R.id.phone);
    this.cellEditText = (EditText) findViewById(R.id.cell);
    this.faxEditText = (EditText) findViewById(R.id.fax);
    this.voipEditText = (EditText) findViewById(R.id.voip);
    this.emailEditText = (EditText) findViewById(R.id.email);
    this.webEditText = (EditText) findViewById(R.id.web);

    // initialize entry fields
    
    updateDisplay();

    // setup listeners
    
    this.roleNameEditText.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        
        storeDisplay();

        Globals.actions.push(new Action(PersonInChargeActivity.this.personInCharge, new PropertySetterFunctor() {
          
          public void setProperty(Object parentEntry, Object childEntry) {
                      
            PersonInCharge personInCharge = (PersonInCharge) parentEntry;
        
            personInCharge.role = (PersonInChargeRole) childEntry;
            
          }
          
        }));
        
        startActivityForResult(new Intent(PersonInChargeActivity.this, PersonInChargeRolesActivity.class), SELECT_PERSONINCHARGEROLE_REQUEST);
        
      }

    });

    // assume everything is going to work out

    setResult(RESULT_OK);

  }
  
  void updateDisplay() {

    this.roleNameEditText.setText(this.personInCharge.role != null ? this.personInCharge.role.name : null);
    this.nameEditText.setText(this.personInCharge.name);
    this.phoneEditText.setText(this.personInCharge.phone);
    this.cellEditText.setText(this.personInCharge.cell);
    this.faxEditText.setText(this.personInCharge.fax);
    this.voipEditText.setText(this.personInCharge.voip);
    this.emailEditText.setText(this.personInCharge.email);
    this.webEditText.setText(this.personInCharge.web);

  }
  
  void storeDisplay() {

    /* person in charge role name cannot be edited so do not store corresponding role here */
    this.personInCharge.name = this.nameEditText.getText().toString();
    this.personInCharge.phone = this.phoneEditText.getText().toString();
    this.personInCharge.cell = this.cellEditText.getText().toString();
    this.personInCharge.fax = this.faxEditText.getText().toString();
    this.personInCharge.voip = this.voipEditText.getText().toString();
    this.personInCharge.email = this.emailEditText.getText().toString();
    this.personInCharge.web = this.webEditText.getText().toString();
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      
    switch (requestCode) {
    
      case SELECT_PERSONINCHARGEROLE_REQUEST:
        
        if (resultCode == RESULT_OK) {
            
          // refresh data
      
          updateDisplay();

        }
        
    }
    
  }
  
  void preAdd() {
    
    //Globals.data.clients.get(Globals.selectedClientIndex).otherPeopleInCharge.add(personInCharge);
    ((ArrayList<PersonInCharge>) Globals.actions.peek().getParent()).add(personInCharge);
    
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

    if (item.getItemId() == getSaveMenuItem()) {
      
      // ensure a role has been specified

      if (this.roleNameEditText.getText().toString().isEmpty()) {
        
        Toast.makeText(this, R.string.personInCharge_message_noRoleName, Toast.LENGTH_SHORT).show();
        
        return false;
        
      }
        
      // store entry fields into local object

      this.personInCharge.role.name = this.roleNameEditText.getText().toString();
      this.personInCharge.name = this.nameEditText.getText().toString();
      this.personInCharge.phone = this.phoneEditText.getText().toString();
      this.personInCharge.cell = this.cellEditText.getText().toString();
      this.personInCharge.fax = this.faxEditText.getText().toString();
      this.personInCharge.voip = this.voipEditText.getText().toString();
      this.personInCharge.email = this.emailEditText.getText().toString();
      this.personInCharge.web = this.webEditText.getText().toString();

      // store local object into database

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

    }

    return true;

  }

  // action to be performed

  int action;

  // entry to be manipulated

  PersonInCharge personInCharge;
  
  // request codes
  
  private static final int SELECT_PERSONINCHARGEROLE_REQUEST = 0;

  // user interface fields

  EditText roleNameEditText;
  EditText nameEditText;
  EditText phoneEditText;
  EditText cellEditText;
  EditText faxEditText;
  EditText voipEditText;
  EditText emailEditText;
  EditText webEditText;

}
