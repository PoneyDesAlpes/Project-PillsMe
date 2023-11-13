package be.helha.pills_me.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import be.helha.pills_me.models.Pill;

public class PillCursorWrapper extends CursorWrapper {
    public PillCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Pill getPill(){
        String uuidString =
                getString(getColumnIndex(PillsMeDbSchema.PillTable.Cols.UUID));
        String name =
                getString(getColumnIndex(PillsMeDbSchema.PillTable.Cols.NAME));
        int duration =
                getInt(getColumnIndex(PillsMeDbSchema.PillTable.Cols.DURATION));
        int morning =
                getInt(getColumnIndex(PillsMeDbSchema.PillTable.Cols.MORNING));
        int midday =
                getInt(getColumnIndex(PillsMeDbSchema.PillTable.Cols.MIDDAY));
        int evening =
                getInt(getColumnIndex(PillsMeDbSchema.PillTable.Cols.EVENING));

        boolean morningBool = (morning != 0);
        boolean middayBool = (midday != 0);
        boolean eveningBool = (evening != 0);

        Pill pill = new Pill(UUID.fromString(uuidString), name, duration, morningBool, middayBool, eveningBool);
        return pill;
    }
}
