package com.example.bingo.demo_client.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.bingo.demo_client.R;

/**
 * Created by Bingo on 15/12/20.
 */
public class AnimationActivity extends FragmentActivity {
    public static final String TAG = AnimationActivity.class.getCanonicalName();
    public static final String TITLE = "AnimationActivity";
    public static final String ACTION_NAME = "com.bingo.demo.animationActivity_action";
    private Button mAlphaButton;
    private Button mScaleButton;
    private Button mRolateButton;
    private Button mTransButton;
    private Button mAllAnimaButton;
    private TextView mAnimatedTv;
    private Animation mAnimation;
    private AnimationButtonClickListener mClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_activity_layout);

        initModel();

        initView();

        initController();
    }
    private void initModel() {
        mClickListener = new AnimationButtonClickListener();

    }
    private void initView() {
        mAlphaButton = (Button)findViewById(R.id.alphaButton);
        mScaleButton = (Button)findViewById(R.id.scaleButton);
        mRolateButton = (Button)findViewById(R.id.rotateButton);
        mTransButton = (Button)findViewById(R.id.transButton);
        mAllAnimaButton = (Button)findViewById(R.id.allAnimaButton);
        mAnimatedTv = (TextView)findViewById(R.id.animatedTv);
    }

    private void initController() {
        mAlphaButton.setOnClickListener(mClickListener);
        mScaleButton.setOnClickListener(mClickListener);
        mRolateButton.setOnClickListener(mClickListener);
        mTransButton.setOnClickListener(mClickListener);
        mAllAnimaButton.setOnClickListener(mClickListener);
    }

    class AnimationButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.alphaButton:
                    mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alph_aniamtion);
                    mAnimatedTv.startAnimation(mAnimation);
                    break;
                case R.id.scaleButton:
                    mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_animation);
                    mAnimatedTv.startAnimation(mAnimation);
                    break;
                case R.id.rotateButton:
                    mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_aniamtion);
                    mAnimatedTv.startAnimation(mAnimation);
                    break;
                case R.id.transButton:
                    mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_animation);
                    mAnimatedTv.startAnimation(mAnimation);
                    break;
                case R.id.allAnimaButton:
                    mAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.all_animation);
                    mAnimatedTv.startAnimation(mAnimation);
                    break;
            }
        }
    }
}
