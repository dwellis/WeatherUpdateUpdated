package android.example.weatherupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText etCity, etCountry;
    TextView tvResult;

    // BEGINNING OF CHANGES
    public static final String COUNTRY_NAME = "android.example.com.activities.extra.COUNTRY_NAME";
    public static final String TEMP = "android.example.com.activities.extra.TEMP";
    public static final String HUMIDITY = "android.example.com.activities.extra.HUMIDITY";
    public static final String DESCRIPTION = "android.example.com.activities.extra.DESCRIPTION";
    public static final String WIND_SPEED = "android.example.com.activities.extra.WIND_SPEED";
    public static final String CLOUD_COVERAGE = "android.example.com.activities.extra.CLOUD_COVERAGE";
    public static final String PRESSURE = "android.example.com.activities.extra.PRESSURE";


    // END OF CHANGES



    public static final String CITY_NAME = "android.example.com.activities.extra.CITY_NAME";
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "1b79bf73d3b4001b6fbc49847ec98423"; //last digit is actually 3
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);
        tvResult = findViewById(R.id.tvResult);
    }

    public void getWeatherDetails(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        String country = etCountry.getText().toString().trim();

        if (city.equals("")) {
            tvResult.setText("Must choose a city");
        } else {
            if(!country.equals("")) {
                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
            } else {
                tempUrl = url + "?q=" + city + "&appid=" + appid;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response", response);
                    String output = "";

                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");

                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        float pressure = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");

                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");
                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                        String clouds = jsonObjectClouds.getString("all");

                        JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");
                        String cityName = jsonResponse.getString("name");


                        //tvResult.setTextColor(Color.rgb(68, 134, 199));

                        output += "Current Weather of " + cityName + " (" + countryName + ")"
                                + "\n Temp: " + df.format(temp) + " *C"
                                + "\n Feels Like: " + df.format(feelsLike) + " *C"
                                + "\n Humidity: " + humidity + "%"
                                + "\n Description: " + description
                                + "\n Wind Speed: " + wind + "m/s (meters per second)"
                                + "\n Cloudiness: " + clouds + "%"
                                + "\n Pressure: " + pressure + " hPa";

                        //tvResult.setText(output);
                        tvResult.setText("Loading");
                        // CHANGED BELOW
                        //intent.putExtra(CITY_NAME, output);

                        // BEGIN CHANGES
                        intent.putExtra(CITY_NAME, cityName);
                        intent.putExtra(COUNTRY_NAME, countryName);
                        intent.putExtra(TEMP, df.format(temp));
                        intent.putExtra(HUMIDITY, String.valueOf(humidity));
                        intent.putExtra(DESCRIPTION, description);
                        intent.putExtra(WIND_SPEED, wind);
                        intent.putExtra(CLOUD_COVERAGE, clouds);
                        intent.putExtra(PRESSURE, String.valueOf(pressure));
                        // END CHANGES




                        startActivity(intent);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }


    }
}