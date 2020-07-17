package com.codestudioworks.clienteventmanager;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.CalendarScopes;

public class MainActivity extends Activity {

  final HttpTransport transport = AndroidHttp.newCompatibleTransport();

  final JsonFactory jsonFactory = new GsonFactory();

  GoogleAccountCredential credential;

  public static final int REQUEST_AUTHORIZATION = 100;

  public static final String PREF_GOOGLEACCOUNT_NAME = "googleAccountName";
  public static final String APP_NAME_SLASH_VERSION = "Client Event Manager/1.0";
  public static final String EXTRA_ATTEMPT_SETUP = "extraAttemptSetup";

  private boolean attemptSetupWhenGoogleAccountChosen;

  // CHECK INTERNET METHOD

  public static final boolean isInternetOn(Activity activity) {

    // Log.d(APP_TAG, ACT_TAG + "Checking connectivity ...");

    ConnectivityManager cm = null;

    cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

    if (cm != null) {

      // Log.d(APP_TAG, ACT_TAG + "We have internet");

      NetworkInfo ni = cm.getActiveNetworkInfo();

      if (ni != null && ni.isConnected())

        return true;

    }

    // Log.d(APP_TAG, ACT_TAG + "No internet connection found");

    return false;

  }

  void init() {

    // since this is first screen initialize globals from database

    Globals.data = DB.db(this).getData();

  }

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // initialization stuff

    init();

    // set content view

    setContentView(R.layout.main);

    // retrieve widgets

    this.clientsButton = (Button) findViewById(R.id.clients);

    this.consultantsButton = (Button) findViewById(R.id.consultants);

    this.employeesButton = (Button) findViewById(R.id.employees);

    this.eventsButton = (Button) findViewById(R.id.events);

    // setup credential

    // set widget listeners

    this.clientsButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {

        startActivity(new Intent(MainActivity.this, ClientsActivity.class));

      }

    });

    this.consultantsButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {

        startActivity(new Intent(MainActivity.this, ConsultantsActivity.class));

      }

    });

    this.employeesButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {

        startActivity(new Intent(MainActivity.this, EmployeesActivity.class));

      }

    });

    this.eventsButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View arg0) {
        
        if (MainActivity.isInternetOn(MainActivity.this)) {

          credential = GoogleAccountCredential.usingOAuth2(MainActivity.this, CalendarScopes.CALENDAR);

          // credential.setSelectedAccountName("nzanella@gmail.com");

          attemptSetupCalendarClient();
          
        } else {
          
          System.out.println("Internet off.");
          Toast.makeText(MainActivity.this, R.string.main_message_internetNotAccessible, Toast.LENGTH_SHORT).show();
          
        }

      }

    });

  }

  void attemptSetupCalendarClient() {

    SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);

    String accountName = settings.getString(PREF_GOOGLEACCOUNT_NAME, null);

    if (accountName != null) {

      this.credential.setSelectedAccountName(accountName);

      Globals.calendarClient = new com.google.api.services.calendar.Calendar.Builder(transport, jsonFactory, credential).setApplicationName(APP_NAME_SLASH_VERSION).build();

      ensureEmployeeSelected();

      startActivity(new Intent(MainActivity.this, EventsActivity.class));

    } else

      chooseGoogleAccount(true);

  }

  void ensureEmployeeSelected() {

    boolean employeeEmailFound = false;

    for (int index = 0; index < Globals.data.employees.size(); index++) {

      Employee employee = Globals.data.employees.get(index);

      if (employee.email != null && employee.email.equals(this.credential.getSelectedAccountName())) {

        employeeEmailFound = true;

        Globals.selectedEventEmployeeIndex = index;

        break;

      }

    }

    if (employeeEmailFound == false) {

      // create an employee with specified email, add employee to employee list,
      // and retrieve its employee index

      Employee employee = new Employee();

      employee.email = this.credential.getSelectedAccountName();

      Globals.data.employees.add(employee);

      Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

      for (int index = 0; index < Globals.data.employees.size(); index++) {

        employee = Globals.data.employees.get(index);

        if (employee.email != null && employee.email.equals(this.credential.getSelectedAccountName())) {

          Globals.selectedEventEmployeeIndex = index;

          break;

        }

      }

    }

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    super.onActivityResult(requestCode, resultCode, data);

    switch (requestCode) {

      case REQUEST_ACCOUNT_PICKER:

        if (resultCode == Activity.RESULT_OK && data != null && data.getExtras() != null) {
          String accountName = data.getExtras().getString(AccountManager.KEY_ACCOUNT_NAME);
          if (accountName != null) {
            this.credential.setSelectedAccountName(accountName);
            SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(PREF_GOOGLEACCOUNT_NAME, accountName);
            editor.commit();
            if (this.attemptSetupWhenGoogleAccountChosen) {
              this.attemptSetupWhenGoogleAccountChosen = false;
              attemptSetupCalendarClient();
            }
          }

        }

        break;

    }

  }

  int getMenu() {

    return R.menu.main_menu;

  }

  int getChooseElatosAccountId() {

    return R.id.setElatosAccount;

  }

  int getChooseGoogleAccountId() {

    return R.id.setGoogleAccount;

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    MenuInflater inflater = getMenuInflater();

    inflater.inflate(getMenu(), menu);

    return true;

  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {

    return super.onPrepareOptionsMenu(menu);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == getChooseElatosAccountId())

      chooseElatosAccount();

    else if (item.getItemId() == getChooseGoogleAccountId()) {

      credential = GoogleAccountCredential.usingOAuth2(MainActivity.this, CalendarScopes.CALENDAR);

      chooseGoogleAccount(false);

    }

    return true;

  }

  private void chooseElatosAccount() {

    startActivity(new Intent(this, SetupElatosActivity.class));

  }

  private void chooseGoogleAccount(boolean attemptSetupWhenDone) {

    Intent intent = credential.newChooseAccountIntent();

    // value below is to be processed on returning from activity

    this.attemptSetupWhenGoogleAccountChosen = attemptSetupWhenDone;

    startActivityForResult(intent, REQUEST_ACCOUNT_PICKER);

  }

  static final int REQUEST_ACCOUNT_PICKER = 0;

  Button usersButton;

  Button clientsButton;

  Button consultantsButton;

  Button employeesButton;

  Button eventsButton;

}
