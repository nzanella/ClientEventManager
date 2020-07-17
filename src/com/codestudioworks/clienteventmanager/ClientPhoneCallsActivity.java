package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ClientPhoneCallsActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.clientphonecalls;

  }
  
  int getMenu() {
      
    return R.menu.clientphonecalls_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedClientPhoneCallIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedClientPhoneCallIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.clientPhoneCalls_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.clientPhoneCalls_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.clientPhoneCalls_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return ((Client) Globals.actions.peek().getEntry()).phoneCalls;

  }
  
  int getItemLayout() {
      
    return R.layout.clientphonecalls_entry;

  }
  
  void populateRow(Object selectionEntry, View row) {
      
    ClientPhoneCall selectionClientPhoneCall = (ClientPhoneCall) selectionEntry;

    TextView callerNameTextView = (TextView) row.findViewById(R.id.callerName);
    TextView callDateTextView = (TextView) row.findViewById(R.id.callDate);
    TextView calleeNameTextView = (TextView) row.findViewById(R.id.calleeName);
    
    callerNameTextView.setText(selectionClientPhoneCall.callerName);
    callDateTextView.setText(selectionClientPhoneCall.callDate);
    calleeNameTextView.setText(selectionClientPhoneCall.calleeName);
    
  }
  
  Class getDetailActivityClass() {
      
    return ClientPhoneCallActivity.class;

  }
  
  Object newEntry() {
            
    return new ClientPhoneCall();
    
  }
  
  void deleteSelectedEntry() {

    getSelectionEntries().remove(getSelectionEntries().get(getSelectedEntryIndex()));
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
}