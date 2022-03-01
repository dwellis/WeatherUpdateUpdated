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
        String message =
                intent.getStringExtra(MainActivity.CITY_NAME);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
    }

    public void onSecondClick(View view) {
        Intent intent = new Intent(this, ActivityResources.class);
        startActivity(intent);
    }
}