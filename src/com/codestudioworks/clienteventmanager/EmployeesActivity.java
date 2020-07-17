package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class EmployeesActivity extends EntriesActivity {

  int getLayout() {

    return R.layout.employees;

  }
  
  int getMenu() {
      
    return R.menu.employees_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedEmployeeIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedEmployeeIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.employees_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.employees_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.employees_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.employees;

  }
  
  int getItemLayout() {
      
    return R.layout.employees_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    Employee selectionEmployee = (Employee) selectionEntry;

    TextView employeeNameTextView = (TextView) row.findViewById(R.id.name);
    
    employeeNameTextView.setText(selectionEmployee.name);
    
  }
  
  Class getDetailActivityClass() {
      
    return EmployeeActivity.class;

  }
  
  Object newEntry() {
            
    return new Employee();
    
  }
  
  void deleteSelectedEntry() {

    Employee employee = (Employee) getSelectionEntries().get(getSelectedEntryIndex());
    
    Globals.data.employees.remove(employee);
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }

}