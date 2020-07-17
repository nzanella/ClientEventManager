package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class BankAccountsDoneActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.bankaccounts;

  }
  
  int getMenu() {
      
    return R.menu.bankaccounts_done_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedBankAccountIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedBankAccountIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.bankAccounts_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.bankAccounts_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.bankAccounts_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.bankAccounts;

  }
  
  int getItemLayout() {
      
    return R.layout.bankaccounts_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    BankAccount selectionBankAccount = (BankAccount) selectionEntry;

    TextView bankAccountNameTextView = (TextView) row.findViewById(R.id.bankAccountName);
    
    bankAccountNameTextView.setText(selectionBankAccount.bankAccountName);
    
  }
  
  Class getDetailActivityClass() {
      
    return BankAccountActivity.class;

  }
  
  Object newEntry() {
            
    return new BankAccount();
    
  }
  
  void deleteSelectedEntry() {

    getSelectionEntries().remove(getSelectionEntries().get(getSelectedEntryIndex()));
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
  boolean canSelectEntry() {

    return true;

  }

}