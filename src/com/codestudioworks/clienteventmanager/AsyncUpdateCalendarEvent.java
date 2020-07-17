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

public class AsyncUpdateCalendarEvent extends CalendarAsyncTask {
  
  static final String APP_CALENDAR_NAME = "ClientEventManager";
  
  AsyncUpdateCalendarEvent(UpdatableActivity activity, Event event) {
    super(activity);
    this.event = event;
    System.out.println("foo event ID: " + calendarEvent.getId());
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
    //important
    this.calendarEvent.setId(event.eventGoogleId);
  }

  @Override
  protected void doInBackground() throws IOException {
    
    System.out.println(Globals.appCalendarId);
    System.out.println(event.eventGoogleId);
    System.out.println("hello");
    System.out.println(calendarEvent.getId());
    System.out.println("hello2");
    com.google.api.services.calendar.model.Event responseEvent = Globals.calendarClient.events().update(Globals.appCalendarId, calendarEvent.getId(), calendarEvent).execute();
    System.out.println(responseEvent);
    
  }
  
  
  protected void startToast() {
    
    Toast.makeText(activity, R.string.event_message_modifyingCalendarEvent, Toast.LENGTH_SHORT).show();
    
 }
  
  protected void successEndToast() {
    
    Toast.makeText(activity, R.string.event_message_modifyCalendarEventSuccess, Toast.LENGTH_SHORT).show();
    
    Globals.data = DB.db(activity).storeAndRetrieve(Globals.data);
    
   }
  
  protected void failureEndToast() {
    
    Toast.makeText(activity, R.string.event_message_modifyCalendarEventFailure, Toast.LENGTH_SHORT).show();
    
  }
  
  static void run(UpdatableActivity activity, Event event) {
    new AsyncUpdateCalendarEvent(activity, event).execute();
  }

  com.google.api.services.calendar.model.Event calendarEvent = new com.google.api.services.calendar.model.Event();
  
  Event event;
  
}