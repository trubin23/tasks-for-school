package ru.trubin23.tasksforschool.colorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Andrey on 19.04.2018.
 */

public class SwatchView extends SquareView implements ColorObserver {

    private Paint mCheckerPaint;
    private Paint mBorderPaint;
    private Paint mOldFillPaint;
    private Paint mNewFillPaint;

    private Path mBorderPath;
    private Path mOldFillPath;
    private Path mNewFillPath;

    private final float mRadialMarginPx = 0;

    public SwatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mCheckerPaint = Resources.makeCheckerPaint(context);
        mBorderPaint = Resources.makeLinePaint(context);
        mOldFillPaint = new Paint();
        mNewFillPaint = new Paint();

        mBorderPath = new Path();
        mOldFillPath = new Path();
        mNewFillPath = new Path();
    }

    @Override
    public void updateColor(ObservableColor observableColor) {
        mNewFillPaint.setColor(observableColor.getColor());
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mBorderPath, mCheckerPaint);
        canvas.drawPath(mOldFillPath, mOldFillPaint);
        canvas.drawPath(mNewFillPath, mNewFillPaint);
        canvas.drawPath(mBorderPath, mBorderPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        final float inset = mBorderPaint.getStrokeWidth() / 2;
        final float r = Math.min(w, h);

        final float margin = mRadialMarginPx;
        final float diagonal = r + 2 * margin;
        final float opp = (float)Math.sqrt(diagonal * diagonal - r * r);
        final float edgeLen = r - opp;

        final float outerAngle = (float)Math.toDegrees(Math.atan2(opp, r));
        final float startAngle = 270 - outerAngle;
        final float sweepAngle = outerAngle - 45;
        final float cornerSweepAngle = 90 - outerAngle;

        beginBorder(mBorderPath, inset, edgeLen, margin, cornerSweepAngle);
        mainArc(mBorderPath, r, margin, startAngle, 2 * sweepAngle);
        endBorder(mBorderPath, inset, edgeLen, margin, cornerSweepAngle);

        mOldFillPath.reset();
        mOldFillPath.moveTo(inset, inset);
        mainArc(mOldFillPath, r, margin, 225, sweepAngle);
        endBorder(mOldFillPath, inset, edgeLen, margin, cornerSweepAngle);

        beginBorder(mNewFillPath, inset, edgeLen, margin, cornerSweepAngle);
        mainArc(mNewFillPath, r, margin, startAngle, sweepAngle);
        mNewFillPath.lineTo(inset, inset);
        mNewFillPath.close();
    }

    private static void beginBorder(Path path, float inset, float edgeLen, float cornerRadius, float cornerSweepAngle) {
        path.reset();
        path.moveTo(inset, inset);
        cornerArc(path, edgeLen, inset, cornerRadius-inset, 0, cornerSweepAngle);
    }

    private static void endBorder(Path path, float inset, float edgeLen, float cornerRadius, float cornerSweepAngle) {
        cornerArc(path, inset, edgeLen, cornerRadius - inset, 90 - cornerSweepAngle, cornerSweepAngle);
        path.lineTo(inset, inset);
        path.close();
    }

    private static void cornerArc(Path path, float cx, float cy, float r, float startAngle, float sweepAngle) {
        final RectF ovalRect = new RectF(-r, -r, r, r);
        ovalRect.offset(cx, cy);
        path.arcTo(ovalRect, startAngle, sweepAngle);
    }

    private static void mainArc(Path path, float viewSize, float margin, float startAngle, float sweepAngle) {
        float r = viewSize + margin;
        final RectF ovalRect = new RectF(-r, -r, r, r);
        ovalRect.offset(viewSize, viewSize);
        path.arcTo(ovalRect, startAngle, sweepAngle);
    }
}
