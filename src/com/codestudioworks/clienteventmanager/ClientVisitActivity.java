package com.codestudioworks.clienteventmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ClientVisitActivity extends Activity {

  int getLayout() {

    return R.layout.clientvisit;

  }
  
  int getAddTitle() {
    
    return R.string.clientVisit_title_add;

  }
  
  int getModifyTitle() {

    return R.string.clientVisit_title_modify;

  }
  
  int getMenu() {

    return R.menu.clientvisit_editable_menu;

  }
  
  int getSaveMenuItem() {

    return R.id.save;

  }
  
  void retrieveFields() {
	  
    this.visitorNameEditText = (EditText) findViewById(R.id.visitorName); 
    this.visitDateEditText = (EditText) findViewById(R.id.visitDate); 
    this.visitNotesEditText = (EditText) findViewById(R.id.visitNotes); 

  }
  
  void loadEntry() {
	  
    this.visitorNameEditText.setText(clientVisit.visitorName);
    this.visitDateEditText.setText(clientVisit.visitDate);
    this.visitNotesEditText.setText(clientVisit.visitNotes);
    
  }
  
  void storeEntry() {
	  
	clientVisit.visitorName = this.visitorNameEditText.getText().toString();
    clientVisit.visitDate = this.visitDateEditText.getText().toString();
    clientVisit.visitNotes = this.visitNotesEditText.getText().toString();
     
  }
  
  void preAdd() {
  
    //Globals.data.clients.get(Globals.selectedClientIndex).visits.add(clientVisit);
    ((ArrayList<ClientVisit>) Globals.actions.peek().getParent()).add(clientVisit);

  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(getLayout());

    // retrieve action parameter passed as global variable
    
    this.action = Globals.actions.peek().getType();
    
    this.clientVisit = (ClientVisit) Globals.actions.peek().getEntry();
    
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
    
  ClientVisit clientVisit;

  EditText visitorNameEditText;
  EditText visitDateEditText;
  EditText visitNotesEditText;

}
