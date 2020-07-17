package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EventsActivity extends EntriesActivity {

  public static final String CLIENT_ID = "850381918262.apps.googleusercontent.com";

  public static final String API_KEY = "AIzaSyC7t5n2akn2HmDrcbbnkiMPGBhG9Hhuyqc";
  
  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    
    Toast.makeText(this, R.string.event_message_ensuringCalendarExists, Toast.LENGTH_SHORT).show();
    
    // ensure calendar exists
    
    AsyncEnsureExistsAndLoadCalendar.run(this);
    
    Globals.neededActivity = this; // update this activity from detail activity
    
  }

  int getLayout() {

    return R.layout.events;

  }

  int getMenu() {

    return R.menu.events_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedEventIndex;

  }

  void setSelectedEntryIndex(int index) {

    Globals.selectedEventIndex = index;

  }

  int getReallyDeleteString() {

    return R.string.events_reallyDelete;

  }

  int getYesDeleteString() {

    return R.string.events_yesDelete;

  }

  int getNoDeleteString() {

    return R.string.events_noDelete;

  }

  List getSelectionEntries() {

    return Globals.data.employees.get(Globals.selectedEventEmployeeIndex).events;

  }

  int getItemLayout() {

    return R.layout.events_entry;

  }

  void populateRow(Object selectionEntry, View row) {

    Event selectionEvent = (Event) selectionEntry;
    TextView eventDateTextView = (TextView) row.findViewById(R.id.eventDate);
    TextView eventStartTimeView = (TextView) row.findViewById(R.id.eventStartTime);
    TextView eventEndTimeView = (TextView) row.findViewById(R.id.eventEndTime);

    eventDateTextView.setText(selectionEvent.eventDate);
    eventStartTimeView.setText(selectionEvent.startTime);
    eventEndTimeView.setText(selectionEvent.endTime);

  }

  Class getDetailActivityClass() {

    return EventActivity.class;

  }

  Object newEntry() {

    return new Event();

  }

  void deleteSelectedEntry() {
    
    Event event = (Event) getSelectionEntries().get(getSelectedEntryIndex());
    
    AsyncDeleteCalendarEvent.run(this, event);
    
  }

}