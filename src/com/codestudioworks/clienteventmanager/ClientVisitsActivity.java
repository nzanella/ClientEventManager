package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.TextView;

public class ClientVisitsActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.clientvisits;

  }
  
  int getMenu() {
      
    return R.menu.clientvisits_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedClientVisitIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedClientVisitIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.clientVisits_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.clientVisits_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.clientVisits_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return ((Client) Globals.actions.peek().getEntry()).visits;

  }
  
  int getItemLayout() {
      
    return R.layout.clientvisits_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    ClientVisit selectionClientVisit = (ClientVisit) selectionEntry;

    TextView visitorNameTextView = (TextView) row.findViewById(R.id.visitorName);
    TextView visitDateTextView = (TextView) row.findViewById(R.id.visitDate);
    
    visitorNameTextView.setText(selectionClientVisit.visitorName);
    visitDateTextView.setText(selectionClientVisit.visitDate);
    
  }
  
  Class getDetailActivityClass() {
      
    return ClientVisitActivity.class;

  }
  
  Object newEntry() {
            
    return new ClientVisit();
    
  }
  
  void deleteSelectedEntry() {

    getSelectionEntries().remove(getSelectionEntries().get(getSelectedEntryIndex()));
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
}