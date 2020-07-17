package com.codestudioworks.clienteventmanager;

public class EmployeesDoneActivity extends EmployeesActivity {
  
  int getMenu() {
    
    return R.menu.employees_done_menu;
    
  }

  boolean canSelectEntry() {

    return true;

  }

}