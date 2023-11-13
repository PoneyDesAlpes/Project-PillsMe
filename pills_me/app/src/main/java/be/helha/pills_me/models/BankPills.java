package be.helha.pills_me.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.helha.pills_me.db.PillCursorWrapper;
import be.helha.pills_me.db.PillsMeBaseHelper;
import be.helha.pills_me.db.PillsMeDbSchema;


//Equivalent de crimeLab
public class BankPills {
    private static BankPills sBankPills;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static BankPills getInstance(Context context){
        if(sBankPills == null){
            sBankPills = new BankPills(context);
        }
        return sBankPills;
    }

    private BankPills(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new PillsMeBaseHelper(mContext).getWritableDatabase();
    }

    public void addPill(Pill p){
        mDatabase.insert(PillsMeDbSchema.PillTable.NAME, null, getContentValues(p));
    }

    public void updatePill(Pill p){
        String uuidString = p.getUUID().toString();
        ContentValues values = getContentValues(p);

        mDatabase.update(PillsMeDbSchema.PillTable.NAME, values,
                PillsMeDbSchema.PillTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public Pill getPill(UUID id){
        PillCursorWrapper cursor = queryPills(
                PillsMeDbSchema.PillTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPill();
        } finally {
            cursor.close();
        }
    }

    public List<Pill> getPills(){
        List<Pill> pills = new ArrayList<>();
        PillCursorWrapper cursor = queryPills(null, null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                pills.add(cursor.getPill());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return pills;
    }

    private ContentValues getContentValues(Pill p){
        ContentValues values = new ContentValues();
        values.put(PillsMeDbSchema.PillTable.Cols.UUID,
                p.getUUID().toString());
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

    private PillCursorWrapper queryPills(String whereClause, String[] whereArgs){
        return new PillCursorWrapper(mDatabase.query(
                PillsMeDbSchema.PillTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,null,null)
        );
    }


//    private static ArrayList<Pill> mPills;
//
//    private static BankPills instance;
//    public static BankPills getInstance(){
//        if(instance == null){
//            instance = new BankPills();
//        }
//        return instance;
//    }
//
//    private BankPills(){
//        mPills = new ArrayList<>(); //ToDO : faire appel a une DB
//        mPills.add(new Pill("Xanax",2, true, false, true));//TODO change this shit!
//    }
//
//    public void AddPill(Pill p){
//        mPills.add(p);
//    }
//
//    public ArrayList<Pill> getBankPills(){
//        return mPills;
//    }
//
//    public ArrayList getBankPillsName(){
//        ArrayList listNamePills = new ArrayList();
//        for (Pill p :mPills) {
//            listNamePills.add(p.getName());
//        }
//        return listNamePills;
//    }
}
