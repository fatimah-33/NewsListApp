package app.qadheeb.fatimah.newslistapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fatimah on 10/4/17.
 */

public class NewsObjects implements Parcelable {
    private String articleTitle;
    private String articleSection;
    private String datePublish;

    public NewsObjects(String articleTitle, String articleSection, String datePublish) {
        this.articleTitle = articleTitle;
        this.articleSection = articleSection;
        this.datePublish = datePublish;
    }

    protected NewsObjects(Parcel in) {
        articleTitle = in.readString();
        articleSection = in.readString();
        datePublish = in.readString();
    }

    public static final Creator<NewsObjects> CREATOR = new Creator<NewsObjects>() {
        @Override
        public NewsObjects createFromParcel(Parcel in) {
            return new NewsObjects(in);
        }

        @Override
        public NewsObjects[] newArray(int size) {
            return new NewsObjects[size];
        }
    };

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleSection() {
        return articleSection;
    }

    public String getDatePublish() {
        return datePublish;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(articleTitle);
        parcel.writeString(articleSection);
        parcel.writeString(datePublish);
    }
}
