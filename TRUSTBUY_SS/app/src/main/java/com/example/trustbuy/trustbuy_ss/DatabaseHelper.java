package com.example.trustbuy.trustbuy_ss;

/**
 * Created by MK on 3/30/2017.
 */
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Trustbuy.db";
    public static final String USER_TABLE = "User";
    public static final String USERCR_TABLE = "User_Credentials";
    public static final String USER_COL_1 = "ID";
    public static final String USER_COL_2 = "FIRSTNAME";
    public static final String USER_COL_3 = "SURNAME";
    public static final String USER_COL_4 = "USERNAME";
    public static final String USER_COL_5 = "COLLEGE_EMAIL";
    public static final String USERCR_COL_6 = "PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USER_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,SURNAME TEXT,USERNAME TEXT, COLLEGE_EMAIL TEXT)");
        db.execSQL("create table " + USERCR_TABLE +" (ID INTEGER PRIMARY KEY, USERNAME TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        onCreate(db);
    }

    public boolean insertData(String firstname,String surname,String username, String college_email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_2,firstname);
        contentValues.put(USER_COL_3,surname);
        contentValues.put(USER_COL_4,username);
        contentValues.put(USER_COL_5,college_email);
        long result = db.insert(USER_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+USER_TABLE,null);
        return res;
    }

    public boolean updateData(String id,String firstname,String surname,String username,String college_email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_1,id);
        contentValues.put(USER_COL_2,firstname);
        contentValues.put(USER_COL_3,surname);
        contentValues.put(USER_COL_4,username);
        contentValues.put(USER_COL_5,college_email);
        db.update(USER_TABLE, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(USER_TABLE, "ID = ?",new String[] {id});
    }
}
