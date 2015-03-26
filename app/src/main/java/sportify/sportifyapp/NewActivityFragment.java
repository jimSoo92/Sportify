package sportify.sportifyapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Derinsleif on 3/25/2015.
 */
public class NewActivityFragment extends DialogFragment{

    private DataSource dataSource;

    Button confirm;
    Button cancel;
    EditText activityName;
    int setNum;
    int repNum;
    String day;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_new_activity);
        dialog.setCanceledOnTouchOutside(false);

        activityName = (EditText) dialog.findViewById(R.id.activityName);

        // Confirm button
        confirm = (Button) dialog.findViewById(R.id.confirmButton);

        //Cancel button
        cancel = (Button) dialog.findViewById(R.id.cancelButton);


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

        return dialog;
    }
}
