package android.example.weatherupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();

        // changed message to cityName
        // BEGINNING OF CHANGES
        String cityName = intent.getStringExtra(MainActivity.CITY_NAME);
        String countryName = intent.getStringExtra(MainActivity.COUNTRY_NAME);
        String temp = intent.getStringExtra(MainActivity.TEMP);
        String humidity = intent.getStringExtra(MainActivity.HUMIDITY);
        String description = intent.getStringExtra(MainActivity.DESCRIPTION);
        String windSpeed = intent.getStringExtra(MainActivity.WIND_SPEED);
        String cloudCoverage = intent.getStringExtra(MainActivity.CLOUD_COVERAGE);
        String pressure = intent.getStringExtra(MainActivity.PRESSURE);


        TextView tvCityName = (TextView) findViewById(R.id.tvCityName);
        TextView tvCountryName = (TextView) findViewById(R.id.tvCountryName);
        TextView tvTemp = (TextView) findViewById(R.id.tvTemp);
        TextView tvHumidity = (TextView) findViewById(R.id.tvHumidity);
        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        TextView tvCloudCover = (TextView) findViewById(R.id.tvCloudCover);
        TextView tvWindSpeed = (TextView) findViewById(R.id.tvWindSpeed);
        TextView tvPressure = (TextView) findViewById(R.id.tvPressure);

        tvCityName.setText(cityName);
        tvCountryName.setText(countryName);
        tvTemp.setText(temp + " *C");
        tvHumidity.setText(humidity + "%");
        tvDescription.setText(description);
        tvCloudCover.setText(cloudCoverage + "%");
        tvWindSpeed.setText(windSpeed + "m/s");
        tvPressure.setText(pressure + "hPa");

    }

    public void onSecondClick(View view) {
        Intent intent = new Intent(this, ActivityResources.class);
        startActivity(intent);
    }
}