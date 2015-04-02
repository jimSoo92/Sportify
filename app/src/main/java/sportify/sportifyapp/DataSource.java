package sportify.sportifyapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class DataSource
{
    // Database fields
    private final SQLiteHelper dbHelper;
    private final String[] allColumns;
    private SQLiteDatabase database;

    private static final String TABLE_ACTIVITIES = "ta";

    {
        allColumns = new String[]
                {
                        SQLiteHelper.COLUMN_ID,
                        SQLiteHelper.COLUMN_NAMESCHEDULE,
                        SQLiteHelper.COLUMN_DAYWEEK,
                        SQLiteHelper.COLUMN_PRESETSCHEDULE,
                        SQLiteHelper.COLUMN_NUMWEEKS,
                };
    }

    public DataSource(Context context)
    {
        dbHelper = new SQLiteHelper(context);
    }

    public void open()throws SQLException
    {
        database = dbHelper.getWritableDatabase();
        int i =5;
    }

    public void close() {
        dbHelper.close();
    }

    public WorkoutSchedule createWorkoutSchedule(String name, String day, String preset, int num_weeks)
    {
        final ContentValues         values;
        final long                  insertId;
        final Cursor                cursor;
        final WorkoutSchedule       newSchedule;

        values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_NAMESCHEDULE, name);
        values.put(SQLiteHelper.COLUMN_DAYWEEK, day);
        values.put(SQLiteHelper.COLUMN_PRESETSCHEDULE, preset);
        values.put(SQLiteHelper.COLUMN_NUMWEEKS, num_weeks);

        insertId = database.insert(SQLiteHelper.TABLE_NAME,
                null,
                values);
        cursor = database.query(SQLiteHelper.TABLE_NAME,
                allColumns,
                SQLiteHelper.COLUMN_ID + " = " + insertId,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
        newSchedule = cursorToWorkoutSchedule(cursor);
        cursor.close();

        return (newSchedule);
    }

    public boolean editWorkoutSchedule(String name, String preset, String num_weeks)
    {
        final ContentValues         values;
        final long                  insertId;
        final Cursor                cursor;
        final WorkoutSchedule       newSchedule;

        values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_PRESETSCHEDULE, preset);
        values.put(SQLiteHelper.COLUMN_NUMWEEKS, num_weeks);
        values.put(SQLiteHelper.COLUMN_NAMESCHEDULE, name);

        int i = database.update(SQLiteHelper.TABLE_NAME, values, SQLiteHelper.COLUMN_ID + "='" + SportifyMain.id + "'", null);

        if(i == 0) {
            return false;
        }

        return true;
    }

    public List<WorkoutSchedule> getAllWorkoutSchedulesForThisWeek()
    {
        final List<WorkoutSchedule> WorkoutSchedules;
        final Cursor        cursor;

        WorkoutSchedules = new ArrayList<WorkoutSchedule>();

        String selectQuery = "SELECT * "+
                " FROM " + SQLiteHelper.TABLE_NAME +
                " WHERE "+SQLiteHelper.COLUMN_NUMWEEKS + " > 0 "+
                "ORDER BY " + SQLiteHelper.COLUMN_ID + " DESC; ";

        cursor = database.rawQuery(selectQuery,null);

        try
        {
            cursor.moveToFirst();

            while(!(cursor.isAfterLast()))
            {
                final WorkoutSchedule WorkoutSchedule;

                WorkoutSchedule = cursorToWorkoutSchedule(cursor);
                WorkoutSchedules.add(WorkoutSchedule);
                cursor.moveToNext();
            }

        }
        finally
        {
            // make sure to close the cursor
            cursor.close();
        }

        return (WorkoutSchedules);
    }

    private WorkoutSchedule cursorToWorkoutSchedule(final Cursor cursor)
    {
        final WorkoutSchedule workoutSchedule;

        workoutSchedule = new WorkoutSchedule();

        workoutSchedule.setId(cursor.getInt(0));
        workoutSchedule.setName_schedule(cursor.getString(1));
        workoutSchedule.setDay_of_the_week(cursor.getString(2));
        workoutSchedule.setPreset_schedule(cursor.getString(3));
        workoutSchedule.setNum_of_weeks(cursor.getInt(4));

        return (workoutSchedule);
    }

    private Activities cursorToActivities(final Cursor cursor)
    {
        final Activities activities;

        activities = new Activities();

        activities.schedule_id = (cursor.getInt(0));
        activities.name = (cursor.getString(1));
        activities.set =(cursor.getString(2));
        activities.rep = (cursor.getString(3));
        activities.check_box = (cursor.getString(4));

        return (activities);
    }

    public Activities createActivities(String name, String set, String rep, String check_box, int schedule_id)
    {
        final ContentValues         values;
        final long                  insertId;
        final Cursor                cursor;
        final Activities            activities;

        activities = new Activities();

        activities.name = name;
        activities.set = set;
        activities.rep = rep;
        activities.check_box = check_box;
        activities.schedule_id = schedule_id;

        values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_ACTIVITY_NAME, name);
        values.put(SQLiteHelper.COLUMN_SET, set);

        values.put(SQLiteHelper.COLUMN_REP, rep);
        values.put(SQLiteHelper.COLUMN_CHECK_BOX, check_box);
        values.put(SQLiteHelper.COLUMN_SHEDULE_ID, schedule_id);

        database.insert(SQLiteHelper.TABLE_ACTIVITIES,
                null,
                values);

        return (activities);
    }
}
