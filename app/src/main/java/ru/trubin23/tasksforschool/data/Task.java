package ru.trubin23.tasksforschool.data;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Andrey on 28.03.2018.
 */

public class Task implements Parcelable {

    private static final DateFormat sDateFormat =
            new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    private final String mTitle;

    private final String mDescription;

    private final String mColor;

    private final String mDateOfCreation;

    public Task(@NonNull String title, @NonNull String description,
                @NonNull String color, @NonNull String dateOfCreation) {
        mTitle = title;
        mDescription = description;
        mColor = color;
        mDateOfCreation = dateOfCreation;
    }

    protected Task(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mColor = in.readString();
        mDateOfCreation = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public String getDateOfCreation() {
        return mDateOfCreation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mDateOfCreation);
    }
}
