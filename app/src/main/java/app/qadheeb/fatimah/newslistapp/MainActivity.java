package app.qadheeb.fatimah.newslistapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsObjects>> {
    NewsAdapter newsAdapter;
    LoaderManager loaderManager;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_news);
        loaderManager = getSupportLoaderManager();
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String urlWeb = newsAdapter.getItem(i).getUrlWeb();
                Intent openUrl = new Intent(Intent.ACTION_VIEW);
                openUrl.setData(Uri.parse(urlWeb));
                startActivity(openUrl);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<List<NewsObjects>> loader) {

    }
}
