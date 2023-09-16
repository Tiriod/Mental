package com.example.mental.FunctionUI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Adapter.CalendarAdapter;
import com.example.mental.Adapter.FoodAdapter;
import com.example.mental.Adapter.HeaderAdapter;
import com.example.mental.Definition.FoodItem;
import com.example.mental.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FoodActivity extends AppCompatActivity {
    private List<String> daysOfWeek;
    private List<String> weekDates;
    private RecyclerView recyclerView;
    // 早中晚餐视图
    private RecyclerView breakfastRecyclerView;
    private RecyclerView lunchRecyclerView;
    private RecyclerView dinnerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        // 初始化头部标签
        RecyclerView headerRecyclerView = findViewById(R.id.FoodHeaderRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        headerRecyclerView.setLayoutManager(layoutManager);
        String headerText = "心身滋养";
        HeaderAdapter headerAdapter = new HeaderAdapter(this, headerText);
        headerRecyclerView.setAdapter(headerAdapter);


        // 初始化日历组件
        recyclerView = findViewById(R.id.calendarRecyclerView);
        GridLayoutManager calendarLayoutManager = new GridLayoutManager(this, 7, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(calendarLayoutManager);

        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());

        // 计算本周的日期
        daysOfWeek = new ArrayList<>();
        weekDates = new ArrayList<>();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = 0; i < 7; i++) {
            String dayOfWeek = getDayOfWeekName(calendar.get(Calendar.DAY_OF_WEEK));
            daysOfWeek.add(dayOfWeek);

            String date = dateFormat.format(calendar.getTime());
            weekDates.add(date);

            calendar.add(Calendar.DATE, 1);
        }

        // 初始化日历适配器并设置
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysOfWeek, weekDates, currentDate);
        recyclerView.setAdapter(calendarAdapter);

        // 营养食谱卡片
        // 初始化早餐RecyclerView
        breakfastRecyclerView = findViewById(R.id.breakfastView);
        LinearLayoutManager breakfastLayoutManager = new LinearLayoutManager(this);
        breakfastRecyclerView.setLayoutManager(breakfastLayoutManager);
        FoodAdapter breakfastAdapter = new FoodAdapter(new ArrayList<>());
        breakfastRecyclerView.setAdapter(breakfastAdapter);


        // 初始化午餐RecyclerView
        lunchRecyclerView = findViewById(R.id.lunchView);
        LinearLayoutManager lunchLayoutManager = new LinearLayoutManager(this);
        lunchRecyclerView.setLayoutManager(lunchLayoutManager);
        FoodAdapter lunchAdapter = new FoodAdapter(new ArrayList<>());
        lunchRecyclerView.setAdapter(lunchAdapter);


        // 初始化晚餐RecyclerView
        dinnerRecyclerView = findViewById(R.id.dinnerView);
        LinearLayoutManager dinnerLayoutManager = new LinearLayoutManager(this);
        dinnerRecyclerView.setLayoutManager(dinnerLayoutManager);
        FoodAdapter dinnerAdapter = new FoodAdapter(new ArrayList<>());
        dinnerRecyclerView.setAdapter(dinnerAdapter);

        // 获取食物数据
        foodDataFetchDataForCategory("breakfast"); // 获取早餐数据
        foodDataFetchDataForCategory("lunch");     // 获取午餐数据
        foodDataFetchDataForCategory("dinner");    // 获取晚餐数据

    }

    // 获取周次时间
    private String getDayOfWeekName(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "周日";
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
            default:
                return "";
        }
    }

    // 使用API请求数据渲染内容
    private void foodDataFetchDataForCategory(String category) {
        // 创建一个 OkHttp 客户端
        OkHttpClient client = new OkHttpClient();

        // 构建 POST 数据
        JSONObject postData = new JSONObject();
        try {
            postData.put("category", category);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 创建请求
        Request request = new Request.Builder()
                .url("https://www.fastapi.com/your_api_endpoint_here") // 替换为实际的 API 地址
                .post(RequestBody.create(MediaType.parse("application/json"), postData.toString()))
                .build();

        // 异步执行请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // 请求成功，处理响应数据
                    String responseBody = response.body().string();
                    try {
                        // 解析响应数据
                        JSONArray foodItemsArray = new JSONArray(responseBody);
                        List<FoodItem> foodItems = new ArrayList<>();

                        for (int i = 0; i < foodItemsArray.length(); i++) {
                            JSONObject foodItemObject = foodItemsArray.getJSONObject(i);
                            String name = foodItemObject.getString("name");
                            String quantity = foodItemObject.getString("quantity");
                            String calories = foodItemObject.getString("calories");
                            // 可能还需要解析其他属性

                            // 创建 FoodItem 对象并添加到列表
                            FoodItem foodItem = new FoodItem(R.drawable.image_food_rice, name, quantity, calories);
                            foodItems.add(foodItem);
                        }

                        // 更新 UI，需要在主线程中执行
                        runOnUiThread(() -> {
                            // 根据食物类别更新适配器
                            updateFoodAdapter(foodItems);
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 请求失败，处理错误
                    runOnUiThread(() -> {
                        // 根据需要处理请求失败的情况
                        String defaultData = "[\n" +
                                "    {\n" +
                                "        \"imageResource\": image_food_fried_potato_chips,\n" +
                                "        \"name\": \"油条\",\n" +
                                "        \"quantity\": \"100\",\n" +
                                "        \"calories\": \"386\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "        \"imageResource\": image_food_fried_potato_chips,\n" +
                                "        \"name\": \"油炸土豆片\",\n" +
                                "        \"quantity\": \"100\",\n" +
                                "        \"calories\": \"612\"\n" +
                                "    },\n" +
                                "    {\n" +
                                "        \"imageResource\": image_food_fried_potato_chips,\n" +
                                "        \"name\": \"食物1\",\n" +
                                "        \"quantity\": \"150\",\n" +
                                "        \"calories\": \"300\"\n" +
                                "    }\n" +
                                "]\n";
                        try {
                            JSONArray foodItemsArray = new JSONArray(defaultData);
                            List<FoodItem> foodItems = new ArrayList<>();

                            for (int i = 0; i < foodItemsArray.length(); i++) {
                                JSONObject foodItemObject = foodItemsArray.getJSONObject(i);
                                String imageResourceStr = foodItemObject.getString("imageResource"); // 注意这里是字符串
                                int imageResource = getResources().getIdentifier(imageResourceStr, "drawable", getPackageName());
                                String name = foodItemObject.getString("name");
                                String quantity = foodItemObject.getString("quantity");
                                String calories = foodItemObject.getString("calories");
                                // 可能还需要解析其他属性

                                // 创建 FoodItem 对象并添加到列表
                                FoodItem foodItem = new FoodItem(imageResource, name, quantity + "g", calories + "千焦耳");
                                foodItems.add(foodItem);
                            }

                            // 更新 UI，需要在主线程中执行
                            runOnUiThread(() -> {
                                // 根据食物类别更新适配器，这里使用默认数据
                                updateFoodAdapter(foodItems);
                            });
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                // 请求发生异常，处理异常
                runOnUiThread(() -> {
                    // 根据需要处理请求异常的情况
                    String defaultData = "[\n" +
                            "    {\n" +
                            "        \"imageResource\": image_food_rice,\n" +
                            "        \"name\": \"油条\",\n" +
                            "        \"quantity\": \"100\",\n" +
                            "        \"calories\": \"386\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"imageResource\": image_food_fried_potato_chips,\n" +
                            "        \"name\": \"油炸土豆片\",\n" +
                            "        \"quantity\": \"100\",\n" +
                            "        \"calories\": \"612\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"imageResource\": image_food_fried_potato_chips,\n" +
                            "        \"name\": \"食物1\",\n" +
                            "        \"quantity\": \"150\",\n" +
                            "        \"calories\": \"300\"\n" +
                            "    }\n" +
                            "]\n";
                    try {
                        // 解析 JSON 数据
                        JSONArray foodItemsArray = new JSONArray(defaultData);
                        List<FoodItem> foodItems = new ArrayList<>();

                        for (int i = 0; i < foodItemsArray.length(); i++) {
                            JSONObject foodItemObject = foodItemsArray.getJSONObject(i);
                            String imageResourceStr = foodItemObject.getString("imageResource"); // 注意这里是字符串
                            int imageResource = getResources().getIdentifier(imageResourceStr, "drawable", getPackageName());
                            String name = foodItemObject.getString("name");
                            String quantity = foodItemObject.getString("quantity");
                            String calories = foodItemObject.getString("calories");

                            // 创建 FoodItem 对象并添加到列表
                            FoodItem foodItem = new FoodItem(imageResource, name, quantity + "g", calories + "千焦耳");
                            foodItems.add(foodItem);
                        }

                        // 更新 UI，需要在主线程中执行
                        runOnUiThread(() -> {
                            // 根据食物类别更新适配器，这里使用默认数据
                            updateFoodAdapter(foodItems);
                        });
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                });
            }
        });
    }

    // 在 FoodActivity 类中添加以下方法
    public void updateFoodAdapter(List<FoodItem> foodItems) {
        // 根据食物类别更新适配器
        runOnUiThread(() -> {
            if (breakfastRecyclerView != null) {
                FoodAdapter breakfastAdapter = (FoodAdapter) breakfastRecyclerView.getAdapter();
                if (breakfastAdapter != null) {
                    breakfastAdapter.updateFoodItems(foodItems);
                }
            }
            if (lunchRecyclerView != null) {
                FoodAdapter lunchAdapter = (FoodAdapter) lunchRecyclerView.getAdapter();
                if (lunchAdapter != null) {
                    lunchAdapter.updateFoodItems(foodItems);
                }
            }
            if (dinnerRecyclerView != null) {
                FoodAdapter dinnerAdapter = (FoodAdapter) dinnerRecyclerView.getAdapter();
                if (dinnerAdapter != null) {
                    dinnerAdapter.updateFoodItems(foodItems);
                }
            }
        });
    }


}