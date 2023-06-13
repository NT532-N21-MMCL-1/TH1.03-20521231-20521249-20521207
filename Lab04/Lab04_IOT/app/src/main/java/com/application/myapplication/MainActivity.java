package com.application.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.application.myapplication.Fragment.AI.AIFragment;
import com.application.myapplication.Fragment.Chart.ChartFragment;
import com.application.myapplication.Fragment.Dashboard.DashBoardFragment;
import com.application.myapplication.Fragment.Log.LogFragment;
import com.application.myapplication.Fragment.Main.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ChipNavigationBar chipNavigationBar;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load the store fragment by default
        replaceFragment(new DashBoardFragment());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_dashboard:
                    replaceFragment(new DashBoardFragment());
                    break;
                case R.id.action_main:
                    replaceFragment(new MainFragment());
                    break;
                case  R.id.action_ai:
                    replaceFragment(new AIFragment());
                    break;
                case R.id.action_chart:
                    replaceFragment(new ChartFragment());
                    break;
                case R.id.action_log:
                    replaceFragment(new LogFragment());
            }
            return true;
        });

//
//        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int id) {
//                switch (id){
//                    case R.id.action_dashboard:
//                        replaceFragment(new DashBoardFragment());
//                        break;
//                    case R.id.action_main:
//                        replaceFragment(new MainFragment());
//                        break;
//                    case R.id.action_ai:
//                        replaceFragment(new AIFragment());
//                        break;
//                    case R.id.action_chart:
//                        replaceFragment(new ChartFragment());
//                        break;
//                    case R.id.action_log:
//                        replaceFragment(new LogFragment());
//                        break;
//                }
//            }
//        });
    }

    private void replaceFragment(Fragment fragment) {
        // load fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.relative_bottom_nav, fragment);
        fragmentTransaction.commit();
    }
}