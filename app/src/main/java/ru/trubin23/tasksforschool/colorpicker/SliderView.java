package ru.trubin23.tasksforschool.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Andrey on 19.04.2018.
 */

public class SliderView extends View implements ColorObserver {

    private ObservableColor mObservableColor = new ObservableColor(0);

    private final Rect mViewRect = new Rect();

    private Path mBorderPath;
    private Paint mBorderPaint;
    private Path mPointerPath;
    private Paint mPointerPaint;
    private Paint mCheckerPaint;
    private int mWidth;
    private int mHeight;
    private Bitmap mBitmap;

    private float currentPos;

    public SliderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPointerPath = Resources.makePointerPath(context);
        mCheckerPaint = Resources.makeCheckerPaint(context);
        mPointerPaint = Resources.makeLinePaint(context);
        mBorderPaint = Resources.makeLinePaint(context);
        mBorderPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        mViewRect.set(0, 0, w, h);
        float inset = mBorderPaint.getStrokeWidth() / 2;
        mBorderPath.reset();
        mBorderPath.addRect(new RectF(inset, inset, w - inset, h - inset), Path.Direction.CW);
        updateBitmap();
    }

    protected void updateBitmap() {
        if (mWidth > 0 && mHeight > 0) {
            mBitmap = makeBitmap(mWidth, mHeight);
            optimisePointerColor();
        }
    }

    protected Bitmap makeBitmap(int w, int h) {
        final boolean isWide = w > h;
        final int n = Math.max(w, h);
        int[] colors = new int[n];

        float[] hsv = new float[]{0, 0, 0};
        mObservableColor.getHsv(hsv);

        for (int i = 0; i < n; ++i) {
            hsv[2] = isWide ? (float)i / n : 1 - (float)i / n;
            colors[i] = Color.HSVToColor(hsv);
        }
        final int bmpWidth = isWide ? w : 1;
        final int bmpHeight = isWide ? 1 : h;
        return Bitmap.createBitmap(colors, bmpWidth, bmpHeight, Bitmap.Config.ARGB_8888);
    }

    private void optimisePointerColor() {
        mPointerPaint.setColor(getPointerColor(currentPos));
    }

    protected int getPointerColor(float currentPos) {
        float brightColorLightness = mObservableColor.getLightness();
        float posLightness = currentPos * brightColorLightness;
        return posLightness > 0.5f ? 0xff000000 : 0xffffffff;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mBorderPath, mCheckerPaint);
        canvas.drawBitmap(mBitmap, null, mViewRect, null);
        canvas.drawPath(mBorderPath, mBorderPaint);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        if (isWide()) {
            canvas.translate(mWidth * currentPos, mHeight / 2);
        }
        else {
            canvas.translate(mWidth / 2, mHeight * (1 - currentPos));
        }
        canvas.drawPath(mPointerPath, mPointerPaint);
        canvas.restore();
    }

    private boolean isWide() {
        return mWidth > mHeight;
    }

    @Override
    public void updateColor(ObservableColor observableColor) {
        currentPos = mObservableColor.getValue();
        optimisePointerColor();
        updateBitmap();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                currentPos = valueForTouchPos(event.getX(), event.getY());
                optimisePointerColor();
                mObservableColor.updateValue(currentPos, this);
                invalidate();
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
        }
        return super.onTouchEvent(event);
    }

    private float valueForTouchPos(float x, float y) {
        final float val = isWide() ? x / mWidth : 1 - y / mHeight;
        return Math.max(0, Math.min(1, val));
    }

    public void observeColor(ObservableColor observableColor) {
        mObservableColor = observableColor;
        observableColor.addObserver(this);
    }

    public void notifyListener(float currentPos) {
        mObservableColor.updateValue(currentPos, this);
    }
}
