package in.wslab.thenewbustonrevised;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by ranvir on 25/10/16.
 */
public class Scale {

    public static final String KEY_ROWID = "id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_SCALE = "person_SCALE";

    private static final String DATABASE_NAME = "SCALE";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE "+DATABASE_TABLE+"("
                    +KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +KEY_NAME+" TEXT NOT NULL,"
                    +KEY_SCALE+" TEXT NOT NULL);"
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
            onCreate(db);
        }
    }

    public Scale(Context c){
        ourContext=c;
    }
    public Scale open() throws SQLException{
        ourHelper = new DBHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }

    public long createEntery(String name,String scale){
        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_SCALE,scale);
       return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }

    public  String getData(){
        String[] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_SCALE};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        String result="";

        int iRow=c.getColumnIndex(KEY_ROWID);
        int iName=c.getColumnIndex(KEY_NAME);
        int iScale=c.getColumnIndex(KEY_SCALE);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result =result + c.getString(iRow)+" "+c.getString(iName)+" "+c.getString(iScale)+ "\n";
        }

        return result;
    }


    public String getName(long l){
        String name="";
        String[] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_SCALE};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
        if(c!=null){
            c.moveToFirst();
            name=c.getString(1);
            return name;
        }

        return name;
    }

    public String getScale(long l){
        String sca="";
        String[] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_SCALE};
        Cursor c=ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
        if(c!=null){
            c.moveToFirst();
            sca=c.getString(2);
            return sca;
        }
        return sca;
    }
    public void updateEntery(long mRow,String mName,String mScale){
        ContentValues cvUpdate= new ContentValues();
        cvUpdate.put(KEY_NAME,mName);
        cvUpdate.put(KEY_SCALE,mScale);
        ourDatabase.update(DATABASE_TABLE,cvUpdate,KEY_ROWID+"="+mRow,null);
    }

    public void deleteEntery(long mRow){

        ourDatabase.delete(DATABASE_TABLE,KEY_ROWID+"="+mRow,null);
    }


}
