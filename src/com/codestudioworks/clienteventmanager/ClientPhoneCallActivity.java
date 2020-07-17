package com.codestudioworks.clienteventmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ClientPhoneCallActivity extends Activity {

  int getLayout() {

    return R.layout.clientphonecall;

  }
  
  int getAddTitle() {
    
    return R.string.clientPhoneCall_title_add;

  }
  
  int getModifyTitle() {

    return R.string.clientPhoneCall_title_modify;

  }
  
  int getMenu() {

    return R.menu.clientphonecall_editable_menu;

  }
  
  int getSaveMenuItem() {

    return R.id.save;

  }
  
  void retrieveFields() {
	  
    this.callerNameEditText = (EditText) findViewById(R.id.callerName);
    this.callDateEditText = (EditText) findViewById(R.id.callDate);
    this.caleeNameEditText = (EditText) findViewById(R.id.calleeName);
    this.callNotesEditText = (EditText) findViewById(R.id.callNotes);

  }
  
  void loadEntry() {
	  
    this.callerNameEditText.setText(clientPhoneCall.callerName);
    this.callDateEditText.setText(clientPhoneCall.callDate);
    this.caleeNameEditText.setText(clientPhoneCall.calleeName);
    this.callNotesEditText.setText(clientPhoneCall.callNotes);
    
  }
  
  void storeEntry() {
	  
    clientPhoneCall.callerName = this.callerNameEditText.getText().toString();
    clientPhoneCall.callDate = this.callDateEditText.getText().toString();
    clientPhoneCall.calleeName = this.caleeNameEditText.getText().toString();
    clientPhoneCall.callNotes = this.callNotesEditText.getText().toString();
     
  }
  
  void preAdd() {
  
    //no good if we don't have a selection
    //Globals.data.clients.get(Globals.selectedClientIndex).phoneCalls.add(clientPhoneCall);
    
    ((ArrayList<ClientPhoneCall>) Globals.actions.peek().getParent()).add(clientPhoneCall);

  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(getLayout());
    
    // retrieve action parameter passed as global variable
    
    this.action = Globals.actions.peek().getType();
    
    this.clientPhoneCall = (ClientPhoneCall) Globals.actions.peek().getEntry();
    
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
    
  ClientPhoneCall clientPhoneCall;

  EditText callerNameEditText;
  EditText callDateEditText;
  EditText caleeNameEditText;
  EditText callNotesEditText;

}
