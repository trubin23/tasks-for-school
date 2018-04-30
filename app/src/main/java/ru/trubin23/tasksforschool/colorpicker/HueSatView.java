package ru.trubin23.tasksforschool.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

/**
 * Created by Andrey on 19.04.2018.
 */

public class HueSatView extends SquareView implements ColorObserver {

    private final Paint mBorderPaint;
    private final Paint mPointerPaint;

    private final Path mPointerPath;
    private final Path mBorderPath;

    private final Rect mViewRect = new Rect();
    private int mWidth;
    private int mHeight;

    private static Bitmap sBitmap;

    private final PointF mPointer = new PointF();
    private ObservableColor mObservableColor = new ObservableColor(0);

    public HueSatView(Context context) {
        this(context, null);
    }

    public HueSatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBorderPaint = Resources.makeLinePaint(context);
        mPointerPaint = Resources.makeLinePaint(context);
        mPointerPaint.setColor(0xff000000);
        mPointerPath = Resources.makePointerPath(context);
        mBorderPath = new Path();
        if (sBitmap == null) {
            sBitmap = makeBitmap(optimalBitmapSize());
        }
    }

    @Override
    public void updateColor(ObservableColor observableColor) {
        setPointer(mPointer, observableColor.getHue(), observableColor.getSat(), mWidth);
        optimisePointerColor();
        invalidate();
    }

    private static void setPointer(PointF pointer, float hue, float sat, float radiusPx) {
        final float r = radiusPx - 1;
        final double distance = r * Math.sqrt(sat);
        final double angle = hue / 360 * Math.PI / 2;
        final double dx = distance * Math.cos(angle);
        final double dy = distance * Math.sin(angle);
        pointer.set(r - (float) dx, r - (float) dy);
    }

    private void optimisePointerColor() {
        mPointerPaint.setColor(
                mObservableColor.getLightnessWithValue(1) > 0.5
                        ? 0xff000000 : 0xffffffff);
    }

    private int optimalBitmapSize() {
        final int scale = 2;
        final int maxBitmapSize = 128;
        final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        return Math.min(
                maxBitmapSize,
                Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) / scale);
    }

    private static Bitmap makeBitmap(int radiusPx) {
        int[] colors = new int[radiusPx * radiusPx];
        float[] hsv = new float[]{0f, 0f, 1f};
        for (int y = 0; y < radiusPx; ++y) {
            for (int x = 0; x < radiusPx; ++x) {
                final int i = x + y * radiusPx;
                final float sat = satForPos(x, y, radiusPx);
                final float arcBleed = 2f / radiusPx; // extend curved edge pixels just outside clip area.
                if (sat <= 1 + arcBleed) {
                    hsv[0] = hueForPos(x, y, radiusPx);
                    hsv[1] = sat;
                    colors[i] = Color.HSVToColor(0xff, hsv);
                }
            }
        }
        return Bitmap.createBitmap(colors, radiusPx, radiusPx, Bitmap.Config.ARGB_8888);
    }

    private static float satForPos(float x, float y, float radiusPx) {
        final double r = radiusPx - 1;
        final double dx = (r - x) / r;
        final double dy = (r - y) / r;
        final double sat = dx * dx + dy * dy;
        return (float) sat;
    }

    private static float hueForPos(float x, float y, float radiusPx) {
        final double r = radiusPx - 1;
        final double dx = (r - x) / r;
        final double dy = (r - y) / r;
        final double angle = Math.atan2(dy, dx);
        final double hue = 360 * angle / (Math.PI / 2);
        return (float) hue;
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save(Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG);

        canvas.clipPath(mBorderPath);
        canvas.drawBitmap(sBitmap, null, mViewRect, null);
        canvas.translate(mPointer.x, mPointer.y);
        canvas.drawPath(mPointerPath, mPointerPaint);

        canvas.restore();

        canvas.drawPath(mBorderPath, mBorderPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                boolean withinPicker = clamp(mPointer, event.getX(), event.getY(), true);
                if (withinPicker) update();
                return withinPicker;
            case MotionEvent.ACTION_MOVE:
                clamp(mPointer, event.getX(), event.getY(), false);
                update();
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void update() {
        mObservableColor.updateHueSat(
                hueForPos(mPointer.x, mPointer.y, mWidth),
                satForPos(mPointer.x, mPointer.y, mWidth),
                this);
        optimisePointerColor();
        invalidate();
    }

    private boolean clamp(PointF pointer, float x, float y, boolean rejectOutside) {
        x = Math.min(x, mWidth);
        y = Math.min(y, mHeight);
        final float dx = mWidth - x;
        final float dy = mHeight - y;
        final float r = (float) Math.sqrt(dx * dx + dy * dy);
        boolean outside = r > mWidth;
        if (!outside || !rejectOutside) {
            if (outside) {
                x = mWidth - dx * mWidth / r;
                y = mWidth - dy * mWidth / r;
            }
            pointer.set(x, y);
        }
        return !outside;
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        mWidth = width;
        mHeight = height;
        mViewRect.set(0, 0, width, height);

        float inset = mBorderPaint.getStrokeWidth() / 2;
        makeBorderPath(mBorderPath, width, height, inset);

        updateColor(mObservableColor);
    }

    private static void makeBorderPath(Path borderPath, int width, int height, float inset) {
        width -= inset;
        height -= inset;
        borderPath.reset();
        borderPath.moveTo(width, inset);
        borderPath.lineTo(width, height);
        borderPath.lineTo(inset, height);
        borderPath.addArc(new RectF(inset, inset, 2 * width, 2 * height), 180, 270);
        borderPath.close();
    }

    void observeColor(ObservableColor observableColor) {
        mObservableColor = observableColor;
        observableColor.addObserver(this);
    }
}
