package com.example.naman.task3;



/**
 * Created by naman on 17-Apr-18.
 */
import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {
    private List<Item> repositoryList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private static final String TAG = "MyActivity";
    private MyAdapter mAdapter;
    public static String url = "https://api.github.com/users/google/repos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new MyAdapter(repositoryList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        new GetContacts().execute();
    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonArray = new JSONArray(jsonStr);
                    Log.e(TAG,"extract"+jsonArray.length() + jsonArray);
                    String name = "default";
                    String stars = "0";
                    String forks = "0";
                    String link = "null";
                    Item repository;
                    try{
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject first = (JSONObject) jsonArray.get(i);
                            name = first.optString("name");
                            stars = first.optString("stargazers_count");
                            forks = first.optString("forks");
                            link = first.optString("html_url");
                            Log.e(TAG,name);
                            repository = new Item(name, forks, stars, link);
                            repositoryList.add(repository);
                        }
                    } catch (JSONException e){
                        Log.e(TAG,"exception in parsing" + name);
                    }
                } catch (final JSONException e) {

                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void v){
            mAdapter.notifyDataSetChanged();
        }
    }
}

