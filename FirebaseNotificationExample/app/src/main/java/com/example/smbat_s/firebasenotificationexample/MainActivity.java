package com.example.smbat_s.firebasenotificationexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("Token");


        // TODO when log out unsubscribe from topic
        // Example: FirebaseMessaging.getInstance().unsubscribeFromTopic("Topic name");

    }

}
