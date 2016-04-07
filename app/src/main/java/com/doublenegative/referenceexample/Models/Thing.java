package com.doublenegative.referenceexample.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thomasclowes on 29/01/16.
 */
public class Thing implements Parcelable {

    public String key;


    public Thing() {

        Long timestamp = System.currentTimeMillis()/1000;
        this.key = timestamp.toString();
    }


    //region Parcelable implementation
    public static final Parcelable.Creator<Thing> CREATOR = new Parcelable.Creator<Thing>() {

        public Thing createFromParcel(Parcel in) {

            return new Thing(in);
        }

        public Thing[] newArray(int size) {

            return new Thing[size];
        }
    };


    @Override
    public int describeContents() {

        return 0;
    }


    @Override
    //We use writeValue and then cast on read because for example when loading a draft
    //lots of properties are not set
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(key);
    }


    private Thing(Parcel in) {

        key = in.readString();
    }
    //endregion
}
