package sportify.sportifyapp;

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

/*

    public void deleteWorkoutSchedule(final WorkoutSchedule WorkoutSchedule)
    {
        final long id;

        id = WorkoutSchedule.getId();
        System.out.println("WorkoutSchedule deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_WorkoutScheduleS,
                SQLiteHelper.COLUMN_ID + " = " + id,
                null);
    }

    public boolean ifWorkoutScheduleExist(final String name, final String day, String preset, String num_weeks)
    {
        String selectQuery = "SELECT * "+
                " FROM " + SQLiteHelper.TABLE_WorkoutScheduleS +
                " WHERE "+SQLiteHelper.COLUMN_NAMESCHEDULE +" = '"+ name + "'" +
                " WHERE "+SQLiteHelper.COLUMN_DAYWEEK +" = '"+ day + "'" +
                " WHERE "+SQLiteHelper.COLUMN_PRESETSCHEDULE +" = '"+ preset + "'" +
                " AND " + SQLiteHelper.COLUMN_NUMWEEKS + " = '" + num_weeks + "'" +
                ";";
        ;
        Cursor c = database.rawQuery(selectQuery,null);
        if (c.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }*/

    public List<WorkoutSchedule> getAllWorkoutSchedulesForThisWeek()
    {
        final List<WorkoutSchedule> WorkoutSchedules;
        final Cursor        cursor;

        WorkoutSchedules = new ArrayList<WorkoutSchedule>();

        String selectQuery = "SELECT * "+
                " FROM " + SQLiteHelper.TABLE_NAME +
                " WHERE "+SQLiteHelper.COLUMN_NUMWEEKS + " > 0 "+
                ";";

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
/*
    public List<WorkoutSchedule> getAllWorkoutSchedules()
    {
        final List<WorkoutSchedule> WorkoutSchedules;
        final Cursor        cursor;

        WorkoutSchedules = new ArrayList<WorkoutSchedule>();
        cursor   = database.query(SQLiteHelper.TABLE_WorkoutScheduleS,
                allColumns,
                null,
                null,
                null,
                null,
                null);

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
*/
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
}
