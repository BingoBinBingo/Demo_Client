package com.example.bingo.demo_client.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;

import java.io.ByteArrayOutputStream;

public class BitmapUtils {

    private static final int color = 0xff424242;

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return null;
    }

    public static Bitmap getBitmapThumbnail(String path, int width, int height) {
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);
        opts.inSampleSize = Math.max((int) (((float) opts.outHeight) / ((float) height)), (int) (((float) opts.outWidth) / ((float) width)));
        opts.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, opts);
    }

    public static Bitmap getBitmapThumbnail(Bitmap bmp, int width, int height) {
        if (bmp == null) {
            return null;
        }
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        if (width == 0 || height == 0) {
            return bmp;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(((float) width) / ((float) bmpWidth), ((float) height) / ((float) bmpHeight));
        return Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
    }

    public static Bitmap getBitmapThumbnail(Bitmap bmp, float scale) {
        if (bmp == null) {
            return null;
        }
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, w, h);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx, int width, int height) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        if (w < width) {
            width = w;
        }
        if (h < height) {
            height = h;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, w, h);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    private static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        if (height <= reqHeight && width <= reqWidth) {
            return 1;
        }
        int heightRatio = Math.round(((float) height) / ((float) reqHeight));
        int widthRatio = Math.round(((float) width) / ((float) reqWidth));
        if (heightRatio < widthRatio) {
            return widthRatio;
        }
        return heightRatio;
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
        if (bitmap == null) {
            return null;
        }
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix mtx = new Matrix();
        mtx.postRotate((float) rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

//    private static int readPictureDegree(String path) {
//        try {
//            switch (new ExifInterface(path).getAttributeInt("Orientation", 1)) {
//                case SettingHomeFragment.ACCOUNT_INDEX /*3*/:
//                    return ScreenUtils.ORIENTATION_180;
//                case CMCCSettingHomeFragment.ABOUT_INDEX /*6*/:
//                    return 90;
//                case PBXService.MSG_ANSWER_CALL /*8*/:
//                    return ScreenUtils.ORIENTATION_270;
//                default:
//                    return 0;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        float roundPx;
        float top;
        float bottom;
        float left;
        float right;
        float dst_left;
        float dst_top;
        float dst_right;
        float dst_bottom;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            roundPx = (float) (width / 2);
            top = 0.0f;
            bottom = (float) width;
            left = 0.0f;
            right = (float) width;
            height = width;
            dst_left = 0.0f;
            dst_top = 0.0f;
            dst_right = (float) width;
            dst_bottom = (float) width;
        } else {
            roundPx = (float) (height / 2);
            float clip = (float) ((width - height) / 2);
            left = clip;
            right = ((float) width) - clip;
            top = 0.0f;
            bottom = (float) height;
            width = height;
            dst_left = 0.0f;
            dst_top = 0.0f;
            dst_right = (float) height;
            dst_bottom = (float) height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect((int) left, (int) top, (int) right, (int) bottom);
        Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, dst, paint);
        return output;
    }

    public static Bitmap toBlackWhiteBitmap(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[(width * height)];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[(width * i) + j];
                grey = (int) (((((double) ((16711680 & grey) >> 16)) * 0.3d) + (((double) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & grey) >> 8)) * 0.59d)) + (((double) (grey & MotionEventCompat.ACTION_MASK)) * 0.11d));
                pixels[(width * i) + j] = grey | (((grey << 16) | ViewCompat.MEASURED_STATE_MASK) | (grey << 8));
            }
        }
        Bitmap newBmp = Bitmap.createBitmap(width, height, Config.RGB_565);
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBmp;
    }

}
