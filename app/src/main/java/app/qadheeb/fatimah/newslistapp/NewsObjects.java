package app.qadheeb.fatimah.newslistapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fatimah on 10/4/17.
 */

public class NewsObjects {
    private String articleTitle;
    private String articleSection;
    private String datePublish;
    private String urlWeb;

    public NewsObjects(String articleTitle, String articleSection, String datePublish, String urlWeb) {
        this.articleTitle = articleTitle;
        this.articleSection = articleSection;
        this.datePublish = datePublish;
        this.urlWeb = urlWeb;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleSection() {
        return articleSection;
    }

    public String getDatePublish() {
        return datePublish;
    }

    public String getUrlWeb() {
        return urlWeb;
    }

}
