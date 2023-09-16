package com.example.mental.Utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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
}
