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
import com.appinventory.Adapter.RecordListUserAdapter;
import com.appinventory.Model.Model;
import com.appinventory.Model.ModelUser;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ListView mListView;
    ArrayList<ModelUser> mList;
    RecordListUserAdapter mAdapter = null;

    ImageView imageViewIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mListView = findViewById(R.id.listViewUser);
        mList = new ArrayList<>();
        mAdapter = new RecordListUserAdapter(this, R.layout.row_user, mList);
        mListView.setAdapter(mAdapter);

        //get all data from sqlite
        Cursor cursor = MainActivity.mSQLiteHelper.getData("SELECT * FROM USER");
        mList.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            String telp = cursor.getString(3);
            String userName = cursor.getString(4);
            String password = cursor.getString(5);
            byte[] image  = cursor.getBlob(6);
            //add to list
            mList.add(new ModelUser(id, name, address, telp, userName, password, image));
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

                AlertDialog.Builder dialog = new AlertDialog.Builder(UserActivity.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0){
                            //update
                            Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM USER");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            //show update dialog
//                            showDialogUpdate(RecordListActivity.this, arrID.get(position));

                            Intent myIntent = new Intent(UserActivity.this, AddUserActivity.class);
                            myIntent.putExtra("id", arrID.get(position).toString());
                            startActivity(myIntent);
                        }
                        if (i==1){
                            //delete
                            Cursor c = MainActivity.mSQLiteHelper.getData("SELECT id FROM USER");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            MainActivity.mSQLiteHelper.deleteData(
                                    arrID.get(position)
                            );
                            mAdapter.notifyDataSetChanged();
                            Intent intents = new Intent(UserActivity.this, DashboardAdminActivity.class);
                            intents.putExtra("id", "");
                            startActivity(intents);
                            Toast.makeText(UserActivity.this, "Deleted Data Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });

    }
    public void intentToAddUser(View view)
    {
        Intent intent = new Intent(this, AddUserActivity.class);
        intent.putExtra("id", "");
        startActivity(intent);
    }

}
