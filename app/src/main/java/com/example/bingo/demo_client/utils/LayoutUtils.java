package com.example.bingo.demo_client.utils;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;


import java.lang.reflect.Method;

public class LayoutUtils {
    public static final int MAX_LAYOUT_TYPE = 13;
    public static final int MIN_LAYOUT_TYPE = 1;
    public static final int POSITION_FULL = 1;
    public static final int POSITION_LEFT = 2;
    public static final int POSITION_LEFT_BOTTOM = 5;
    public static final int POSITION_LEFT_TOP = 4;
    public static final int POSITION_RIGHT = 3;
    public static final int POSITION_RIGHT_BOTTOM = 7;
    public static final int POSITION_RIGHT_TOP = 6;
    private static final int POSITION_DEFAULT = 11;

    public static final int ONLY_REMOTE = 1;
    public static final int ONLY_LOCAL = 2;
    public static final int ONLY_LOCAL_SLIDES = 3;
    public static final int ONLY_REMOTE_SLIDES = 4;
    public static final int REMOTE_WITH_SMALL_LOCAL = 5;
    public static final int REMOTE_SLIDES_WITH_SMALL_LOCAL_SLIDES = 6;
    public static final int LOCAL_SLIDES_WITH_SMALL_REMOTE_SLIDES = 7;
    public static final int REMOTE_WITH_LOCAL = 8;
    public static final int REMOTE_WITH_REMOTE_SLIDES = 9;
    public static final int LOCAL_SLIDES_WITH_REMOTE_SLIDES = 10;
    public static final int FOUR_VIEWS = 11;

    private static final int NO_REMOTE_SLIDES = 0;
    private static final int NO_LOCAL_SLIDES = 0;
    private static final int NO_SLIDES = 0;

    private static Point localVideoSize  = new Point(720,480);
    private static Point videoSize = new Point(720,480);
    private static Point screenSize = new Point(720, 480);

    public static boolean isValidLayoutType(int layoutType) {
        return !(layoutType < 0 || layoutType > MAX_LAYOUT_TYPE);
    }

    public static boolean setLayout(int layoutType,
                                    ViewGroup mainSurfaceView,
                                    ViewGroup localMainTextureView,
                                    ViewGroup slidesSurfaceView,
                                    ViewGroup localSlidesTextureView) {
        return isValidLayoutType(layoutType)
                && resizeLayout(layoutType,
                                mainSurfaceView,
                                localMainTextureView,
                                slidesSurfaceView,
                                localSlidesTextureView);
    }


    private static boolean resizeLayout(int layoutType,
                                        ViewGroup mainSurfaceView,
                                        ViewGroup localMainTextureView,
                                        ViewGroup slidesSurfaceView,
                                        ViewGroup localSlidesTextureView) {
        return resizeLayout(
                    layoutType,
                    localVideoSize,
                    videoSize,
                    mainSurfaceView,
                    localMainTextureView,
                    slidesSurfaceView,
                    localSlidesTextureView);
    }

    private static boolean resizeLayout(int layoutType,
                                        Point localVideoSize, Point videoSize,
                                        ViewGroup mainSurfaceView,
                                        ViewGroup localMainTextureView,
                                        ViewGroup slidesSurfaceView,
                                        ViewGroup localSlidesTextureView) {
        boolean flag = true;
        try {
            if (!isValidLayoutType(layoutType)) {
                layoutType = 5;
            }

            if (videoSize !=null && localVideoSize != null) {
                if (videoSize.x < 1 || videoSize.y < 1) {
                    flag = false;
                    videoSize.set(localVideoSize.x, localVideoSize.y);
                }
            }
            float screenRatio = (((float) screenSize.x) * 1f) / ((float) screenSize.y);
            float videoRatio = (((float) videoSize.x) * 1f) / ((float) videoSize.y);
            float localVideoRatio = (((float) localVideoSize.x) * 1f) / ((float) localVideoSize.y);
            int width;
            float f;
            int height;
            switch (layoutType) {
                case ONLY_REMOTE:
                    setViewLayout(mainSurfaceView, screenSize.x, screenSize.y, POSITION_FULL);
                    setViewLayout(slidesSurfaceView, 1, 1, POSITION_RIGHT_TOP);
                    setViewLayout(localMainTextureView, 1, 1, POSITION_LEFT_BOTTOM);
                    setViewLayout(localSlidesTextureView, 1, 1, POSITION_RIGHT_BOTTOM);
                    return flag;
                case ONLY_LOCAL:
                    setViewLayout(localMainTextureView, screenSize.x, screenSize.y, POSITION_FULL);
                    setViewLayout(mainSurfaceView, 1, 1, POSITION_LEFT_TOP);
                    setViewLayout(slidesSurfaceView, 1, 1, POSITION_RIGHT_TOP);
                    setViewLayout(localSlidesTextureView, 1, 1, POSITION_RIGHT_BOTTOM);
                    return flag;
                case ONLY_LOCAL_SLIDES /*3*/:
                    setViewLayout(localSlidesTextureView, screenSize.x, screenSize.y, POSITION_FULL);
                    setViewLayout(mainSurfaceView, 1, 1, POSITION_LEFT_TOP);
                    setViewLayout(slidesSurfaceView, 1, 1, POSITION_RIGHT_TOP);
                    setViewLayout(localMainTextureView, 1, 1, POSITION_LEFT_BOTTOM);
                    return flag;
                case ONLY_REMOTE_SLIDES /*4*/:
                    setViewLayout(slidesSurfaceView, screenSize.x, screenSize.y, POSITION_FULL);
                    setViewLayout(mainSurfaceView, 1, 1, POSITION_LEFT_TOP);
                    setViewLayout(localMainTextureView, 1, 1, POSITION_LEFT_BOTTOM);
                    setViewLayout(localSlidesTextureView, 1, 1, POSITION_RIGHT_BOTTOM);
                    return flag;
                case REMOTE_SLIDES_WITH_SMALL_LOCAL_SLIDES /*6*/:
                    setViewLayout(slidesSurfaceView, screenSize.x, screenSize.y, POSITION_FULL);
                    width = screenSize.x / 4;
                    setViewLayout(localSlidesTextureView, width, (int) (((float) width) / localVideoRatio), POSITION_RIGHT_BOTTOM);
                    setViewLayout(mainSurfaceView, 1, 1, POSITION_LEFT_TOP);
                    setViewLayout(localMainTextureView, 1, 1, POSITION_LEFT_BOTTOM);
                    return flag;
                case LOCAL_SLIDES_WITH_SMALL_REMOTE_SLIDES /*7*/:
                    setViewLayout(localSlidesTextureView, screenSize.x, screenSize.y, POSITION_FULL);
                    width = screenSize.x / 4;
                    setViewLayout(slidesSurfaceView, width, (int) (((float) width) / videoRatio), POSITION_RIGHT_BOTTOM);
                    setViewLayout(mainSurfaceView, 1, 1, POSITION_LEFT_TOP);
                    setViewLayout(localMainTextureView, 1, 1, POSITION_LEFT_BOTTOM);
                    slidesSurfaceView.bringToFront();
                    return flag;
                case REMOTE_WITH_LOCAL :
                    width = screenSize.x / 2;
                    f = (float) width;
                    if (videoRatio <= localVideoRatio) {
                        videoRatio = localVideoRatio;
                    }
                    height = (int) (f / videoRatio);
                    setViewLayout(mainSurfaceView, width, height, POSITION_LEFT);
                    setViewLayout(localMainTextureView, width, height, POSITION_RIGHT);
                    setViewLayout(slidesSurfaceView, 1, 1, POSITION_RIGHT_TOP);
                    setViewLayout(localSlidesTextureView, 1, 1, POSITION_RIGHT_BOTTOM);
                    return flag;
                case REMOTE_WITH_REMOTE_SLIDES :
                    width = screenSize.x / 2;
                    height = (int) (((float) width) / videoRatio);
                    setViewLayout(mainSurfaceView, width, height, POSITION_LEFT);
                    setViewLayout(slidesSurfaceView, width, height, POSITION_RIGHT);
                    setViewLayout(localMainTextureView, 1, 1, POSITION_LEFT_BOTTOM);
                    setViewLayout(localSlidesTextureView, 1, 1, POSITION_RIGHT_BOTTOM);
                    return flag;
                case LOCAL_SLIDES_WITH_REMOTE_SLIDES :
                    width = screenSize.x / 2;
                    f = (float) width;
                    if (videoRatio <= localVideoRatio) {
                        videoRatio = localVideoRatio;
                    }
                    height = (int) (f / videoRatio);
                    setViewLayout(localSlidesTextureView, width, height, POSITION_LEFT);
                    setViewLayout(slidesSurfaceView, width, height, POSITION_RIGHT);
                    setViewLayout(mainSurfaceView, 1, 1, POSITION_LEFT_TOP);
                    setViewLayout(localMainTextureView, 100, 100, POSITION_LEFT_BOTTOM);
                    return flag;
                case FOUR_VIEWS:
                    width = screenSize.x / 2;
                    height = screenSize.y / 2;
                    setViewLayout(slidesSurfaceView, width, height, POSITION_LEFT_TOP);
                    setViewLayout(localSlidesTextureView, width, height, POSITION_RIGHT_TOP);
                    setViewLayout(mainSurfaceView, width, height, POSITION_LEFT_BOTTOM);
                    setViewLayout(localMainTextureView, width, height, POSITION_RIGHT_BOTTOM);
                    return flag;
                default:
                    setViewLayout(mainSurfaceView, screenSize.x, screenSize.y, POSITION_FULL);
                    width = screenSize.x / 4;
                    setViewLayout(localMainTextureView, width, (int) (((float) width) / localVideoRatio), POSITION_RIGHT_BOTTOM);
                    setViewLayout(slidesSurfaceView, 1, 1, POSITION_RIGHT_TOP);
                    setViewLayout(localSlidesTextureView, 1, 1, POSITION_RIGHT_BOTTOM);
                    return flag;
            }
        } catch (Throwable t) {
            return false;
        }
    }

    public static int resetLayout(ViewGroup mainSurfaceView, ViewGroup localMainTextureView, ViewGroup slidesSurfaceView, ViewGroup localSlidesTextureView) {
        mainSurfaceView.setVisibility(View.GONE);
        localMainTextureView.setVisibility(View.GONE);
        slidesSurfaceView.setVisibility(View.GONE);
        localSlidesTextureView.setVisibility(View.GONE);
        setViewLayout(mainSurfaceView, 1, 1, POSITION_LEFT_TOP);
        setViewLayout(slidesSurfaceView, 1, 1, POSITION_RIGHT_TOP);
        setViewLayout(localSlidesTextureView, 1, 1, POSITION_RIGHT_BOTTOM);
        setViewLayout(localMainTextureView, screenSize.x, screenSize.y, POSITION_FULL);
        return ONLY_LOCAL;
    }

    public static void setViewLayout(View view, int width, int height, int position) {
        LayoutParams sendParams = new LayoutParams(width, height);
        switch (position) {
            case POSITION_FULL /*1*/:
                sendParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                sendParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                break;
            case POSITION_LEFT /*2*/:
                sendParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                break;
            case POSITION_RIGHT /*3*/:
                sendParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                break;
            case POSITION_LEFT_TOP /*4*/:
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                break;
            case POSITION_LEFT_BOTTOM /*5*/:
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                break;
            case POSITION_RIGHT_TOP /*6*/:
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                break;
            case POSITION_RIGHT_BOTTOM /*7*/:
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                sendParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                break;
        }
        view.setLayoutParams(sendParams);
        view.invalidate();
        view.setVisibility(View.VISIBLE);
    }


    public static Point getRealSize(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        int realWidth;
        int realHeight;

        if (Build.VERSION.SDK_INT >= 17){
            //new pleasant way to get real metrics
            DisplayMetrics realMetrics = new DisplayMetrics();
            display.getRealMetrics(realMetrics);
            realWidth = realMetrics.widthPixels;
            realHeight = realMetrics.heightPixels;

        } else if (Build.VERSION.SDK_INT >= 14) {
            //reflection for this weird in-between time
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } catch (Exception e) {
                //this may not be 100% accurate, but it's all we've got
                realWidth = display.getWidth();
                realHeight = display.getHeight();
                Log.e("Display Info", "Couldn't use reflection to get the real display metrics.");
            }

        } else {
            //This should be close, as lower API devices should not have window navigation bars
            realWidth = display.getWidth();
            realHeight = display.getHeight();
        }

        return new Point(realWidth, realHeight);
    }

    public static Point getScreenSize() {
        return screenSize;
    }
}
