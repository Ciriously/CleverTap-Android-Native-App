package com.example.clever_app;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

/**
 * Content class for holding Display Unit Content Data
 */
public class CleverTapDisplayUnitContent implements Parcelable {
    private final String title;
    private final String titleColor;
    private final String message;
    private final String messageColor;
    private final String media;
    private final String contentType;
    private final String posterUrl;
    private final String actionUrl;
    private final String icon;

    protected CleverTapDisplayUnitContent(Parcel in) {
        title = in.readString();
        titleColor = in.readString();
        message = in.readString();
        messageColor = in.readString();
        media = in.readString();
        contentType = in.readString();
        posterUrl = in.readString();
        actionUrl = in.readString();
        icon = in.readString();
    }

    public static final Creator<CleverTapDisplayUnitContent> CREATOR = new Creator<CleverTapDisplayUnitContent>() {
        @Override
        public CleverTapDisplayUnitContent createFromParcel(Parcel in) {
            return new CleverTapDisplayUnitContent(in);
        }

        @Override
        public CleverTapDisplayUnitContent[] newArray(int size) {
            return new CleverTapDisplayUnitContent[size];
        }
    };


    // GETTER-SETTER
    public String getTitle() {
        return title;
    }
    public String getMessage() {
        return message;
    }
    public String getMedia() {
        return media;
    }
    public String getActionUrl() {
        return actionUrl;
    }
    public String getIcon() {
        return icon;
    }
    public String getTitleColor() {
        return titleColor;
    }
    public String getMessageColor() {
        return messageColor;
    }
    public String getPosterUrl() {
        return posterUrl;
    }
    public String getContentType() {
        return contentType;
    }
    public boolean mediaIsImage() {
        return contentType != null && this.media != null && contentType.startsWith("image") && !contentType.equals("image/gif");
    }
    public boolean mediaIsGIF() {
        return contentType != null && this.media != null && contentType.equals("image/gif");
    }
    public boolean mediaIsVideo() {
        return contentType != null && this.media != null && contentType.startsWith("video");
    }
    public boolean mediaIsAudio() {
        return contentType != null && this.media != null && contentType.startsWith("audio");
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(titleColor);
        dest.writeString(message);
        dest.writeString(messageColor);
        dest.writeString(media);
        dest.writeString(contentType);
        dest.writeString(posterUrl);
        dest.writeString(actionUrl);
        dest.writeString(icon);
    }
}