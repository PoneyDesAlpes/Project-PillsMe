package be.helha.pills_me.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import be.helha.pills_me.models.Prescription;

public class PrescriptionCursorWrapper extends CursorWrapper {
    public PrescriptionCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Prescription getPrescription() {
        String idString =
                getString(getColumnIndex(PillsMeDbSchema.PrescriptionTable.Cols.ID));
        String startDate =
                getString(getColumnIndex(PillsMeDbSchema.PrescriptionTable.Cols.START_DATE_PERIOD));
        String endDate =
                getString(getColumnIndex(PillsMeDbSchema.PrescriptionTable.Cols.END_DATE_PERIOD));
        int morning =
                getInt(getColumnIndex(PillsMeDbSchema.PrescriptionTable.Cols.MORNING));
        int midday =
                getInt(getColumnIndex(PillsMeDbSchema.PrescriptionTable.Cols.MIDDAY));
        int evening =
                getInt(getColumnIndex(PillsMeDbSchema.PrescriptionTable.Cols.EVENING));
        int pillId =
                getInt(getColumnIndex(PillsMeDbSchema.PrescriptionTable.Cols.PILL_ID));

        boolean morningBool = (morning != 0);
        boolean middayBool = (midday != 0);
        boolean eveningBool = (evening != 0);

        Prescription prescription = new Prescription(Integer.parseInt(idString), startDate, endDate, morningBool, middayBool, eveningBool, pillId);
        return prescription;
    }
}
