package jar.pe.edu.upeu.dbconn;

import android.content.Context;

import pe.edu.upeu.dbexterno.ExternalSQLiteOpenHelper;

/**
 * Created by Joselin on 27/05/2018.
 */

public class DBConn extends ExternalSQLiteOpenHelper {

    private static final String DATABASE_NAME="asistenciadb.db";
    private static final int DATABASE_VERSION=3;

    public DBConn(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}

