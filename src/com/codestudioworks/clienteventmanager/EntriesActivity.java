package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public abstract class EntriesActivity extends UpdatableActivity {

  abstract int getLayout();
  
  abstract int getMenu();

  abstract int getSelectedEntryIndex();
  
  abstract void setSelectedEntryIndex(int index);
  
  abstract int getReallyDeleteString();
  
  abstract int getYesDeleteString();
  
  abstract int getNoDeleteString();
  
  abstract List getSelectionEntries();
  
  abstract int getItemLayout();
  
  abstract void populateRow(Object selectionEntry, View row);
      
  abstract Class getDetailActivityClass();
      
  abstract Object newEntry();
  
  abstract void deleteSelectedEntry();
  
  void synchronizeEntries() {
    
    /* to be overridden in derived class only if implemented */
    
  }
  
  
  void init() {

    /* intentionally left blank, override if necessary */

  }

  int getListId() {
    
    return R.id.list;
    
  }
  
  int getAddId() {
    
    return R.id.add;
    
  }
  
  int getModifyId() {
    
    return R.id.modify;
    
  }
  
  int getDeleteId() {
    
    return R.id.delete;
  }
  
  int getSelectId() {

   return R.id.select;

  }
  
  int getSyncronizeId() {

   return R.id.syncronize;

  }
  
  int getRowSelectedColor() {

    return R.color.entries_rowSelected;

  }

  int getRowUnselectedColor() {

    return R.color.entries_rowUnselected;

  }
  
  boolean canSelectEntry() {
    
    return false;

  }
  
  boolean canSyncronize() {

    return false;

  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    
    // perform any required initializations
    
    init();
    
    // set content view

    setContentView(getLayout());

    // retrieve list view

    this.listView = (ListView) findViewById(getListId());

    // set list view adapter

    this.listView.setAdapter(new EntriesAdapter());
    
    // update display

    updateDisplay();

    // set successful result code in case needed by calling activity

    setResult(RESULT_OK);
    
  }

  @Override
  public void updateDisplay() {

    if (getSelectedEntryIndex() > getSelectionEntries().size() - 1)
        
      setSelectedEntryIndex(getSelectionEntries().size() - 1);

    ((EntriesAdapter) this.listView.getAdapter()).notifyDataSetChanged();

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      
    switch (requestCode) {
    
      case ADD_ENTRY_REQUEST:
        
        if (resultCode == RESULT_OK) {
//neil exchanged these two lines            
          // pop action performed from actions stack
    
          Globals.actions.pop();

          // refresh data
      
          updateDisplay();

        }

        break;
    
      case MODIFY_ENTRY_REQUEST:

        if (resultCode == RESULT_OK) {
 //neil exchanged these two lines                       
          // pop action performed from actions stack
    
          Globals.actions.pop();

          // refresh data
      
          updateDisplay();
          
        }

        break;
    
    }
    
 }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    MenuInflater inflater = getMenuInflater();

    inflater.inflate(getMenu(), menu);
            
    return true;

  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {

    MenuItem addItem = menu.findItem(getAddId());
    MenuItem modifyItem = menu.findItem(getModifyId());
    MenuItem deleteItem = menu.findItem(getDeleteId());
    MenuItem selectItem = menu.findItem(getSelectId());

    addItem.setEnabled(true);
    if (getSelectedEntryIndex() != -1) {
      modifyItem.setEnabled(true);
      deleteItem.setEnabled(true);
      if (canSelectEntry())
        selectItem.setEnabled(true);
    } else {
      modifyItem.setEnabled(false);
      deleteItem.setEnabled(false);
      if (canSelectEntry())
        selectItem.setEnabled(false);
    }

    return super.onPrepareOptionsMenu(menu);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    
    if (item.getItemId() == getAddId())
      
      addEntry();
        
    else if (item.getItemId() == getModifyId())
        
      modifyEntry();
        
    else if (item.getItemId() == getDeleteId())
        
      showDialog(DELETEENTRY_DIALOG_ID);
    
    else if (item.getItemId() == getSelectId()) {
      
      selectEntry();
      
      Globals.actions.pop();
      
      finish();
           
    }
    
    else if (item.getItemId() == getSyncronizeId()) {
      
      synchronizeEntries();
      
    }

    return true;

  }

  @Override
  protected Dialog onCreateDialog(int id) {

    switch (id) {

      case DELETEENTRY_DIALOG_ID:

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
            .setMessage(getString(getReallyDeleteString()))
            .setCancelable(false)
            .setPositiveButton(getString(getYesDeleteString()),
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {

                    doDeleteEntry();

                  }
                })
            .setNegativeButton(getString(getNoDeleteString()),
                new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                  }
                });

        return builder.create();
            
    }
    
    return null;

  }  

  private class EntriesAdapter extends BaseAdapter {

    @Override
    public int getCount() {

      return getSelectionEntries().size();

    }

    @Override
    public long getItemId(int position) {

      return position;

    }

    @Override
    public Object getItem(int position) {

      return getSelectionEntries().get(position);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      Object selectionEntry = getItem(position);

      LayoutInflater inflater = getLayoutInflater();

      View row = inflater.inflate(getItemLayout(), parent, false);

      if (position == getSelectedEntryIndex()) {

        row.setBackgroundResource(getRowSelectedColor());
            
      } else {

        row.setBackgroundResource(getRowUnselectedColor());

      }
      
      // populate row with selection entry values
      
      populateRow(selectionEntry, row);

      // for item modification

      row.setOnClickListener(new EntryClickListener(position));

      return row;

    }

  }

  private class EntryClickListener implements OnClickListener {

    EntryClickListener(int position) {

      this.position = position;

    }

    @Override
    public void onClick(View v) {

      // check whether user had already highlighted this row

      if (getSelectedEntryIndex() == position) {

        // goto entry screen

        modifyEntry();

      } else {

        // update selected entry index
          
        setSelectedEntryIndex(position);

        // undo highlighted image at old position and set highlighted image at specified position

        ((BaseAdapter) EntriesActivity.this.listView.getAdapter()).notifyDataSetChanged();

      }

    }

    private int position;
    
  }

  void addEntry() {

    Globals.actions.push(new Action(Action.ACTION_ENTRY_ADD, newEntry(), getSelectionEntries()));

    startActivityForResult(new Intent(this, getDetailActivityClass()), ADD_ENTRY_REQUEST);
    
  }

  void modifyEntry() {

    Globals.actions.push(new Action(Action.ACTION_ENTRY_MODIFY, getSelectionEntries().get(getSelectedEntryIndex())));
    
    startActivityForResult(new Intent(this, getDetailActivityClass()), MODIFY_ENTRY_REQUEST);

  }

  private void doDeleteEntry() {

    deleteSelectedEntry();
    
    setSelectedEntryIndex(getSelectedEntryIndex() - 1);
    
    updateDisplay();
      
  }
  
  void selectEntry() {
    
    Object parent = Globals.actions.peek().getEntry();
    
    Globals.actions.peek().getPropertySetterFunctor().setProperty(parent, getSelectionEntries().get(getSelectedEntryIndex()));
    
  }
  
  // ensure requests do not overlap with requests in derived classes
  
  private static final int ADD_ENTRY_REQUEST = 10000;
  
  private static final int MODIFY_ENTRY_REQUEST = 10001;
  
  //private static final int DEFINE_CONTENT_REQUEST = 10002;
  
  private static final int DELETEENTRY_DIALOG_ID = 0;

  ListView listView;

}