package com.codestudioworks.clienteventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentTypeActivity extends Activity {

  int getLayout() {

    return R.layout.paymenttype;

  }
  
  int getAddTitle() {
    
    return R.string.paymentType_title_add;

  }
  
  int getModifyTitle() {

    return R.string.paymentType_title_modify;

  }
  
  int getMenu() {

    return R.menu.paymenttype_editable_menu;

  }
  
  int getSaveMenuItem() {

    return R.id.save;

  }
  

  int getCodeUsedMessage() {

    return R.string.paymentType_message_codeused;

  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(getLayout());
    
    // retrieve action parameter passed as global variable
    
    this.action = Globals.actions.peek().getType();
    
    this.paymentType = (PaymentType) Globals.actions.peek().getEntry();
    
    // retrieve and set screen title according to action
    
    ((TextView) findViewById(R.id.title)).setText(this.action == Action.ACTION_ENTRY_ADD ? getAddTitle() : getModifyTitle());
    
    // retrieve entry fields

    this.paymentTypeCodeEditText = (EditText) findViewById(R.id.code); 

    // initialize entry fields
    
    this.paymentTypeCodeEditText.setText(this.paymentType.code);
    
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
      
        this.paymentType.code = this.paymentTypeCodeEditText.getText().toString();
            
        switch (this.action) {

          case Action.ACTION_ENTRY_ADD:

            // ensure code has not already been used

            if (PaymentType.isCodeUsed(Globals.data.paymentTypes, this.paymentType.code)) {
              
              Toast.makeText(this, getCodeUsedMessage(), Toast.LENGTH_SHORT).show();
              
              return false;
              
            }

            Globals.data.paymentTypes.add(paymentType);
            
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
    
  PaymentType paymentType;

  EditText paymentTypeCodeEditText;

}
