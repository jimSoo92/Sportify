package sportify.sportifyapp;

/**
 * Created by Dany on 2015-03-18.
 */
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;

    import java.util.ArrayList;
    import java.util.List;


public class SQLiteHelper extends SQLiteOpenHelper
    {
        public static final String TABLE_NAME;
        public static final String COLUMN_ID;

        public static final String COLUMN_NAMESCHEDULE;
        public static final String COLUMN_DAYWEEK;
        public static final String COLUMN_PRESETSCHEDULE;
        public static final String COLUMN_NUMWEEKS;

        public static final String TABLE_ACTIVITIES;
        public static final String COLUMN_ACTIVITY_NAME;
        public static final String COLUMN_SET;
        public static final String COLUMN_SHEDULE_ID;
        public static final String COLUMN_REP;
        public static final String COLUMN_CHECK_BOX;

        private static final String DATABASE_NAME;
        private static final int DATABASE_VERSION;
        private static final String DATABASE_CREATE;

        static
        {
            TABLE_NAME = "gymSchedule";
            COLUMN_ID = "_id";
            COLUMN_NAMESCHEDULE = "name";
            COLUMN_DAYWEEK = "day";
            COLUMN_PRESETSCHEDULE = "preset";
            COLUMN_NUMWEEKS = "num_weeks";

            TABLE_ACTIVITIES = "ta";
            COLUMN_ACTIVITY_NAME = "an";
            COLUMN_SET = "cs";
            COLUMN_SHEDULE_ID = "si";
            COLUMN_REP = "cr";
            COLUMN_CHECK_BOX = "cb";

            DATABASE_NAME = "users.db";
            DATABASE_VERSION = 2;
            DATABASE_CREATE  = "create table " +
                    TABLE_NAME + "(" + COLUMN_ID +" integer primary key autoincrement, " +
                    COLUMN_NAMESCHEDULE + " text not null, " +
                    COLUMN_DAYWEEK + " text not null, " +
                    COLUMN_PRESETSCHEDULE + " text not null, " +
                    COLUMN_NUMWEEKS + " integer not null ); " +
                    "create table " +
                        TABLE_ACTIVITIES + "(" + COLUMN_SHEDULE_ID +" integer, " +
                        COLUMN_ACTIVITY_NAME + " text not null, " +
                        COLUMN_SET + " text not null, " +
                        COLUMN_REP + " text not null, " +
                        COLUMN_CHECK_BOX + " text not null ); ";
        }

        public SQLiteHelper(final Context context)
        {
            super(context,
                    DATABASE_NAME,
                    null,
                    DATABASE_VERSION);
        }

        @Override
        public void onCreate(final SQLiteDatabase database)
        {
            database.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(final SQLiteDatabase db,
                              final int            oldVersion,
                              final int            newVersion)
        {
            Log.w(SQLiteHelper.class.getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

        public List<Activities> getAllActivities() {
            List<Activities> activitiesList = new ArrayList<Activities>();

            String selectQuery = "SELECT * FROM " + TABLE_ACTIVITIES;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            //loop through all rows and add it to the list
            if (cursor.moveToFirst()) {
                do {
                    Activities activities = new Activities();
                    activities.schedule_id = cursor.getInt(0);
                    activities.name = cursor.getString(1);
                    activities.set = cursor.getString(2);
                    activities.rep = cursor.getString(3);
                    activities.check_box = cursor.getString(4);
                    //adding activity to my list
                    activitiesList.add(activities);
                } while (cursor.moveToNext());
            }

            //return activity list
            return activitiesList;
        }
    }


