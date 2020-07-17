package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class PaymentTypesDoneActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.paymenttypes;

  }
  
  int getMenu() {
      
    return R.menu.paymenttypes_done_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedPaymentTypeIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedPaymentTypeIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.paymentTypes_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.paymentTypes_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.paymentTypes_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.paymentTypes;

  }
  
  int getItemLayout() {
      
    return R.layout.paymenttypes_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    PaymentType selectionPaymentType = (PaymentType) selectionEntry;

    TextView paymentTypeCodeTextView = (TextView) row.findViewById(R.id.code);
    
    paymentTypeCodeTextView.setText(selectionPaymentType.code);
  
    
  }
  
  Class getDetailActivityClass() {
      
    return PaymentTypeActivity.class;

  }
  
  Object newEntry() {
            
    return new PaymentType();
    
  }
  
  void deleteSelectedEntry() {

    getSelectionEntries().remove(getSelectionEntries().get(getSelectedEntryIndex()));
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
  boolean canSelectEntry() {

    return true;

  }

}