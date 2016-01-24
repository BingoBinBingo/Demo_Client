package com.example.bingo.demo_client.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.application.DemoApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bingo on 15/12/26.
 */
public class RingtoneUtils {
    private static final String TAG = RingtoneUtils.class.getCanonicalName();
    private static Context mContext = DemoApplication.getContext();

    public static Ringtone getDefaultRingtone(int type) {
        return RingtoneManager.getRingtone(mContext, RingtoneManager.getActualDefaultRingtoneUri(mContext, type));
    }

    public static Uri getDefaultRingtoneUri(int type) {
        return RingtoneManager.getActualDefaultRingtoneUri(mContext, type);
    }


    public static List<Ringtone> getRingtoneList(int type) {
        List<Ringtone> resArr = new ArrayList<Ringtone>();
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);

        Cursor cursor = manager.getCursor();
        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            resArr.add(manager.getRingtone(i));
        }
        return resArr;
    }

    public static Ringtone getRingtone(int type, int pos) {
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);
        return manager.getRingtone(pos);
    }

    public static List<String> getRingtoneTitleList(int type) {
        List<String> resArr = new ArrayList<String>();
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);

        Cursor cursor = manager.getCursor();
        if (cursor.moveToFirst()) {
            do {
                resArr.add(cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX));
            } while (cursor.moveToNext());
        }
        return resArr;
    }

    public static List<String> getRingtoneUriList(int type) {
        List<String> resArr = new ArrayList<String>();
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);

        Cursor cursor = manager.getCursor();
        if (cursor.moveToFirst()) {
            do {
                resArr.add(cursor.getString(RingtoneManager.URI_COLUMN_INDEX));
                Log.v(TAG, "---bBin--getRingtoneUriList===uir=" + cursor.getString(RingtoneManager.URI_COLUMN_INDEX));
            } while (cursor.moveToNext());
        }
        return resArr;
    }

    public static String getRingtoneUriPath(int type, int pos, String def) {
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);
        Uri uri = manager.getRingtoneUri(pos);
        return uri == null ? def : uri.toString();
    }


    public static Ringtone getRingtoneByUriPath(int type, String uriPath) {
        RingtoneManager manager = new RingtoneManager(mContext);
        manager.setType(type);
        Uri uri = Uri.parse(uriPath);
        return manager.getRingtone(mContext, uri);
    }

    public static List<Uri> scannerMediaFile() {
        List<Uri> datas = new ArrayList<>();
        ContentResolver cr = mContext.getContentResolver();
        Cursor cursor = cr.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, new String[]{MediaStore.Audio.Media._ID, MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.TITLE}, "is_music = ?", new String[]{"1"}, "_id asc");
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            datas.add(Uri.parse(cursor.getString(1)));
        }
        return  datas;
    }

    /**
     * 用来将还没 插入到媒体库的音乐 设置为来电铃声
     * 设置--铃声的具体方法
     * @param path
     */
    public static void setMyRingtone(String path) {
        File sdfile = new File(path);
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, sdfile.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, sdfile.getName());
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/*");
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
        values.put(MediaStore.Audio.Media.IS_ALARM, false);
        values.put(MediaStore.Audio.Media.IS_MUSIC, false);

        Uri uri = MediaStore.Audio.Media.getContentUriForPath(sdfile.getAbsolutePath());
        Uri newUri = mContext.getContentResolver().insert(uri, values);
        RingtoneManager.setActualDefaultRingtoneUri(mContext, RingtoneManager.TYPE_RINGTONE, newUri);
        Toast.makeText(mContext, mContext.getString(R.string.set_incoming_ringgtone_is_success), Toast.LENGTH_SHORT).show();
    }

    /**
     * 用来将已经再媒体库中的音乐，设置来电铃声
     * @param path2
     * @param id
     */
    public static void setRingtoneFromMedia(String path2, int ringtoneType) {
        String msg = "";
        boolean is_ringtone = false;
        boolean is_notification = false;
        boolean is_alarm = false;
        boolean is_all = false;
        ContentValues cv = new ContentValues();
        Uri newUri = null;
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(path2);

        // 查询音乐文件在媒体库是否存在
        Cursor cursor = mContext.getContentResolver().query(uri, null, MediaStore.MediaColumns.DATA + "=?", new String[]{path2}, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            String _id = cursor.getString(0);
            switch (ringtoneType) {
                case RingtoneManager.TYPE_RINGTONE:
                    is_ringtone = true;
                    msg = "设置来电铃声成功！";
                    break;
                case RingtoneManager.TYPE_NOTIFICATION:
                    is_notification = true;
                    msg = "设置通知铃声成功！";
                    break;
                case RingtoneManager.TYPE_ALARM:
                    is_alarm = true;
                    msg = "设置闹钟铃声成功！";
                    break;
                case RingtoneManager.TYPE_ALL:
                    is_alarm = true;
                    msg = "设置全部铃声成功！";
                    break;
                default:
                    break;
            }
            cv.put(MediaStore.Audio.Media.IS_RINGTONE, is_ringtone);
            cv.put(MediaStore.Audio.Media.IS_NOTIFICATION, is_notification);
            cv.put(MediaStore.Audio.Media.IS_ALARM, is_alarm);
            cv.put(MediaStore.Audio.Media.IS_MUSIC, is_all);

            // 把需要设为铃声的歌曲更新铃声库
            mContext.getContentResolver().update(uri, cv, MediaStore.MediaColumns.DATA + "=?", new String[]{path2});
            newUri = ContentUris.withAppendedId(uri, Long.valueOf(_id));
            RingtoneManager.setActualDefaultRingtoneUri(mContext, ringtoneType, newUri);
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
//            播放铃声
            Ringtone rt = RingtoneManager.getRingtone(mContext, newUri);
            rt.play();
        }
    }
}
