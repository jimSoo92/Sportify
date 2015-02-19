package sportify.sportifyapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class EditScheduleFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_edit_schedule);

        // Number of weeks
        Spinner weeksSelect = (Spinner) dialog.findViewById(R.id.One_spinner);
        ArrayAdapter<CharSequence> setWeeks = ArrayAdapter.createFromResource(getActivity(), R.array.number_weeks, android.R.layout.simple_spinner_item);
        setWeeks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weeksSelect.setAdapter(setWeeks);

        weeksSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        return dialog;
    }
}
