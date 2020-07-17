package com.codestudioworks.clienteventmanager;

import java.io.IOException;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;

import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;

interface UpdatableInterface {
  
  void updateDisplay();
  
}

abstract class UpdatableActivity extends Activity implements UpdatableInterface {
  
}

public abstract class CalendarAsyncTask extends AsyncTask<Void, Void, Boolean> {

  final UpdatableActivity activity; // activity from which to prompt user for credentials
  
  CalendarAsyncTask(UpdatableActivity activity) {
      
    this.activity = activity;
        
  }

  @Override
  protected final Boolean doInBackground(Void... ignored) {
    try {
      doInBackground();
      return true;
    } catch (final GooglePlayServicesAvailabilityIOException availabilityException) {
      System.out.println(availabilityException.getConnectionStatusCode());
      availabilityException.printStackTrace();
    } catch (UserRecoverableAuthIOException userRecoverableException) {
      this.activity.startActivityForResult(userRecoverableException.getIntent(), MainActivity.REQUEST_AUTHORIZATION);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }
  
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    startToast();
  }

  @Override
  protected final void onPostExecute(Boolean success) {
    if (success && activity != null) {
      successEndToast();
      activity.updateDisplay();
    }
    else {
      failureEndToast();
    }
  }

  abstract protected void doInBackground() throws IOException;
  
  abstract protected void startToast();
  abstract protected void successEndToast();
  abstract protected void failureEndToast();
      
}
