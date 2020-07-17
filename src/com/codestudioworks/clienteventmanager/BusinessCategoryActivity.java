package com.codestudioworks.clienteventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class BusinessCategoryActivity extends Activity {

  int getLayout() {

    return R.layout.businesscategory;

  }

  int getAddTitle() {

    return R.string.businessCategory_title_add;

  }

  int getModifyTitle() {

    return R.string.businessCategory_title_modify;

  }

  int getMenu() {

    return R.menu.businesscategory_editable_menu;

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

    this.businessCategory = (BusinessCategory) Globals.actions.peek().getEntry();

    // retrieve and set screen title according to action

    ((TextView) findViewById(R.id.title))
        .setText(this.action == Action.ACTION_ENTRY_ADD ? getAddTitle()
            : getModifyTitle());

    // retrieve entry fields

    this.businessCategoryNameEditText = (EditText) findViewById(R.id.businessCategoryName);

    // initialize entry fields

    this.businessCategoryNameEditText.setText(this.businessCategory.name);

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

    if (item.getItemId() == getSaveMenuItem()) {

      this.businessCategory.name = this.businessCategoryNameEditText.getText().toString();

      switch (this.action) {

        case Action.ACTION_ENTRY_ADD:

          Globals.data.businessCategories.add(businessCategory);

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

  BusinessCategory businessCategory;

  // user interface fields

  EditText businessCategoryNameEditText;

}
