package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.TextView;

public class PersonInChargeRolesActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.personinchargeroles;

  }
  
  int getMenu() {
      
    return R.menu.personinchargeroles_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedPersonInChargeRoleIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedPersonInChargeRoleIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.personInChargeRoles_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.personInChargeRoles_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.personInChargeRoles_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.personInChargeRoles;

  }
  
  int getItemLayout() {
      
    return R.layout.personinchargeroles_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    PersonInChargeRole selectionPersonInChargeRole = (PersonInChargeRole) selectionEntry;

    TextView personInChargeRoleNameTextView = (TextView) row.findViewById(R.id.roleName);
    
    personInChargeRoleNameTextView.setText(selectionPersonInChargeRole.name);
    
  }
  
  Class getDetailActivityClass() {
      
    return PersonInChargeRoleActivity.class;

  }
  
  Object newEntry() {
            
    return new PersonInChargeRole();
    
  }
  
  void deleteSelectedEntry() {

    PersonInChargeRole personInChargeRole = (PersonInChargeRole) getSelectionEntries().get(getSelectedEntryIndex());
    
    getSelectionEntries().remove(personInChargeRole);
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
  boolean canSelectEntry() {

    return true;

  }

}