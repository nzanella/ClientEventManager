package com.codestudioworks.clienteventmanager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ClientBusinessAreasActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    // set content view

    setContentView(R.layout.clientbusinessareas);

    // retrieve action parameter passed as global variable

    this.concernedBusinessAreas = (List<ConcernedBusinessArea>) Globals.actions.peek().getEntry();

    // retrieve entry fields and set them to their respective values

    this.layout = (LinearLayout) findViewById(R.id.layout);
    this.defineBusinessAreasButton = (Button) findViewById(R.id.defineBusinessAreas);

    // define listeners

    this.defineBusinessAreasButton.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {

        // no action to push onto actions stack

        defineContent();

      }

    });

    // update display

    updateDisplay();

    // set successful result code in case needed by calling activity

    setResult(RESULT_OK);

  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    switch (requestCode) {

      case DEFINE_CONTENT_REQUEST:

        if (resultCode == RESULT_OK) {

          // refresh data

          updateDisplay();

        }

        break;
    }

    // no action to pop from actions stack

  }

  Class getDefinitionActivityClass() {

    return BusinessAreasActivity.class;

  }
  
  class ConcernRadioButtonOnClickListener implements OnClickListener {
    
    ConcernRadioButtonOnClickListener(int listIndex, int concernType) {
      
      this.listIndex = listIndex;
      
      this.concernType = concernType;
      
    }

    @Override
    public void onClick(View v) {

      ClientBusinessAreasActivity.this.concernTypesList.set(this.listIndex, this.concernType);

    }
    
    private int listIndex;
    private int concernType;

  }

  void updateDisplay() {

    this.layout.removeAllViews();

    this.codesList.clear();
    this.concernTypesList.clear();

    for (int i = 0; i < Globals.data.businessAreas.size(); i++) {
      
      BusinessArea businessArea = Globals.data.businessAreas.get(i);

      View row = getLayoutInflater().inflate(R.layout.clientbusinessareas_entry, null);

      TextView codeTextView = (TextView) row.findViewById(R.id.code);
      TextView descriptionTextView = (TextView) row.findViewById(R.id.description);
      RadioButton businessAreaPrimaryRadioButton = (RadioButton) row.findViewById(R.id.businessAreaPrimary);
      RadioButton businessAreaSecondaryRadioButton = (RadioButton) row.findViewById(R.id.businessAreaSecondary);
      RadioButton businessAreaNoneRadioButton = (RadioButton) row.findViewById(R.id.businessAreaNone);

      codeTextView.setText(businessArea.code);
      descriptionTextView.setText(businessArea.description);
      
      businessAreaPrimaryRadioButton.setOnClickListener(new ConcernRadioButtonOnClickListener(i, ConcernedBusinessArea.CONCERN_PRIMARY));
      businessAreaSecondaryRadioButton.setOnClickListener(new ConcernRadioButtonOnClickListener(i, ConcernedBusinessArea.CONCERN_SECONDARY));
      businessAreaNoneRadioButton.setOnClickListener(new ConcernRadioButtonOnClickListener(i, ConcernedBusinessArea.CONCERN_NONE));
        
      for (ConcernedBusinessArea concernedBusinessArea : this.concernedBusinessAreas)

        if (concernedBusinessArea.businessArea.code.equals(codeTextView.getText()))

          switch (concernedBusinessArea.isPrimaryOrSecondaryConcern) {

            case ConcernedBusinessArea.CONCERN_PRIMARY:

              businessAreaPrimaryRadioButton.setChecked(true);

              break;

            case ConcernedBusinessArea.CONCERN_SECONDARY:

              businessAreaSecondaryRadioButton.setChecked(true);

              break;

            default:

              businessAreaNoneRadioButton.setChecked(true);

              break;
          }

      this.layout.addView(row);

      this.codesList.add(codeTextView.getText().toString());
      this.concernTypesList.add(businessAreaPrimaryRadioButton.isChecked() ? ConcernedBusinessArea.CONCERN_PRIMARY
        : businessAreaSecondaryRadioButton.isChecked() ? ConcernedBusinessArea.CONCERN_SECONDARY : ConcernedBusinessArea.CONCERN_NONE);

    }
    
    this.layout.invalidate();

  }

  private void defineContent() {

    startActivityForResult(new Intent(this, getDefinitionActivityClass()), DEFINE_CONTENT_REQUEST);

  }

  private int getMenu() {

    return R.menu.clientbusinessareas_editable_menu;

  }

  private int getDoneMenuItem() {

    return R.id.done;

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    MenuInflater inflater = getMenuInflater();

    inflater.inflate(getMenu(), menu);

    return true;

  }

  @Override
  public boolean onPrepareOptionsMenu(Menu menu) {

    MenuItem saveItem = menu.findItem(getDoneMenuItem());

    saveItem.setEnabled(true);

    return super.onPrepareOptionsMenu(menu);

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if (item.getItemId() == getDoneMenuItem()) {

      // clear client concerned business areas

      this.concernedBusinessAreas.clear();

      // iterate over all user interface elements

      for (int i = 0; i < this.codesList.size(); i++) {

        // check whether user interface element has a concerned business area different from none

        if (this.concernTypesList.get(i).intValue() != ConcernedBusinessArea.CONCERN_NONE) {

          // instantiate a new concerned business area to keep track of for this client

          ConcernedBusinessArea concernedBusinessArea = new ConcernedBusinessArea();

          // iterate over list of business areas searching for the one with corresponding code

          for (BusinessArea businessArea : Globals.data.businessAreas)

            if (businessArea.code.equals(this.codesList.get(i))) {

              concernedBusinessArea.businessArea = businessArea;

              break;

            }

          // set the concern type for this concerned business area

          concernedBusinessArea.isPrimaryOrSecondaryConcern = this.concernTypesList.get(i).intValue();

          // add concerned business area to client list of concerned business areas

          this.concernedBusinessAreas.add(concernedBusinessArea);
          
        }

      }
      
      // pop action performed from actions stack
      
      Globals.actions.pop();
          
      finish();

    }

    return true;

  }

  // structures used locally

  List<String> codesList = new ArrayList<String>();
  List<Integer> concernTypesList = new ArrayList<Integer>();

  // request type for child activity

  private static final int DEFINE_CONTENT_REQUEST = 0;

  // entry to be manipulated

  Action action;

  List<ConcernedBusinessArea> concernedBusinessAreas;

  LinearLayout layout;
  Button defineBusinessAreasButton;

  public static int SET_BUSINESSAREAS_REQUEST = 0; // remember to update display upon returning from this

}
