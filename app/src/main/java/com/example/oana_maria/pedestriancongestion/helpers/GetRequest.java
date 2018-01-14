package com.example.oana_maria.pedestriancongestion.helpers;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Oana-Maria on 13/01/2018.
 */

public class GetRequest {

    OkHttpClient client = new OkHttpClient();

    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
