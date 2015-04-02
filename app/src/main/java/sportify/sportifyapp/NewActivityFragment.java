package sportify.sportifyapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewActivityFragment extends DialogFragment{

    private DataSource dataSource;

    Button confirm;
    Button cancel;
    EditText activityName;
    EditText editText;
    EditText editText1;
    EditText editText2;
    int setNum;
    int repNum;
    String day;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_new_activity);
        dialog.setCanceledOnTouchOutside(false);

        activityName = (EditText) dialog.findViewById(R.id.activityName);

        editText = (EditText) dialog.findViewById(R.id.scheduleName);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }

        });

        editText1 = (EditText) dialog.findViewById(R.id.setInt);
        editText2 = (EditText) dialog.findViewById(R.id.repInt);

        // Confirm button
        confirm = (Button) dialog.findViewById(R.id.confirmButton);

        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /* Toast.makeText(getActivity(), "Days: " + dayItemSelect + "," + "Workout: " + scheItemSelect + ", " + "Weeks: "
                                  + weekItemSelect, Toast.LENGTH_SHORT).show(); */
                dataSource = new DataSource(getActivity());
                dataSource.open();

                Activities act= dataSource.createActivities(editText.getText().toString(), editText1.getText().toString(),
                        editText2.getText().toString(), "n",0);
                dataSource.close();
                Toast.makeText(getActivity(), act.name, Toast.LENGTH_SHORT).show();
            }
        });

        //Cancel button
        cancel = (Button) dialog.findViewById(R.id.cancelButton);


        cancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                dismiss();
            }
        });

        return dialog;
    }
}
