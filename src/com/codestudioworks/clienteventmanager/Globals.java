package com.codestudioworks.clienteventmanager;

import java.util.Stack;

class Globals {
  
  static UpdatableActivity neededActivity; // activity needing refresh
  
  static Data data = new Data();
  
  static com.google.api.services.calendar.Calendar calendarClient = null;
  
  static String appCalendarId = null;
  
  static int selectedEventEmployeeIndex = -1;

  static int selectedClientIndex = -1; 
 
  static int selectedBusinessAreaIndex = -1; 

  static int selectedPersonInChargeIndex = -1; 
  
  static int selectedPersonInChargeRoleIndex = -1; 
  
  static int selectedBusinessCategoryIndex = -1;
  
  static int selectedAdvisorIndex = -1; 
  
  static int selectedEmployeeIndex = -1; 

  static int selectedInitialContactChannelIndex = -1; 
  
  static int selectedPaymentTypeIndex = -1; 
  
  static int selectedBankAccountIndex = -1; 
  
  static int selectedTaxCategoryIndex = -1; 
  
  static int selectedWorkOrderLocationIndex = -1; 
  
  static int selectedClientPhoneCallIndex = -1; 
  
  static int selectedClientVisitIndex = -1; 
  
  static int selectedEventIndex = -1;

  static Stack<Action> actions = new Stack<Action>();
  
}

class Action {
    
  static final int ACTION_ENTRY_ADD = 0;
  
  static final int ACTION_ENTRY_MODIFY = 1;
    
  static final int ACTION_PROPERTY_SELECTANDSET = 2;
  
  static final int ACTION_CONTENT_DEFINE = 3;

  Action(int type, Object entry) {
      
    this.type = type;
    
    this.entry = entry;
	  
    this.parentEntry = null; //
    
  }
  
  Action(int type, Object entry, Object parentEntry) {
      
    this.type = type;
    
    this.entry = entry;
    
    this.parentEntry = parentEntry;
	  
  }
  
  Action(Object parentEntry, PropertySetterFunctor propertySetterFunction) {
    
    this.type = ACTION_PROPERTY_SELECTANDSET;
    
    this.entry = parentEntry;
    
    this.propertySetterFunctor = propertySetterFunction;
    
  }
  
  int getType() {

    return type;
    
  }
  
  Object getEntry() {

    return entry;
    
  }
  
  Object getParent() {

    return parentEntry;
    
  }
  
  PropertySetterFunctor getPropertySetterFunctor() {

    return propertySetterFunctor;
    
  }
  
  PropertySetterFunctor propertySetterFunctor;

  private int type;
  
  private Object entry;
  
  private Object parentEntry;//added by neil

}

interface PropertySetterFunctor {
  
  public void setProperty(Object parent, Object child);
  
}