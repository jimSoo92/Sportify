package sportify.sportifyapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class EditActivityFragment extends DialogFragment{

    private DataSource dataSource;

    Button confirm;
    Button cancel;
    Button delete;
    EditText activityName;
    int setNum;
    int repNum;
    String day;
    EditText editText;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_edit_activity1);
        dialog.setCanceledOnTouchOutside(false);

        activityName = (EditText) dialog.findViewById(R.id.activityName);

        activityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityName.setText("");
            }

        });

        // Confirm button
        confirm = (Button) dialog.findViewById(R.id.confirmButton);

        //Cancel button
        cancel = (Button) dialog.findViewById(R.id.cancelButton);

        //Delete button
        delete = (Button) dialog.findViewById(R.id.deleteActivity);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
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
