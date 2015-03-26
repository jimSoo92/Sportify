package sportify.sportifyapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditScheduleFragment extends DialogFragment {

    private DataSource dataSource;

    Button confirm;
    Button cancel;
    Button delete;
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
        dialog.setContentView(R.layout.fragment_edit_schedule);
        dialog.setCanceledOnTouchOutside(false);

        editText = (EditText)dialog.findViewById(R.id.scheduleName);

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

        return dialog;
    }

}
