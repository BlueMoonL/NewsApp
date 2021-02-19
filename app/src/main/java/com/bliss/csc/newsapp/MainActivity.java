package com.bliss.csc.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataset = {"1" , "2"};

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        queue = Volley.newRequestQueue(this);

        getNews();
    }

    public void getNews() {

        String url = "http://newsapi.org/v2/top-headlines?country=kr&apiKey=5a67009886af423fae0c11e4cb853f1d";
                //"https://openapi.naver.com/v1/search/news?query=KOSPI&display=5";
                //"http://newsapi.org/v2/top-headlines?country=kr&apiKey=5a67009886af423fae0c11e4cb853f1d";
                // "http://localhost/newsdb.php";





        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);



                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    List<NewsData> news = new ArrayList<>();

                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        Log.d("응답이 왔나요", obj.toString());

                        NewsData newsData = new NewsData();
                        newsData.setTitle(obj.getString("title"));
                        newsData.setUrlToImage(obj.getString("urlToImage"));

                        news.add(newsData);

                        mAdapter = new MyAdapter(news, MainActivity.this);
                        mRecyclerView.setAdapter(mAdapter);
                    }



                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
}