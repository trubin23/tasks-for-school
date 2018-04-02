package ru.trubin23.tasksforschool.data;

import android.graphics.Color;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Andrey on 28.03.2018.
 */

public class Task {

    private static final DateFormat sDateFormat =
            new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

    private final String mTitle;

    private final String mDescription;

    private final Color mColor;

    private final String mDateOfCreation;

    public Task(@NonNull String title, @NonNull String description,
                @NonNull Color color, @NonNull String dateOfCreation) {
        mTitle = title;
        mDescription = description;
        mColor = color;
        mDateOfCreation = dateOfCreation;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDateOfCreation() {
        return mDateOfCreation;
    }
}
