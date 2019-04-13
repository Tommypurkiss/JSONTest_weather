package com.tommypurkissdev.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    public TextView tvTemperature;
    public TextView tvLocation;
    public TextView tvDescription;
    public RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemperature = findViewById(R.id.tv_temp);
        tvLocation = findViewById(R.id.tv_location);
        tvDescription = findViewById(R.id.tv_description);

        mRequestQueue = Volley.newRequestQueue(this);



        getWeather();

    }


    private void getWeather() {

        String url = "https://api.openweathermap.org/data/2.5/find?q=London,uk&units=metric&appid=7b35bff27c44e02a0ed4347c65c2ffa8";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    //list array
                    JSONArray jsonArray = response.getJSONArray("list");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);


                    //main object
                    JSONObject main = jsonObject.getJSONObject("main");


                    //weather array inside list array
                    JSONArray jsonArray1 = jsonObject.getJSONArray("weather");
                    JSONObject jsonObject1 = jsonArray1.getJSONObject(0);


                    //temp - works
                    //main get string calls temp
                    String temp = main.getString("temp");
                    tvTemperature.setText(temp);


                    //city - works
                    //json object has name
                    String city = jsonObject.getString("name");
                    tvLocation.setText(city);

                    //description - works
                    //json object1 get string calls description from array
                    String desc = jsonObject1.getString("description");
                    tvDescription.setText(desc);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

}


/* MARK: - SPARE CODE


*/