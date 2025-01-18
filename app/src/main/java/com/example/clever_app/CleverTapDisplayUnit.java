package com.example.clever_app;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.displayunits.CTDisplayUnitType;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This model class holds the data of an individual Display Unit.
 */
public class CleverTapDisplayUnit implements Parcelable {
    private final String unitID;              // Display unit identifier
    private CTDisplayUnitType type;     // Display Type
    private final String bgColor;             // Background Color
    private final ArrayList<CleverTapDisplayUnitContent> contents;    // Item Content
    private HashMap<String, String> customExtras;       // Custom Key-Value pair

    protected CleverTapDisplayUnit(Parcel in) {
        unitID = in.readString();
        bgColor = in.readString();
        contents = in.createTypedArrayList(CleverTapDisplayUnitContent.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(unitID);
        dest.writeString(bgColor);
        dest.writeTypedList(contents);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CleverTapDisplayUnit> CREATOR = new Creator<CleverTapDisplayUnit>() {
        @Override
        public CleverTapDisplayUnit createFromParcel(Parcel in) {
            return new CleverTapDisplayUnit(in);
        }

        @Override
        public CleverTapDisplayUnit[] newArray(int size) {
            return new CleverTapDisplayUnit[size];
        }
    };

    // GETTER-SETTER
    public String getUnitID() {
        return unitID;
    }
    public HashMap<String, String> getCustomExtras() {
        return customExtras;
    }
    public String getBgColor() {
        return bgColor;
    }
    public CTDisplayUnitType getType() {
        return type;
    }
    public ArrayList<CleverTapDisplayUnitContent> getContents() {
        return contents;
    }
}