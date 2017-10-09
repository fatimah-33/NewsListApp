package app.qadheeb.fatimah.newslistapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fatimah on 10/8/17.
 */

public class NewsLoader extends android.support.v4.content.AsyncTaskLoader<List<NewsObjects>> {
    private String apiLink = "https://content.guardianapis.com/search?api-key=54d024d3-8a07-43e0-a2f3-05a6efe6a6d6";
    private List<NewsObjects> newsList;
    private String articleTitle;
    private String articleSection;
    private String publishDate;
    private HttpURLConnection httpURLConnection;
    private URL url;


    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public List<NewsObjects> loadInBackground() {
        httpURLConnection = null;
        try {
            url = new URL(apiLink);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String inputText=bufferedReader.readLine();;
            while (inputText != null) {
                stringBuilder.append(inputText);
                inputText=bufferedReader.readLine();
            }
            newsList=new ArrayList<>();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONObject getRoot = jsonObject.getJSONObject("response");
            JSONArray jsonArray = getRoot.getJSONArray("results");
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject news = jsonArray.getJSONObject(i);
                    articleSection = news.getString("sectionName");
                    String date = news.getString("webPublicationDate");
                    publishDate = date.substring(0, 10);
                    articleTitle = news.getString("webTitle");
                    String uri=news.getString("webUrl");
                    newsList.add(new NewsObjects(articleTitle, articleSection, publishDate));

                }
            }


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @Override
    protected void onStartLoading() {
            forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}



