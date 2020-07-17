package com.codestudioworks.clienteventmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EventActivity extends UpdatableActivity {
  
  int getMenu() {

    return R.menu.event_editable_menu;

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(R.layout.event);
    
    // retrieve action parameter passed as global variable
    
    this.action = Globals.actions.peek().getType();
    
    this.event = (Event) Globals.actions.peek().getEntry();
    
    // retrieve and set screen title according to action
    
    ((TextView) findViewById(R.id.title)).setText(this.action == Action.ACTION_ENTRY_ADD ? R.string.event_title_add : R.string.event_title_modify);
    
    // retrieve entry fields

    this.eventDateEditText = (EditText) findViewById(R.id.eventDate);
    this.eventStartTimeEditText = (EditText) findViewById(R.id.eventStartTime);
    this.eventEndTimeEditText = (EditText) findViewById(R.id.eventEndTime);
    this.eventDescriptionEditText = (EditText) findViewById(R.id.eventDescription);
    this.workOrderNameEditText = (EditText) findViewById(R.id.workOrderName);
    this.consultantNameEditText = (EditText) findViewById(R.id.consultantName);
    this.clientNameEditText = (EditText) findViewById(R.id.clientName);
    this.notesEditText = (EditText) findViewById(R.id.notes);
    
    // retrieve entry set and clear buttons
    
    this.pickEventDateButton = (Button) findViewById(R.id.pickEventDate);
    this.pickEventStartTimeButton = (Button) findViewById(R.id.pickEventStartTime);
    this.pickEventEndTimeButton = (Button) findViewById(R.id.pickEventEndTime);
    this.setConsultantButton = (Button) findViewById(R.id.setEventConsultant);
    this.clearConsultantButton = (Button) findViewById(R.id.clearEventConsultant);
    this.setClientButton = (Button) findViewById(R.id.setEventClient);
    this.clearClientButton = (Button) findViewById(R.id.clearEventClient);
 
    // update display

    updateDisplay();

    // setup button click listeners

    this.pickEventDateButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        
        DatePickerDialog.OnDateSetListener eventDateListener = new DatePickerDialog.OnDateSetListener() {
          
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            EventActivity.this.event.eventDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            EventActivity.this.eventDateEditText.setText(EventActivity.this.event.eventDate);
          }

        };
        
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(EventActivity.this, eventDateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show();
        
      }
      
    });
    
    this.pickEventStartTimeButton.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        
        TimePickerDialog.OnTimeSetListener eventStartTimeListener = new TimePickerDialog.OnTimeSetListener() {
          
          @Override
          public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);
            EventActivity.this.event.startTime = new SimpleDateFormat("HH:mm").format(cal.getTime());
            EventActivity.this.eventStartTimeEditText.setText(EventActivity.this.event.startTime);

          }
              
        };
        
        Calendar cal = Calendar.getInstance();
        new TimePickerDialog(EventActivity.this, eventStartTimeListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show();
        
      }
      
    });
    
    this.pickEventEndTimeButton.setOnClickListener(new OnClickListener() {
    
      @Override
      public void onClick(View v) {
        
        TimePickerDialog.OnTimeSetListener eventEndTimeListener = new TimePickerDialog.OnTimeSetListener() {
          
          @Override
          public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            cal.set(Calendar.MINUTE, minute);
            EventActivity.this.event.endTime = new SimpleDateFormat("HH:mm").format(cal.getTime());
            EventActivity.this.eventEndTimeEditText.setText(EventActivity.this.event.endTime);

          }
              
        };
        
        Calendar cal = Calendar.getInstance();
        new TimePickerDialog(EventActivity.this, eventEndTimeListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show();
        
      }
      
    });

    this.setConsultantButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        
        Globals.actions.push(new Action(Action.ACTION_CONTENT_DEFINE, EventActivity.this.event.consultant));

        startActivityForResult(new Intent(EventActivity.this, ConsultantsDoneActivity.class), SELECT_EVENTCONSULTANT_REQUEST);
        
      }
      
    });
    
    this.clearConsultantButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        
        EventActivity.this.event.consultant = null;
        
      }
      
    });
    
    this.setClientButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        
        storeDisplay();

        Globals.actions.push(new Action(Action.ACTION_CONTENT_DEFINE, EventActivity.this.event.client));

        startActivityForResult(new Intent(EventActivity.this, ClientsDoneActivity.class), SELECT_EVENTCLIENT_REQUEST);
        
      }
      
    });
    
    this.clearClientButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        
        EventActivity.this.event.client = null;
        
      }
      
    });
     
    // assume everything is going to work out

    setResult(RESULT_OK);

  }
  
  @Override
  public void updateDisplay() {

    // initialize simple entry fields
    
    this.eventDateEditText.setText(this.event.eventDate);
    this.eventStartTimeEditText.setText(this.event.startTime);
    this.eventEndTimeEditText.setText(this.event.endTime);
    this.eventDescriptionEditText.setText(this.event.description);
    this.workOrderNameEditText.setText(this.event.workOrderName);
    this.consultantNameEditText.setText(this.event.consultant != null ? this.event.consultant.name : null);
    this.clientNameEditText.setText(this.event.client != null ? this.event.client.companyName : null);
    this.notesEditText.setText(this.event.notes);
 
  }
  
  void storeDisplay() {
    
    this.event.eventDate = this.eventDateEditText.getText().toString();
    this.event.startTime = this.eventStartTimeEditText.getText().toString();
    this.event.endTime = this.eventEndTimeEditText.getText().toString();
    this.event.description = this.eventDescriptionEditText.getText().toString();
    this.event.workOrderName = this.workOrderNameEditText.getText().toString();
    /* this.event.consultant already stored */
    /* this.event.client already stored */
    this.event.notes = this.notesEditText.getText().toString();
     
  }
  
  boolean ensureRequiredFieldsSetAndFieldsValid() {
    
    if (this.event.eventDate == null) {
      
      Toast.makeText(this, R.string.event_message_missingEventDate, Toast.LENGTH_SHORT).show(); 
      
      return false;
    }
      
    if (this.event.startTime == null) {
      
      Toast.makeText(this, R.string.event_message_missingEventStartTime, Toast.LENGTH_SHORT).show(); 
      
      return false;
    }
      
    if (this.event.endTime == null) {
      
      Toast.makeText(this, R.string.event_message_missingEventEndTime, Toast.LENGTH_SHORT).show(); 
      
      return false;
      
    }
    
    if (this.event.endTime.compareTo(this.event.startTime) == -1) {
      
      Toast.makeText(this, R.string.event_message_endTimeMustNotBeLessThanStartTime, Toast.LENGTH_SHORT).show(); 
      
      return false;
      
    }
      
    return true;
      
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      
    switch (requestCode) {
    
      default:
        
        if (resultCode == RESULT_OK) {
            
          // refresh data
      
          updateDisplay();

        }

        break;
    
    }
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    MenuInflater inflater = getMenuInflater();

    inflater.inflate(getMenu(), menu);

    return true;

  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {

    MenuItem saveItem = menu.findItem(R.id.save);

    saveItem.setEnabled(true);

    return super.onPrepareOptionsMenu(menu);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()) {

      case R.id.save:
        
        storeDisplay();
      
        if (ensureRequiredFieldsSetAndFieldsValid()) {
        
          switch (this.action) {

            case Action.ACTION_ENTRY_ADD:
              
              AsyncInsertCalendarEvent.run(Globals.neededActivity, event);

              break;
            
            case Action.ACTION_ENTRY_MODIFY:
              
              AsyncUpdateCalendarEvent.run(Globals.neededActivity, event);

              break;
        
          }
        
          finish();
        
          break;
          
        }

    }

    return true;

  }
  
  // action to be performed
  
  int action;
  
  // request codes associated with started activities

  public static final int SELECT_CONCERNEDBUSINESSAREAS_REQUEST = 0;
  public static final int SELECT_OTHERPEOPLEINCHARGE_REQUEST = 1;
  
  public static final int SELECT_WORKORDERLOCATIONS_REQUEST = 2;
  public static final int DEFINE_PHONECALLS_REQUEST = 3;
  public static final int DEFINE_VISITS_REQUEST = 4;
  public static final int DEFINE_ATTACHMENTS_REQUEST = 5;
  
  public static final int SELECT_BUSINESSCONSULTANT_REQUEST = 6;
  
  // entry to be manipulated
    
  Event event;

  Calendar cal = Calendar.getInstance();
        
  EditText eventDateEditText;
  EditText eventStartTimeEditText;
  EditText eventEndTimeEditText;
  EditText eventDescriptionEditText;
  EditText workOrderNameEditText;
  EditText consultantNameEditText;
  EditText clientNameEditText;
  EditText notesEditText;
  
  Button pickEventDateButton;
  Button pickEventStartTimeButton;
  Button pickEventEndTimeButton;
  Button setConsultantButton;
  Button clearConsultantButton;
  Button setClientButton;
  Button clearClientButton;
   
  public static final int SELECT_EVENTCONSULTANT_REQUEST = 20;
  public static final int SELECT_EVENTCLIENT_REQUEST = 21;
  
}
