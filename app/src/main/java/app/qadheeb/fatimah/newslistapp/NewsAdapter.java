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
    public NewsAdapter(Context context, List<NewsObjects> bookList) {
        super(context, 0, bookList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View bookListView = convertView;
        if (bookListView == null) {
            bookListView = LayoutInflater.from(getContext()).inflate(R.layout.news_row, parent, false);
        }

        NewsObjects currantBookList = getItem(position);

        TextView articleSection = (TextView) bookListView.findViewById(R.id.article_section);
        TextView AuthorName = (TextView) bookListView.findViewById(R.id.author_name);
        TextView publishDate = (TextView) bookListView.findViewById(R.id.date_publish);
        TextView articleTitle = (TextView) bookListView.findViewById(R.id.article_title);

        articleTitle.setText(currantBookList.getArticleTitle());
        AuthorName.setText(currantBookList.getAuthorName());
        articleSection.setText(currantBookList.getArticleSection());
        publishDate.setText(currantBookList.getDatePublish());


        return bookListView;
    }
}
