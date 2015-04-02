package sportify.sportifyapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewScheduleFragment extends DialogFragment {

    private DataSource dataSource;

    Button confirm;
    Button cancel;
    Spinner dayItemSpinner;
    String dayItemSelect;
    String weekItemSelect;
    String scheItemSelect;
    Spinner scheItemSpinner;
    Spinner weekItemSpinner;

    EditText editText;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_new_schedule);
        dialog.setCanceledOnTouchOutside(false);

        editText = (EditText)dialog.findViewById(R.id.scheduleName);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }

        });
      /*  // Day of the week
        Spinner daySelect = (Spinner) dialog.findViewById(R.id.Day_of_the_week_spinner);
        ArrayAdapter<CharSequence> daySche = ArrayAdapter.createFromResource(getActivity(), R.array.day_of_the_week, android.R.layout.simple_spinner_item);
        daySche.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySelect.setAdapter(daySche);

        // Preset schedule
        Spinner scheSelect = (Spinner) dialog.findViewById(R.id.Preset_Schedule_spinner);
        ArrayAdapter<CharSequence> presetSche = ArrayAdapter.createFromResource(getActivity(), R.array.preset_sche, android.R.layout.simple_spinner_item);
        presetSche.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scheSelect.setAdapter(presetSche); */

        // Number of weeks
        Spinner weeksSelect = (Spinner) dialog.findViewById(R.id.One_spinner);
        ArrayAdapter<CharSequence> setWeeks = ArrayAdapter.createFromResource(getActivity(), R.array.number_weeks, android.R.layout.simple_spinner_item);
        setWeeks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weeksSelect.setAdapter(setWeeks);

        // Confirm button
        Button confirm = (Button) dialog.findViewById(R.id.confirmButton);

        //Cancel button
        cancel = (Button) dialog.findViewById(R.id.cancelButton);

        /*daySelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                dayItemSpinner = (Spinner) parent;
                dayItemSelect = (String) dayItemSpinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        scheSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                scheItemSpinner = (Spinner) parent;
                scheItemSelect = (String) scheItemSpinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        }); */

        weeksSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                weekItemSpinner = (Spinner) parent;
                weekItemSelect = (String) weekItemSpinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /* Toast.makeText(getActivity(), "Days: " + dayItemSelect + "," + "Workout: " + scheItemSelect + ", " + "Weeks: "
                                  + weekItemSelect, Toast.LENGTH_SHORT).show(); */
                dataSource = new DataSource(getActivity());
                dataSource.open();
                WorkoutSchedule newWork= dataSource.createWorkoutSchedule(editText.getText().toString(),"", "",Integer.parseInt(weekItemSelect) );
                dataSource.close();
                Toast.makeText(getActivity(), newWork.getName_schedule(),Toast.LENGTH_SHORT).show();

                SportifyMain.name_schedule = editText.getText().toString();
                SportifyMain.update_name();
                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dismiss();
            }
        });

        return dialog;
    }
}
