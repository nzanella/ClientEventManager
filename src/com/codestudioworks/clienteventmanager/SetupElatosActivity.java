package com.codestudioworks.clienteventmanager;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class SetupElatosActivity extends Activity {

  int getLayout() {

    return R.layout.setupelatos;

  }
  
  int getMenu() {

    return R.menu.setupelatos_editable_menu;

  }
  
  int getSaveMenuItem() {

    return R.id.save;

  }
  
  int getCannotConnectMessage() {
    
    return R.string.setupelatos_message_cannotconnect;
    
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(getLayout());
    
    // retrieve entry fields

    this.companyNameEditText = (EditText) findViewById(R.id.companyName); 
    this.userNameEditText = (EditText) findViewById(R.id.userName); 
    this.passwordEditText = (EditText) findViewById(R.id.password); 

    // initialize entry fields from preferences
    
    SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
    
    this.companyNameEditText.setText(settings.getString(Data.PREF_ELATOSACCOUNT_BUSINESSNAME, null));
    this.userNameEditText.setText(settings.getString(Data.PREF_ELATOSACCOUNT_USERNAME, null));
    this.passwordEditText.setText(settings.getString(Data.PREF_ELATOSACCOUNT_PASSWORD, null));

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
        
        // retrieve entry fields
      
        String businessName = this.companyNameEditText.getText().toString();
        String userName = this.userNameEditText.getText().toString();
        String password = this.passwordEditText.getText().toString();
        
        // store entry fields into preferences
        
        SharedPreferences settings = getSharedPreferences(Data.PREF_ELATOSACCOUNT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Data.PREF_ELATOSACCOUNT_BUSINESSNAME, businessName);
        editor.putString(Data.PREF_ELATOSACCOUNT_USERNAME, userName);
        editor.putString(Data.PREF_ELATOSACCOUNT_PASSWORD, password);
        editor.commit();
        
        /* following code would raise network on main thread exception, can place in asynctask if desired
        if (ElatosConnection.connect(businessName, userName, password))
        
          finish();
        
        else
          
          Toast.makeText(this, getCannotConnectMessage(), Toast.LENGTH_SHORT).show();
        */

        finish();
        
        break;

    }

    return true;

  }
  
  // entry to be manipulated
    
  BankAccount bankAccount;

  EditText companyNameEditText;
  EditText userNameEditText;
  EditText passwordEditText;
  
}