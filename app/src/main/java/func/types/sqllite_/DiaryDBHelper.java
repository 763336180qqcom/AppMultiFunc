package func.types.sqllite_;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * on 2016-08-18.
 */
public class DiaryDBHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "diary.db";

    public static int DATABASE_VERSION = 1;

    public DiaryDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDb) {

        String sqlStr = "create table diary(" +
                "_id integer primary key autoincrement " +
                "topic varchar(100)," +
                "content varchar(1000)" +
                ")";
        sqlDb.execSQL(sqlStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDb, int i, int i1) {

        String sqlStr = "drop table if exists diary";
        sqlDb.execSQL(sqlStr);
        onCreate(sqlDb);
    }
}
