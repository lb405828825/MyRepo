package com.fineos.transferintentparameter.data;

import android.os.Parcel;
import android.os.Parcelable;

//import java.io.Serializable;

/**
 * Created by liubo on 15-12-30.
 */
public class intentDataObject implements Parcelable{

    private String name;
    private int age;

    public intentDataObject(String name, int age){
        this.name = name;
        this.age = age;
    }

    public intentDataObject(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<intentDataObject> CREATOR = new Creator<intentDataObject>() {
        @Override
        public intentDataObject createFromParcel(Parcel in) {
            return new intentDataObject(in);
        }

        @Override
        public intentDataObject[] newArray(int size) {
            return new intentDataObject[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
