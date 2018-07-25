package com.teso.net.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * Created by a.belichenko@gmail.com on 6/13/18.
 */

public class WrapContentDraweeView extends SimpleDraweeView {

    private ScaleGestureDetector mScaleDetector;
    private ScaleGestureDetector.OnScaleGestureListener scaleListener;

    private float currentScale;
    private Matrix currentMatrix;
    private float midX;
    private float midY;

    @Nullable private OnZoomChangeListener zoomListener;

    private final ControllerListener listener = new BaseControllerListener<ImageInfo>() {
        @Override
        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            updateViewSize(imageInfo);
        }

        @Override
        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
            updateViewSize(imageInfo);
        }
    };

    public WrapContentDraweeView(Context context) {
        super(context);
        initView();
    }

    public WrapContentDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public WrapContentDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        scaleListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                float scaleFactor = detector.getScaleFactor();
                float newScale = currentScale * scaleFactor;

                if (newScale > 1.0f) {
                    if (midX == 0.0f) {
                        midX = getWidth() / 2.0f;
                    }
                    if (midY == 0.0f) {
                        midY = getHeight() / 2.0f;
                    }
                    currentScale = newScale;
                    currentMatrix.postScale(scaleFactor, scaleFactor, midX, midY);
                    invalidate();
                } else {
                    scaleFactor = 1.0f / currentScale;
                    reset();
                }

                if (zoomListener != null && scaleFactor != 1.0f) {
                    zoomListener.onZoomChange(currentScale);
                }
                return true;
            }
        };
        mScaleDetector = new ScaleGestureDetector(getContext(), scaleListener);
        currentMatrix = new Matrix();
    }

    public void setOnZoomChangeListener(OnZoomChangeListener listener) {
        zoomListener = listener;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setOnZoomChangeListener(null);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        int saveCount = canvas.save();
        canvas.concat(currentMatrix);
        super.onDraw(canvas);
        canvas.restoreToCount(saveCount);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mScaleDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }

    public void resetZoom() {
        reset();
    }

    private void reset() {
        currentMatrix.reset();
        currentScale = 1.0f;
        invalidate();
    }

    @Override
    public void setImageURI(Uri uri, Object callerContext) {
        DraweeController controller = ((PipelineDraweeControllerBuilder) getControllerBuilder())
                .setControllerListener(listener)
                .setCallerContext(callerContext)
                .setUri(uri)
                .setOldController(getController())
                .build();
        setController(controller);
    }

    void updateViewSize(@Nullable ImageInfo imageInfo) {
        if (imageInfo != null) {
            setAspectRatio((float) imageInfo.getWidth() / imageInfo.getHeight());
            Point displaySize = new Point();
            getRootView().getDisplay().getSize(displaySize);
            if (displaySize.x > 0) getLayoutParams().width = displaySize.x;
            requestLayout();
            invalidate();
        }
    }

    public interface OnZoomChangeListener {
        void onZoomChange(float scale);
    }
}
