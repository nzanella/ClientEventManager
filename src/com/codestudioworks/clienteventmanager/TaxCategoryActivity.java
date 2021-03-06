package com.codestudioworks.clienteventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TaxCategoryActivity extends Activity {

  int getLayout() {

    return R.layout.taxcategory;

  }
  
  int getAddTitle() {
    
    return R.string.taxCategory_title_add;

  }
  
  int getModifyTitle() {

    return R.string.taxCategory_title_modify;

  }
  
  int getMenu() {

    return R.menu.taxcategory_editable_menu;

  }
  
  int getSaveMenuItem() {

    return R.id.save;

  }
  
  int getCodeUsedMessage() {

    return R.string.taxCategory_message_codeUsed;

  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(getLayout());
    
    // retrieve action parameter passed as global variable
    
    this.action = Globals.actions.peek().getType();
    
    this.taxCategory = (TaxCategory) Globals.actions.peek().getEntry();
    
    // retrieve and set screen title according to action
    
    ((TextView) findViewById(R.id.title)).setText(this.action == Action.ACTION_ENTRY_ADD ? getAddTitle() : getModifyTitle());
    
    // retrieve entry fields

    this.taxCategoryCodeEditText = (EditText) findViewById(R.id.code);
    this.taxCategoryRateEditText = (EditText) findViewById(R.id.rate);
    this.taxCategoryDescriptionEditText = (EditText) findViewById(R.id.description);

    // initialize entry fields
    
    this.taxCategoryCodeEditText.setText(this.taxCategory.code);
    this.taxCategoryRateEditText.setText(this.taxCategory.rate);
    this.taxCategoryDescriptionEditText.setText(this.taxCategory.description);

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
      
 		this.taxCategory.code = this.taxCategoryCodeEditText.getText().toString();
   		this.taxCategory.rate = this.taxCategoryRateEditText.getText().toString();
   		this.taxCategory.description = this.taxCategoryDescriptionEditText.getText().toString();
            
        switch (this.action) {

          case Action.ACTION_ENTRY_ADD:

            // ensure code has not already been used

            if (TaxCategory.isCodeUsed(Globals.data.taxCategories, this.taxCategory.code)) {
              
              Toast.makeText(this, getCodeUsedMessage(), Toast.LENGTH_SHORT).show();
              
              return false;
              
            }

            Globals.data.taxCategories.add(taxCategory);
            
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
    
  TaxCategory taxCategory;

  EditText taxCategoryCodeEditText;
  EditText taxCategoryRateEditText;
  EditText taxCategoryDescriptionEditText;

}
