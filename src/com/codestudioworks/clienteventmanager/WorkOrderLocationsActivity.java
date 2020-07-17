package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.TextView;

public class WorkOrderLocationsActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.workorderlocations;

  }
  
  int getMenu() {
      
    return R.menu.workorderlocations_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedWorkOrderLocationIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedWorkOrderLocationIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.workOrderLocations_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.workOrderLocations_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.workOrderLocations_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    //return Globals.data.clients.get(Globals.selectedClientIndex).workOrderLocations;
    return ((Client) Globals.actions.peek().getEntry()).workOrderLocations;

  }
  
  int getItemLayout() {
      
    return R.layout.workorderlocations_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    WorkOrderLocation selectionWorkOrderLocation = (WorkOrderLocation) selectionEntry;

    TextView workOrderLocationAddressTextView = (TextView) row.findViewById(R.id.address);
    TextView workOrderLocationCityTextView = (TextView) row.findViewById(R.id.city);
    
    workOrderLocationAddressTextView.setText(selectionWorkOrderLocation.address);
    workOrderLocationCityTextView.setText(selectionWorkOrderLocation.city);
    
  }
  
  Class getDetailActivityClass() {
      
    return WorkOrderLocationActivity.class;

  }
  
  Object newEntry() {
            
    return new WorkOrderLocation();
    
  }
  
  void deleteSelectedEntry() {

    getSelectionEntries().remove(getSelectionEntries().get(getSelectedEntryIndex()));
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
}