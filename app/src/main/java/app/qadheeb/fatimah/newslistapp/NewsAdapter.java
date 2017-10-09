package app.qadheeb.fatimah.newslistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fatimah on 9/16/17.
 */

public class NewsAdapter extends ArrayAdapter<NewsObjects> {
    public NewsAdapter(Context context, List<NewsObjects> newsList) {
        super(context, 0, newsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newsListView = convertView;
        if (newsListView == null) {
            newsListView = LayoutInflater.from(getContext()).inflate(R.layout.news_row, parent, false);
        }

        NewsObjects currantNewsList = getItem(position);

        TextView articleSection = (TextView) newsListView.findViewById(R.id.article_section);
        TextView publishDate = (TextView) newsListView.findViewById(R.id.date_publish);
        TextView articleTitle = (TextView) newsListView.findViewById(R.id.article_title);

        articleTitle.setText(currantNewsList.getArticleTitle());
        articleSection.setText(currantNewsList.getArticleSection());
        publishDate.setText(currantNewsList.getDatePublish());



        return newsListView;
    }


}
