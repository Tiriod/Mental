package com.example.mental.Utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiRequest {

    // 接口回调，用于处理请求结果
    public interface ApiResponseListener {
        void onSuccess(String response);

        void onError(String error);
    }

    // 异步任务来执行 API 请求
    public static class ApiRequestTask extends AsyncTask<String, Void, String> {
        private ApiResponseListener listener;
        private OkHttpClient client;

        public ApiRequestTask(ApiResponseListener listener) {
            this.listener = listener;
            this.client = new OkHttpClient();
        }

        @Override
        protected String doInBackground(String... urls) {
            String url = urls[0];
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return response.body().string();
                } else {
                    return null;
                }
            } catch (IOException e) {
                Log.e("ApiRequest", "Error: " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                listener.onSuccess(result);
            } else {
                listener.onError("API请求失败");
            }
        }
    }

    // 使用示例
    public static void makeApiRequest(String apiUrl, ApiResponseListener listener) {
        ApiRequestTask task = new ApiRequestTask(listener);
        task.execute(apiUrl);
    }

    // Post请求方法
    public static void makePostRequest(String url, String requestBody, ApiResponseListener listener) {
        try {
            // 创建一个 OkHttpClient 实例
            OkHttpClient client = new OkHttpClient();

            // 创建一个 JSON 请求体
            RequestBody postBody = RequestBody.create(MediaType.parse("application/json"), requestBody);

            // 创建一个 POST 请求
            Request request = new Request.Builder()
                    .url(url)
                    .post(postBody)
                    .build();

            // 异步执行请求
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        // 请求成功，获取响应数据
                        String responseData = response.body().string();
                        listener.onSuccess(responseData);
                    } else {
                        // 请求失败，获取错误信息
                        String error = "Error: " + response.code() + " " + response.message();
                        listener.onError(error);
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    // 请求发生异常
                    String error = "Error: " + e.getMessage();
                    listener.onError(error);
                }
            });
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            listener.onError(e.getMessage());
        }
    }

}
