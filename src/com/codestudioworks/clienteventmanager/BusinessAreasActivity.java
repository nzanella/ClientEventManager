package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class BusinessAreasActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.businessareas;

  }
  
  int getMenu() {
      
    return R.menu.businessareas_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedBusinessAreaIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedBusinessAreaIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.businessAreas_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.businessAreas_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.businessAreas_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.businessAreas;

  }
  
  int getItemLayout() {
      
    return R.layout.businessareas_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    BusinessArea selectionBusinessArea = (BusinessArea) selectionEntry;

    TextView businessAreaCodeTextView = (TextView) row.findViewById(R.id.code);
    
    businessAreaCodeTextView.setText(selectionBusinessArea.code);
    
    TextView businessAreaDescriptionTextView = (TextView) row.findViewById(R.id.description);
    
    businessAreaDescriptionTextView.setText(selectionBusinessArea.description);
    
  }
  
  Class getDetailActivityClass() {
      
    return BusinessAreaActivity.class;

  }
  
  Object newEntry() {
            
    return new BusinessArea();
    
  }
  
  void deleteSelectedEntry() {

    BusinessArea businessArea = (BusinessArea) getSelectionEntries().get(getSelectedEntryIndex());
    
    getSelectionEntries().remove(businessArea);
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }

}