package com.appinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class UserProfileActivity extends AppCompatActivity {
    EditText mName, mAdress, mTelp, mUserName, mPassword;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mName = findViewById(R.id.nameField);
        mAdress = findViewById(R.id.addressField);
        mTelp = findViewById(R.id.telpField);
        mUserName = findViewById(R.id.userField);
        mPassword =  findViewById(R.id.passField);
        imageView = findViewById(R.id.imageViewUser);
        Intent myIntent = getIntent(); // gets the previously created intent
        Integer id = myIntent.getIntExtra("id", -1);

        if(id != null){
            Cursor cursorKurang = MainActivity.mSQLiteHelper.getData("SELECT * FROM USER  WHERE id = " + id);
            while (cursorKurang.moveToNext()){
                mName.setText(cursorKurang.getString(1));
                mAdress.setText(cursorKurang.getString(2));
                mTelp.setText(cursorKurang.getString(3));
                mUserName.setText(cursorKurang.getString(4));
                mPassword.setText(cursorKurang.getString(5));

                byte[] recordImage = cursorKurang.getBlob(6);
                if (recordImage != null) {

                    Bitmap bitmap = BitmapFactory.decodeByteArray(recordImage, 0, recordImage.length);
                    imageView.setImageBitmap(bitmap);

                }
            }
        }
    }
}
