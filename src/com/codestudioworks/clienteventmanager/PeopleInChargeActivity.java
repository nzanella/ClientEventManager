package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class PeopleInChargeActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.peopleincharge;

  }
  
  int getMenu() {
      
    return R.menu.peopleincharge_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedPersonInChargeIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedPersonInChargeIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.peopleInCharge_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.peopleInCharge_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.peopleInCharge_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    //return Globals.data.clients.get(Globals.selectedClientIndex).otherPeopleInCharge;
   return ((Client) Globals.actions.peek().getEntry()).otherPeopleInCharge;

  }
  
  int getItemLayout() {
      
    return R.layout.peopleincharge_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {

    PersonInCharge selectionPersonInCharge = (PersonInCharge) selectionEntry;

    TextView personInChargeRoleNameTextView = (TextView) row.findViewById(R.id.roleName);
    TextView personInChargeNameTextView = (TextView) row.findViewById(R.id.personName);
    
    personInChargeRoleNameTextView.setText(selectionPersonInCharge.role.name);
    personInChargeNameTextView.setText(selectionPersonInCharge.name);
    
  }
  
  Class getDetailActivityClass() {
      
    return PersonInChargeActivity.class;

  }
  
  Object newEntry() {
            
    return new PersonInCharge();
    
  }
  
  void deleteSelectedEntry() {

    PersonInCharge personInCharge = (PersonInCharge) getSelectionEntries().get(getSelectedEntryIndex());
    
    getSelectionEntries().remove(personInCharge);
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }

}