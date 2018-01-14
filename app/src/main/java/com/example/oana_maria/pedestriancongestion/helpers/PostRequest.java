package com.example.oana_maria.pedestriancongestion.helpers;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Oana-Maria on 13/01/2018.
 */

public class PostRequest {

    public static final MediaType JSON = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
    OkHttpClient client = new OkHttpClient();

    public String doPostRequest(String url, String json) throws IOException {

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();


        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
