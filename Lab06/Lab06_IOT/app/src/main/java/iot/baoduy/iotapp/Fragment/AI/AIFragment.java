package iot.baoduy.iotapp.Fragment.AI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import iot.baoduy.iotapp.Adapter.ApiService;
import iot.baoduy.iotapp.ApiRetrofit;
import iot.baoduy.iotapp.WeatherRequest;
import iot.baoduy.iotapp.R;
import iot.baoduy.iotapp.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AIFragment extends Fragment {

    EditText temperature, humidity;
    TextView windText;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a_i, container, false);

        temperature = view.findViewById(R.id.et_temp);
        humidity = view.findViewById(R.id.et_humi);
        windText = view.findViewById(R.id.windSpeedTextView);
        fab = view.findViewById(R.id.floatingActionButton);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float temp = Float.parseFloat(temperature.getText().toString());
                float humi = Float.parseFloat(humidity.getText().toString());

                WeatherRequest request = new WeatherRequest(temp, humi);

                // Gửi request qua Retrofit
                ApiService apiService = ApiRetrofit.getClient().create(ApiService.class);
                Call<WeatherResponse> call = apiService.getWeatherData(request);
                call.enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                        if (response.isSuccessful()) {
                            WeatherResponse weatherResponse = response.body();
                            float windSpeed = weatherResponse.getWindspeed();
                            windText.setText(String.valueOf(windSpeed + " km/h"));
                        } else {
                            // Xử lý lỗi khi response không thành công
                            Log.d("API AI", "RESPONSE FAIL");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                        // Xử lý lỗi khi gửi request thất bại
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("API AI", t.getMessage());
                    }
                });

            }
        });


        return view;
    }
}