package ru.trubin23.tasksforschool.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Andrey on 28.03.2018.
 */

public class Task implements Parcelable {

    private static final int MIN_COLOR = 63;
    private static final int RANGE_COLOR = 128;

    private static final DateFormat sDateFormat =
            new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    private final int mId;

    private String mTitle;

    private String mDescription;

    private final String mColor;

    private final String mDateOfCreation;

    public Task(@NonNull String title, @NonNull String description) {
        Random random = new Random();

        mId = random.nextInt();
        mTitle = title;
        mDescription = description;
        mColor = String.format("#%02x%02x%02x",
                random.nextInt(RANGE_COLOR) + MIN_COLOR,
                random.nextInt(RANGE_COLOR) + MIN_COLOR,
                random.nextInt(RANGE_COLOR) + MIN_COLOR);
        mDateOfCreation = sDateFormat.format(new Date());
    }

    private Task(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mDescription = in.readString();
        mColor = in.readString();
        mDateOfCreation = in.readString();
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getDateOfCreation() {
        return mDateOfCreation;
    }

    public String getColor() {
        return mColor;
    }

    public void setTitle(@NonNull String title) {
        mTitle = title;
    }

    public void setDescription(@NonNull String description) {
        mDescription = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeString(mColor);
        dest.writeString(mDateOfCreation);
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

    public static boolean isValidTitle(@NonNull String title) {
        return title != null && !title.trim().isEmpty();
    }
}
