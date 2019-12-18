package com.appinventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.appinventory.Adapter.RecordListAdapter;
import com.appinventory.Model.Model;

import java.util.ArrayList;

public class TransaksiActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<Model> mList;
    RecordListAdapter mAdapter = null;

    ImageView imageViewIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new RecordListAdapter(this, R.layout.row_barang, mList);
        mListView.setAdapter(mAdapter);

        //get all data from sqlite
        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM RECORD");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Integer safetyStock = cursor.getInt(2);
            Integer optimumStock = cursor.getInt(3);
            Integer currentStock = cursor.getInt(4);
            String tags = cursor.getString(7);
            String notes = cursor.getString(6);
            String price = cursor.getString(5);
            byte[] image  = cursor.getBlob(8);
            //add to list
            mList.add(new Model(id, name, safetyStock, optimumStock, currentStock, tags, notes, price, image));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size()==0){
            //if there is no record in table of database which means listview is empty
            Toast.makeText(this, "No record found...", Toast.LENGTH_SHORT).show();
        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //alert dialog to display options of update and delete
                final CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(TransaksiActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            //update
                            Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show update dialog
//                            showDialogUpdate(RecordListActivity.this, arrID.get(position));

                            Intent myIntent = new Intent(TransaksiActivity.this, AddBarangActivity.class);
                            myIntent.putExtra("id", arrID.get(position).toString());
                            startActivity(myIntent);
                        }
                        if (i==1){
                            //delete
                            Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM RECORD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            MainActivity.mSQLiteHelper.deleteData(
                                    arrID.get(position)
                            );
                            mAdapter.notifyDataSetChanged();
                            Intent intents = new Intent(TransaksiActivity.this, DashboardAdminActivity.class);
                            intents.putExtra("id", "");
                            startActivity(intents);
                            Toast.makeText(TransaksiActivity.this, "Deleted Data Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    public void intentToAddBarang(View view)
    {
        Intent intent = new Intent(this, AddBarangActivity.class);
        intent.putExtra("id", "");
        startActivity(intent);
    }

}
