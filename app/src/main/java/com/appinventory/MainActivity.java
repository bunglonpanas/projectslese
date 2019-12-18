package com.appinventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.appinventory.Helper.SQLiteHelper;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {
    Resources res ;
    final int REQUEST_CODE_GALLERY = 999;
    Button btnLogin;
    Button btnLoginAdmin;
    CheckBox chkbx;
    Integer idUser;
    Integer idTemp;
    String userName;
    EditText user, pwd ;
    ImageView  mImageView ;
    public static SQLiteHelper mSQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSQLiteHelper = new SQLiteHelper(this, "RECORDDB.sqlite", null, 1);
//        btnLogin = findViewById(R.id.btnLogin);
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS RECORD " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR, safetyStock INTEGER, optimumStock INTEGER, currentStock INTEGER, " +
                "price VARCHAR, note VARCHAR,tags VARCHAR,image BLOB)");
        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS USER " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR, address VARCHAR, telp VARCHAR, userName VARCHAR, " +
                "password VARCHAR, image BLOB)");

        mImageView = findViewById(R.id.imgLogoAwal);
        res = this.getResources();
        btnLoginAdmin = findViewById(R.id.btnLoginAdmin);
        user = findViewById(R.id.usernameField);
        pwd = findViewById(R.id.passwordField);

    }
    public void intentToDashboard(View view)
    {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
    public void intentToDashboardAdmin(View view)
    {

        Cursor cursorTemp = MainActivity.mSQLiteHelper.getData("SELECT * FROM USER  WHERE UPPER(userName) = 'ADMIN' AND UPPER(password) = 'ADMIN'");
        while (cursorTemp.moveToNext()){
            idTemp =  cursorTemp.getInt(0);
        }
        if(idTemp == null) {
            mSQLiteHelper.insertDataUser(
                    "ADMIN",
                    "UNKNOWN",
                    "xxxxxxxxxx",
                    "ADMIN",
                    "ADMIN",
                    imageViewToByte(res)
            );
        }


        String userInsert = user.getText().toString().trim() ;
        String pwdInsert = pwd.getText().toString().trim();

        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM USER  WHERE UPPER(userName) = '" + userInsert.toUpperCase() + "' AND UPPER(password) = '" + pwdInsert.toUpperCase() +"'");
        while (cursor.moveToNext()){
            idUser =  cursor.getInt(0);
            userName = cursor.getString(1);
        }



        if(userInsert.isEmpty() || pwdInsert.isEmpty())
        {
            Toast.makeText(MainActivity.this, "Username & Pasword Must Be Inserted", Toast.LENGTH_SHORT).show();

        }
        else if (idUser == null)
        {

            Toast.makeText(MainActivity.this, "Usernmae / Pasword Wrong, Pls Try Again", Toast.LENGTH_SHORT).show();

        }
        else
        {


            Toast.makeText(MainActivity.this, "Login Successfull , Welcome " + userName, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DashboardAdminActivity.class);
            intent.putExtra("id", idUser);
            startActivity(intent);

        }



    }
    public static byte[] imageViewToByte(Resources res) {
        Drawable drawable = res.getDrawable(R.drawable.barang_keluar);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }



}
