package com.codestudioworks.clienteventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class BankAccountActivity extends Activity {

  int getLayout() {

    return R.layout.bankaccount;

  }
  
  int getAddTitle() {
    
    return R.string.bankAccount_title_add;

  }
  
  int getModifyTitle() {

    return R.string.bankAccount_title_modify;

  }
  
  int getMenu() {

    return R.menu.bankaccount_editable_menu;

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
    
    this.bankAccount = (BankAccount) Globals.actions.peek().getEntry();
    
    // retrieve and set screen title according to action
    
    ((TextView) findViewById(R.id.title)).setText(this.action == Action.ACTION_ENTRY_ADD ? getAddTitle() : getModifyTitle());
    
    // retrieve entry fields

    this.bankAccountNameEditText = (EditText) findViewById(R.id.bankAccountName); 
    this.institutionNumberEditText = (EditText) findViewById(R.id.institutionNumber); 
    this.branchNumberEditText = (EditText) findViewById(R.id.branchNumber); 
    this.accountNumberEditText = (EditText) findViewById(R.id.accountNumber); 
    this.ibanEditText = (EditText) findViewById(R.id.iban); 

    // initialize entry fields
    
    this.bankAccountNameEditText.setText(this.bankAccount.bankAccountName);
    this.institutionNumberEditText.setText(this.bankAccount.institutionNumber);
    this.branchNumberEditText.setText(this.bankAccount.branchNumber);
    this.accountNumberEditText.setText(this.bankAccount.accountNumber);
    this.ibanEditText.setText(this.bankAccount.iban);

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
      
    this.bankAccount.bankAccountName = this.bankAccountNameEditText.getText().toString();
    this.bankAccount.institutionNumber = this.institutionNumberEditText.getText().toString();
    this.bankAccount.branchNumber = this.branchNumberEditText.getText().toString();
    this.bankAccount.accountNumber = this.accountNumberEditText.getText().toString();
    this.bankAccount.iban = this.ibanEditText.getText().toString();
            
        switch (this.action) {

          case Action.ACTION_ENTRY_ADD:

            Globals.data.bankAccounts.add(bankAccount);
            
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
    
  BankAccount bankAccount;

  EditText bankAccountNameEditText;
  EditText institutionNumberEditText;
  EditText branchNumberEditText;
  EditText accountNumberEditText;
  EditText ibanEditText;

}
