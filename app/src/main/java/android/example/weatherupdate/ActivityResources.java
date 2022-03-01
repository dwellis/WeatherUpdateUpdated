package android.example.weatherupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityResources extends AppCompatActivity {

        TextView batterinfo;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_resources);

            batterinfo = (TextView)findViewById(R.id.battery);

            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Context context = this;

            Intent batteryStatus = context.registerReceiver(null, ifilter);

            // Are we charging / charged?
            int status = batteryStatus.getIntExtra(android.os.BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == android.os.BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == android.os.BatteryManager.BATTERY_STATUS_FULL;

            // How are we charging?
            int chargePlug = batteryStatus.getIntExtra(android.os.BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == android.os.BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == android.os.BatteryManager.BATTERY_PLUGGED_AC;
        }

        public void checkBattery(View view) {
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Context context = this;

            Intent batteryStatus = context.registerReceiver(null, ifilter);

            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            int batteryPct = level*100 / scale;

            String msg = "Battery charge is " + batteryPct + "%.";

            int status = batteryStatus.getIntExtra(android.os.BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == android.os.BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == android.os.BatteryManager.BATTERY_STATUS_FULL;

            String msgCharging;
            if(isCharging) {
                msgCharging = "The battery is charging.";
            } else {
                msgCharging = "The battery is not charging.";
            }

            batterinfo = (TextView) findViewById(R.id.battery);
            batterinfo.setText(msg);

            batterinfo = (TextView) findViewById(R.id.charging);
            batterinfo.setText(msgCharging);
        }
    }