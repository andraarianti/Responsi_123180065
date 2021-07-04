package com.example.responsitpm_123180065;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.responsitpm_123180065.data.faskes.DataItem;
import com.example.responsitpm_123180065.service.APIListener;
import com.example.responsitpm_123180065.service.APIService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FaskesActivity extends AppCompatActivity {

    private RecyclerView rcv_rs;
    private FaskesAdapter faskesAdapter;
    BottomNavigationView menu;

    APIListener<ArrayList<DataItem>> listAPIListener = new APIListener<ArrayList<DataItem>>() {
        @Override
        public void onSuccess(ArrayList<DataItem> body) {
            faskesAdapter.setFaskes(body);
        }

        @Override
        public void onFailed(String message) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faskes);
        faskesAdapter = new FaskesAdapter();
        menu = findViewById(R.id.menu_bawah);
        rcv_rs = findViewById(R.id.rcv_rs);

        rcv_rs.setLayoutManager(new LinearLayoutManager(this));
        rcv_rs.setHasFixedSize(true);
        rcv_rs.setAdapter(faskesAdapter);
        rcv_rs.setLayoutManager(new LinearLayoutManager(this));

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.data :
                        Intent home = new Intent(FaskesActivity.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.rs :
                        Intent rs = new Intent(FaskesActivity.this, FaskesActivity.class);
                        startActivity(rs);
                        finish();
                        break;
                }
                return true;
            }
        });

        APIService apiService = new APIService();
        apiService.getFaskes(listAPIListener);
    }
}
