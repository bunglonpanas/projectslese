package com.appinventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {
    Integer idUSer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Intent myIntent = getIntent(); // gets the previously created intent
        idUSer = myIntent.getIntExtra("id", -1);
    }
    public void intentToBeranda(View view)
    {
        Intent intent = new Intent(this, BerandaActivity.class);
        startActivity(intent);
    }
    public void intentToTransaksi(View view)
    {
        Intent intent = new Intent(this, TransaksiActivity.class);
        startActivity(intent);
    }
    public void intentToLaporan(View view)
    {
        Intent intent = new Intent(this, LaporanActivity.class);
        startActivity(intent);
    }
    public void intentDataMaster(View view)
    {
        Intent intent = new Intent(this, DataMasterActivity.class);
        startActivity(intent);
    }
    public void intentUserProfile(View view)
    {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
    public void intentToLogin(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
