package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class InitialContactChannelsDoneActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.initialcontactchannels;

  }
  
  int getMenu() {
      
    return R.menu.initialcontactchannels_done_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedInitialContactChannelIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedInitialContactChannelIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.initialContactChannels_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.initialContactChannels_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.initialContactChannels_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.initialContactChannels;

  }
  
  int getItemLayout() {
      
    return R.layout.initialcontactchannels_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    InitialContactChannel selectionInitialContactChannel = (InitialContactChannel) selectionEntry;

    TextView initialContactChannelNameTextView = (TextView) row.findViewById(R.id.initialContactChannelName);
    
    initialContactChannelNameTextView.setText(selectionInitialContactChannel.name);
    
  }
  
  Class getDetailActivityClass() {
      
    return InitialContactChannelActivity.class;

  }
  
  Object newEntry() {
            
    return new InitialContactChannel();
    
  }
  
  void deleteSelectedEntry() {

    getSelectionEntries().remove(getSelectionEntries().get(getSelectedEntryIndex()));
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
  boolean canSelectEntry() {

    return true;

  }

}