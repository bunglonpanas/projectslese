package com.appinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appinventory.Adapter.RecordListAdapter;
import com.appinventory.Adapter.RecordListStockAdapter;
import com.appinventory.Model.Model;

import java.util.ArrayList;

public class BerandaActivity extends AppCompatActivity {
    TextView txtTotBarang, txtLimitBarang, txtOverBarang;
    ListView mListView;
    ArrayList<Model> mList;
    RecordListStockAdapter mAdapter = null;

    ImageView imageViewIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int tot  = 0;
        int totLimit = 0;
        int totOver = 0;

        setContentView(R.layout.activity_beranda);

        txtTotBarang = findViewById(R.id.totBarang);
        txtLimitBarang = findViewById(R.id.totBarangKurang);
        txtOverBarang = findViewById(R.id.totBarangLebih);

        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT COUNT(id) FROM RECORD");
        while (cursor.moveToNext()){
             tot = cursor.getInt(0);
        }
        Cursor cursorKurang = MainActivity.mSQLiteHelper.getData("SELECT COUNT(id) FROM RECORD  WHERE safetyStock > currentStock");
        while (cursorKurang.moveToNext()){
            totLimit = cursorKurang.getInt(0);
        }
        Cursor cursorLebih = MainActivity.mSQLiteHelper.getData("SELECT COUNT(id) FROM RECORD a WHERE optimumStock < currentStock");
        while (cursorLebih.moveToNext()){
            totOver = cursorLebih.getInt(0);
        }

        txtTotBarang.setText(String.valueOf(tot));
        txtLimitBarang.setText(String.valueOf(totLimit));
        txtOverBarang.setText(String.valueOf(totOver));

        mListView = findViewById(R.id.listViewStock);
        mList = new ArrayList<>();
        mAdapter = new RecordListStockAdapter(this, R.layout.row_stock, mList);
        mListView.setAdapter(mAdapter);

        //get all data from sqlite
        Cursor cursorList = MainActivity.mSQLiteHelper.getData("SELECT * FROM RECORD  WHERE safetyStock > currentStock");
        mList.clear();
        while (cursorList.moveToNext()){
            int id = cursorList.getInt(0);
            String name = cursorList.getString(1);
            Integer safetyStock = cursorList.getInt(2);
            Integer optimumStock = cursorList.getInt(3);
            Integer currentStock = cursorList.getInt(4);
            String tags = cursorList.getString(7);
            String notes = cursorList.getString(6);
            String price = cursorList.getString(5);
            byte[] image  = cursorList.getBlob(8);
            //add to list
            mList.add(new Model(id, name, safetyStock, optimumStock, currentStock, tags, notes, price, image));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this, "No record found...", Toast.LENGTH_SHORT).show();
        }
    }
}
