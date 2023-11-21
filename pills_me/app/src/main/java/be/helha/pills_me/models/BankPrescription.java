package be.helha.pills_me.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import be.helha.pills_me.db.PillsMeBaseHelper;
import be.helha.pills_me.db.PillsMeDbSchema;
import be.helha.pills_me.db.PrescriptionCursorWrapper;

public class BankPrescription {
    private static BankPrescription instance;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static BankPrescription getInstance(Context context) {
        if (instance == null) {
            instance = new BankPrescription(context);
        }
        return instance;
    }

    private BankPrescription(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PillsMeBaseHelper(mContext).getWritableDatabase();
    }

    public void addPrescription(Prescription p) {
        mDatabase.insert(PillsMeDbSchema.PrescriptionTable.NAME, null, getContentValues(p));
        Log.d("PillsMe", "Prescription added to DB");
    }

    public void updatePrescription(Prescription p) {
        String id = p.getId().toString();
        ContentValues values = getContentValues(p);

        mDatabase.update(PillsMeDbSchema.PrescriptionTable.NAME, values,
                PillsMeDbSchema.PrescriptionTable.Cols.ID + " = ?",
                new String[]{id});
    }

    public Prescription getPrescription(Integer id) {
        PrescriptionCursorWrapper cursor = queryPrescriptions(
                PillsMeDbSchema.PrescriptionTable.Cols.ID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPrescription();
        } finally {
            cursor.close();
        }
    }

    public List<Prescription> getPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        PrescriptionCursorWrapper cursor = queryPrescriptions(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                prescriptions.add(cursor.getPrescription());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return prescriptions;
    }

    private ContentValues getContentValues(Prescription p) {
        ContentValues values = new ContentValues();
        values.put(PillsMeDbSchema.PrescriptionTable.Cols.START_DATE_PERIOD,
                p.getStartDate());
        values.put(PillsMeDbSchema.PrescriptionTable.Cols.END_DATE_PERIOD,
                p.getEndDate());
        values.put(PillsMeDbSchema.PrescriptionTable.Cols.MORNING,
                (p.isMorning()) ? 1 : 0);
        values.put(PillsMeDbSchema.PrescriptionTable.Cols.MIDDAY,
                (p.isMidDay()) ? 1 : 0);
        values.put(PillsMeDbSchema.PrescriptionTable.Cols.EVENING,
                (p.isEvening()) ? 1 : 0);
        values.put(PillsMeDbSchema.PrescriptionTable.Cols.PILL_ID,
                p.getPillId());
        return values;
    }

    private PrescriptionCursorWrapper queryPrescriptions(String whereClause, String[] whereArgs) {
        return new PrescriptionCursorWrapper(
                mDatabase.query(
                        PillsMeDbSchema.PrescriptionTable.NAME,
                        null,
                        whereClause,
                        whereArgs,
                        null, null, null
                )
        );
    }
}
