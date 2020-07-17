package com.codestudioworks.clienteventmanager;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.widget.Toast;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event.ExtendedProperties;
import com.google.api.services.calendar.model.EventDateTime;

public class AsyncDeleteCalendarEvent extends CalendarAsyncTask {
  
  static final String APP_CALENDAR_NAME = "ClientEventManager";
  
  AsyncDeleteCalendarEvent(UpdatableActivity activity, Event event) {
    super(activity);
    this.event = event;
  }

  @Override
  protected void doInBackground() throws IOException {
    
    System.out.println(Globals.appCalendarId);
    
    if (event.eventGoogleId != null)
      
      Globals.calendarClient.events().delete(Globals.appCalendarId, event.eventGoogleId).execute();
    
    //System.out.println(responseEvent);//no response event on deletes
    
  }
  
  
  protected void startToast() {
    
    Toast.makeText(activity, R.string.event_message_deletingCalendarEvent, Toast.LENGTH_SHORT).show();
    
 }
  
  protected void successEndToast() {
    
    Toast.makeText(activity, R.string.event_message_deleteCalendarEventSuccess, Toast.LENGTH_SHORT).show();
      
    Globals.data.employees.get(Globals.selectedEventEmployeeIndex).events.remove(event);

    Globals.data = DB.db(activity).storeAndRetrieve(Globals.data);
    
   }
  
  protected void failureEndToast() {
    
    Toast.makeText(activity, R.string.event_message_deleteCalendarEventFailure, Toast.LENGTH_SHORT).show();
    
  }
  
  static void run(UpdatableActivity activity, Event event) {
    new AsyncDeleteCalendarEvent(activity, event).execute();
  }

  com.google.api.services.calendar.model.Event calendarEvent = new com.google.api.services.calendar.model.Event();
  
  Event event;
  
}