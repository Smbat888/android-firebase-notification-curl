package com.example.smbat_s.firebasenotificationclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String SERVER_KEY = "AAAAE3vzC34:APA91bHyysHVQbtKHeOG3iHDGChU2kNHLJCH0CR1h9Z2YaK9kYpEADqCNjF7JX31cOEB8QoNmT3h_gSqQiM8P5qOenwn-HrNcEWMmviB1iqRNdKjy7WceiVaqGUGsbdhouR5dbjSfPFL";
    private static final String ID_TOKEN = "dHnsXislNQI:APA91bFpswyS_dC6iLlQH7DfrSUa9BWrIwAtyo_a-KvGWN6HQlCtPPWZns4sz7MmWGcCdhcrjT2B3xfd3nSmKUlYH5P1e5VztvfLdvfhiUlhdLr7QCuqeEK3yd3j2AVwCcV-ex__5IZ9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = (Button) findViewById(R.id.send_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHttpRequest();
            }
        });

    }

    private void sendHttpRequest() {
        Gson gson = new Gson();
        NotificationData data = new NotificationData();
        data.setTitle("You have a new order!!!");
        PostRequestData postRequestData = new PostRequestData();
//        postRequestData.setTo(ID_TOKEN);
        postRequestData.setTo("/topics/Token");
        postRequestData.setData(data);
        String json = gson.toJson(postRequestData);
        String url = "https://fcm.googleapis.com/fcm/send";

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "key=" + SERVER_KEY)
                .post(body)
                .build();

        Callback responseCallBack = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("Fail Message", "fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("response", response.toString());
            }

        };
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(responseCallBack);
    }
}
