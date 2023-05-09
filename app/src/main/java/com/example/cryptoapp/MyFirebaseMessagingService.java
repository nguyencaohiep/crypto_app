package com.example.cryptoapp;

import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.cryptoapp.dao.Api;
import com.example.cryptoapp.dao.ResponeToken;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        System.out.println("token:" + s);
        sendRegistrationTokenToServer(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        getFirebaseMessage(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    public void getFirebaseMessage(String title, String msg) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myFirebaseChannel")
                    .setSmallIcon(R.drawable.bell)
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setAutoCancel(true);

            NotificationManagerCompat manager = NotificationManagerCompat.from(this);
            manager.notify(101, builder.build());
        }

    }

    public void sendRegistrationTokenToServer(String token) {
        Api.api.saveToken(token).enqueue(new Callback<ResponeToken>() {
            @Override
            public void onResponse(Call<ResponeToken> call, Response<ResponeToken> response) {

            }

            @Override
            public void onFailure(Call<ResponeToken> call, Throwable t) {

            }
        });
    }
}
