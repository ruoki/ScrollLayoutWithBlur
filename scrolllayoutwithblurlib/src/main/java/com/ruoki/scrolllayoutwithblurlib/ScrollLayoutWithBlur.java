package com.ruoki.scrolllayoutwithblurlib;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.ruoki.scrolllayoutwithblurlib.utils.BlurBitmapUtil;
import com.ruoki.scrolllayoutwithblurlib.utils.ScreenUtil;


/**
 * Created by ruoqi.zhou on 2017/10/31 0031.
 * 模仿华为杂志锁屏view从下滑进，往下滑出；
 * 在滑动的过程中增加实时模糊效果。
 * 调用该view时注意：
 * 1、内部view为请使用或继承content包下的ContentListView/ContentScrollView或ContentRecyclerView控件；
 * 必须调用的有（按顺序）：
 * 1、setRootView():需要截屏的view
 * 2、setPreBlurView():需要模糊背景的view
 * 3、init():初始化控件
 * 可配置的：
 * 1、setScrollChangedListener():设置上下滑动监听
 *
 * 在以上方法中，init()必须最后调用！
 */

public class ScrollLayoutWithBlur extends ScrollLayout {

    private static final String TAG = "ScrollLayoutWithBlur";
    private View rootView;
    private View toBlurView;
    private ScrollLayout.OnScrollChangedListener mOnScrollChangedListener = new ScrollLayout.OnScrollChangedListener(){
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            if (currentProgress > -1) {
                float precent = 255;
                if(currentProgress>= 0) {
                    precent = 255 * currentProgress;
                    if (precent > 255) {
                        precent = 255;
                    } else if (precent < 0) {
                        precent = 0;
                    }
                }
                BlurBitmapUtil.blur(rootView, toBlurView, 14, 8);
                if(getBackground()!= null) {
                    getBackground().setAlpha(255 - (int) precent);
                }
            }
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus.equals(ScrollLayout.Status.EXIT)) {
            }
        }

        @Override
        public void onChildScroll(int top) {
        }
    };

    public ScrollLayoutWithBlur(Context context) {
        super(context);
        initSetting(context);
    }

    public ScrollLayoutWithBlur(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting(context);
    }

    public ScrollLayoutWithBlur(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initSetting(context);
    }

    private void initSetting(Context context) {
        /**设置 setting*/
//        offset = (int) (ScreenUtil.getScreenHeight(this) * 0.5);
//        setMinOffset(offset);
//        setMaxOffset(offset);
        int offset = (int) (ScreenUtil.getScreenHeight((Activity)context) * 0.5);
        setMinOffset(0);
        setMaxOffset(offset);
        setExitOffset(0);
        setIsSupportExit(true);
        setAllowHorizontalScroll(true);
    }

    /**
     * 用于截屏的view
     * @param rootView
     */
    public void setRootView(View rootView){
        this.rootView = rootView;
    }


    /**
     * 用于模糊背景的view
     * @param toBlurView
     */
    public void setPreBlurView(View toBlurView){
        this.toBlurView = toBlurView;
    }

    /**
     * 初始化
     */
    public void init(){
        setOnScrollChangedListener(mOnScrollChangedListener);
        setToExit();
        getBackground().setAlpha(0);
    }

    /**
     * 隐藏当前view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void hideScrollLayout() {
            scrollToExit();
            setBackground(new ColorDrawable(Color.parseColor("#000000")));
            getBackground().setAlpha(0);
    }

    /**
     * 显示当前view
     */
    public void showScrollLayout() {
//            setToOpenWithAnimation();
            scrollToClose();//最大化显示scrolllayout，close的是被scrollLayout遮住的布局
    }

    public class OnScrollChangedListener implements ScrollLayout.OnScrollChangedListener{
        @Override
        public void onScrollProgressChanged(float currentProgress) {
            if (currentProgress > -1) {
                float precent = 255;
                if(currentProgress>= 0) {
                    precent = 255 * currentProgress;
                    if (precent > 255) {
                        precent = 255;
                    } else if (precent < 0) {
                        precent = 0;
                    }
                }
                BlurBitmapUtil.blur(rootView, toBlurView, 14, 8);
                if(getBackground()!= null) {
                    getBackground().setAlpha(255 - (int) precent);
                }
            }
        }

        @Override
        public void onScrollFinished(ScrollLayout.Status currentStatus) {
            if (currentStatus.equals(ScrollLayout.Status.EXIT)) {
            }
        }

        @Override
        public void onChildScroll(int top) {
        }
    }
}
