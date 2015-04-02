package sportify.sportifyapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.util.List;


public class SportifyMain extends ActionBarActivity {

    private Button monday;
    private Button tuesday;
    private Button wednesday;
    private Button thursday;
    private Button friday;
    private Button saturday;
    private Button sunday;
    private ImageView backButton;
    private ImageButton editSche;
    private ImageButton newSche;

    static String name_schedule;
    static TextView textView;
    static int id;

    private View mDecorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportify_main);

        String filename = "SportifySchedule";
        String string = "Hello world!";
        FileOutputStream outputStream;

        mDecorView = getWindow().getDecorView();
        textView = (TextView)findViewById(R.id.scheID);

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() , MainPage.class);
                startActivity(i);
            }
        });

        saturday = (Button) findViewById(R.id.saturday);
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent dayI = new Intent(getApplicationContext(), DayOfWeek.class);
                startActivity(dayI);
            }
        });

        sunday = (Button) findViewById(R.id.sunday);
        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent dayI = new Intent(getApplicationContext(), DayOfWeek.class);
                startActivity(dayI);
            }
        });

        monday = (Button) findViewById(R.id.monday);
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent dayI = new Intent(getApplicationContext(), DayOfWeek.class);
                startActivity(dayI);
            }
        });

        tuesday = (Button) findViewById(R.id.tuesday);
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent dayI = new Intent(getApplicationContext(), DayOfWeek.class);
                startActivity(dayI);
            }
        });

        wednesday = (Button) findViewById(R.id.wednesday);
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent dayI = new Intent(getApplicationContext(), DayOfWeek.class);
                startActivity(dayI);
            }
        });

        thursday = (Button) findViewById(R.id.thursday);
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent dayI = new Intent(getApplicationContext(), DayOfWeek.class);
                startActivity(dayI);
            }
        });

        friday = (Button) findViewById(R.id.friday);
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent dayI = new Intent(getApplicationContext(), DayOfWeek.class);
                startActivity(dayI);
            }
        });

        DataSource dataSource = new DataSource(this);
        dataSource.open();
        List<WorkoutSchedule> myList =  dataSource.getAllWorkoutSchedulesForThisWeek();
        WorkoutSchedule sched = myList.get(0);
        if (sched != null) {
            id = sched.getId();
            name_schedule = sched.getName_schedule();
            update_name();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sportify_main, menu);
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

    public void addClicked(View view){
        NewScheduleFragment nw = new NewScheduleFragment();
        nw.show(getFragmentManager(),"");
    }

    public void addClick(View view) {
        EditScheduleFragment wn = new EditScheduleFragment();
        wn.show(getFragmentManager(),"");
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

    static void update_name() {
        textView.setText(name_schedule);
    }
}
