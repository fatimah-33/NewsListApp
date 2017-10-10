package app.qadheeb.fatimah.newslistapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsObjects>> {
    NewsAdapter newsAdapter;
    LoaderManager loaderManager;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkNetworkConnection() == true) {
            listView = (ListView) findViewById(R.id.list_news);
            loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, this);
        } else {
            Toast.makeText(this, " " + getString(R.string.no_network), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public Loader<List<NewsObjects>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this);

    }

    @Override
    public void onLoadFinished(Loader<List<NewsObjects>> loader, List<NewsObjects> data) {
        if (data.isEmpty()) {
            listView = (ListView) findViewById(R.id.list_news);
            newsAdapter = new NewsAdapter(MainActivity.this, data);
            listView.setAdapter(newsAdapter);
            Toast.makeText(this, "" + getString(R.string.no_data), Toast.LENGTH_SHORT).show();
        } else {
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
    }

    @Override
    public void onLoaderReset(Loader<List<NewsObjects>> loader) {
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkStatus = connectivityManager.getActiveNetworkInfo();
        return networkStatus != null && networkStatus.isConnected();

    }
}
