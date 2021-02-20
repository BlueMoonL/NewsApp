package com.bliss.csc.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

public class NewsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        queue = Volley.newRequestQueue(this);

        getNews();
    }

    public void getNews() {

        String url = "http://newsapi.org/v2/top-headlines?country=kr&apiKey=5a67009886af423fae0c11e4cb853f1d";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray arrayArticles = jsonObject.getJSONArray("articles");

                    List<NewsData> news = new ArrayList<>();

                    for(int i = 0; i < arrayArticles.length(); i++)
                    {
                        JSONObject obj = arrayArticles.getJSONObject(i);

                        Log.d("NEWS", obj.toString());

                        NewsData newsData = new NewsData();
                        newsData.setTitle(obj.getString("title"));
                        newsData.setContent(obj.getString("title"));
                        newsData.setUrlToImage(obj.getString("urlToImage"));

                        news.add(newsData);

                        mAdapter = new MyAdapter(news, NewsActivity.this);
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
                Log.d("onErrorResponse: ", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headerParams = new HashMap<>();
                headerParams.put("User-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
                Log.d("getHeaders headerParams", headerParams.toString());
                return headerParams;
            }
        };

        queue.add(stringRequest);
    }
}