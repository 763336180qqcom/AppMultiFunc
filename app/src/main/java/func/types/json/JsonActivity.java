package func.types.json;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;

import func.types.DividerItemDecoration;
import func.types.R;

/**
 * author-ZKC
 * create on 2016-08-30-22-19.
 */
public class JsonActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private JsonListAdapter mJsonListAdapter;
    private Context mContext;
    private static StringBuilder Builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        mContext = this;
        getSupportActionBar().setTitle(R.string.json);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_json);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        getJsonData();
    }

    private void iniData(StringBuilder string) {
        Tg_Root tgRoot = new Tg_Root();
        List<Tg> tgs = new ArrayList<>();
        try {
            JSONObject rootObj = new JSONObject(string.toString());
            tgRoot.setStatus(rootObj.getBoolean("status"));
            JSONArray ja = rootObj.getJSONArray("tngou");
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                Tg tg = new Tg();
                tg.setName(jo.getString("name"));
                tg.setDescription(jo.getString("description"));
                tgs.add(tg);
            }
            tgRoot.setTgs(tgs);
            mJsonListAdapter = new JsonListAdapter(mContext, tgRoot.getTgs());
            mRecyclerView.setAdapter(mJsonListAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getJsonData() {
        new Thread() {
            @Override
            public void run() {
                Builder = new StringBuilder();
                try {
                    String ad = "http://www.tngou.net/api/top/classify";
                    URL url = new URL(ad);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                /*    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");*/
                    connection.setConnectTimeout(6000);
                    connection.setReadTimeout(10000);
                    connection.setUseCaches(true);
                    connection.connect();
                  /*  PrintWriter p=new PrintWriter(connection.getOutputStream());
                    p.print("string");
                    p.flush();
                    p.close();*/
                    if (connection.getResponseCode() == 200) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String content;
                        while ((content = br.readLine()) != null) {
                            Builder.append(content);
                        }
                        br.close();
                    }
                    Handler handler = new Handler(getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iniData(Builder);
                        }
                    });
                } catch (MalformedInputException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
