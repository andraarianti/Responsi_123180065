package com.example.responsitpm_123180065;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.responsitpm_123180065.data.kumulatif.ContentItem;
import com.example.responsitpm_123180065.service.APIListener;
import com.example.responsitpm_123180065.service.APIService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcv_data;
    private MainAdapter mainAdapter;
    BottomNavigationView menu;

     APIListener<ArrayList<ContentItem>> listAPIListener = new APIListener<ArrayList<ContentItem>>() {
        @Override
        public void onSuccess(ArrayList<ContentItem> body) {
            mainAdapter.setKumulatif(body);
        }

        @Override
        public void onFailed(String message) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainAdapter = new MainAdapter();
        rcv_data = findViewById(R.id.rcv_data);
        menu = findViewById(R.id.menu_bawah);

        rcv_data.setLayoutManager(new LinearLayoutManager(this));
        rcv_data.setHasFixedSize(true);
        rcv_data.setAdapter(mainAdapter);
        rcv_data.setLayoutManager(new LinearLayoutManager(this));

        menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.data :
                        Intent home = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(home);
                        finish();
                        break;
                    case R.id.rs :
                        Intent rs = new Intent(MainActivity.this, FaskesActivity.class);
                        startActivity(rs);
                        finish();
                        break;
                }
                return true;
            }
        });

        APIService apiService = new APIService();
        apiService.getKumulatif(listAPIListener);
    }

}