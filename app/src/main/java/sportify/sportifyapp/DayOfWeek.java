package sportify.sportifyapp;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DayOfWeek extends ActionBarActivity {

    private ImageView backButton;
    private ImageButton editAct;
    private ImageButton addAct;
    private TextView day; //Changes based on the day of the week. Refs database.
    SQLiteHelper db = new SQLiteHelper(this);
    List<Activities> list;
    int selectedItem;

    ListView listView;

    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_activities);

        mDecorView = getWindow().getDecorView();

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(), SportifyMain.class);
                startActivity(i);
            }
        });

        day = (TextView) findViewById(R.id.day);

       /* listView = (ListView)findViewById(R.id.list);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        list = db.getAllActivities();
        listView.setItemsCanFocus(true);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        final ArrayList<String> array = new ArrayList<String>();

        for(int i=0; i<list.size(); ++i) {
            array.add(i, list.get(i).name + " " + list.get(i).set + " " + list.get(i).rep + " ");
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.select_dialog_singlechoice, array);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
	        final String item = (String) parent.getItemAtPosition(position);
                selectedItem = position;
            }

        }); */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addActivity(View view){
        NewActivityFragment naf = new NewActivityFragment();
        naf.show(getFragmentManager(),"");
    }

    //Dialog box to delete all the activities (pop up bottom)
    public void editActivity(View view){
        //EditActivityFragment eaf = new EditActivityFragment();
        //eaf.show(getFragmentManager(), "");

        LayoutInflater inflater = this.getLayoutInflater();
        new AlertDialog.Builder(this)
                .setTitle("Delete Activities")
                 .setInverseBackgroundForced(true)
	            .setMessage("Do you want to delete all the Activities?")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

   /* private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    } */

}
