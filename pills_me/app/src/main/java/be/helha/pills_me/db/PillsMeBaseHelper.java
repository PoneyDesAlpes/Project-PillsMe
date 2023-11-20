package be.helha.pills_me.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PillsMeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "pillsMeBase.db";

    public PillsMeBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + PillsMeDbSchema.PillTable.NAME + "(" +
                PillsMeDbSchema.PillTable.Cols.ID + " integer primary key autoincrement, " +
                PillsMeDbSchema.PillTable.Cols.DURATION + ", " +
                PillsMeDbSchema.PillTable.Cols.NAME + ", " +
                PillsMeDbSchema.PillTable.Cols.MORNING + ", " +
                PillsMeDbSchema.PillTable.Cols.MIDDAY + ", " +
                PillsMeDbSchema.PillTable.Cols.EVENING + ")"
        );

        sqLiteDatabase.execSQL("create table " + PillsMeDbSchema.PrescriptionTable.NAME +  "(" +
            PillsMeDbSchema.PrescriptionTable.Cols.ID + " integer primary key autoincrement, " +
            PillsMeDbSchema.PrescriptionTable.Cols.START_DATE_PERIOD + ", " +
            PillsMeDbSchema.PrescriptionTable.Cols.END_DATE_PERIOD + ", " +
            PillsMeDbSchema.PrescriptionTable.Cols.MORNING + ", " +
            PillsMeDbSchema.PrescriptionTable.Cols.MIDDAY + ", " +
            PillsMeDbSchema.PrescriptionTable.Cols.EVENING + ", " +
            PillsMeDbSchema.PrescriptionTable.Cols.PILL_ID + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //nothing to see here!
    }
}
