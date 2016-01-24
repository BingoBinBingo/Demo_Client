package com.bingo.wechat2.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.bingo.wechat2.ui.fragment.BallFriendFragment;
import com.bingo.wechat2.ui.fragment.BallKillFragment;
import com.bingo.wechat2.ui.fragment.BallMeFragment;
import com.bingo.wechat2.ui.fragment.BallRequirementlFragment;
import com.example.bingo.demo_client.R;
import com.example.bingo.demo_client.activity.FlightPreferenceActivity;

/**
 * Created by Bingo on 15/12/11.
 */
public class MainActivity extends FragmentActivity implements View.OnTouchListener {
    public static final String TAG = MainActivity.class.getCanonicalName();
    public static final String TITLE = "wechat2-MainActivity";
    public static final String ACTION_NAME = "com.bingo.wechat2.mainActivity_action";

    /**
     * 滚动显示和隐藏menu时，手指滑动需要达到的速度。
     */
    public static final int SNAP_VELOCITY = 200;

    /**
     * 屏幕宽度值。
     */
    private int screenWidth;

    /**
     * menu最多可以滑动到的左边缘。值由menu布局的宽度来定，marginLeft到达此值之后，不能再减少。
     */
    private int leftEdge;

    /**
     * menu最多可以滑动到的右边缘。值恒为0，即marginLeft到达0之后，不能增加。
     */
    private int rightEdge = 0;

    /**
     * menu完全显示时，留给content的宽度值。
     */
    private int menuPadding = 300;

    /**
     * 主内容的布局。
     */
    private View content;

    /**
     * menu的布局。
     */
    private View menu;

    /**
     * menu布局的参数，通过此参数来更改leftMargin的值。
     */
    private LinearLayout.LayoutParams menuParams;

    /**
     * 记录手指按下时的横坐标。
     */
    private float xDown;

    /**
     * 记录手指移动时的横坐标。
     */
    private float xMove;

    /**
     * 记录手机抬起时的横坐标。
     */
    private float xUp;

    /**
     * menu当前是显示还是隐藏。只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。
     */
    private boolean isMenuVisible;

    /**
     * 用于计算手指滑动的速度。
     */
    private VelocityTracker mVelocityTracker;

    /**
     *  管理屏幕的一个类
     */
    private WindowManager mWindowManager;

    /**
     * 四个主要的fragment
     * @param savedInstanceState
     */
    private static final String BALL_KILL_FRAGMENT_TAG        = "ballKillFragment";
    private static final String BALL_REQUIREMENT_FRAGMENT_TAG = "ballRequirementFragment";
    private static final String BALL_FRIEND_FRAGMENT_TAG      = "ballFriendFragment";
    private static final String BALL_ME_FRAGMENT_TAG          = "ballMeFragment";
    private Fragment mBallKillFrag;
    private Fragment mBallRequirmentFrag;
    private Fragment mBallFriendFrag;
    private Fragment mBallMeFrag;
    private FragmentManager mFragmentManager;
    private RadioButton mBallKillRB;
    private RadioButton mBallRequirementRB;
    private RadioButton mBallFriendRB;
    private RadioButton mBallMeRB;
    private String mPrevShowedFragTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wechat2_main_layout);
        initModel();
        initView();
        initData();
        initController();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initModel() {
        WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        screenWidth = window.getDefaultDisplay().getWidth();
        this.mFragmentManager = getSupportFragmentManager();

    }

    private void initView() {
        this.menu = findViewById(R.id.menu_layout);
        this.content = findViewById(R.id.content_layout);

        this.mBallKillRB = (RadioButton)findViewById(R.id.ball_kill_rb);
        this.mBallRequirementRB = (RadioButton)findViewById(R.id.ball_requirement_rb);
        this.mBallFriendRB = (RadioButton)findViewById(R.id.ball_friend_rb);
        this.mBallMeRB = (RadioButton)findViewById(R.id.ball_me_rb);

        this.mBallKillFrag = new BallKillFragment();
        this.mBallRequirmentFrag = new BallRequirementlFragment();
        this.mBallFriendFrag = new BallFriendFragment();
        this.mBallMeFrag = new BallMeFragment();

        this.mBallKillRB.setSelected(true);
        this.mBallKillRB.setFocusable(true);
        addFragment(mBallKillFrag, BALL_KILL_FRAGMENT_TAG);
    }

    private void initData() {
        this.menuParams = (LinearLayout.LayoutParams) menu.getLayoutParams();
        // 将menu的宽度设置为屏幕宽度减去menuPadding
        this.menuParams.width = screenWidth - menuPadding;
        // 左边缘的值赋值为menu宽度的负数
        this.leftEdge = -menuParams.width;
        // menu的leftMargin设置为左边缘的值，这样初始化时menu就变为不可见
        this.menuParams.leftMargin = leftEdge;
        // 将content的宽度设置为屏幕宽度
        this.content.getLayoutParams().width = screenWidth;
    }

    private void initController() {
        this.content.setOnTouchListener(this);
        this.mBallKillRB.setOnClickListener(new RadioBtnOnClickListener());
        this.mBallRequirementRB.setOnClickListener(new RadioBtnOnClickListener());
        this.mBallFriendRB.setOnClickListener(new RadioBtnOnClickListener());
        this.mBallMeRB.setOnClickListener(new RadioBtnOnClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.preference_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_prefs:
                Intent intent = new Intent();
                intent.setClass(this, FlightPreferenceActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.menu_quit:
                finish();
                break;

        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        createVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.xDown = event.getRawX();
                break;

            case MotionEvent.ACTION_MOVE:
                this.xMove = event.getRawX();
                int distance = (int)(this.xMove - this.xDown);

                if(isMenuVisible) {
                    menuParams.leftMargin = distance;
                } else {
                    menuParams.leftMargin = leftEdge + distance;
                }

                if (menuParams.leftMargin < leftEdge) {
                    menuParams.leftMargin = leftEdge;
                } else if (menuParams.leftMargin > rightEdge) {
                    menuParams.leftMargin = rightEdge;
                }

                menu.setLayoutParams(menuParams);
                break;

            case MotionEvent.ACTION_UP:
                // 手指抬起时，进行判断当前手势的意图，从而决定是滚动到menu界面，还是滚动到content界面
                xUp = event.getRawX();
                if(wantToShowMenu()) {
                    if(shouldScrollToMenu()) {
                        scrollToMenu();
                    } else {
                        scrollToContent();
                    }
                } else if(wantToShowContent()) {
                    if(shouldScrollToContent()) {
                        scrollToContent();
                    } else {
                        scrollToMenu();
                    }
                }
                recycleVelocityTracker();
                break;
        }

        return true;
    }


    private boolean wantToShowMenu() {
        return (this.xUp - this.xDown) > 0 && !isMenuVisible;
    }

    private boolean wantToShowContent() {
        return (this.xUp - this.xDown) < 0 && isMenuVisible;
    }

    private boolean shouldScrollToMenu() {
        return xUp - xDown > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
    }

    private boolean shouldScrollToContent() {
        return xDown - xUp + menuPadding > screenWidth / 2 || getScrollVelocity() > SNAP_VELOCITY;
    }

    private void scrollToMenu() {
        new ScrollTask().execute(30);
    }
    private void scrollToContent() {
        new ScrollTask().execute(-30);
    }

    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }

    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... speed) {
            int leftMargin = menuParams.leftMargin;
            // 根据传入的速度来滚动界面，当滚动到达左边界或右边界时，跳出循环。
            while (true) {
                leftMargin = leftMargin + speed[0];
                if (leftMargin > rightEdge) {
                    leftMargin = rightEdge;
                    break;
                }
                if (leftMargin < leftEdge) {
                    leftMargin = leftEdge;
                    break;
                }
                publishProgress(leftMargin);
                // 为了要有滚动效果产生，每次循环使线程睡眠20毫秒，这样肉眼才能够看到滚动动画。
                sleep(20);
            }
            if (speed[0] > 0) {
                isMenuVisible = true;
            } else {
                isMenuVisible = false;
            }
            return leftMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... leftMargin) {
            menuParams.leftMargin = leftMargin[0];
            menu.setLayoutParams(menuParams);
        }

        @Override
        protected void onPostExecute(Integer leftMargin) {
            menuParams.leftMargin = leftMargin;
            menu.setLayoutParams(menuParams);
        }
    }

    /**
     * 使当前线程睡眠指定的毫秒数。
     *
     * @param millis
     *            指定当前线程睡眠多久，以毫秒为单位
     */
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class RadioBtnOnClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            switchFragment(view);
        }
    }

    private void switchFragment(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()) {
            case R.id.ball_kill_rb:
                if(!mPrevShowedFragTag.equals(BALL_KILL_FRAGMENT_TAG)) {
                    hideFragment(this.mPrevShowedFragTag);
                    if (!mBallKillFrag.isAdded()) {
                        addFragment(this.mBallKillFrag, BALL_KILL_FRAGMENT_TAG);
                    } else {
                        showFragment(BALL_KILL_FRAGMENT_TAG);
                    }
                }
                break;
            case R.id.ball_requirement_rb:
                if(!mPrevShowedFragTag.equals(BALL_REQUIREMENT_FRAGMENT_TAG)) {
                    hideFragment(this.mPrevShowedFragTag);
                    if (!mBallRequirmentFrag.isAdded()) {
                        addFragment(this.mBallRequirmentFrag, BALL_REQUIREMENT_FRAGMENT_TAG);
                    } else {
                        showFragment(BALL_REQUIREMENT_FRAGMENT_TAG);
                    }
                }
                break;
            case R.id.ball_friend_rb:
                if(!mPrevShowedFragTag.equals(BALL_FRIEND_FRAGMENT_TAG)) {
                    hideFragment(this.mPrevShowedFragTag);
                    if (!mBallFriendFrag.isAdded()) {
                        addFragment(this.mBallFriendFrag, BALL_FRIEND_FRAGMENT_TAG);
                    } else {
                        showFragment(BALL_FRIEND_FRAGMENT_TAG);
                    }
                }
                break;
            case R.id.ball_me_rb:
                if(!mPrevShowedFragTag.equals(BALL_ME_FRAGMENT_TAG)) {
                    hideFragment(this.mPrevShowedFragTag);
                    if (!mBallMeFrag.isAdded()) {
                        addFragment(this.mBallMeFrag, BALL_ME_FRAGMENT_TAG);
                    } else {
                        showFragment(BALL_ME_FRAGMENT_TAG);
                    }
                }
                break;
        }
    }

    private void addFragment(Fragment fragment, String fragmentTag) {
        this.mPrevShowedFragTag = fragmentTag;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.contentContainer, fragment, fragmentTag);
        transaction.commit();
    }

    private void showFragment(String fragmentTag) {
        this.mPrevShowedFragTag = fragmentTag;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment showedFragment = manager.findFragmentByTag(fragmentTag);
        transaction.show(showedFragment);
        transaction.commit();
    }

    private void hideFragment(String fragmentTag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment showedFragment = manager.findFragmentByTag(fragmentTag);
        transaction.hide(showedFragment);
        transaction.commit();
    }
}
