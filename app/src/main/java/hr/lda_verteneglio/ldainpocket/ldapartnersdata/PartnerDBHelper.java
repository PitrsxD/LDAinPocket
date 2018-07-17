package hr.lda_verteneglio.ldainpocket.ldapartnersdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class PartnerDBHelper extends SQLiteOpenHelper {

    //Name of Database!
    public static final String DB_NAME = "activity_partners.db";
    //Version of Database!
    public static final int DB_VERSION = 1;

    private static final String PACKAGE_NAME = "hr.lda_verteneglio.ldainpocket";

    public PartnerDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static String getDatabaseFolder () {
        return Environment.getDataDirectory() + PACKAGE_NAME + "/databases//";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
