package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ConsultantsActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.consultants;

  }
  
  int getMenu() {
      
    return R.menu.consultants_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedAdvisorIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedAdvisorIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.consultants_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.consultants_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.consultants_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.businessConsultants;

  }
  
  int getItemLayout() {
      
    return R.layout.consultants_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    BusinessConsultant selectionAdvisor = (BusinessConsultant) selectionEntry;

    TextView advisorNameTextView = (TextView) row.findViewById(R.id.name);
    
    advisorNameTextView.setText(selectionAdvisor.name);
    
  }
  
  Class getDetailActivityClass() {
      
    return ConsultantActivity.class;

  }
  
  Object newEntry() {
            
    return new BusinessConsultant();
    
  }
  
  void deleteSelectedEntry() {

    BusinessConsultant businessAdvisor = (BusinessConsultant) getSelectionEntries().get(getSelectedEntryIndex());
    
    Globals.data.businessConsultants.remove(businessAdvisor);
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }

}