package app.qadheeb.fatimah.newslistapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsObjects>> {
    private List<NewsObjects> newsArrayList;
    NewsAdapter newsAdapter;
    LoaderManager loaderManager;
    private ListView listView;
    private static final String MY_LIST_KEY = "newsList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsArrayList = new ArrayList<>();
        listView=(ListView) findViewById(R.id.list_news);
        loaderManager = getSupportLoaderManager();
        if (savedInstanceState != null && savedInstanceState.containsKey("newsList")) {
            newsArrayList = savedInstanceState.getParcelableArrayList(MY_LIST_KEY);
            listView=(ListView) findViewById(R.id.list_news);
            newsAdapter = new NewsAdapter(MainActivity.this, newsArrayList);
            listView.setAdapter(newsAdapter);
        }
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<NewsObjects>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this);

    }

    @Override
    public void onLoadFinished(Loader<List<NewsObjects>> loader, List<NewsObjects> data) {
            listView = (ListView) findViewById(R.id.list_news);
            newsAdapter = new NewsAdapter(MainActivity.this, data);
            listView.setAdapter(newsAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsObjects>> loader) {

    }
}
