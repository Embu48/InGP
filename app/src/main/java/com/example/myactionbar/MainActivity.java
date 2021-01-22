package com.example.myactionbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "channel_01";
    private static final CharSequence CHANNEL_NAME = "Next Race";
    private AlarmReceiver alarmReceiver;
    private TextView tvOnceDate;
    private TextView tvOnceTime;
    private String edtOnceMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inisiasi view untuk one time alarm
        Button btnSetOnce = findViewById(R.id.btn_set_once_alarm);
        edtOnceMessage = "GP QATAR, Losail International Circuit";
        // Listener one time alarm
        btnSetOnce.setOnClickListener(this);
        alarmReceiver = new AlarmReceiver();
    }
    private final static String DATE_PICKER_TAG = "DatePicker";
    private final static String TIME_PICKER_ONCE_TAG = "TimePickerOnce";
    private final static String TIME_PICKER_REPEAT_TAG = "TimePickerRepeat";
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_set_once_alarm) {
            String onceDate = "2021-03-28";
            String onceTime = "00:00";
            String onceMessage = edtOnceMessage;
            alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                    onceDate,
                    onceTime,
                    onceMessage);
        }
    }
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {
        // Siapkan date formatter-nya terlebih dahulu
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 03, 28);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        // Set text dari textview once
        tvOnceDate.setText(dateFormat.format(calendar.getTime()));
    }
    @Override
    public void onDialogTimeSet(String tag, int hourOfDay, int minute) {
        // Siapkan time formatter-nya terlebih dahulu
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        // Set text dari textview berdasarkan tag
        switch (tag) {
            case TIME_PICKER_ONCE_TAG:
                tvOnceTime.setText(dateFormat.format(calendar.getTime()));
                break;
            default:
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuA:
                Intent j = new Intent(this,TesNav.class);
                startActivity(j);
                return true;
            default:
                return true;
        }
    }

    public void sendNotification(View view) {
        Intent intent = new Intent(this,TesNav.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notifications_white_48px)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_white_48px))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(getResources().getString(R.string.content_text))
                .setSubText(getResources().getString(R.string.subtext))
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_NAME.toString());
            mBuilder.setChannelId(CHANNEL_ID);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }
        Notification notification = mBuilder.build();
        if (mNotificationManager != null) {
            mNotificationManager.notify(NOTIFICATION_ID, notification);
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ingp_logoc)
                .setTitle(R.string.app_name)
                .setMessage("Kamu yakin ingin keluar?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}