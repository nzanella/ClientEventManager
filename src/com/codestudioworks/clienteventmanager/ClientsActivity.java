package com.codestudioworks.clienteventmanager;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ClientsActivity extends EntriesActivity {
  
  int getLayout() {

    return R.layout.clients;

  }
  
  int getMenu() {
      
    return R.menu.clients_menu;

  }

  int getSelectedEntryIndex() {

    return Globals.selectedClientIndex;

  }
  
  void setSelectedEntryIndex(int index) {
      
    Globals.selectedClientIndex = index;

  }
  
  int getReallyDeleteString() {
                    
    return R.string.clients_reallyDelete;
    
  }
  
  int getYesDeleteString() {
  
    return R.string.clients_yesDelete;
    
  }
  
  int getNoDeleteString() {
  
    return R.string.clients_noDelete;
    
  }
  
  List getSelectionEntries() {
      
    return Globals.data.clients;

  }
  
  int getItemLayout() {
      
    return R.layout.clients_entry;
      
  }
  
  void populateRow(Object selectionEntry, View row) {
      
    Client selectionClient = (Client) selectionEntry;

    TextView clientCodeTextView = (TextView) row.findViewById(R.id.code);
    
    clientCodeTextView.setText(selectionClient.code);
    
    TextView clientCompanyNameTextView = (TextView) row.findViewById(R.id.companyName);
    
    clientCompanyNameTextView.setText(selectionClient.companyName);
    
    TextView clientAdminHeadquartersAddressTextView = (TextView) row.findViewById(R.id.adminHeadquartersAddress);
    
    clientAdminHeadquartersAddressTextView.setText(selectionClient.adminAddress);
    
    TextView clientAdminHeadquartersCityTextView = (TextView) row.findViewById(R.id.adminHeadquartersCity);
    
    clientAdminHeadquartersCityTextView.setText(selectionClient.adminCity);
    
    TextView clientAdminHeadquartersCountryTextView = (TextView) row.findViewById(R.id.adminHeadquartersCountry);
    
    clientAdminHeadquartersCountryTextView.setText(selectionClient.adminCountry);
    
  }
  
  Class getDetailActivityClass() {
      
    return ClientActivity.class;

  }
  
  Object newEntry() {
            
    return new Client();
    
  }
  
  void deleteSelectedEntry() {

    Client client = (Client) getSelectionEntries().get(getSelectedEntryIndex());
    
    Globals.data.clients.remove(client);
    
    Globals.data = DB.db(this).storeAndRetrieve(Globals.data);

  }
  
  @Override
  void synchronizeEntries() {
    new AsyncTask<Void, Integer, Void>() {
      @Override
      protected Void doInBackground(Void...voids) {
        
        ElatosConnection.setDatabase(DB.db(ClientsActivity.this));
        
        //final String businessName = "ASA0710";
        //final String userName = "GIRARDIS";
        //final String password = "GIRARDIS";

        // retrieve authentication parameters from settings

        SharedPreferences settings = ClientsActivity.this.getSharedPreferences(Data.PREF_ELATOSACCOUNT, Context.MODE_WORLD_READABLE);
        
        String businessName = settings.getString(Data.PREF_ELATOSACCOUNT_BUSINESSNAME, null);
        String userName = settings.getString(Data.PREF_ELATOSACCOUNT_USERNAME, null);
        String password = settings.getString(Data.PREF_ELATOSACCOUNT_PASSWORD, null);
        
        System.out.println(businessName + " " + userName + " " + password);
        
        publishProgress(R.string.getClients_connecting);
        
        if (ElatosConnection.connect(businessName, userName, password)) {

          publishProgress(R.string.getClients_retrieving);

          ElatosConnection.doRetrieveClientsXML();

          publishProgress(R.string.getClients_retrieved);

        } else {

          publishProgress(R.string.getClients_authorizationFailed);

        }

        return null;

      }

      @Override
      protected void onProgressUpdate(Integer... values) {
        
        Toast.makeText(ClientsActivity.this, values[0], Toast.LENGTH_SHORT).show(); 
        
      }
      @Override
      protected void onPostExecute(Void void0) {
        
        Globals.data = DB.db(ClientsActivity.this).getData();
	
        ClientsActivity.this.updateDisplay();
        
      }
      
    }.execute();
    
  }

}
