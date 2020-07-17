package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.TextView;

public class TaxCategoriesDoneActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.taxcategories;

  }
  
  int getMenu() {
      
    return R.menu.taxcategories_done_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedTaxCategoryIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedTaxCategoryIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.taxCategories_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.taxCategories_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.taxCategories_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.taxCategories;

  }
  
  int getItemLayout() {
      
    return R.layout.taxcategories_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    TaxCategory selectionTaxCategory = (TaxCategory) selectionEntry;

    TextView taxCategoryCodeTextView = (TextView) row.findViewById(R.id.code);
    TextView taxCategoryDescriptionTextView = (TextView) row.findViewById(R.id.description);
    
    taxCategoryCodeTextView.setText(selectionTaxCategory.code);
    taxCategoryDescriptionTextView.setText(selectionTaxCategory.description);
    
  }
  
  Class getDetailActivityClass() {
      
    return TaxCategoryActivity.class;

  }
  
  Object newEntry() {
            
    return new TaxCategory();
    
  }
  
  void deleteSelectedEntry() {

    getSelectionEntries().remove(getSelectionEntries().get(getSelectedEntryIndex()));
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
  boolean canSelectEntry() {

    return true;

  }

}