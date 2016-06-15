package com.example.weather.util;

import java.net.URLEncoder;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weather.content.Config;




public class WeatherUtil {
	
	public static void testStringRequest(final Context context) {
		RequestQueue requestQueue =Volley.newRequestQueue(context);
		try {
			String name = URLEncoder.encode("π„÷›", "utf8");
			String url = "http://op.juhe.cn/onebox/weather/query?cityname="+name+"&key=15b3860417a0875de210d562b0be2ce3";
			Listener<String> listener = new Listener<String>() {
				@Override
				public void onResponse(String arg0) {
					Intent intent = new Intent(Config.UPDATE_WEATHER);
					intent.putExtra("weather", arg0);
					context.sendBroadcast(intent);
				}
			};
			ErrorListener errorListener = new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError arg0) {
					Log.d("Tag", arg0.getMessage());
				}
			};
			StringRequest request = new StringRequest(url,listener ,errorListener );
			requestQueue.add(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
