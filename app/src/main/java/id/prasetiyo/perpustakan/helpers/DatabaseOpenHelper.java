package id.prasetiyo.perpustakan.helpers;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by aoktox on 30/04/16.
 */
public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME="perpustakaan.db";
    private static final int DATABASE_VERSION=1;
    public DatabaseOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
}
