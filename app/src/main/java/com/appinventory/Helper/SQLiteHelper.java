package com.appinventory.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    //constructor
    public SQLiteHelper(Context context,
                        String name,
                        SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //insertData
    public void insertData(String name, Integer safetyStock, Integer optimumStock, Integer currentStock, String price, String note, String tags, byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        //query to insert record in database table
        String sql = "INSERT INTO RECORD VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?)"; //where "RECORD" is table name in database we will create in mainActivity

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindLong(2, safetyStock);
        statement.bindLong(3, optimumStock);
        statement.bindLong(4, currentStock);
        statement.bindString(5, price);
        statement.bindString(6, note);
        statement.bindString(7, tags);
        statement.bindBlob(8, image);

        statement.executeInsert();
    }

//    updateData
    public void updateData(String name, Integer safetyStock, Integer optimumStock, Integer currentStock, String price, String note, String tags, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();
        //query to update record
        String sql = "UPDATE RECORD SET name=?, safetyStock=?, optimumStock=?, currentStock=?, price=?, note=?, tags=?, image=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindLong(2, safetyStock);
        statement.bindLong(3, optimumStock);
        statement.bindLong(4, currentStock);
        statement.bindString(5, price);
        statement.bindString(6, tags);
        statement.bindString(7, note);
        if(image!= null){
            statement.bindBlob(8, image);
        }
        statement.bindDouble(9, (double) id);

        statement.execute();
        database.close();
    }
//
//    //deleteData
    public void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM RECORD WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);

        statement.execute();
        database.close();
    }
//



    public void insertDataUser(String name,  String address, String telp, String userName, String password, byte[] image) {
        SQLiteDatabase database = getWritableDatabase();
        //query to insert record in database table
        String sql = "INSERT INTO USER VALUES(NULL, ?, ?, ?, ?, ?, ?)"; //where "RECORD" is table name in database we will create in mainActivity

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, address);
        statement.bindString(3, telp);
        statement.bindString(4, userName);
        statement.bindString(5, password);
        statement.bindBlob(6, image);

        statement.executeInsert();
    }

    //    updateData
    public void updateDataUser(String name,  String address, String telp, String userName, String password, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();
        //query to update record
        String sql = "UPDATE USER SET name=?, address=?, telp=?, userName=?, password=?, image=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, address);
        statement.bindString(3, telp);
        statement.bindString(4, userName);
        statement.bindString(5, password);
        if(image!= null){
            statement.bindBlob(8, image);
        }
        statement.bindDouble(9, (double) id);

        statement.execute();
        database.close();
    }
    //
//    //deleteData
    public void deleteDataUser(int id) {
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM USER WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);

        statement.execute();
        database.close();
    }


    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}