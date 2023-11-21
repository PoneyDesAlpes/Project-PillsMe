package be.helha.pills_me.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import be.helha.pills_me.db.PillCursorWrapper;
import be.helha.pills_me.db.PillsMeBaseHelper;
import be.helha.pills_me.db.PillsMeDbSchema;

public class BankPills {
    private static BankPills instance;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static BankPills getInstance(Context context) {
        if (instance == null) {
            instance = new BankPills(context);
        }
        return instance;
    }

    private BankPills(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new PillsMeBaseHelper(mContext).getWritableDatabase();
    }

    public void addPill(Pill p) {
        mDatabase.insert(PillsMeDbSchema.PillTable.NAME, null, getContentValues(p));
    }

    public void updatePill(Pill p) {
        String id = p.getId().toString();
        ContentValues values = getContentValues(p);

        mDatabase.update(PillsMeDbSchema.PillTable.NAME, values,
                PillsMeDbSchema.PillTable.Cols.ID + " = ?",
                new String[]{id});
    }

    public Pill getPill(Integer id) {
        PillCursorWrapper cursor = queryPills(
                PillsMeDbSchema.PillTable.Cols.ID + " = ?",
                new String[]{id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPill();
        } finally {
            cursor.close();
        }
    }

    public List<Pill> getPills() {
        List<Pill> pills = new ArrayList<>();
        PillCursorWrapper cursor = queryPills(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                pills.add(cursor.getPill());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return pills;
    }

    private ContentValues getContentValues(Pill p) {
        ContentValues values = new ContentValues();
        values.put(PillsMeDbSchema.PillTable.Cols.NAME,
                p.getName());
        values.put(PillsMeDbSchema.PillTable.Cols.DURATION,
                p.getDuration());
        values.put(PillsMeDbSchema.PillTable.Cols.MORNING,
                (p.isMorning()) ? 1 : 0);
        values.put(PillsMeDbSchema.PillTable.Cols.MIDDAY,
                (p.isMidDay()) ? 1 : 0);
        values.put(PillsMeDbSchema.PillTable.Cols.EVENING,
                (p.isEvening()) ? 1 : 0);
        return values;
    }

    private PillCursorWrapper queryPills(String whereClause, String[] whereArgs) {
        return new PillCursorWrapper(
                mDatabase.query(
                        PillsMeDbSchema.PillTable.NAME,
                        null,
                        whereClause,
                        whereArgs,
                        null, null, null)
        );
    }
}
