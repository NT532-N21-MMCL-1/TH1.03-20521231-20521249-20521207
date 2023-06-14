package iot.baoduy.iotapp.Fragment.Chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import iot.baoduy.iotapp.R;


public class ChartFragment extends Fragment {

    private int currentFragment = 0;
    ImageButton nextChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        nextChart = view.findViewById(R.id.btn_next_chart);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (currentFragment == 0) {
            Fragment tempFragment = new TempChartFragment();
            switchFragment(tempFragment);
            currentFragment += 1;
        }
        nextChart.setOnClickListener(view1 -> {
            if (currentFragment == 1) {
                Fragment humidityFragment = new HumidityChartFragment();
                switchFragment(humidityFragment);
                currentFragment += 1;
            } else if (currentFragment == 2) {
                Fragment lightFragment = new LightChartFragment();
                switchFragment(lightFragment);
                currentFragment -= 2;

            } else if (currentFragment == 0) {
                Fragment tempFragment = new TempChartFragment();
                switchFragment(tempFragment);
                currentFragment += 1;
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.switch_chart, fragment);
        fragmentTransaction.commit();
    }

}