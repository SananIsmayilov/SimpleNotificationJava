package com.example.sananismayilov.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button button;
public NotificationCompat.Builder builder;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }


    public void showNotification(){
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelid = "channelid";
            String channelname = "channelname";
            String channeldescription = "channeldescription";
            int channelimportantly = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = manager.getNotificationChannel(channelid);

            if(channel == null){
                channel = new NotificationChannel(channelid,channelname,channelimportantly);
                channel.setDescription(channeldescription);
                manager.createNotificationChannel(channel);
            }
            builder = new NotificationCompat.Builder(this,channelid);
            builder.setAutoCancel(true);
            builder.setContentText("Text");
            builder.setContentTitle("Tittle");
            builder.setSmallIcon(R.drawable.image);
            builder.setContentIntent(pendingIntent);


        }else {

            builder = new NotificationCompat.Builder(this);
            builder.setAutoCancel(true);
            builder.setContentText("Text");
            builder.setContentTitle("Tittle");
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.drawable.image);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }
        manager.notify(1,builder.build());
    }
}