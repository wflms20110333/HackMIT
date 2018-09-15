package com.example.elizabethzou.hackmit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    Log.i("didnotpasstest", "rip");
                    return;
                }
                Log.i("passedTest", "hey");
                cursor = getContentResolver().query(CalendarContract.Events.CONTENT_URI, null, null, null, null);
                while (cursor.moveToNext()) {
                    if (cursor != null) {
                        int id_1 = cursor.getColumnIndex(CalendarContract.Events._ID);
                        int id_2 = cursor.getColumnIndex(CalendarContract.Events.TITLE);
                        int id_3 = cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION);
                        int id_4 = cursor.getColumnIndex(CalendarContract.Events.EVENT_LOCATION);
                        int id_5 = cursor.getColumnIndex(CalendarContract.Events.DTSTART);
                        int id_6 = cursor.getColumnIndex(CalendarContract.Events.DTEND);
                        String idValue = cursor.getColumnName(id_1);
                        String titleValue = cursor.getString(id_2);
                        String descriptionValue = cursor.getString(id_3);
                        String eventValue = cursor.getString(id_4);
                        String startValue = cursor.getString(id_5);
                        String endValue = cursor.getString(id_6);
                        Toast.makeText(this, idValue + ", " + titleValue + ", " + descriptionValue + ", " + eventValue, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Event is not present.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}