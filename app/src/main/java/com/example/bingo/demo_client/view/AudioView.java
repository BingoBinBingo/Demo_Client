package com.example.bingo.demo_client.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.application.DemoApplication;
import com.example.bingo.demo_client.utils.BitmapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bingo on 15/12/15.
 */
public class AudioView extends RelativeLayout {
    private static final String TAG = AudioView.class.getCanonicalName();
    private static final String KEY_BOARD_ITEM = "keyboardItem";
    private ImageView photoImgView;
    private TextView nameTextView;
    private ImageView typeImgView;
//    private CallingStatusView timeView;
    private GridView mAudioCallKeyBoadView;
    private List<Map<String, String>> keyboardItemList = new ArrayList<>();
    public AudioView(Context context) {
        super(context);
    }
    private Context mContex;
    private EditText mNumberEdit;
    private TextView mHangUpTv;
    private AudioCallClickListener mClickListener;
//    private CallPresenter mCallPresenter;
    private LinearLayout mAudioCallDialLayout;
    private LinearLayout mAudioCallKeyBoardLayout;
    private boolean isKeyBoardShow;
    private ImageView mAudioCalllDial;
    private TextView mAudioCalllDialTv;
    private LinearLayout mAudioCallMoreLayout;
    private LinearLayout mAudioCallsecondLayout;
    private boolean isSecondLayoutShow;
    private ImageView mAudioCallHorLine1;
    private ImageView mAudioCallHorLine2;
    private ImageView mAudioCallMoreOptionImg;


    public AudioView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public AudioView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContex = context;
        initModel();
        initView(attrs, context);
        initData();
        initControl();
        Log.v(TAG, "--audioView2");
    }
    private void initModel() {
        this.mClickListener = new AudioCallClickListener();
//        this.mCallPresenter = CallPresenter.getInstance();
        this.isKeyBoardShow = false;
        this.isSecondLayoutShow = false;
    }
    private void initView(AttributeSet attributeSet, Context context) {
        inflate(context, R.layout.audio_call_layout, this);
        this.photoImgView = (ImageView)findViewById(R.id.audio_call_photo);
        this.nameTextView = (TextView)findViewById(R.id.audio_call_name);
        this.typeImgView = (ImageView)findViewById(R.id.audio_call_type);
//        this.timeView = (CallingStatusView)findViewById(R.id.audio_call_time);
        this.mAudioCallKeyBoadView = (GridView)findViewById(R.id.audio_call_digit_keyboard);
        this.mNumberEdit = (EditText)findViewById(R.id.audio_call_number_edit);
        this.mHangUpTv = (TextView)findViewById(R.id.audio_call_hang_up);
        this.mAudioCallDialLayout = (LinearLayout)findViewById(R.id.audio_call_dial_layout);
        this.mAudioCallKeyBoardLayout = (LinearLayout)findViewById(R.id.audio_call_operation_left_layout);
        this.mAudioCalllDial = (ImageView)findViewById(R.id.audio_call_dial);
        this.mAudioCalllDialTv = (TextView)findViewById(R.id.audio_call_dial_tv);
        this.mAudioCallMoreLayout = (LinearLayout)findViewById(R.id.audio_call_more_option_layout);
        this.mAudioCallsecondLayout = (LinearLayout)findViewById(R.id.audio_call_operation_center_second_layout);
        this.mAudioCallHorLine1 = (ImageView)findViewById(R.id.audio_call_horizontal_line_1);
        this.mAudioCallHorLine2 = (ImageView)findViewById(R.id.audio_call_horizontal_line_2);
        this.mAudioCallMoreOptionImg = (ImageView)findViewById(R.id.audio_call_more_option);
    }

    private void initData() {
        this.nameTextView.setText("keBo");
        this.nameTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 56F);
        this.nameTextView.setAlpha(0.8F);
//        this.nameTextView.setTextColor(0xC4C3C3);

//        this.timeView.setTextSize(TypedValue.COMPLEX_UNIT_PX, 42F);
        this.photoImgView.setImageBitmap(BitmapUtils.toRoundBitmap(
                BitmapFactory.decodeResource(DemoApplication.getContext().getResources(),
                        R.drawable.man_photo)));
        this.typeImgView.setImageResource(R.drawable.audio_calling_type_1);

        String[] keyBoardDigititems = mContex.getResources().getStringArray(R.array.audio_call_key_board_items);
        for(String str : keyBoardDigititems) {
            Map<String, String> map = new HashMap<>();
            map.put(KEY_BOARD_ITEM, str);
            keyboardItemList.add(map);
        }

        SimpleAdapter keyBoardAdapter = new SimpleAdapter(mContex,
                keyboardItemList, R.layout.audio_call_digit_item,
                new String[]{KEY_BOARD_ITEM}, new int[]{R.id.audio_call_digit_tv});
        mAudioCallKeyBoadView.setAdapter(keyBoardAdapter);
    }
    private void initControl() {
        this.mAudioCallKeyBoadView.setOnItemClickListener(new GridViewItemClickListener());
        this.mHangUpTv.setOnClickListener(mClickListener);
        this.mAudioCallDialLayout.setOnClickListener(mClickListener);
        this.mAudioCallMoreLayout.setOnClickListener(mClickListener);
    }

//    public void setCallerName(CallerInfo info) {
//        String name = info.displayName;
//        if(name == null || name.equals("")) {
//            name = getCallerName(info.number);
//        }
//        this.nameTextView.setText(name);
//    }
    public void setCallerPhoto(Bitmap photo) {
        if(photo == null) {
            this.photoImgView.setImageBitmap(BitmapUtils.toRoundBitmap(
                    BitmapFactory.decodeResource(DemoApplication.getContext().getResources(),
                            R.drawable.man_photo)));
            return;
        }
        this.photoImgView.setImageBitmap(BitmapUtils.toRoundBitmap(photo));
    }
    public void setCallerType(Bitmap callerType) {
        this.typeImgView.setImageBitmap(BitmapUtils.toRoundBitmap(callerType));
    }

//    public CallingStatusView getTimeView() {
//        return timeView;
//    }

//    private String getCallerName(String number) {
//        for(ContactItem item : ContactManager.getManager().getContacts()) {
//            if(item.getNumber().equals(number)) {
//                return item.getDisplayName();
//            }
//        }
//        return number;
//    }

    private class GridViewItemClickListener implements OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Map<String, String> map = keyboardItemList.get(position);
            mNumberEdit.append(map.get(KEY_BOARD_ITEM));
        }
    }

    private class AudioCallClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.audio_call_hang_up:
//                    mCallPresenter.hangUpCall();
                    break;
                case R.id.audio_call_dial_layout:
                    isKeyBoardShow = !isKeyBoardShow;
                    setKeyBoardStatus(isKeyBoardShow);
                    break;
                case R.id.audio_call_more_option_layout:
                    isSecondLayoutShow = !isSecondLayoutShow;
                    setSecondLayoutStatus(isSecondLayoutShow);
                    break;

            }
        }
    }

    private void setKeyBoardStatus(boolean isKeyBoardShow) {
        if(isKeyBoardShow) {
            this.mAudioCallKeyBoardLayout.setVisibility(View.VISIBLE);
            this.mAudioCalllDial.setImageDrawable(getResources().getDrawable(R.drawable.audio_call_dial_show));
            this.mAudioCalllDialTv.setText(getResources().getString(R.string.audio_call_hide_dial_str));

        } else {
            mAudioCallKeyBoardLayout.setVisibility(View.INVISIBLE);
            this.mAudioCalllDial.setImageDrawable(getResources().getDrawable(R.drawable.audio_call_dial_hide));
            this.mAudioCalllDialTv.setText(getResources().getString(R.string.audio_call_dial_str));
        }
    }

    private void setSecondLayoutStatus(boolean isSecondLayoutShow) {
        if(isSecondLayoutShow) {
            this.mAudioCallsecondLayout.setVisibility(View.VISIBLE);
            this.mAudioCallHorLine1.setVisibility(View.VISIBLE);
            this.mAudioCallMoreOptionImg.setImageDrawable(getResources().getDrawable(R.drawable.audio_call_more_option_down));
        } else {
            this.mAudioCallsecondLayout.setVisibility(View.GONE);
            this.mAudioCallHorLine1.setVisibility(View.GONE);
            this.mAudioCallMoreOptionImg.setImageDrawable(getResources().getDrawable(R.drawable.audio_call_more_option_up));
        }
    }
}
