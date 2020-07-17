package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class BusinessCategoriesDoneActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.businesscategories;

  }
  
  int getMenu() {
      
    return R.menu.businesscategories_done_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedBusinessCategoryIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedBusinessCategoryIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.businessCategories_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.businessCategories_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.businessCategories_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.businessCategories;

  }
  
  int getItemLayout() {
      
    return R.layout.businesscategories_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    BusinessCategory selectionBusinessCategory = (BusinessCategory) selectionEntry;

    TextView businessCategoryNameTextView = (TextView) row.findViewById(R.id.businessCategoryName);
    
    businessCategoryNameTextView.setText(selectionBusinessCategory.name);
    
  }
  
  Class getDetailActivityClass() {
      
    return BusinessCategoryActivity.class;

  }
  
  Object newEntry() {
            
    return new BusinessCategory();
    
  }
  
  void deleteSelectedEntry() {

    BusinessCategory businessCategory = (BusinessCategory) getSelectionEntries().get(getSelectedEntryIndex());
    
    getSelectionEntries().remove(businessCategory);
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
  boolean canSelectEntry() {

    return true;

  }

}