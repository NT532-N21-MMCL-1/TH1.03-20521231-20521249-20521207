package iot.baoduy.iotapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import iot.baoduy.iotapp.Fragment.AI.AIFragment;
import iot.baoduy.iotapp.Fragment.Chart.ChartFragment;
import iot.baoduy.iotapp.Fragment.Dashboard.DashBoardFragment;
import iot.baoduy.iotapp.Fragment.Log.LogFragment;
import iot.baoduy.iotapp.Fragment.Main.MainFragment;

import iot.baoduy.iotapp.R;
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

    }

    private void replaceFragment(Fragment fragment) {
        // load fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.relative_bottom_nav, fragment);
        fragmentTransaction.commit();
    }
}