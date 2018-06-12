package jar.pe.edu.upeu.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import jar.pe.edu.upeu.dbconn.DBConn;

/**
 * Created by Joselin on 27/05/2018.
 */
public class UsuarioDao extends DBConn {
    DBConn con;
    Context contex;
    SQLiteDatabase db;
    Cursor cur;
    String sql;

    public UsuarioDao(Context context) {
        super(context);
        this.contex=context;
    }

    public boolean validarUsuario(String usuario, String clave){
        con=new DBConn(contex);
        db=con.getReadableDatabase();
        sql=" select * from usuario where usuario='"+usuario+"' and clave='"+clave+"'";
        cur=db.rawQuery(sql, null);
        if(cur.getCount()==1){
            return true;
        }else{
        return false;
        }
    }

    public Cursor listarUsuario(String usuario){
        con=new DBConn(contex);
        db=con.getReadableDatabase();
        sql=" select * from usuario where usuario='"+usuario+"'";
        cur=db.rawQuery(sql, null);
        return cur;
    }


}
