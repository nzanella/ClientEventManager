package com.codestudioworks.clienteventmanager;

import java.io.IOException;

import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;

public class AsyncEnsureExistsAndLoadCalendar extends CalendarAsyncTask {
  
  static final String APP_CALENDAR_NAME = "ClientEventManager";
  
  AsyncEnsureExistsAndLoadCalendar(UpdatableActivity activity) {
    super(activity);
  }

  @Override
  protected void doInBackground() throws IOException {
    
    // retrieve list of user calendars
    
    CalendarList calendarList = Globals.calendarClient.calendarList().list().execute();
    
    // ensure calendar list includes client event manager specific calendar
    
    Globals.appCalendarId = null;
    
    do {
    
      for (CalendarListEntry calendarListEntry : calendarList.getItems())
    
         if (calendarListEntry.getSummary().equals(APP_CALENDAR_NAME)) {
         
           Globals.appCalendarId = calendarListEntry.getId();
    
           break;
         
         }
    
      if (Globals.appCalendarId == null) {
      
        Calendar calendar = new Calendar();
        calendar.setSummary(APP_CALENDAR_NAME);
        Globals.calendarClient.calendars().insert(calendar).setFields("summary").execute();

      }
      
    } while (Globals.appCalendarId == null);
    
    // load calendar events into user interface
    
    //... (well, better do this from a synchronize menu item)
    
    // keep using this calendar to perform rest of calendar actions
    
    System.out.println("Calendar ID: " + Globals.appCalendarId);
    
  }

  protected void startToast() {
    
  }
  
  protected void successEndToast() {
    
  }
  
  protected void failureEndToast() {
    
  }

  static void run(UpdatableActivity activity) {
    new AsyncEnsureExistsAndLoadCalendar(activity).execute();
  }

}