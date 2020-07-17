package com.codestudioworks.clienteventmanager;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import android.widget.Toast;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event.ExtendedProperties;
import com.google.api.services.calendar.model.EventDateTime;

public class AsyncInsertCalendarEvent extends CalendarAsyncTask {
  
  AsyncInsertCalendarEvent(UpdatableActivity activity, Event event) {
    super(activity);
    this.event = event;
    this.calendarEvent.setSummary(event.description);
    this.calendarEvent.setDescription(event.notes);
    Calendar startCalendar = Calendar.getInstance();
    Calendar endCalendar = Calendar.getInstance();
    startCalendar.set(event.getYear(), event.getMonth(), event.getDay(), event.getStartHour(), event.getStartMinute());
    endCalendar.set(event.getYear(), event.getMonth(), event.getDay(), event.getEndHour(), event.getEndMinute());
    EventDateTime startDateTime = new EventDateTime();
    EventDateTime endDateTime = new EventDateTime();
    startDateTime.setDateTime(new DateTime(startCalendar.getTime()));
    endDateTime.setDateTime(new DateTime(endCalendar.getTime()));
    this.calendarEvent.setStart(startDateTime);
    this.calendarEvent.setEnd(endDateTime);
    this.calendarEvent.setPrivateCopy(false);
    Map<String, String> extendedPropertiesMap = new HashMap<String, String>();
    extendedPropertiesMap.put("eventContactPerson", event.contactPerson);
    extendedPropertiesMap.put("eventWorkOrderName", event.workOrderName);
    extendedPropertiesMap.put("eventConsultantName", event.consultant != null ? event.consultant.name : null);
    extendedPropertiesMap.put("eventClientName", event.client != null ? event.client.companyName : null);
    this.calendarEvent.setExtendedProperties(new ExtendedProperties().setShared(extendedPropertiesMap));

  }

  @Override
  protected void doInBackground() throws IOException {
    
    System.out.println(Globals.appCalendarId);
    com.google.api.services.calendar.model.Event responseEvent = Globals.calendarClient.events().insert(Globals.appCalendarId, calendarEvent).execute();
    System.out.println(responseEvent);
    event.eventGoogleId = responseEvent.getId();
    System.out.println("Retrieved and set Event Google ID: " + event.eventGoogleId);
    
  }
  
  protected void startToast() {
    
    Toast.makeText(activity, R.string.event_message_insertingCalendarEvent, Toast.LENGTH_SHORT).show();
    
 }
  
  protected void successEndToast() {
    
    Toast.makeText(activity, R.string.event_message_insertCalendarEventSuccess, Toast.LENGTH_SHORT).show();
    
    Globals.data.employees.get(Globals.selectedEventEmployeeIndex).events.add(event);
  
    Globals.data = DB.db(activity).storeAndRetrieve(Globals.data);
    
   }
  
  protected void failureEndToast() {
    
    Toast.makeText(activity, R.string.event_message_insertCalendarEventFailure, Toast.LENGTH_SHORT).show();
    
  }

  static void run(UpdatableActivity activity, Event event) {
    new AsyncInsertCalendarEvent(activity, event).execute();
  }
  
  com.google.api.services.calendar.model.Event calendarEvent = new com.google.api.services.calendar.model.Event();
  
  Event event;

}