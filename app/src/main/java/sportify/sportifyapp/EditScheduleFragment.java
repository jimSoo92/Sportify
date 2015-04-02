package sportify.sportifyapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditScheduleFragment extends DialogFragment {
    Button confirm;
    Button cancel;
    Button delete;

    EditText editText;

    private DataSource dataSource;

    Spinner dayItemSpinner;
    String dayItemSelect;
    String weekItemSelect;
    String scheItemSelect;
    Spinner scheItemSpinner;
    Spinner weekItemSpinner;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_edit_schedule);
        dialog.setCanceledOnTouchOutside(false);

        editText = (EditText)dialog.findViewById(R.id.scheduleName);

        // Confirm button
        confirm = (Button) dialog.findViewById(R.id.confirmButton);

        //Cancel button
        cancel = (Button) dialog.findViewById(R.id.cancelButton);

        dialog.setCanceledOnTouchOutside(false);

        editText = (EditText)dialog.findViewById(R.id.name);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }

        });
        // Confirm button
        confirm = (Button) dialog.findViewById(R.id.confirmButton);

        //Cancel button
        cancel = (Button) dialog.findViewById(R.id.cancelButton);


        //Delete button
        delete = (Button) dialog.findViewById(R.id.deleteSche);

        confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dismiss();

            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dismiss();
            }
        });

        // Preset schedule
      /*Spinner scheSelect = (Spinner) dialog.findViewById(R.id.Preset_Schedule_spinner);
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

        weeksSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dismiss();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /* Toast.makeText(getActivity(), "Days: " + dayItemSelect + "," + "Workout: " + scheItemSelect + ", " + "Weeks: "
                                  + weekItemSelect, Toast.LENGTH_SHORT).show(); */

                DataSource dataSource = new DataSource(getActivity());
                dataSource.open();

                String name = ((EditText)dialog.findViewById(R.id.name)).getText().toString();
            /*  String preset = ((Spinner)getActivity().findViewById(R.id.Preset_Schedule_spinner)).toString(); */
                String num_weeks = ((Spinner)dialog.findViewById(R.id.One_spinner)).getSelectedItem().toString();

                dataSource.editWorkoutSchedule(name, "", num_weeks);

                Toast.makeText(getActivity(), name,Toast.LENGTH_SHORT).show();

                SportifyMain.name_schedule = name;
                SportifyMain.update_name();
                dismiss();

                dataSource.close();

            }
        });

        return dialog;
    }

}
